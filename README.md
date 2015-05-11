Projet Informatique Répartie Javabyrinthe
===

L'objectif de ce projet est de mettre en pratique les notions vues en cours d'Informatique Répartie. Pour cela, il a été choisi de faire un jeu de labyrinthe multijoueurs avec une application s'exécutant chez chaque client et un serveur de supervision. Les principales technologies utilisées seront :

- les clients et le serveur seront codés en `JAVA` ;
- la communication entre les clients et le serveur sera effectuée à l'aide de `RMI` ;
- l'interface utilisateur sera réalisée à l'aide de la bibliothèque `Slick2D`.

## Compilation

Pour compiler notre projet, se placer dans le dossier `developpement` et lancer dans un terminal la commande `./compile.sh`.

## Exécution

1. se placer dans le dossier `developpement`.
2. lancer le `rmiregistry` à l'aide la commande `./runRMIRegistry.sh` dans un premier terminal
3. dans un second terminal, lancer le serveur avec `./runServeur.sh`
4. puis pour chaque nouveau joueur lancer le jeu, dans un nouveau terminal, à l'aide de `./runIhm.sh` (linux) ou `./runIhmOSX.sh` (Mac)

Pour utiliser l'interface, se référer si besoin à la vidéo de démonstration dans le dossier `qualité/video_demo/demonstration.mov` du dossier parent.
