javac javabyrinthe/*.java

cp -R javabyrinthe/*.class serveur/javabyrinthe/
cp -R javabyrinthe/serveur/*.class serveur/javabyrinthe/serveur/

cp -R javabyrinthe/*.class client/javabyrinthe/

cp -R javabyrinthe/*.class rmiregistry/javabyrinthe/

javac serveur/*.java

javac client/*.java
