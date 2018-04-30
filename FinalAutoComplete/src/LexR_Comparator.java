import java.util.Comparator;

public class LexR_Comparator implements Comparator<Term> {

    //Declares r for scope
    int r;

    //Constructor (requires r)
    LexR_Comparator(int r){
        this.r = r;
    }


    @Override
    public int compare(Term key, Term t) {
        //NOTE: FIRST TERM MUST BE KEY!!

        //Declares prefix for scope
        String prefix;


        //Crops key.query to r chars for this specific search, provided key.query is longer than r
        if(key.query.length() > r){
            prefix = key.query.substring(0,r).toLowerCase();
        } else prefix = key.query.toLowerCase();


        if (t.query.toLowerCase().startsWith(prefix) == true) {
            return 0;
        } else {
            int comparison = prefix.compareTo(t.query.toLowerCase());
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

