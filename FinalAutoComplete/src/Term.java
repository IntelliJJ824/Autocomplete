import java.lang.*;
import java.util.*;


// Term API
public class Term implements Comparable<Term> {

    public String query;
    public Long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight){
        this.query = query;
        this.weight = weight;
    }

    // Compares the two terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder(){
        Weight_Comparator comparator = new Weight_Comparator();
        return comparator;
    }

    // Compares the two terms in lexicographic order
    // but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r){
        LexR_Comparator comparator = new LexR_Comparator(r);
        return comparator;
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that){
        Lex_Comparator comparator = new Lex_Comparator();
        return comparator.compare(this,that);

    }

    // Returns a string representation of this term in the format:
    // the weight, followed by a tab, followed by the query.
    public String toString(){
        String returnString = this.weight + "\t" + this.query;
        return returnString;
    }
}

