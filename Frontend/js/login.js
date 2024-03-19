document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Empêche le comportement de soumission par défaut du formulaire
    
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    
    let headersList = {
        "Accept": "*/*",
        "Content-Type": "application/json" // Modifier le type de contenu à JSON
    };

    let bodyContent = JSON.stringify({
        email: email,
        password: password
    });

    let response = await fetch("http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/auth", { 
        method: "POST",
        body: bodyContent,
        headers: headersList
    });

    if (response.ok) {
        let responseData = await response.text();
        if (responseData.length === 0) {
            alert("User does not exist. Please sign up first.");
            return;
        }
        let userData = JSON.parse(responseData);
        // Définir les informations d'authentification dans sessionStorage
        setAuthenticationSession(userData.id, userData.admin, email);
       
        if (userData.isAdmin === 1) {
            window.location.href = "AdminDashboard.html";
          
        } else {
            if (userData.validate === 1) {
                window.location.href = "UserDashboard.html";
            } else {
                window.location.href = "errorValidate.html";
            }
        }
    }
});

function setAuthenticationSession(userId, isAdmin, userEmail) {
    sessionStorage.setItem('userId', userId);
    sessionStorage.setItem('isAdmin', isAdmin);
    sessionStorage.setItem('userEmail', userEmail);
}
