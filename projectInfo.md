# Echec Solitaire

## Qu'est ce que c'est ?

Echec Solitaire est un projet crée par nos soin avec l'aide d'un .jar donné par Mr.Le Pivert pour l'interface IHM.
Basé sur le jeu Solitary chess, votre but et de faire en sorte de bougé les pion de sortes qu'il n'en reste plus qu'un.

Composé de plusieurs niveaux, plus ou moins difficiles, vous pourrez avancer dans ses niveaus, et retourner en arrière pour vous entrainer.

## Comment l'utiliser ?
Avant de récupérer les fichiers, il est important de prévoir un serveur X11. Suivez les étapes suivantes pour continuer :

### Serveur X11

#### Windows
Sur windows, vous n'avez pas de serveur X11. Vous allez donc devoir passé par l'application Xming avant :
- Téléchargez Xming à partir du [site officiel] (https://sourceforge.net/projects/xming/)
- Une fois l'installation terminée, lancez Xming (via XLaunch)
- Puis, dans un terminal administrateur, tappez la commande `set DISPLAY=host.docker.internal:0` pour définir la variable DISPLAY


#### Debian
Sur Debian, tous ca est beaucoup plus simple :
- Assurez vous d'avoir les droits, et télécharger l'application avec `apt-get install xorg`
- Puis, lancez le serveur avec `startx`
- Ensuite, définissez la variable DISPLAY `export DISPLAY=:0`

### Récupérez les fichiers
Les fichiers se trouvant sur gitHub, vous aurez besoin de télécharger Git. Ensuite, mettez vous à l'endroit de votre choix et tapper la commande suivante dans un terminal 
``

### Créer et lancer l'image 

Ensuite, 