rm -rf target
mkdir -p target/scala-2.10/classes
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/ihm/*.java
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/jeu/*.java
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/labyrinthe/*.java
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/tools/*.java
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/rmi/client/*.java
javac -d ./target/scala-2.10/classes/ -cp ./target/scala-2.10/classes/:./lib/* -sourcepath src/main/java/ src/main/java/javabyrinthe/rmi/serveur/*.java
