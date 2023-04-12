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


