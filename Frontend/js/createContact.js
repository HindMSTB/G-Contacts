document.addEventListener("DOMContentLoaded", function() {
    const contactForm = document.getElementById('contactForm');
    contactForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const phone = document.getElementById('phone').value;
        const email = document.getElementById('email').value;
        const address = document.getElementById('address').value;

        // Vérification du format de l'email
        const emailRegex = /^[a-zA-Z0-9_+&*-]+(?:\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\.)+[a-zA-Z]{2,7}$/;
        if (!emailRegex.test(email)) {
            alert('Format d\'email invalide');
            return;
        }

        // Vérification du format du numéro de téléphone
        const phoneRegex = /^\d{10}$/;
        if (!phoneRegex.test(phone)) {
            alert('Format de numéro de téléphone invalide');
            return;
        }

        const contactData = {
            nom: name,
            telephone: phone,
            email: email,
            adresse: address,
            user: {
                id: getUserIdFromUrl()
            }
        };

        try {
            const response = await fetch('http://localhost:9080/gestioncontact/api/gestioncontact/v1/contacts/addContact', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(contactData)
            });
            
            if (response.ok) {
                alert('Contact créé avec succès!');
                window.location.href = 'userDashboard.html';
            } else {
                alert('Échec de la création du contact. Veuillez réessayer plus tard.');
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Une erreur s\'est produite. Veuillez réessayer plus tard.');
        }
    });
});

function getUserIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    const userId = urlParams.get('userId');
    return userId ? parseInt(userId) : null;
}