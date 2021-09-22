import java.util.ArrayList;
import java.util.Collections;

/**
 * Die Klasse enthält alle Zahlen einer Talermenge, die werden durch ArrayList speichert
 */
public class Talermenge {
    private ArrayList<Integer> talermenge;

    /**
     * Die Konstruktor enthält eine ArrayList und sortiert die Liste von der Größste zu der Kleinste
     * @param talermenge die ArrayList, die alle Werte einer Talermenge enthält
     */
    public Talermenge(ArrayList<Integer> talermenge){
        this.talermenge = talermenge;
        Collections.sort(talermenge);
        Collections.reverse(talermenge);
    }

    /**
     * Getter für Talermenge
     * @return eine ArrayList von allen Werten
     */
    public ArrayList<Integer> getTalermenge(){
        return talermenge;
    }
}
