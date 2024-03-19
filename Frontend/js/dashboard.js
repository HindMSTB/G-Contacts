function checkAuthentication() {
    const userIdSession = sessionStorage.getItem("userId");
    const isAdminSession = sessionStorage.getItem("isAdmin");
    if (!userIdSession || !isAdminSession) {
        window.location.href = 'login.html';
    }
}

function logout() {
   
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("isAdmin");
    sessionStorage.removeItem("userEmail");
    
    window.location.href = 'login.html';
}
