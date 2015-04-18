package javabyrinthe.jeu;

public class PartieRunner implements  Runnable {
	Partie partie;

	public PartieRunner(Partie partie) {
		this.partie = partie;
	}

	/**
	 * Diplay the winner
	 * 
	 * @param gagnant the name of the winner player
	 */
	public void lancerFinDePartie(String gagnant) {
		System.out.println(gagnant + " a GAGNE !!!");
	}

	/**
	 * Run the turns of a game until the max of turns is reached or a player wins
	 * 
	 */
	public void run(){
		while(partie.getnbTour() < partie.getnbTourMax()) {
			System.out.println();
			System.out.println("Partie "+ partie + " en cours. Etat nombre de tours : "+ partie.getnbTour() + "/" + partie.getnbTourMax());
			try{
				String gagnant = partie.tourSuivant();
				System.out.println("gagnant: " + gagnant);
				if(!gagnant.isEmpty()) {
					lancerFinDePartie(gagnant);
					break;
				}
			}catch(Exception e){
				System.out.println("/!\\PartieRunner : " + e);
			}
		}
		System.out.println("Fin de partie" + partie);
	}
}
