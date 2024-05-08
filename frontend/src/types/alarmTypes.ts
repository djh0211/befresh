type alarmType = {
  messageId: string,
  data: {
    category :string
  },
  notification: {
    title: string,
    body: string
  }
}

// 타입 가드 함수
function isAlarmType(payload: any): payload is alarmType {
  return (
    typeof payload === 'object' &&
    payload !== null &&
    'messageId' in payload &&
    typeof payload.messageId === 'string' &&
    'data' in payload &&
    typeof payload.data === 'object' &&
    'category' in payload.data &&
    typeof payload.data.category === 'string' &&
    'notification' in payload &&
    typeof payload.notification === 'object' &&
    'title' in payload.notification &&
    typeof payload.notification.title === 'string' &&
    'body' in payload.notification &&
    typeof payload.notification.body === 'string'
  );
}


export type {alarmType}
export {isAlarmType}