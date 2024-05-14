type alarmType = {
  data: {
    category :'danger'|'warn'|'register'|'error'|'noUpdate'|'reUpdate',
    notificationId: string
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
    'data' in payload &&
    typeof payload.data === 'object' &&
    'category' in payload.data &&
    (payload.data.category === 'danger'|| 'warn' || 'register'||'error'||'noUpdate'||'reUpdate')&&
    'notificationId' in payload.data &&
    typeof payload.data.notificationId === 'string' &&
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