### Docker

## Telecharger Docker

Lorsque vous avez fini d'installer Docker (cela prend du temps)

## Image
Une image Docker est un fichier immuable (non modifiable) qui contient le code source, les bibliothèques, les dépendances, les outils et d’autres fichiers nécessaires à l’exécution d’une application. Étant donné que les images ne sont, en quelque sorte, que des modèles, vous ne pouvez pas les démarrer ou les exécuter. Ce que vous pouvez faire, c’est utiliser ce modèle comme base pour créer un conteneur.

Pour voir toutes les images télécharger, utiliser la commande :

`docker images`

<div style = "border:1px solid yellow;">
	<p>
		/!\ Les images occupent parfois une certaine quantité d'espace, et même si cette espace est minim, il est possible de les supprimer. Pour les supprimer, suiver les étapes suivantes :
	</p>

	<ol>
		<li>Arreter tous les conteneurs en executions avec <code>docker stop $(docker ps -qa)  </code> </li>
		<li>Supprimer tous les conteneurs                  <code>docker rm $(docker ps -qa)    </code> </li>
		<li>Suppression de toutes les images Docker        <code>docker rmi $(docker images -q)</code> </li>
	</ol>
</div>

Il existe de nombreuse images, trouvable sur le [hub](https://hub.docker.com/search?q=&type=image).

Pour les exécuter, il suffit simplement de tapper la commande suivante : 

`docker run <image>`

Mais cette commande ne nous permet pas d'intéragir avec. Si vous voulez une interactions, il faut utiliser la commande suivant :

`docker run -it <image>`

Pour quitter le conteneur, ilm faut utiliser la commande 
`exit`

## Conteneur
Un conteneur Docker n’est finalement qu’une image en cours d’exécution. Une fois que vous avez créé un conteneur, il ajoute une couche inscriptible au-dessus de l’image immuable, ce qui signifie que vous pouvez maintenant le modifier.

Pour voir les conteneurs, il faut utiliser soit la commande 

`docker ps    ` pour les conteneur actif
`docker ps - a` pour tous les conteneur

## TP1

1. Le client a contacter un docker du nom de daemon
2. Celui-ci à récuperer l'image "Hello world" du docker hub
3. Il a  rée un nouveau conteneur de cette image, et la excecuté
4. Il affiche le résultats dans le terminal

## Conteneur Alpine : Linux sous windows

Avec la commande `docker run -it alpine` vous pourrez lancer et interagir avec le conteneur Alpine qui simule un système linux

Tappez la commande `cat /etc/os-release`, celle-ci renverra les informations sur le conteneur (date de sortie et autre).


## Intéragir avec un conteneur deja lancer

Il est possible d'intéragir avec le même conteneur dans un terminal différent, pour cela il suffit d'afficher les conteneur actif avec la commande 

`docker ps`

Dans la ligne du conteneur voulue, vous trouverez dans la colonne **CONTAINER ID** le code hachag du conteneur. 
Vous pourrez donc tapper la commande 

`docker exec <HACHAGE>`

Imgainons nous voulons intéragir avec le conteneur dans un terminak /bin/sh (je pense a Alpine), il faudra utiliser la commande :

`docker exec -it <HACHAGE ou NOM DU CONTENEUR> /bin/sh`


## Ports, volumes et copies de fichiers

Un conteneur est un processus isolé. Cela signifie que ce qui se passe dans l’environnement virtuel du conteneur n’est pas accessible depuis la machine hôte. En soi un conteneur n’aurait pas beaucoup d’intérêt s’il est complètement isolé. 

Ainsi, il est possible d’exposer certaines parties du conteneur à la machine hôte. Dans cette section, nous verrons comment exposer des ports, comment copier des fichiers de la machine hôte vers le conteneur et comment partager des volumes (répertoires) entre la machine hôte et le conteneur. 

Pour cela nous utiliserons l’image httpd qui fournie le service HTTP apache.

### Ports
La première chose que nous allons faire est exposer le port 80 d'un conteneur httpd. Ce port est le port par défauts du protocole HTTP.

Le serveur apache que nous allons lancer avec le conteneur httpd agit sur ce port, cependant, ledit port sera isolé dans le conteneur. Essayons.

Tappez la commande suivante : `docker run httpd`

Puis, dans un navigateur, tapez **localhost**. Si tous c'est bien passer, vous tomberez sur... _rien_.

C'est normal, ce que nous avons besoin c'est de rendre le service accessible, et pour ca, il faut mapper le port 80 du conteneur à un port donner à la machine hôte. Pour ca, utiliser la commande suivante :

`docker run --name httpd-<votre nom> -d -p <port hôte>:80 httpd`

Les paramètres suivants sont :
- `--name httpd-<nom>` permet de nommer le conteneur avec un nom aux choix.
- `-d` permet d'exécuter le conteneur en arrière-plan.
- `-p <port hote>:80` permet de mapper un port de l'hote avec le port 80 du conteneur. Remplacer à gauche par le port a utiliser (exemple : 8080).
- `httpd` qui est tous simplement l'image quon execute.

Après l'avoir exécutez, aller dans votre navigateur et tapper : **localhost:\ltporthost\gt**


### Copie de fichiers

La commande `docker cp` permet de copier le contenue d'un dossier ou fichier source vers un dossier destinations.

Par exemple, relancer le conteneur précédent avec les mêmes options (ou continuer si vous l'avez pas arreter).

Créer un fichier **index.html**, et rentrer les informations suivantes à l'intérieur :

```
<html> 
   <head> 
	<meta charset="UTF-8"> 
   </head> 	
   <body> 
       <h1> Le fichier index.html a été modifié </h1>
   </body> 
</html> 
```

Puis, copier le fichier dans le conteneur avec la commande :
`docker cp ./index.html httpd-<nom>:/usr/local/apache2/htdocs/index.html`

### Volumes

Il existe une autre facon pour copier des fichiers ou dossier entre la machine hôte et le conteneur : les volumes permettent d'établir un point de montage entre les deux machines afin qu'un répetroir puisse être partagé à tout moment.

Assurer vous que votre conteneur précédent soit maintenant arrete avec la commande `docker stop <nom conteneur>`.

Ensuite, créez un fichier **html** (`mkdir html`) et créer un fichier index.html à l'intérieur (mettez ce que vous voulez dans ce fichier).

Enfin, dans le dossier html executer la commande suivante :

`docker run --name httpd-<votre nom> -d -p <port hôte>:80 -v $(pwd):/usr/local/apache2/htdocs httpd`

Cette commande doit vous faire rappeler la commande vue précédement, la seule option nouvelle est 
`-v $(pwd):/usr/local/apache2/htdocs httpd` qui permet de créer un point de montage (volume) entre l'hôte et le conteneur.

- A gauche se trouve le dossier que nous volons partager (pwd permet de renvoyer le fichier ou nous sommes actuellement)
- A droit se trouve le dossier dans le conteneur (ici, le dossier **/usr/local/apache2/htdocs** gérent les pages webs desservies par le serveur Apache)

