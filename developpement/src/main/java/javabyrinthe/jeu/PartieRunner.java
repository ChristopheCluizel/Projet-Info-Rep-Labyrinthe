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
		for (JoueurInterface joueur : partie.getJoueurs())
		{
			try{
				System.out.println(joueur.getPseudo());
				if(joueur.getPseudo().equals(gagnant))
				{
					joueur.victoire();
					System.out.println(gagnant + " a vraiment GAGNE !!!");
				}
				else
					joueur.defaite();
			}catch(Exception e)
			{
				System.out.println("/!\\Fin de Partie, exception: " + e);
			}
		}
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
				if(!gagnant.isEmpty()) {
					lancerFinDePartie(gagnant);
					break;
				}
				for(JoueurInterface j : partie.getJoueurs())
					j.setPartie(partie);
			}catch(Exception e){
				System.out.println("/!\\PartieRunner : " + e);
			}
		}
		if(partie.getnbTour() >= partie.getnbTourMax())
			lancerFinDePartie("");
		System.out.println("Fin de partie" + partie);
	}
}
