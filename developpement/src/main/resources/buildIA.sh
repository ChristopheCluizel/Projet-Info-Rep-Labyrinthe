#!/bin/bash

# $0 : nom du script
# $1 : nom du fichier
# $2 : code de l'IA (string)

# Sauvegarde du modele dans un nouveau fichier .java
cat modele.txt > $1.java
# Renommage de la classe pour correspondre au nom du fichier
sed -i -e "s/modele/"$1"/g" $1.java
# Sauvegarde du code IA à la suite
echo $2 >> $1.java
# Ajout des accolades fermantes
echo "}" >> $1.java

# Compilation du code
javac $1.java

echo "Compilation OK"
