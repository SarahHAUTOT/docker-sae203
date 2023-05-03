# SAE 2.03 GROUPE 04
# EchecSolitaire

Dans cette SAE, nous verrons comment utiliser GitHub et Docker, mais avant cela , il est important de comprendre ce à quoi ces outils correspondent.


## GitHub, un outil important pour les développeurs
A un haut niveau, GitHub est un **site web et un service de cloud** qui aide les développeurs à stocker et à gérer leur code, ainsi qu’à suivre et contrôler les modifications qui lui sont apportées. 

Créé en 2008, et appartenant maintenant à Microsoft, GitHub est devenu un outil très reconnu et très utilisé.

<div style="text-align: center;"><img src="https://github.githubassets.com/images/modules/logos_page/GitHub-Mark.png" alt="logo GitHub" width="50%" height="50%" align="centre"></div>

GitHub est une société à but lucratif qui offre un service d’hébergement de référentiel Git basé sur le cloud. Essentiellement, il est beaucoup plus facile pour les individus et les équipes d’utiliser Git pour le contrôle de versions de projets et la facilité collaboration.

De plus, n’importe qui peut s’inscrire et héberger gratuitement un dépôt de code public, ce qui rend GitHub particulièrement populaire pour y publier des projets open-source.

Cliquer sur ce [lien](./gitInfo.md) pour savoir comment utiliser Git !

## Dockeur, une machine virtuelle légère.

[Docker](./dockerInfo.md), c'est un peu comme une boîte magique dans laquelle on peut mettre toutes les choses nécessaires pour faire fonctionner une application. Cette boîte aussi appellé conteneur permet de faciliter leur deploiement, et permet de faire fonctionner des applications dans leur environnement.

Comme ça, on utiliser les applications partout, sans avoir à se soucier de savoir s'ils fonctionneront ou non et donc de ne pas se préocupper des différences entre les ordinateurs.

<div style="text-align: center;"><img src="https://www.docker.com/wp-content/uploads/2022/03/Moby-logo.png" alt="logo GitHub" width="50%" height="50%" align="centre"></div>

## Notre projet
Dans cette SAE, nous avons décidé d'installer une application java sur laquelle nous avons travaillé plus tôt dans l'année.
Le solitaire chess, ou l'echec Solitaire, est un en semble de puzzle dont le but est d'utiliser le mouvement des pieces pour ne finir qu'avec une seule pièce, chaque pièce ne peut bien sûr que se déplacer de la façon habituelle avec comme simple contrainte le fait dêtre obligé d'atterir (de manger) une autre pièce lors de son mouvement.


Donc au travers du Dockerfile, on installe java, on crée une variable d'environnement CLASSPATH puis on copie les fichiers  on le compile et on l'exécute pour faire fonctionner ce jeu, avec le code suivant : 

```
FROM debian:latest

RUN apt-get update 

# On installe java
RUN apt-get install openjdk-17-jdk -y
RUN apt-get install openjdk-17-jre -y

# Copier les fichiers
COPY ./ihmgui.jar ./paquetage_classe

# On crée une variable d'environnement
ENV CLASSPATH=/paquetage_classe:/paquetage_classe/ihmgui.jar

# Copier les fichiers Java avec rsync
COPY ./EchecSolitaire/*.java /paquetage_java/
COPY ./EchecSolitaire/Metier/*.java /paquetage_java/
COPY ./EchecSolitaire/Metier/Piece/*.java /paquetage_java/

# Compiler le code source
WORKDIR /paquetage_java
RUN javac  *.java -encoding utf8 -d ./paquetage_classe 

# Définir la commande par défaut
CMD ["java", "-cp", "/paquetage_classe/ihmgui.jar/:/paquetage_classe/", "Main"]
```

Un dockerfile simple, et facile à comprendre.