document.getElementById("signupForm").addEventListener("submit", async function(event) {
    event.preventDefault(); 
    
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;
    
    
    if (!email || !password) {
        alert("Veuillez remplir tous les champs.");
        return; 
    }

    let headersList = {
        "Accept": "*/*",
        "Content-Type": "application/json" 
    };

    let bodyContent = JSON.stringify({
        email: email,
        password: password
    });

    let response = await fetch("http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/insert", { 
        method: "POST",
        body: bodyContent,
        headers: headersList
    });

    if (response.ok) {
       
        window.location.href = 'errorValidate.html';
    } else {
        alert("Signup failed. Please try again later.");
    }
});
