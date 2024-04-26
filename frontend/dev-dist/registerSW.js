// if('serviceWorker' in navigator) navigator.serviceWorker.register('/dev-sw.js?dev-sw', { scope: '/', type: 'classic' })

if ('serviceWorker' in navigator) {
    window.addEventListener('load', function() {
        if (window.location.pathname !== '/jenkins') {
            // '/jenkins' 경로에는 서비스 워커를 등록하지 않음
            navigator.serviceWorker.register('/service-worker.js', { scope: '/' })
            .then(function(registration) {
                console.log('ServiceWorker registration successful with scope: ', registration.scope);
            })
            .catch(function(err) {
                console.error('ServiceWorker registration failed: ', err);
            });
        } else {
            console.log('Skipping service worker registration for /jenkins path');
        }
    });
}
