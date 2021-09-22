import java.util.ArrayList;
import java.util.Scanner;

/**
 * Die Klasse enthält alle Funktionen, die die Talermenge in beliebigen Untermengen aufteilen
 */
public class Distributor {
    public Distributor(){
    }

    /**
     * Die Funktion nimmt die Eingabe vom Nutzer und erzeugt eine Talermenge
     * @return eine Talermenge, die vom Nutzer definiert wird.
     */
    public Talermenge talerInput() {
        Scanner sc = new Scanner (System.in);
        System.out.println("Please type in your list of numbers, type stop when you are done:");

        ArrayList<Integer> input = new ArrayList<>();
        while(sc.hasNextInt()){
            input.add(sc.nextInt());
        }

        Talermenge t = new Talermenge(input);
        System.out.println(t.getTalermenge().toString());
        return t;
    }

    /**
     * Die Funktion berechnet die Summe einer ArrayList
     * @param source die zu berechnende ArrayList
     * @return die Summe
     */
    public int sum(ArrayList<Integer> source){
        int sum = 0;
        for (Integer t: source){
            sum = sum+t;
        }
        return sum;
    }


    /**
     * Die Funktion überprüft, ob die Summe aller Werte einer Talermenge durch die Anzahl der von Nutzer gewünschten
     * Untermengen geteilt werden kann.
     * @param setNumber die Anzahl von Untermenge
     * @param t die Talermenge
     * @return einen boolean-wert, ob teilbar oder nicht
     */
    public boolean possibleAnswer(int setNumber, Talermenge t){
        int sum = sum(t.getTalermenge());
        if (sum % setNumber == 0){
            return true;
        } else{
            System.out.println("No possible answer");
            return false;
        }

    }

    /**
     * Die Funktion erzeugt beliebige viele leere Untermenge
     * @param setNumber die Anzahl von Untermenge
     * @return eine Arraylist von vielen leeren ArrayList des Typs int.
     */
    public ArrayList<ArrayList<Integer>> createSubset(int setNumber){
        ArrayList<ArrayList<Integer>> generalSet = new ArrayList<>();
            for (int i = 0; i < setNumber; i++) {
                ArrayList<Integer> subSet = new ArrayList<>();
                generalSet.add(subSet);
            }
            return generalSet;
    }

    /**
     * Die rekursive Funktion fügt eine Zahl von einer Talermenge zu den leeren ArrayList
     * @param t die zu teilende Talermenge
     * @param generalSet die ArrayList von Untermenge als Ergebnis
     * @param subSum die Summe, die eine Untermenge haben soll
     * @param generalSetIndex das Index von Untermengen der ArrayList generalSet
     */
    public void addingResult(Talermenge t, ArrayList<ArrayList<Integer>>generalSet, int subSum, int generalSetIndex){

        ArrayList<Integer> taler = t.getTalermenge();
        String talerBeforeAdding = taler.toString();

        int subSumBeforeAdding = subSum;

        for (int i = 0; i < taler.size(); i++){
            if (subSum >= taler.get(i) && !taler.isEmpty() && generalSetIndex < generalSet.size()){
                generalSet.get(generalSetIndex).add(taler.get(i));
                subSum = subSum - taler.get(i);
                taler.remove(taler.get(i));
                i--;
            }
        }

        subSum = subSumBeforeAdding;
        String talerAfterAdding = t.getTalermenge().toString();

        if (taler.isEmpty()){
            System.out.println("The result is:");
            //System.out.println((generalSet.toString()));
            for (ArrayList<Integer> result: generalSet){
                System.out.println(result.toString());
            }
            return;
        }

        if (talerAfterAdding.equals(talerBeforeAdding)){
            System.out.println("No possible answer");
            return;
        }

        addingResult(t, generalSet, subSum, generalSetIndex+1);
    }


    /**
     * Die Funktion nimmt die Eingabe von Nutzer als Talermenge und startet das Programm.
     */
    public void start(){

        Talermenge t = talerInput();

        Scanner sc = new Scanner(System.in);
        System.out.println("How many subsets do you want to be printed out");
        int setNumber = sc.nextInt();

        if (possibleAnswer(setNumber,t)){
            ArrayList<ArrayList<Integer>> generalSet = createSubset(setNumber);

            int sum = sum(t.getTalermenge());
            int subSum = sum/setNumber;

            addingResult(t, generalSet, subSum, 0);
        }
    }
}

