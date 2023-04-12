### [GIT](#git)

## C'est quoi ?

Git est un système de contrôle de version open source très populaire pour la gestion de projet informatique, développé par Linus Torvalds en 2005. 

Il permet de stocker et de gérer l'historique des modifications apportées à des fichiers de code source ou à tout autre type de fichier, de manière à pouvoir revenir en arrière ou à collaborer avec d'autres développeurs de manière efficace.

***

## Comment on enregistre nos fichiers ?
Git fonctionne en enregistrant des "snapshots" de l'état du projet à chaque étape de son évolution. Ces snapshots sont appelés "commit" et contiennent des informations sur les modifications apportées aux fichiers. Git conserve une base de données de ces commits, permettant aux développeurs de suivre l'historique du projet et de revenir à des versions antérieures si nécessaire.

Si vous avez besoin d'enregistré une snapshots, il faut d'abord ajouter les fichier avec la commande : 
`git add NomDuFichier`

Ensuite, une fois les fichiers ajoutés, vous pouvez enregistré la snapshots avec la commande : 

`git commit -m "Un petit commentaire pour savoir ce que c'était"`

Pour connaitre le status de votre git, il suffit d'utiliser la commande : `git status`

Vous pouvez voir toutes 

Git utilise une approche décentralisée, ce qui signifie que chaque développeur dispose d'une copie complète du dépôt de code sur son ordinateur. Cela permet aux développeurs de travailler sur des modifications de manière indépendante, puis de les fusionner ultérieurement dans le code principal. Git facilite également la collaboration entre les développeurs en permettant la gestion des conflits de fusion, la création de branches et la gestion des autorisations d'accès au code source.

Et pour télécharger et synchroniser la dernière version sur le dépôt distant à partir de notre dépôt local, il faut utiliser la commande : `git push`

Pour faire l'inverse (d'un dépôt distant vers notre dépôt local), utilisez la commande : 
`git pull`

Il est parfois nécessaire de copier un dépôt distant dans votre dépôt local. Pour ce faire, nous aurons besoin de la commande :

`git clone git@github.com:<nom_de_utilisateur>/<nom_de_dépôt>.git`

[Haut de la page](#git)

***

## Nouvelles fonctionnalités à l’aide des branches

Lorsque nous créons notre dépôt, le défaut est de le créer dans une branche appelée **main** ou **master** (très rare pour le moment). Mais supposons que nous ayons une "embranchement" d'idées et de mise en œuvre du projet, ou que nous voulions tester une fonctionnalité dans une autre branche avant de l'intégrer dans la branche principale. Pour ce faire, nous pouvons travailler dans une nouvelle branche.

Avec `git branch` vous pouvez savoir sur quelle branche vous êtes actuellement.

La commande `git checkout -b <nom_de_branche>` avec le modificateur **-b** nous permet de créer et de déplacer vers une branche nouvellement créée 

Si vous voulez juste passer à une branche existante sans en créer une nouvelle, alors la commande `git checkout <nom_de_branche>` fera l'affaire.

<div style = "border:2px solid yellow; background-color:yellow;"> Remarque : lorsque vous crée des fichier dans les branches (par exemple la branche nomBranche), ils ne seront pas visible par la branche main.</div>

Si vous voulez merger deux branches, par exemple, main et test, aller tous dabord sur la branche main puis tapper la commande : 

`git merge test`

[Haut de la page](#git)

***
