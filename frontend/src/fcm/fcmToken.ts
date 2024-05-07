const FCM_TOKEN = 'fcm_token';

export function saveFcmTokens(fcmToken: string) {
  localStorage.setItem(FCM_TOKEN, fcmToken);
}

export function getFcmToken(): string | null {
  return localStorage.getItem(FCM_TOKEN);
}