Projet Informatique Répartie Javabyrinthe
===

L'objectif de ce projet est de mettre en pratique les notions vues en cours d'Informatique Répartie. Pour cela, il a été choisi de faire un jeu de labyrinthe multijoueurs avec une application s'exécutant chez chaque client et un serveur de supervision. Les principales technologies utilisées seront :

- les clients et le serveur seront codés en `JAVA` ;
- la communication entre les clients et le serveur sera effectuée à l'aide de `RMI` ;
- l'interface utilisateur sera réalisée à l'aide de la bibliothèque `Slick2D`.

## Exécution

Pour exécuter `rmiregistry`, le `serveur` et le `client`, il faut préalablement déplacer un certain nombre de fichiers `.class`. Mettre tous les .class des dossiers jeu/, labyrinthe/ et tools/ dans les dossiers `client`, `rmiregistry` et `serveur`. Le script `moveFiles.sh` fait cela automatiquement.

Ensuite se placer dans le dossier `classes` et lancer dans un terminal différent : 

- `rmiregistry`
- `java serveur.Serveur`
- `java client.Client` avec l'option `R` (rejoindre) ou `C` (créer) et l'option `H` (joueur humain) ou `I` (joueur IA)
