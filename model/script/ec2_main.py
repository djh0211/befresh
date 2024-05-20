import json
import pandas as pd
import time
import csv
from dotenv import load_dotenv
import os
from pathlib import Path
import asyncio
from multiprocessing import Process, Manager, Pool
import concurrent.futures
import pickle
# kafka
from kafka import KafkaConsumer
# scheduler
from apscheduler.schedulers.asyncio import AsyncIOScheduler
# influxdb
import influxdb_client, os, time
from influxdb_client import InfluxDBClient, Point, WritePrecision
from influxdb_client.client.write_api import SYNCHRONOUS
# oracle
import oracledb


# global variables
df_latest = pd.DataFrame()

#################################################################
# method
def insert_into_influx():
    load_dotenv()  # take environment variables from .env.
    INFLUX_URL = os.getenv('INFLUX_URL')
    INFLUX_ORG = os.getenv('INFLUX_ORG')
    INFLUX_TOKEN = os.getenv('INFLUX_TOKEN')
    INFLUX_BUCKET = os.getenv('INFLUX_BUCKET')

    file_path = Path('../data/latest_data.csv')
    if file_path.exists():
        with InfluxDBClient(url=INFLUX_URL, token=INFLUX_TOKEN, org=INFLUX_ORG) as client:
            for df in pd.read_csv(file_path, dtype={
                'temperature': float,
                'humidity': float,
                'gas': float,
                'nh3': float
            }, chunksize=1_000):
                with client.write_api() as write_api:
                    try:
                        write_api.write(
                            record=df,
                            bucket=INFLUX_BUCKET,
                            data_frame_measurement_name="container",
                            data_frame_tag_columns=["qr_id"],
                            data_frame_timestamp_column="time",
                        )
                    except Exception as e:
                        print(e)

def get_oracle_conn(ORACLE_USER, ORCLE_PASSWORD, ORACLE_DSN):

    return oracledb.connect(user=ORACLE_USER, password=ORCLE_PASSWORD, dsn=ORACLE_DSN)   # DB에 연결 (호스트이름 대신 IP주소 가능)

def get_influx_conn(INFLUX_URL, INFLUX_ORG, INFLUX_TOKEN):

    return InfluxDBClient(url=INFLUX_URL, token=INFLUX_TOKEN, org=INFLUX_ORG)


def model_predict():
    load_dotenv()  # take environment variables from .env.
    ORACLE_USER = os.getenv('ORACLE_USER')
    ORCLE_PASSWORD = os.getenv('ORCLE_PASSWORD')
    ORACLE_DSN = os.getenv('ORACLE_DSN')

    INFLUX_URL = os.getenv('INFLUX_URL')
    INFLUX_ORG = os.getenv('INFLUX_ORG')
    INFLUX_TOKEN = os.getenv('INFLUX_TOKEN')
    INFLUX_BUCKET = os.getenv('INFLUX_BUCKET')


    # load csv
    df = pd.read_csv("../data/latest_data.csv")
    qr_unique_id = df['qr_id'].unique()
    print(qr_unique_id)
    
    # load startDateTime from Oracle
    # with get_oracle_conn(ORACLE_USER, ORCLE_PASSWORD, ORACLE_DSN) as con:
    # con = get_oracle_conn(ORACLE_USER, ORCLE_PASSWORD, ORACLE_DSN)
    status = True
    try:
        # cursor = con.cursor()
        con = oracledb.connect(user=ORACLE_USER, password=ORCLE_PASSWORD, dsn=ORACLE_DSN)
        con.callTimeout = 5000  # 타임아웃을 5000 밀리초(5초)로 설정
        cursor = con.cursor()

        qt_unique_id_str = ', '.join("'{}'".format(qt_id) for qt_id in qr_unique_id)
        sql_query = """
            SELECT f.FOOD_ID, f.REG_DTTM, c.QR_ID, f.NAME
            FROM FOOD f 
            INNER JOIN CONTAINER c ON f.FOOD_ID = c.FOOD_ID 
            WHERE c.qr_id IN ({})
        """.format(qt_unique_id_str)
        cursor.execute(sql_query)
        out_data = cursor.fetchall()
        print(out_data)
    except:
        status = False
        print('failed')
    finally:
        cursor.close()
        con.close()
    if not status:
        return
    status = True
    

    # load testX data from influxDB
    query_results = []
    with get_influx_conn(INFLUX_URL, INFLUX_ORG, INFLUX_TOKEN) as client:
        query_api = client.query_api()
        for d in out_data:
            tables = []
            # ISO 8601 형식의 문자열로 변환하여 UTC 시간으로 표시
            iso_formatted = d[1].strftime('%Y-%m-%dT%H:%M:%S.%fZ')
            print(iso_formatted)
            print("\n")
            print("============== 현재 QR_ID ================> " , d[2])
            query = f"""from(bucket: "{INFLUX_BUCKET}")
                |> range(start: {iso_formatted})
                |> filter(fn: (r) => r["_measurement"] == "container")
                |> filter(fn: (r) => r["qr_id"] == "{d[2]}") 
                |> group(columns: ["qr_id"])
            """
            # print(query)
            tables = query_api.query(query, org=INFLUX_ORG)
            query_results.append((d, tables))

            # predict TODO methodify
            with open('predict_ph', 'rb') as pp:
                mod = pickle.load(pp)
            predicted_values = []
            if tables:  # 테이블에 데이터가 있는 경우에만 예측 수행
                # InfluxDB에서 쿼리한 데이터를 데이터프레임으로 변환
                influx_data = []
                for table in tables:
                    for record in table.records:
                        influx_data.append({
                            'time': record.get_time(),
                            'qr_id': record.get_value(),
                            'temperature': record.get_value(),
                            'humidity': record.get_value(),
                            'gas': record.get_value(),
                            'nh3': record.get_value()
                        })
                influx_df = pd.DataFrame(influx_data)
                # 시간 데이터를 연도, 월, 일, 시간 등으로 분리하여 숫자로 변환
                d_time = pd.to_datetime(d[1])  # d[1]은 REG_DTTM 데이터
                influx_df['time'] = pd.to_datetime(influx_df['time'])  # influx_df의 time 컬럼도 datetime으로 변환
                # 데이터의 첫 번째 시간
                start_time = d_time.tz_localize(influx_df['time'].dt.tz)
                # 상대적인 시간(시간의 경과)을 분 단위로 계산하여 새로운 열로 추가
                influx_df['elapsed_time'] = (influx_df['time'] - start_time).dt.total_seconds() / 60
                # 예측을 위해 필요한 특성만 선택
                X_new = influx_df[['elapsed_time', 'temperature', 'humidity', 'gas', 'nh3']]
                
                # 예측 수행
                predicted_values = mod.predict(X_new)
                # 예측 결과 출력
                print("===> 예측 결과 : ", predicted_values[-1].round(2))
            else:
                print("InfluxDB에서 해당 QR_ID에 대한 데이터가 없습니다.")

    if predicted_values is not None and not all(pd.isna(predicted_values)):  # predicted_values가 null이 아닌 경우에만 실행
        in_data = predicted_values[-1].round(2)
        food_id = d[0]
        qr_id = d[2]

        try:
            con = oracledb.connect(user=ORACLE_USER, password=ORCLE_PASSWORD, dsn=ORACLE_DSN)
            con.callTimeout = 5000  # 타임아웃을 5000 밀리초(5초)로 설정
            cursor = con.cursor()

            # CONTAINER 테이블에 같은 (FOOD_ID, QR_ID) 조합의 데이터가 있는지 확인
            cursor.execute("SELECT COUNT(*) FROM CONTAINER WHERE FOOD_ID = :1 AND QR_ID = :2", (food_id, qr_id))
            count = cursor.fetchone()[0]
            print(count)

            con = oracledb.connect(user=ORACLE_USER, password=ORCLE_PASSWORD, dsn=ORACLE_DSN)
            con.callTimeout = 5000  # 타임아웃을 5000 밀리초(5초)로 설정
            cursor = con.cursor()

            if count == 0:  # 같은 조합의 데이터가 없는 경우에만 삽입
                cursor.execute("INSERT INTO CONTAINER (FOOD_ID, QR_ID, PH) VALUES (:1, :2, :3)", (food_id, qr_id, in_data))
            else:
                # 같은 조합의 데이터가 존재할 경우 pH 값을 업데이트
                print((in_data, food_id, qr_id))
                cursor.execute("UPDATE CONTAINER SET PH = :1 WHERE FOOD_ID = :2 AND QR_ID = :3", (in_data, food_id, qr_id))
            cursor.execute('commit')
                # con.commit()
        except:
            status = False
            print('failed')
        finally:
            cursor.close()
            con.close()



async def start_model_multiprocessing():
    process = Process(target=model_predict)
    process.start()



async def batch_update():
    """
    batch job
    1. latest_df -> file
    2. influx DB insert
    3. update model predict
    4. update oracle db(신선도)
    """
    global df_latest

    load_dotenv()  # take environment variables from .env.
    ORACLE_USER = os.getenv('ORACLE_USER')
    ORCLE_PASSWORD = os.getenv('ORCLE_PASSWORD')
    ORACLE_DSN = os.getenv('ORACLE_DSN')

    INFLUX_URL = os.getenv('INFLUX_URL')
    INFLUX_ORG = os.getenv('INFLUX_ORG')
    INFLUX_TOKEN = os.getenv('INFLUX_TOKEN')
    INFLUX_BUCKET = os.getenv('INFLUX_BUCKET')

    # print('start')
    # print(len(df_latest))
    if len(df_latest)>0:
        # 1. latest_df -> file
        df_latest.to_csv("../data/latest_data.csv", index=False)
        df_latest = pd.DataFrame()
        # 2. influx DB insert
        insert_into_influx()
        """
        3. update model predict
        4. update oracle db(신선도)
        """
        asyncio.create_task(start_model_multiprocessing())



async def listener():
    global df_latest
    load_dotenv()  # take environment variables from .env.
    KAFKA_SERVER = os.getenv('KAFKA_SERVER')

    consumer = KafkaConsumer('sensor-data-topic', 
                        group_id='parser',
                        bootstrap_servers=[KAFKA_SERVER],
                        value_deserializer=lambda v: json.loads(v.decode('utf-8')), 
                        auto_offset_reset='earliest',  # 처음부터 메시지 소비
                        enable_auto_commit=False)
    
    try:
        while True:
            data_list = []
            datas = []
            for _, records in consumer.poll(timeout_ms=1000).items():
                for record in records:
                    try:
                        # 여기에서 record를 처리하는 동작을 한다.
                        # print(record)
                        data = record.value['data']
                        datas.append(data)
                        consumer.commit_async()
                    except Exception as e:
                        print(e)
            for data in datas:
                for qr_id, qr_serial_data in data.items():
                    for d in qr_serial_data:
                        temp = (qr_id, d['time'], d['data']['temperature'], d['data']['humidity'], d['data']['gas'], d['data']['nh3'])
                        data_list.append(temp)
            data_now = pd.DataFrame(data_list, columns=['qr_id', 'time', 'temperature', 'humidity', 'gas', 'nh3'])
            if len(data_now) > 0:
                df_latest = pd.concat([df_latest, data_now])
            # print(len(df_latest))

            await asyncio.sleep(10)
    finally:
        consumer.commit()
        consumer.close()


async def main():
    # schedule job
    scheduler = AsyncIOScheduler()
    scheduler.add_job(batch_update, 'interval', minutes=30)
    scheduler.start()
    # kafka listener
    task = asyncio.create_task(listener())

    await task

if __name__=='__main__':
    asyncio.run(main())