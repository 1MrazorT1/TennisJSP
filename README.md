# Template exemple pour le TP3 de TechnologiesJava
Le template présente une configuration pour avoir une base intégrée **H2 database**.

Un exemple de fonctionnement est fourni pour la gestion d'une TodoList.

### Configuration BDD

Le fichier de configuration pour la BDD est `src/main/resources/META-INF/persistence.xml`

La propriété `javax.persistence.jdbc.url` donne l'emplacement du fichier de BDD.

On remarque qu'il est défini dans ' `~/h2`. C'est ici plus simple d'avoir un chemin absolu.

C'est la bibliothèque **Hibernate** qui fait le travail.

### Configuration WEB

Le fichier de configuration Web est `src/main/webapp/WEB-INF/web.xml`

* On trouve le fichier par défaut `welcome-file` : ici `index.jsp`
* La classe qui se lance au démarrage du serveur : `ServletContextManager`
* Et la console H2 accessible à [http://localhost:8080/tennis/console/](http://localhost:8080/tennis/console/)
  * Mêmes infos à remplir que dans `persistence.xml` pour `Driver Class, JDBC URL, User Name et Password`

## Lancement
### Lancement IntelliJ
Ouvrir le projet dans IntelliJ Idea.

Si cela ne crée pas de configuration de lancement, ajouter une configuration :
* Faire le `+` puis `Tomcat > Local`
  * Dans `Application server`, il nous faut un `Tomcat 10` (il suffit du Tomcat Core dézippé quelque part)
* Vérifier dans `Deployement`
  * on doit avoir l'artefact `Gradle ... Exploded` (sinon l'ajouter)
  * en bas de cette fenêtre remplir `Application context` avec `/tennis`

Le contexte de déploiement conseillé est donc `/tennis`.
Et l'application est accessible à [http://localhost:8080/tennis/](http://localhost:8080/tennis/)

### Dans un serveur d'application Java
Faire le build : `./gradlew build` et déployer le fichier war créé dans `build/libs` sur le serveur avec l'interface Apache Tomcat GUI Manager.

## Informations sur le code 
### Détails des classes
* persistence.TodoEntity : déclaration d'une table de BDD
* persistence.Database : ouvre la BDD, conserve la connexion et fait les requêtes
  * C'est une classe importante, vous aurez beaucoup d'ajout ici.
  * Pour améliorer, on remplacerait ceci par le pattern DAO pour nos différentes tables mais ça fera l'affaire pour le TP.
* servlet.TodoServlet : le point d'entrée HTTP pour les services liés à la Todolist

### Les pages Html et Jsp
Présentes dans `src/main/webapp`, les pages JSP utilisent `header.jsp` et `footer.jsp` (cf. `src/main/webapp/WEB-INF/includes`). 

Vous pouvez réutiliser ce principe et utiliser le Header comme Menu.

**Bootstrap** est déclaré dans le header pour avoir un peu de CSS. Le fichier `css/myapp.css` aussi et permet d'ajouter un peu de personnalisation.
(C'est une autre manière d'envisager le web comparable à HTML5+Tailwind)

### Session
Utilisez le principe de Java Session pour conserver des données (l'objet Adhérent par exemple après avoir fait un login).

* Création de la session : `HttpSession session = request.getSession(true);`
* Récupérer la session sans création : `HttpSession s = request.getSession(false);`
* Contrôler la durée de session : `session.setMaxInactiveInterval(120);`
* Ajouter un élément : `session.setAttribute("adherent", adherent);`
* Récupérer l'élément : `session.getAttribute("adherent")`

Le contenu du menu dans `header.jsp` peut être dépendant d'un élément de session !

## Docker
Si vous souhaitez tester avec Docker, il vous faut une installation de Docker sur votre poste personnel.

Pour lancer les commandes, se placer dans le répertoire du projet.

### Dockerfile
Le fichier `Dockerfile` propose une construction en 2 étapes (multi-stage)

#### Construction
Construction d'une image dont le nom sera `tennis_app_img` :

`docker build -t tennis_app_img .`

#### Lancement
Lancement dans un container dont le nom sera `tennis_app`

* En mode interactif : `docker run -it -p 8080:8080 --rm tennis_app_img --name tennis_app`
* En mode non-interactif : `docker run -d -p 8080:8080 --name tennis_app --rm tennis_app_img`

### Docker Compose
Composition des commandes précédentes à partir de fichier de configuration `docker-compose.yml`

* En mode interactif : `docker compose up`
* En mode non-interactif : `docker compose up -d`

