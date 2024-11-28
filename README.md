# Mutuelle Batch Processing

## Objectif du projet

Ce projet est une application Spring Batch destinée à automatiser le traitement des dossiers pour une mutuelle de santé. Il permet de lire des dossiers depuis un fichier JSON, de les valider, de les transformer à l'aide de multiples processeurs, puis de les enregistrer dans une base de données. Le projet est également configuré pour suivre l'état d'exécution des jobs via une base de données dédiée au suivi des métadonnées de Spring Batch.

---

## Fonctionnalités principales

1. **Lecture des dossiers** :
   - Les dossiers sont lus depuis un fichier JSON.
2. **Traitement des données** :
   - Plusieurs étapes de validation et transformation des données sont appliquées grâce à un composite processor.
3. **Écriture en base de données** :
   - Les dossiers traités sont enregistrés dans la base de données principale.
4. **Suivi des métadonnées de jobs** :
   - L'état d'exécution des jobs est suivi dans une base de données spécifique.
5. **Deux méthodes pour exécuter le job** :
   - Automatiquement via `CommandLineRunner`.
   - Manuellement via une requête POST à un contrôleur REST.

---

## Pré-requis

Avant d'exécuter ce projet, assurez-vous d'avoir les éléments suivants installés :

- **Java 17** ou une version supérieure.
- **Maven** pour la gestion des dépendances.
- **MySQL** pour les bases de données.

---

## Configuration de la base de données

1. **Créer les bases de données :**
   - **Base principale (`assurance`) :** utilisée pour stocker les dossiers traités.
   - **Base de métadonnées Batch (`batch_metadata_db`) :** utilisée pour suivre l'état des jobs.

2. **Exécuter les scripts SQL :**
   - Ouvrez votre outil MySQL préféré (comme MySQL Workbench) et exécutez les requêtes SQL contenues dans le fichier `schema-mysql.sql` (dans le dossier `resources/sql/`) pour créer les tables nécessaires dans `batch_metadata_db`.

3. **Mettre à jour le fichier `application.properties` :**
   - Vérifiez que les configurations des bases de données dans `application.properties` correspondent à votre environnement MySQL.

---

## Instructions pour exécuter le projet

### Méthode 1 : Via `CommandLineRunner`

1. Cloner le projet :
   > git clone https://github.com/karima-bou10/mutuelle_batch.git
   > cd mutuelle_batch
   
3. Compiler et exécuter le projet :
   > mvn clean install
   > mvn spring-boot:run
   
5. Résultat attendu :
   Le job sera exécuté automatiquement.
   Consultez la base batch_metadata_db pour voir l'état du job et les journaux d'exécution.

### Méthode 2 : Via `le contrôleur REST`

1. Démarrez le projet :
   > mvn spring-boot:run
   
2. Exécutez le job manuellement en envoyant une requête POST dans Postman:
   URL : http://localhost:8080/api/jobs/start
   Méthode : POST
   
3. Vérifiez l'état du job dans la base batch_metadata_db.

---

## Structure du projet

**DTOs**       : Contient les classes de transfert de données (par exemple, DossierDto).
**model**      : Contient les entités métier (par exemple, Dossier).
**processor**  : Contient les différents processeurs utilisés dans le traitement des données.
**reader**     : Contient le composant de lecture des fichiers JSON.
**writer**     : Contient le composant pour écrire les données dans la base.
**config**     : Contient la configuration principale de Spring Batch.
**controller** : Fournit une API REST pour exécuter manuellement le job.

---

## Points importants

**Gestion des erreurs** : Le projet utilise des stratégies de skip pour ignorer les erreurs spécifiques tout en continuant le traitement.
**Personnalisation des jobs** : Les jobs sont configurables grâce à Spring Batch, avec des fonctionnalités comme skipPolicy, retryPolicy et listeners.





