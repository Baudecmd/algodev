package commun;

public interface Partie {
    void initialiser();
    static Joueur selectionJoueur(String nom){
        return new Joueur(nom);
    }
    void partieGagnee();
    void partiePerdu();
    void ajoutClassement(Joueur j);

}
