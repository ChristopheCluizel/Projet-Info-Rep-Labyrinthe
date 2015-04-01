javac javabyrinth/*.java

cp -R javabyrinth/*.class serveur/javabyrinth/
cp -R javabyrinth/serveur/*.class serveur/javabyrinth/serveur/

cp -R javabyrinth/*.class client/javabyrinth/

cp -R javabyrinth/*.class rmiregistry/javabyrinth/

javac serveur/*.java

javac client/*.java
