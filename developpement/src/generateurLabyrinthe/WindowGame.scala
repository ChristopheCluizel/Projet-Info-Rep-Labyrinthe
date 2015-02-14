package generateurLabyrinthe;

import org.newdawn.slick.BasicGame
import org.newdawn.slick.Color
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.Input
import org.newdawn.slick.SlickException
import org.newdawn.slick.geom.Line
import graph._;
import org.newdawn.slick.geom.Rectangle

class  WindowGame extends BasicGame("Javabyrinthe") {
	var container: GameContainer = _
	var generateur = new GenerateurLabyrinthe
	var tailleLabyrinthe = 50
	val (graphe, positionDepart, positionArrivee) = generateur.genererLabyrinthe(tailleLabyrinthe, tailleLabyrinthe)
//	val graphe = generateur.chargerLabyrinthe("ressources/graph2.dot")
//	println("=========== Graphe final ============")
//	graphe.display
//	generateur.sauvegarderLabyrinthe(graphe)

	override def init(gc: GameContainer) {
		this.container = gc;
	}

	override def update(gc: GameContainer, i: Int) {

	}
	
	override def render(gc: GameContainer, g: Graphics) {
		gc.setShowFPS(false);	// permet de ne pas afficher les FPS
		g.setBackground(Color.white)
		val tailleCase = 10
		var x = 0
		var y = 0
		var offsetBordFenetre = 10
		
		graphe.adjacence.keys.foreach{key =>
			x = generateur.keyToCoordinates(key, tailleLabyrinthe).x
			y = generateur.keyToCoordinates(key, tailleLabyrinthe).y
			g.setColor(Color.black)
			g.draw(new Rectangle(x * tailleCase + offsetBordFenetre, y * tailleCase + offsetBordFenetre, tailleCase, tailleCase))
			g.setColor(Color.red)
			g.fillRect(positionDepart.x * tailleCase  + offsetBordFenetre, positionDepart.y * tailleCase + offsetBordFenetre, tailleCase, tailleCase)
			g.setColor(Color.blue)
			g.fillRect(positionArrivee.x * tailleCase  + offsetBordFenetre, positionArrivee.y * tailleCase + offsetBordFenetre, tailleCase, tailleCase)
		}
		
		graphe.adjacence.keys.foreach{key =>
		  	x = generateur.keyToCoordinates(key, tailleLabyrinthe).x
			y = generateur.keyToCoordinates(key, tailleLabyrinthe).y
  			val successeur = graphe.getSuccessors(key)
			for(keySuccesseur <- successeur) {
				val x_successeur = generateur.keyToCoordinates(keySuccesseur, tailleLabyrinthe).x
				val y_successeur = generateur.keyToCoordinates(keySuccesseur, tailleLabyrinthe).y
				g.setColor(Color.white)
				if(x_successeur < x) g.draw(new Line(x * tailleCase + offsetBordFenetre, y * tailleCase + offsetBordFenetre, x * tailleCase + offsetBordFenetre, y * tailleCase + offsetBordFenetre + tailleCase - 1))
				if(x_successeur > x) g.draw(new Line(x_successeur * tailleCase + offsetBordFenetre, y_successeur * tailleCase + offsetBordFenetre, x_successeur * tailleCase + offsetBordFenetre, y_successeur * tailleCase + offsetBordFenetre + tailleCase - 1))
				if(y_successeur > y) g.draw(new Line(x_successeur * tailleCase + offsetBordFenetre, y_successeur * tailleCase + offsetBordFenetre, x_successeur * tailleCase + offsetBordFenetre + tailleCase - 1, y_successeur * tailleCase + offsetBordFenetre))
				if(y_successeur < y) g.draw(new Line(x * tailleCase + offsetBordFenetre, y * tailleCase + offsetBordFenetre, x * tailleCase + offsetBordFenetre + tailleCase - 1, y * tailleCase + offsetBordFenetre))
			}
		}
		Thread.sleep(40)
	}
	
    override def keyReleased(key: Int, c: Char) {
		
        if (Input.KEY_ESCAPE == key) {
            this.container.exit();
        }
    }
}