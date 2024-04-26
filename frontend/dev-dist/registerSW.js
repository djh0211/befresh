// if('serviceWorker' in navigator) navigator.serviceWorker.register('/dev-sw.js?dev-sw', { scope: '/', type: 'classic' })

if ('serviceWorker' in navigator) {
    let registrationOptions = {};

    // "/jenkins" 경로에 대해서는 Service Worker를 등록하지 않습니다.
    if (!window.location.pathname.startsWith('/jenkins')) {
        registrationOptions = { scope: '/', type: 'classic' };
    }

    navigator.serviceWorker.register('/dev-sw.js?dev-sw', registrationOptions);
}