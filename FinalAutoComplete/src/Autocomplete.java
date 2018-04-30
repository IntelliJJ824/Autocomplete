import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.ArrayList;

public class Autocomplete {


    //SearchType enumerator for choosing search type
    public enum SearchType {
        LEXR_SEARCH, LEX_SEARCH
    }

    //SearchType variable; static for access before instantiation
    public static SearchType searchType = SearchType.LEX_SEARCH;

    //Declares array of terms
    Term[] terms;


    //Instantiates empty term for method uses
    Term functionTerm = new Term(" ", 0);

    //Declares r for LexR_Comparator; static for access before instantiation
    public static int r;


    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        //Takes sorted list (as array) from main method and sets to Term[] terms in this class
        this.terms = terms;
    }


    // Returns all terms that start with the given
    // prefix, in descending order of weight
    public Term[] allMatches(String prefix) {

        //Creates search term to work with required arguments of first/last indexOf in BinarySearch
        Term searchTerm = new Term(prefix, 0);

        //Instantiates comparator (depending on searchtype) to search with
        Comparator<Term> comparator = new Lex_Comparator(); //Default
        if(searchType == SearchType.LEXR_SEARCH){
            comparator = new LexR_Comparator(r);
        }

        //Finds first and last indices and difference between them depending on search
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, comparator);
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, comparator);
        int difference = lastIndex - firstIndex;

        //Checks for no values
        if(firstIndex == -1 || lastIndex == -1){
            Term[] empty = new Term[0];
            return empty;
        }

        //Initialises new array for matching terms to be put in
        // +1, since 1 matching term would give array size 0 otherwise
        Term[] returnArray = new Term[difference + 1];

        //Fills array
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = terms[i + firstIndex];
        }

        //Converts filled array to list then sorts with weight comparator
        ArrayList<Term> returnArrayAsList = new ArrayList<>(Arrays.asList(returnArray));
        Collections.sort(returnArrayAsList, new Weight_Comparator());

        //Converts list back to array
        returnArray = new Term[returnArrayAsList.size()];
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = returnArrayAsList.get(i);
        }

        //Returns filled array
        return returnArray;
    }


    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        //Creates search term to work with required arguments of first/last indexOf in BinarySearch
        Term searchTerm = new Term(prefix, 0);

        //Instantiates comparator (depending on searchtype) to search with
        Comparator<Term> comparator = new Lex_Comparator(); //Default
        if(searchType == SearchType.LEXR_SEARCH){
            comparator = new LexR_Comparator(r);
        }

        //Finds first and last indices
        int firstIndex = BinarySearchDeluxe.firstIndexOf(terms, searchTerm, comparator);
        int lastIndex = BinarySearchDeluxe.lastIndexOf(terms, searchTerm, comparator);
        //TODO
//        System.out.println("firstIndex = " + firstIndex);
//        System.out.println("lastIndex = " + lastIndex);

        //Checks for no -1s
        if(firstIndex == -1 || lastIndex == -1){
            return 0;
        }

        //Returns difference + 1 (since if indices are same, would return 0 otherwise), or 0 if no matches
        int returnValue = (lastIndex - firstIndex) + 1;
        if (returnValue >= 0) {
            return returnValue;
        } else return 0;
    }
}

