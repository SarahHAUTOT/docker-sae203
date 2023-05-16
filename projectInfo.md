# Planning sup'

## Qu'est ce que c'est ?

Planning Sup' est une application java permettant la création de groupe et d'mploi du temps pour les passages selon des datas fournis.
C'est datas peuvent bien évidemment être supprimer, ce qui changera les pages web généré et affiché par l'image Docker. 

## Comment l'utiliser ?
Les datas situé dans le fichier app/data peuvent être modifié, tant que les règles suivantes sont respectés :
- Ils doivent garder la même forme
- La capacité des salles doivent être nécessaire pour tous les groupes (pour éviter de tombé dans une boucle infini)

N'ayant pas encore appris à faire des pages dynamiques, il sera malheureusement nécessaire de reconstruire le docker quand on change les datas (en gros, c'est à usage unique un peu).

Mais vous inquietez pas, la première installation est la plus lente, les autres sont rapides.

Ce projet peut être diviser en 3 language :

### Java
Java est le cerveau de l'application. C'est lui qui :
- Lis les fichiers et interprètre les données
- Disperce les élèves dans différents groupe et salle
- Crée un emploie du temps et un jury selon les horaire données
- Génère les pages html

Il est composé de lui même plusieurs java

#### Main
Comme le nom l'indique, le main est le corps du programme. C'est lui qui fait les calculs et appelle les autres programme.

#### Différents objet
Dans cette classe, nous avons utiliser plusieurs types crée par nos soins :
- Etudiant, qui contient les informations sur, comme le nom l'indique, les élèves
- Groupe, qui est tous simplement un tableau d'élèves lié à une salle
- Jury, lié au temps des oraux, des équipes qu'ils font passé etc...

#### Changer les datas
Pour changers les datas, il est important de garder la même forme pour éviter les bugs. Le format est le suivant :

##### promotion.data
Le fichier promotion.data est le fichier contenant les informations les élèves. 
L'ordre des informations à donnée est donnée dans la première ligne, vous avez juste à les séparer par des tabulations.

**/!\ Il ne faut PAS supprimer la première ligne : la première ligne est ignoré par le programme, l'enlevé reviendra à rendre le premier élèves invisible au programme.**

##### ressources.data
Le fichier ressources est le fichier contenant : la tailles des équipes, les salles et le nombre de groupe que chaque salle peut prendre. 
La première ligne est la taille des groupes, elle doit donc être rempli par un chiffre juste.
Les lignes suivantes eux, sont les salles (identifiant, cela ne peut être que un chiffre), suivit d'après la capacité des salles.

Notes : Il est important que la capacité total des salles soit suffisante, ou le programme java rentera dans une boucle infini. N'ayez pas peur d'avoir plus de place que nécessaire, cela ne genera en rien le programme.

##### jury.data
Le fichier jury eux est le fichier contenant : le temps de présentations, le temps de pause entre chaque présentations, et les différents jury avec leur horaires, les différents profs, leur horaires, etc...

La première ligne doit être composé de deux chiffres : le temps de présentations et le temps de pause (en minute).

Ensuite, pour ajouter les jurys vous aurez juste besoin de respecter le format suivant :
``<identifiant>    <salle>    <date>    <heure de début>    <heure de fin>    <composition du jury>``

Notez que les heures sont dans le format suivant : hh:mm.



### Html
Les fichiers html sont générès par java (hormis le index.html), ils vous permettra de visualiser les groupes et emplois du temps.
Afficher par le dockerfile sur un port local, vous pouvez y avoir accès facilement en suivant les instructions fournis dans le ReadMe fournis au début.

### Dockerfile

Le Dockefile est celui qui vas tous préparé il vas :
- copier les fichiers que vous avait récupéré ainsi que les datas que vous aurait pu modifier.
- exécuté le programme, et déplacer les fichiers de sorte qu'ils puissent être visible grace à Apache.

Le dockerfile dans son ensemble ressemble à ca : 

```
# Utiliser l'image debia officielle comme image parent
FROM debian:latest

# Installations de tous ce qu'il nous faut
RUN apt-get update &&  \
    apt-get -y install \
    apache2            \
    openjdk-17-jdk     \
    openjdk-17-jre -y

# Copier les fichiers de l'hote chez nous
COPY ./app/programme/*.java ./app/programme/
COPY ./app/data/*.data ./app/data/
COPY ./app/Web/* ./app/Web/
COPY ./app/Web/image/*.png ./var/www/html/image/

# Copie le .jar et crée la variable CLASSPATH
COPY ./app/iut.jar ./app/rsc/
ENV CLASSPATH=/app/rsc/iut.jar:/app/rsc/

# On se met dans le fichier app contenant le java, et on l'execute
WORKDIR ./app/programme
RUN javac *.java -d ../rsc/
RUN java Main.java

# On se met dans le fichier web de l'app pour tous déplacer
WORKDIR ../Web/

# Déplace les fichiers vers le repertoire var/www/html
RUN mv *.html ../../var/www/html/

RUN mkdir ../../var/www/html/style/
RUN mv *.css ../../var/www/html/style/


# On expose le port 80 (port nécessaire pour le web)
EXPOSE 80

# On lance le service apache au démarrage du conteneur
CMD ["/usr/sbin/apache2ctl","-DFOREGROUND"]
```


### 