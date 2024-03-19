README - Gestion des Contacts
Description
Ce projet consiste en un petit portail en Java Enterprise Edition (JEE) permettant à une entreprise de gérer ses contacts. 
L'application utilise les technologies REST, JPA (Java Persistence API) et CDI (Contexts and Dependency Injection).

Base de données
La base de données est composée des tables suivantes :

Table User avec les colonnes :
id (auto-incrémenté),login,password,isAdmin (indicateur si l'utilisateur est administrateur)
Table Contact avec les colonnes :
Nom,Tele,Email,Adresse,id_User (clé étrangère faisant référence à l'utilisateur propriétaire du contact)
Fonctionnalités
L'application offre les fonctionnalités suivantes :


Gestion des contacts :
Ajout : Ajouter un nouveau contact en vérifiant le format de l'email et du numéro de téléphone, et en s'assurant qu'il n'existe pas déjà dans la base de données.
Suppression : Supprimer un contact sélectionné de la table.
Modification : Modifier un contact existant. La modification se fait sur la même page.
Recherche 

Gestion des entreprises :
Validation et désactivation des inscriptions : Valider ou désactiver l'inscription d'une entreprise.
Modification et suppression des entreprises : Modifier ou supprimer une entreprise existante.
Dashboard : Afficher le nombre de contacts par entreprise.
Déconnexion


Technologies utilisées
backend:
Jakarta EE (JEE)
REST api 
JPA (Java Persistence API)
CDI (Contexts and Dependency Injection)
frontend: html css, javascript

