  // Fonction pour rediriger vers la page de création de contact avec userId dans l'URL
  function redirectToCreateContact() {
    const userId = sessionStorage.getItem("userId");
    window.location.href = `createContact.html?userId=${userId}`;
}

// Fonction pour charger les contacts de l'utilisateur
async function loadUserContacts(userId) {
    const response = await fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/contacts/user/${userId}`);
    const contacts = await response.json();

    const contactsTableBody = document.getElementById('contactsTableBody');
    contactsTableBody.innerHTML = ''; // Vide le contenu du corps du tableau des contacts

    contacts.forEach(contact => {
        // Crée une nouvelle ligne de tableau
        const row = document.createElement('tr');
        
        // Remplit les cellules de la ligne avec les données du contact
        row.innerHTML = `
            <td>${contact.nom}</td>
            <td>${contact.telephone}</td>
            <td>${contact.email}</td>
            <td>${contact.adresse}</td>
            <td>
                <button class="btn btn-danger" onclick="deleteContact(${contact.id})"><i class="fas fa-trash"></i></button>
             <button class="btn btn-primary" onclick="editContact(${contact.id})"><i class="fas fa-edit"></i></button>

            </td>
        `;
        
        // Ajoute la ligne au tableau
        contactsTableBody.appendChild(row);
    });

    // Mettre à jour le nombre de contacts affiché
    document.getElementById('contactCount').textContent = contacts.length;
}

// Fonction pour supprimer un contact avec son ID
async function deleteContact(contactId) {
    try {
        const response = await fetch('http://localhost:9080/gestioncontact/api/gestioncontact/v1/contacts/deleteContact', {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: contactId })
        });

        if (response.ok) {
            alert('Contact supprimé avec succès!');
            const userId = sessionStorage.getItem("userId");
            loadUserContacts(userId); // Recharger les contacts après la suppression
        } else {
            alert('Échec de la suppression du contact. Veuillez réessayer plus tard.');
        }
    } catch (error) {
        console.error('Erreur:', error);
        alert('Une erreur s\'est produite. Veuillez réessayer plus tard.');
    }
}

// Fonction pour afficher le formulaire de modification de contact avec les détails du contact sélectionné
function editContact(contactId) {
    $('#editContactModal').modal('show'); // Afficher le modal de modification de contact
    fetchContactDetails(contactId); // Appeler la fonction pour récupérer les détails du contact
}

// Fonction pour soumettre le formulaire de modification de contact
function submitEditContactForm() {
    updateContact();
}

// Fonction pour mettre à jour un contact
async function updateContact() {
    const editContactId = document.getElementById('editContactId').value;
    const editContactNom = document.getElementById('editContactNom').value;
    const editContactTelephone = document.getElementById('editContactTelephone').value;
    const editContactEmail = document.getElementById('editContactEmail').value;
    const editContactAdresse = document.getElementById('editContactAdresse').value;

    const updatedContact = {
        id: editContactId,
        nom: editContactNom,
        telephone: editContactTelephone,
        email: editContactEmail,
        adresse: editContactAdresse,
        id_user: userId
    };

    try {
        const response = await fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/contacts/updateContact`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedContact)
        });

        if (response.ok) {
            alert('Contact mis à jour avec succès!');
            $('#editContactModal').modal('hide'); // Cacher le modal de modification de contact après la mise à jour
            const userId = sessionStorage.getItem("userId");
            loadUserContacts(userId); // Recharger les contacts après la mise à jour
        } else {
            throw new Error('Failed to update contact');
        }
    } catch (error) {
        console.error('Error:', error);
        alert('Une erreur s\'est produite. Veuillez réessayer plus tard.');
    }
}

// Appel de la fonction pour charger les contacts de l'utilisateur
const userId = sessionStorage.getItem("userId");
if (userId) {
    loadUserContacts(userId);
} else {
    window.location.href = 'login.html';
}

const userEmail = sessionStorage.getItem("userEmail");
if (userEmail) {
    document.getElementById("userEmail").textContent = userEmail;
} else {
    // Rediriger vers la page de connexion si l'email n'est pas disponible dans sessionStorage
    window.location.href = 'login.html';
}

// Fonction asynchrone pour récupérer les détails du contact
async function fetchContactDetails(contactId) {
    try {
        const response = await fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/contacts/findContact`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: contactId }) // Envoyez l'ID du contact à trouver dans le corps de la requête
        });
        if (!response.ok) {
            throw new Error('Failed to fetch contact details');
        }
        const contact = await response.json();
        // Remplir les champs du formulaire avec les détails du contact
        document.getElementById('editContactId').value = contact.id;
        document.getElementById('editContactNom').value = contact.nom;
        document.getElementById('editContactTelephone').value = contact.telephone;
        document.getElementById('editContactEmail').value = contact.email;
        document.getElementById('editContactAdresse').value = contact.adresse;
    } catch (error) {
        console.error('Error:', error);
        alert('An error occurred. Please try again later.');
    }
}