document.addEventListener("DOMContentLoaded", function() {
    // Vérifier l'authentification
    checkAuthentication();

    // Récupérer l'email de l'utilisateur à partir de sessionStorage
    const userEmail = sessionStorage.getItem("userEmail");
    if (userEmail) {
        document.getElementById("userEmail").textContent = userEmail;
    } else {
        // Rediriger vers la page de connexion si l'email n'est pas disponible dans sessionStorage
        window.location.href = 'login.html';
    }

    // Effectuer une requête GET pour récupérer la liste des utilisateurs
    fetch("http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/all")
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error("Failed to fetch user data");
        }
    })
    .then(users => {
        // Afficher les données des utilisateurs dans le HTML
        const userListBody = document.getElementById("userListBody");
        userListBody.innerHTML = ""; // Nettoyer le contenu existant
        users.forEach(async user => {
            // Récupérer le nombre de contacts pour cet utilisateur
            const contactCount = await getContactCount(user.id);

            const userRow = document.createElement("tr");
            userRow.innerHTML = `
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.validate === 1 ? "Validé" : "En attente"}</td>
                <td>${contactCount}</td>
                <td>
                    <button class="btn btn-danger" onclick="deleteUser(${user.id})"><i class="fas fa-trash"></i></button>
                    <button class="btn btn-primary edit-user-btn" onclick="openEditModal(${user.id})"><i class="fas fa-edit"></i></button>
                    ${user.validate === 0 ? `<button class="btn btn-success" onclick="validateUser(${user.id})"><i class="fas fa-check"></i></button>` : ""}
                </td>
            `;
            userListBody.appendChild(userRow);
        });
    })
    .catch(error => {
        console.error("Error fetching user data:", error);
        alert("Failed to fetch user data. Please try again later.");
    });
});

// Fonction pour ouvrir le modal de modification d'utilisateur
async function openEditModal(userId) {
    try {
        const response = await fetch('http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/find', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ id: userId })
        });

        if (!response.ok) {
            throw new Error('Failed to fetch user data');
        }

        const userData = await response.json();
        
        // Remplir les champs du formulaire avec les informations récupérées
        document.getElementById('editUserId').value = userData.id;
        document.getElementById('editEmail').value = userData.email;
        document.getElementById('editState').value = userData.state;

        // Afficher le modal de modification
        const editModal = new bootstrap.Modal(document.getElementById('editUserModal'));
        editModal.show();
    } catch (error) {
        console.error('Error opening edit modal:', error);
        alert('Failed to open edit modal. Please try again later.');
    }
}

// Fonction pour enregistrer les modifications de l'utilisateur
async function saveUserChanges() {
    const userId = document.getElementById('editUserId').value;
    const userEmail = document.getElementById('editEmail').value;
    const userState = document.getElementById('editState').value;

    const updatedUserData = {
        id: userId,
        email: userEmail,
        state: userState
    };

    try {
        const response = await fetch('http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/update', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedUserData)
        });

        if (!response.ok) {
            throw new Error('Failed to update user');
        }

        // Les modifications ont été enregistrées avec succès
        alert('User updated successfully');
        // Actualiser la page pour refléter les changements
        window.location.reload();
    } catch (error) {
        console.error('Error updating user:', error);
        alert('Failed to update user. Please try again later.');
    }
}

async function getContactCount(userId) {
    const response = await fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/nbrContact`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: userId })
    });
    const data = await response.json();
    return data;
}

function deleteUser(userId) {
    fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/delete`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: userId })
    })
    .then(response => {
        if (response.ok) {
            // L'utilisateur a été supprimé avec succès
            alert('User deleted successfully');
            // Actualiser la page pour refléter les changements
            window.location.reload();
        } else {
            // Il y a eu une erreur lors de la suppression de l'utilisateur
            console.error('Failed to delete user');
            // Vous pouvez afficher un message d'erreur à l'utilisateur ici si nécessaire
        }
    })
    .catch(error => {
        // Il y a eu une erreur lors de la requête
        console.error('Error deleting user:', error);
        // Vous pouvez afficher un message d'erreur à l'utilisateur ici si nécessaire
    });
}

function validateUser(userId) {
    fetch(`http://localhost:9080/gestioncontact/api/gestioncontact/v1/users/validate`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ id: userId })
    })
    .then(response => {
        if (response.ok) {
            // L'utilisateur a été validé avec succès
            alert('User validated successfully');
            // Actualiser la page pour afficher les changements
            window.location.reload();
        } else {
            // Il y a eu une erreur lors de la validation de l'utilisateur
            console.error('Failed to validate user');
            // Vous pouvez afficher un message d'erreur à l'utilisateur ici si nécessaire
        }
    })
    .catch(error => {
        // Il y a eu une erreur lors de la requête
        console.error('Error validating user:', error);
        // Vous pouvez afficher un message d'erreur à l'utilisateur ici si nécessaire
    });
}
