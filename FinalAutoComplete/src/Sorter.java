import java.util.Comparator;

public class Sorter implements Comparator<Term>{

    @Override
    public int compare(Term t1, Term t2) {
        return t1.query.toLowerCase().compareTo(t2.query.toLowerCase());
    }
}
