
## 🥦 Be Fresh : 냉장고의 음식 단속

**스마트 용기를 통한 식품 신선도 관리 시스템**

- 👪 기관: 삼성 청년 SW 아카데미     
- 📆 기간: 2024.04.08 ~ 2024.05.20


### **💡 목표**
>
> 소비자들이 식품의 신선도를 정확히 알고, 보다 효율적으로 음식 관리를 할 수 있게 함으로써 음식물 쓰레기를 줄여 경제적 이득 및 환경 보호에 기여합니다. 
>

### 목차
|<center>No</center>|<center>내용</center>|
|:----:|:----:|
|**1**|[**💡 프로젝트 개요**](#1)
|**2**|[**🥨 주요 기능**](#2)
|**3**|[**🍗 기대 효과**](#3)
|**4**|[**🔍 기술 스택**](#4)
|**5**|[**💾 DataBase**](#5)
|**6**|[**📂 시스템 아키텍처**](#6) 
|**7**|[**📱 기술 소개**](#7)
|**8**|[**👪 팀 소개**](#8)
|**9**|[**🗂️ Directory 구조**](#9)

<div id="1"></div>

### **💡 프로젝트 개요**


> 유통 과정에서의 불법 행위나 소비자의 잘못된 보관 방법 등으로 인해 식품의 신선도가 유통기한과 다를 수 있습니다. 이러한 문제를 해결하고자, 저희는 스마트 용기를 통해 식품의 실시간 데이터를 모니터링하여 **소비자에게 정확한 신선도 정보를 제공**하고자 합니다.
>

<div id="2"></div>

### **🥨 주요 기능**


- **STT를 통한 음식 등록:** 사용자는 냉장고에 부착된 마이크를 통해 음식 이름을 말해서 등록할 수 있습니다.
- **OCR 기능:** 포장지에 인쇄된 유통 기한을 인식하여 자동으로 등록합니다.
- **일반적인 유통기한 제공:** 등록된 음식에 대한 일반적인 유통기한 정보를 제공합니다.
- **실시간 데이터 모니터링:** 냉장고 내의 스마트 용기에 부착된 센서를 통해 식품의 기체 배출량, 온도 등을 수집 및 분석합니다.
- **통계 분석을 통한 식품 신선도 측정**: R 통계 분석 기법을 활용하여 검출된 기체의 농도 데이터를 기반으로 신선도를 분류합니다.
- **식품 신선도 및 부패 감지 알림:** 특정 기체(암모니아 등)의 농도를 분석하여 신선도 저하 및 부패 시작을 감지하고 소비자에게 알림을 전송하고, 정보를 제공합니다.
- **음식 상태 모니터링 서비스:** 소비자가 자신의 음식 상태를 모니터링 할 수 있게 하며, 신선도, 유통 기한 등의 정보를 제공합니다.

**[ 시연용 결과물 ]**

<img alt="메인화면" src="./readme_assets/real.png" height=400>

**[ 어플 메인 화면 ]**

<img alt="메인화면" src="./readme_assets/main.jpg" height=400>

**[ 어플 용기 정보 ]**

<img alt="메인화면" src="./readme_assets/container.jpg" height=400>

**[ 어플 알림 화면 ]**

<img alt="메인화면" src="./readme_assets/alarm.jpg" height=400>

<div id="3"></div>

### **🍗 기대 효과**

- **음식의 신선도 확인 간소화:** 스마트폰이나 냉장고에 부착된 태블릿을 통해 간편하게 신선도 확인이 가능합니다.
- **경제적 이득과 환경 보호:** 식품의 정확한 신선도 파악으로 불필요한 음식물 쓰레기를 줄이고, 결과적으로 경제적 이득 및 환경 보호에 기여합니다.
- **사용자 경험 향상:** 식품의 신선도 관리를 자동화함으로써 사용자의 편의성과 만족도를 크게 향상시킵니다.

<div id="4"></div>

### **🔍 기술 스택**
**FrontEnd**

![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB) ![Redux](https://img.shields.io/badge/redux-%23593d88.svg?style=for-the-badge&logo=redux&logoColor=white) 
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E) ![TypeScript](https://img.shields.io/badge/TypeScript-007ACC?style=for-the-badge&logo=typescript&logoColor=white) ![Vite](https://img.shields.io/badge/vite-%23646CFF.svg?style=for-the-badge&logo=vite&logoColor=white) ![MUI](https://img.shields.io/badge/MUI-%230081CB.svg?style=for-the-badge&logo=mui&logoColor=white) ![PWA](https://img.shields.io/badge/PWA-5A0FC8.svg?style=for-the-badge&logo=pwa&logoColor=white)

**BackEnd**

![Spring](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot) ![SpringSecurity](https://img.shields.io/badge/Spring%20Security-6DB33F.svg?style=for-the-badge&logo=Spring-Security&logoColor=white) ![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens) ![ElasticSearch](https://img.shields.io/badge/-ElasticSearch-005571?style=for-the-badge&logo=elasticsearch) ![Apache Kafka](https://img.shields.io/badge/Apache%20Kafka-000?style=for-the-badge&logo=apachekafka) ![Oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black) ![InfluxDB](https://img.shields.io/badge/InfluxDB-22ADF6?style=for-the-badge&logo=InfluxDB&logoColor=white) 

**IOT**

![Raspberry](https://img.shields.io/badge/Raspberry%20Pi-A22846?style=for-the-badge&logo=Raspberry%20Pi&logoColor=white) ![Arduino](https://img.shields.io/badge/Arduino-00979D?style=for-the-badge&logo=Arduino&logoColor=white) ![OpenCV](https://img.shields.io/badge/OpenCV-27338e?style=for-the-badge&logo=OpenCV&logoColor=white) ![Bluetooth](https://img.shields.io/badge/Bluetooth-0082FC?style=for-the-badge&logo=Bluetooth&logoColor=white)

**Data**

![python](https://img.shields.io/badge/Python-FFD43B?style=for-the-badge&logo=python&logoColor=blue) ![scikit_learn](https://img.shields.io/badge/scikit_learn-F7931E?style=for-the-badge&logo=scikit-learn&logoColor=white) ![numpy](https://img.shields.io/badge/Numpy-777BB4?style=for-the-badge&logo=numpy&logoColor=white)

**Infra**

![AWS](https://img.shields.io/badge/AWS-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white) ![AWS-EC2](https://img.shields.io/badge/AWS_EC2-%23FF9900.svg?style=for-the-badge&logo=amazonec2&logoColor=white) ![Jenkins](https://img.shields.io/badge/jenkins-%232C5263.svg?style=for-the-badge&logo=jenkins&logoColor=white) ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white) ![Nginx](https://img.shields.io/badge/nginx-%23009639.svg?style=for-the-badge&logo=nginx&logoColor=white) ![Sonarqube](https://img.shields.io/badge/Sonarqube-5190cf?style=for-the-badge&logo=sonarqube&logoColor=white)

**Collaboration Tool**

![GitLab](https://img.shields.io/badge/gitlab-%23181717.svg?style=for-the-badge&logo=gitlab&logoColor=white) ![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white) ![Jira](https://img.shields.io/badge/jira-%230A0FFF.svg?style=for-the-badge&logo=jira&logoColor=white) ![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white) ![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white) 


<div id="5"></div>

### 💾 DataBase

**[oracle DB]**

<img alt="erd" src="./readme_assets/erd.PNG">

**[influx DB]**

<img alt="influxDB" src="./readme_assets/influxDB.png">

<div id="6"></div>

### 📂 시스템 아키텍처

<img alt="architecture" src="./readme_assets/architecture.png">

<div id="7"></div>

### 📱 기술 소개

**[ IOT - Asyncio / Multiprocessiong ]**

> 냉장고 모듈에서 처리하는 프로세스가 동기적으로 진행된다면 대기시간이 길어지기 때문에 Asyncio, Multiprocessing을 사용합니다.

<img alt="iot" src="./readme_assets/iot.png">

- **Asyncio**: IO 블로킹시 다른 CPU 작업 수행, IO 작업 이점
- **Multiprocessiong**: 별도의 프로세스를 사용하여 멀티 코어의 경우 CPU 작업 이점

**[ IOT - Bluetooth Job 스케쥴링 ]**

> 하나의 냉장고가 여러 개의 스마트 용기가 순차적으로 블루투스 통신을 해야하기 때문에 Bluetooth Job 스케쥴링을 적용했습니다.

<img alt="iot" src="./readme_assets/iot2.png">

- 10대 이상으로 증가할 경우 자동으로 로직 변경    
=> 최악의 경우에도 n분 주기로 용기당 60초 배정

**[ Kafka ]**
>
> 1. 냉장고에서 등록할 음식 목록을 Publish 하면, 구독 중인 Spring 서버가 메시지를 받아 음식을 등록합니다.
> 2. 냉장고에서 센서 데이터를 Publish하면, 구독 중인 파이썬 서버가 수신하여 신선도를 계산합니다.
>

- RestAPI: 트래픽 증가시 타임아웃 및 메시지 유실 가능성이 존재

- Kafka: 수신자가 본인의 처리 속도에 맞게 메시지를 수신 가능

**[ Elastic Search ]**

> STT로 음식을 등록하는 과정에서, 음식 이름의 오차를 줄이고 음식에 대한 유통기한을 반환하기 위해 Elastic Search 검색 엔진을 구현하였습니다.

<img alt="elastic" src="./readme_assets/elastic.png">

**[ Spring - Virtual Thread ]**

> 스프링 서버에서 병렬 처리를 위해 음식 등록 과정을 비동기처리로 구현하였습니다. 또한, 성능 향상을 위해 생성 비용이 작고, 논블로킹 방식인 Virtual Thread를 적용했습니다.

- 1000개의 요청에 대한 응답시간 및 처리량
    - 플랫폼 스레드 처리량: 10.9/sec

    <img alt="thread1" src="./readme_assets/thread1.png" width="500">

    - 가상 스레드 처리량: 20.2/sec

    <img alt="thread2" src="./readme_assets/thread2.png" width="500">
    => 플랫폼 스레드에 비해 더 높은 처리량과 빠른 응답시간

**[ Spring Batch ]**

> 유통기한 및 센서 데이터를 기반으로 상태를 주기적으로 업데이트 하고 알림을 전송하기 위해 Spring Batch를 도입하였습니다.

- 데이터 처리 안전성 보장
- 데이터 일관성 유지 가능
- 모니터링 및 관리 가능

**[ Python - 신선도 예측 ]**

> 용기의 센서를 통해 온도, 습도, 가스 센서 데이터를 수집합니다. 가스 센서를 통해 훈련시킨 Random Forest Model로 pH 센서를 예측하여 신선도를 분류합니다.

- pH가 5.9이하는 신선, 5.9 ~ 6.2는 주의, 6.2 이상은 부패로 분류합니다.

- **Random Forest Model**
    > 센서로 얻은 데이터를 통해 PH 값을 예측합니다.

    - train을 위한 데이터 수집

    <img alt="sensor" src="./readme_assets/sensor.png" width="300">

    - 선정 이유
        - NH3, 가스 등 다양한 변수들 간의 복잡한 상호작용을 고려할 수 있습니다.
        - 시간에 따른 데이터 변화를 처리할 수 있습니다.
        - 변수 중요도를 계산할 수 있습니다.

    => Accuracy가 약 92%로, 높은 정확도를 보입니다.

**[PWA + FCM]**

> 음식의 신선도 상태, 등록 확인, 연결의 끊김 등의 알림을 사용자에게 전달하기 위해 PWA와 FCM을 도입하였습니다.

<img alt="pwa" src="./readme_assets/pwa.png" width="300">

- **PWA**: Progressive Web Application의 약자로, 웹의 장점과 앱의 장점을 모두 가짐. 대표적인 기능으로는 설치를 통해 앱처럼 사용 가능, 푸시 알림, 오프라인에서의 동작 등이 있습니다.
- **FCM**: 파이어베이스 기반 웹 푸시 서비스로, 서버에서 클라이언트 앱으로 메세지를 전달하는 기능을 제공합니다.

- PWA를 통해 백그라운드 환경에서도 사용자들에게 푸시 알림을 전송하고, 모바일의 카메라를 활용할 수 있습니다.
- 메시지를 Redux에 저장하여 새로고침 없이 알림을 확인할 수 있습니다.

**[ Atomic Design, Styled Components ]**

> 코드 재사용성과 유지보수성을 높이기 위해 Atomic Design과 Styled Components를 도입하였습니다.

<img alt="design" src="./readme_assets/design.png" width="300">

- 각 컴포넌트에서 쉽게 CSS 확인이 가능합니다.
- CSS의 오염을 최소화합니다.


<div id="8"></div>

### 👪 팀 소개

|![정승환](https://avatars.githubusercontent.com/u/100360525?v=4)|![하동준](https://avatars.githubusercontent.com/u/77885587?v=4)|![남수진](https://avatars.githubusercontent.com/u/77006790?v=4)|![김예지](https://avatars.githubusercontent.com/u/139518026?v=4)|![정유경](https://avatars.githubusercontent.com/u/83561356?v=4)|![김동현](https://avatars.githubusercontent.com/u/139518194?v=4)|
|:----:|:----:|:----:|:----:|:----:|:----:|
|[정승환<br >(팀장)](https://github.com/seunghw2)|[하동준](https://github.com/djh0211)|[남수진](https://github.com/ss0510s)|[김예지](https://github.com/gimezi)|[정유경](https://github.com/YuKyung-Chung)|[김동현](https://github.com/Chico0902/)|


|<center>이름</center>|<center>역할</center>|<center>개발 내용</center>|어려웠던 점과 배운 점|
|:----:|:----:|----|----|
|정승환|BackEnd|||
|남수진|BackEnd|||
|정유경|BackEnd + Infra|||
|하동준|BackEnd + IOT|||
|김예지|FrontEnd + IOT|||
|김동현|FrontEnd|||

<div id="9"></div>

### 🗂️ Directory 구조