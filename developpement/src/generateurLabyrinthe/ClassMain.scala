package generateurLabyrinthe;

import org.newdawn.slick.AppGameContainer

object ClassMain {
	def main(args: Array[String])
	{			
		val windowGame = new WindowGame;
		val appgc = new AppGameContainer(windowGame);
		appgc.setDisplayMode(1366, 768, false);
		appgc.start();
	}
}
