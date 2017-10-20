package aufgabe5;

import java.util.List;
import java.util.ArrayList;

public class Konto {

    private String bezeichnung;
    private GeldBetrag geldBetrag = new GeldBetrag();
    private List<Kunde> zeichnungsberechtigte = new ArrayList<Kunde>();

    public Konto(List<Kunde> zeichnungsberechtigte, String bezeichnung) {
        if (zeichnungsberechtigte.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.zeichnungsberechtigte.addAll(zeichnungsberechtigte);
        this.bezeichnung = bezeichnung;
    }

    public GeldBetrag saldo() {
        return geldBetrag;
    }

    public void einzahlen(GeldBetrag betrag) {
        this.geldBetrag.add(betrag);
    }
}
