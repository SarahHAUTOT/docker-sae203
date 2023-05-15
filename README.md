# Planning Sup'

## C'est quoi ?
Planning sup est une application java permettant la création de groupe et d'emploi du temps rapidement via des fichiers data.

## Comment l'installer ?

### Logiciels nécessaire
Il est important que les logiciels suivant soit présent sur votre machine :
- GIT
- Docker

### Récupérer les fichier
Pour que notre application fonctionne, il est important de récupérer les fichiers nécessaire à son fonctionnement.
Mettez vous dans un répértoire de votre choix, puis, tappez la commande suivante :

``git clone git@github.com:Ottirate/docker-sae203.git``

Si vous regardez votre répértoire, vous verrez qu'un dossier **docker-sea203** c'est crée, déplacer vous dedans avec la commande

``cd docker-sae203``

Dedans, vous trouverez le fichier README.md, le fichier DockerFile, et un dossier app.

### Crée et lancer l'image 
Toujours dans le dossier docker-sae203, exécuter la commande suivante :

``docker build -it <nom_image> .``

Cela vous prendra surement un petit moment (le temps que tous s'installe). 
Une fois terminer, tappez la commande suivante :

``docker run --name <nom_image> -d -p <port-hôte>:80 <nom_image>``

