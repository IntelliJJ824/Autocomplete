import java.util.Comparator;

public class Weight_Comparator implements Comparator<Term> {

    public int compare(Term t1, Term t2) {

        return t2.weight.compareTo(t1.weight);

    }


}
