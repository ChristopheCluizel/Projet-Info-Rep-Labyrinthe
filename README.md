Projet Informatique Répartie Javabyrinthe
===

L'objectif de ce projet est de mettre en pratique les notions vues en cours d'Informatique Répartie. Pour cela, il a été choisi de faire un jeu de labyrinthe multijoueurs avec une application s'exécutant chez chaque client et un serveur de supervision. Les principales technologies utilisées seront :

- les clients et le serveur seront codés en `JAVA` ;
- la communication entre les clients et le serveur sera effectuée à l'aide de `RMI` ;
- l'interface utilisateur sera réalisée à l'aide de la bibliothèque `Slick2D`.

Bonus : Un générateur automatique de labyrinthe sera implémenté en `Scala`. Celui-ci utilise l'algorithme `Recursive backtracker`. La taille maximum des labyrinthes pouvant être générés sera donc de l'odre de 1000x1000.

## Compilation

La compilation des fichiers `scala` n'est pas obligatoire car ceux-ci seront fournis sous forme d'un `.jar` qu'il faudra inclure lors de la compilations des sources `java` si besoin des classes `Graph` et `GenerateurLabyrinthe`.

## Exécution

Pour exécuter `rmiregistry`, le `serveur` et le `client`, il faut préalablement déplacer un certain nombre de fichiers `.class` :

- mettre `Client.class`, `Joueur.class`, `JouerImpl.class`, `Partie.class`, `PartieImpl.class`, `PartieManager.class` et `PartieManagerImpl.class` dans les dossiers `client`, `rmiregistry` et `serveur`.
- mettre le fichier `javabyrinthe/serveur/PartieRunner.class` dans le dossier `serveur`.

Ensuite se placer dans le dossier `classes` et lancer dans un terminal différent : 

- `rmiregistry`
- `java serveur.Serveur`
- `java client.Client` avec l'option `R` ou `C`
