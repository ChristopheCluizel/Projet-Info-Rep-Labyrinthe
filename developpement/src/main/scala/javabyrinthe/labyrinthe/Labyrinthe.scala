package javabyrinthe.labyrinthe

import javabyrinthe.graph.Graph

class Coordonnees(var x: Int, var y: Int) {
    override def toString: String = "(" + x + ", " + y + ")"
}

class Labyrinthe(val graph: Graph[Int], val depart: Coordonnees, val arrivee: Coordonnees) {

}
