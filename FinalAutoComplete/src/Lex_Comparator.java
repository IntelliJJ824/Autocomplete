import java.util.Comparator;

public class Lex_Comparator implements Comparator<Term> {
    @Override
    public int compare(Term key, Term t) {
        //NOTE: FIRST TERM MUST BE KEY!!

        if (t.query.toLowerCase().startsWith(key.query.toLowerCase()) == true) {
            return 0;
        } else {
            int comparison = key.query.toLowerCase().compareTo(t.query.toLowerCase());
            if (comparison < 0) {
                return -1;
            }
            if (comparison > 0) {
                return 1;
            } else {
                System.out.println("Error in Lex_Comparitor");
                return -2;
            }
        }


    }
}
