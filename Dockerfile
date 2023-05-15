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