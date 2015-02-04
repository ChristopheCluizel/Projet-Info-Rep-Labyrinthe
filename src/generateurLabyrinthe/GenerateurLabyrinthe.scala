package generateurLabyrinthe

import scala.util._
import java.io.BufferedWriter
import java.io.BufferedReader
import java.io.Reader
import java.io.Writer
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import graph._

class GenerateurLabyrinthe {

    def sauvegarderLabyrinthe(graph: Graph[Int]) = {
        val writer = new BufferedWriter(new FileWriter("ressources/" + graph.nom + ".dot"))
        writer.write(graph.adjacence.size + "\n")
        writer.write("graph " + graph.nom + " {\n")
        graph.adjacence.keys.foreach { i =>
            for(j <- 0 until graph.adjacence(i).size) {
                writer.write(i + " -> " + graph.adjacence(i)(j) + "\n")
            }
        }
        writer.write("}")
        writer.close()
    }
    def chargerLabyrinthe(cheminFichier: String): Graph[Int] = {
        val reader = new BufferedReader(new FileReader(cheminFichier))
        val nbNodes = reader.readLine.toInt
        println("nb Node : " + nbNodes)
        val premiereLigne = reader.readLine.toString
        val result = premiereLigne.split("\\s");
        val nomGraph = result(1)

        val graph = new Graph[Int](nomGraph, nbNodes)
        for(i <- 0 until nbNodes){
            val Array(key1, key2) = for(i <- reader.readLine split " -> ") yield i.toInt
            if(!graph.nodePresent(key1)) graph.addNode(key1, key1)
            if(!graph.nodePresent(key2)) graph.addNode(key2, key2)
            graph.addEdge(key1, key2, 1)
            graph.addEdge(key2, key1, 1)
        }
        graph
    }
}

object ClassMain {
    def main(args: Array[String]): Unit = {
        var generateur = new GenerateurLabyrinthe
        var graph = new Graph[Int]("graph1", 4)
        graph.addNode(0, 0)
        graph.addNode(1, 1)
        graph.addNode(2, 2)
        graph.addNode(3, 3)

        graph.addEdge(0, 1, 1)
        graph.addEdge(1, 2, 1)
        graph.addEdge(2, 0, 1)
        graph.addEdge(2, 3, 1)

        generateur.sauvegarderLabyrinthe(graph)
        val graphCharge = generateur.chargerLabyrinthe("ressources/graph1.dot")
        graphCharge.display
    }
}
