<<<<<<< HEAD
if('serviceWorker' in navigator) navigator.serviceWorker.register('./sw.js', { scope: '/', type: 'classic' })
=======
// if('serviceWorker' in navigator) navigator.serviceWorker.register('/dev-sw.js?dev-sw', { scope: '/', type: 'classic' })

if ('serviceWorker' in navigator) {
    let registrationOptions = {};

    // 현재 URL을 출력합니다.
    console.log('Current URL:', window.location.href);

    // 현재 URL이 /jenkins로 시작하지 않으면서 Service Worker를 등록합니다.
    if (!window.location.pathname.startsWith('/jenkins')) {
        registrationOptions = { scope: '/', type: 'classic' };
        navigator.serviceWorker.register('/dev-sw.js?dev-sw', registrationOptions);
    } else {
        // /jenkins 경로로 요청이 들어오면 Service Worker를 등록하지 않습니다.
        console.log('Service Worker registration skipped for /jenkins');
    }
}
>>>>>>> develop
