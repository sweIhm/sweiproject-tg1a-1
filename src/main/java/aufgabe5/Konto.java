package aufgabe5;

public class Konto {

    private String bezeichnung;
    private GeldBetrag geldBetrag;



    public GeldBetrag saldo() {
        return geldBetrag;
    }

    public void einzahlen(GeldBetrag betrag) {
        this.geldBetrag.add(betrag);
    }
}
