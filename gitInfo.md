### GIT

## C'est quoi ?

Git est un système de contrôle de version open source très populaire pour la gestion de projet informatique, développé par Linus Torvalds en 2005. 

Il permet de stocker et de gérer l'historique des modifications apportées à des fichiers de code source ou à tout autre type de fichier, de manière à pouvoir revenir en arrière ou à collaborer avec d'autres développeurs de manière efficace.

***

## Comment on enregistre nos fichiers ?
Git fonctionne en enregistrant des "snapshots" de l'état du projet à chaque étape de son évolution. Ces snapshots sont appelés "commit" et contiennent des informations sur les modifications apportées aux fichiers. Git conserve une base de données de ces commits, permettant aux développeurs de suivre l'historique du projet et de revenir à des versions antérieures si nécessaire.

Si vous avez besoin d'enregistré une snapshots, il faut d'abord ajouter les fichier avec la commande :
`git add NomDuFichier`

Ensuite, une fois les fichiers ajoutés, vous pouvez enregistré la snapshots avec la commande
`git commit -m "Un petit commentaire pour savoir ce que c'était"`

Pour connaitre le status de votre git, il suffit d'utiliser la commande : `git status`

Vous pouvez voir toutes 

Git utilise une approche décentralisée, ce qui signifie que chaque développeur dispose d'une copie complète du dépôt de code sur son ordinateur. Cela permet aux développeurs de travailler sur des modifications de manière indépendante, puis de les fusionner ultérieurement dans le code principal. Git facilite également la collaboration entre les développeurs en permettant la gestion des conflits de fusion, la création de branches et la gestion des autorisations d'accès au code source.

Et pour télécharger et synchroniser la dernière version sur le dépôt distant à partir de notre dépôt local, il faut utiliser la commande : `git push`

Et pour faire l'inverse (d'un dépôt distant vers notre dépôt local), utilisez la commande : `git push`

Il est parfois nécessaire de copier un dépôt distant dans votre dépôt local.

