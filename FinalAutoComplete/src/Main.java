import java.io.*;
import java.lang.*;
import java.util.*;

/**
 * This is for testing the binary search and link to GUI.
 * author sam and Zhaonan
 */
public class Main {
	String userInput = "";
    Term[] outputArray;
    String fileLocation;
    String result;
    public Main(String userInput, int option, String fileLocation) {
    	this.fileLocation = fileLocation;
    	this.userInput = userInput;
    	if (userInput.equals("")) {
            System.out.println("You forget the input");
        }
    	else {
            try (BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in))) {
                String inFile = " ";
                inFile = fileLocation;

                int choice2 = option;

                switch (choice2) {
                    case 1:
                        Autocomplete.searchType = Autocomplete.SearchType.LEX_SEARCH;
                        Autocomplete.r = -1;
                        break;

                    case 2:
                        try {
                            Autocomplete.searchType = Autocomplete.SearchType.LEXR_SEARCH;
                            Autocomplete.r = Integer.parseInt(runGUI.getrKey());
                        } catch (NumberFormatException e) {
                            System.out.println("Unexpected Input, Please input the correct k number");
                            Autocomplete.searchType = Autocomplete.SearchType.LEXR_SEARCH;
                            Autocomplete.r = 2;
                        } finally {
                            break;
                        }

                    default:
                        System.out.println("Invalid choice: Program terminated");
                        return; //Ends program

                }


                //Initialises List for content of read file to go into (odd entries are weights, even are queries)
                ArrayList<String> inputList = new ArrayList<>();

                //Takes input 1 line at a time, then splits it, then puts it into inputList
                try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
                    //String for each line to be read into
                    String thisLine;
                    //Array size 2 for weight and query
                    String[] WAndQ;
                    //Ignores number of words (first line)
                    thisLine = reader.readLine();
                    //Sets up iteration
                    thisLine = reader.readLine();

                    //Loops through lines
                    while (thisLine != null) {
                        //Splits
                        WAndQ = thisLine.split("\\s+", 2);
                        WAndQ = WAndQ[1].split("\\s", 2);
                        //Adds to inputList
                        inputList.add(WAndQ[0]);
                        inputList.add(WAndQ[1]);
                        //Reads line
                        thisLine = reader.readLine();
                    }

                } catch (IOException e) {
                    System.out.println("IO Exception in MainTest: " + e.getMessage());
                }

                //Initialises list of terms for data from inputList to be entered
                ArrayList<Term> termList = new ArrayList<>();

                //Turns inputList into termList
                for (int i = 0; i < inputList.size(); i += 2) {
                    //Gets weight
                    //System.out.println(inputList.get(i));
                    long weight = Long.parseLong(inputList.get(i));
                    //Gets query
                    String query = inputList.get(i + 1);
                    //Inputs a new term
                    termList.add(new Term(query, weight));
                }

                //TODO: Remove all the System.out.printlns

                //Initialises a comparator to sort list
                Comparator<Term> sorter = new Sorter();
                Collections.sort(termList, sorter);

                //Makes termList into an array for Autocomplete to handle
                Term[] termArray = new Term[termList.size()];
                for (int i = 0; i < termArray.length; i++) {
                    termArray[i] = termList.get(i);
                }


                //Instantiates a new Autocomplete
                Autocomplete autocompleter = new Autocomplete(termArray);

                //Takes user query string and returns number and value of search results from autocomplete

                //Declares input string
                String input = userInput;

                //Takes user input and prints out autocomplete details, until quit
                while (!input.equals("Quit")) {
                    //Reads input
                    //Prints out number of matches
                    result = "Number of results: " + autocompleter.numberOfMatches(input);
                    System.out.println("Number of results: " + autocompleter.numberOfMatches(input));
                    //Prints out matches
                    outputArray = autocompleter.allMatches(input);
                    try {
                        if (outputArray[0] != null) {
                            for (int i = 0; i < outputArray.length; i++) {
                                System.out.println(outputArray[i]);
                                if (i == (outputArray.length - 1)) {
                                    input = "Quit";
                                }
                            }
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Words no found!");
                        input = "Quit";
                    }
                }

            } catch (IOException e) {
                System.out.println("IO Exception in MainTest: ");
            }
        }
  }
  public Term[] getArray(){
    	return outputArray;
  }
  
  public void getUserInput(){
  	  System.out.println(userInput);
  }

  public String getResult() {
      return result;
  }
  
  
}