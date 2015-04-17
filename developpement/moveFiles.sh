#!/bin/bash

export cheminClasses=target/scala-2.10/classes

mkdir $cheminClasses/javabyrinthe/rmi/rmiregistry -p
cp $cheminClasses/javabyrinthe/jeu/*.class $cheminClasses/javabyrinthe/rmi/rmiregistry
cp $cheminClasses/javabyrinthe/labyrinthe/*.class $cheminClasses/javabyrinthe/rmi/rmiregistry
cp $cheminClasses/javabyrinthe/tools/*.class $cheminClasses/javabyrinthe/rmi/rmiregistry


cp $cheminClasses/javabyrinthe/jeu/*.class $cheminClasses/javabyrinthe/rmi/serveur
cp $cheminClasses/javabyrinthe/labyrinthe/*.class $cheminClasses/javabyrinthe/rmi/serveur
cp $cheminClasses/javabyrinthe/tools/*.class $cheminClasses/javabyrinthe/rmi/serveur


cp $cheminClasses/javabyrinthe/jeu/*.class $cheminClasses/javabyrinthe/rmi/client
cp $cheminClasses/javabyrinthe/labyrinthe/*.class $cheminClasses/javabyrinthe/rmi/client
cp $cheminClasses/javabyrinthe/tools/*.class $cheminClasses/javabyrinthe/rmi/client


cp $cheminClasses/javabyrinthe/serveur/PartieRunner.class $cheminClasses/javabyrinthe/rmi/serveur