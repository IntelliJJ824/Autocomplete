import java.util.Comparator;

public class BinarySearchDeluxe {

    //Returns the index of the first key in a[] that
    //equals the search key, or -1 if no such key
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        //Setup
        int midPos;
        int[] searchRange = {0, a.length - 1};

        //Iteration
        while (searchRange[1] - searchRange[0] > 1) {

            //Calculates midPos
            midPos = (int) Math.floor(searchRange[0] + ((searchRange[1] - searchRange[0]) / 2));

            //TODO
//            System.out.println("DEBUG: " + a[midPos] + " at " + midPos);
//            System.out.println(searchRange[0] + " to " + searchRange[1]);
//            System.out.println("Midpos = " + midPos);

            //Checks to see if midPos is correct pos
            if (comparator.compare(key, a[midPos]) == 0 && comparator.compare(key, a[midPos - 1]) == 1) {
                return midPos;
            }

            //Checks to see if higher search is needed
            if (comparator.compare(key, a[midPos]) == 1) {
                searchRange[0] = midPos;
            }

            //Checks to see if lower search is needed
            else if (comparator.compare(key, a[midPos]) == -1 || comparator.compare(key, a[midPos]) == 0) {
                searchRange[1] = midPos;
            }

        }

        //Lower bound (one off case) check for size 2 array
        if (searchRange[1] - searchRange[0] == 1
            && searchRange[0] == 0
            && comparator.compare(key, a[searchRange[0]]) == 0) {
            return searchRange[0];
        }

        //General check for size 2 array
        if(searchRange[1] - searchRange[0] == 1
            && comparator.compare(key,a[searchRange[0]])==1
            && comparator.compare(key,a[searchRange[1]]) ==0){
            return searchRange[1];
        }

        //Check for size 1 array
        if (searchRange[1] - searchRange[0] == 0 && comparator.compare(key, a[searchRange[0]]) == 0) {
            return searchRange[0];
        }

        return -1;
    }


    //Returns the index of the last key in a[] that
    //equals the search key, or -1 if no such key
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {

        //Setup
        int midPos;
        int[] searchRange = {0, a.length - 1};

        //Iteration
        while (searchRange[1] - searchRange[0] > 1) {

            //Calculates midPos
            midPos = (int) Math.ceil(searchRange[0] + ((searchRange[1] - searchRange[0]) / 2));

            //TODO
//            System.out.println(searchRange[0] + " to " + searchRange[1]);
//            System.out.println("Midpos = " + midPos);


            //Checks if midPos is correct pos
            if (comparator.compare(key, a[midPos]) == 0 && comparator.compare(key, a[midPos + 1]) == -1) {
                return midPos;
            }

            //Checks if lower search is needed
            if (comparator.compare(key, a[midPos]) == -1) {
                searchRange[1] = midPos;
            }

            //Checks if higher search is needed
            else if (comparator.compare(key, a[midPos]) == 1 || comparator.compare(key, a[midPos]) == 0) {
                searchRange[0] = midPos;
            }

        }

        //Upper bound (one off case) check for size 2 array
        if(searchRange[1] - searchRange[0] == 1
            && searchRange[1] == a.length-1
            && comparator.compare(key, a[searchRange[1]]) ==0){
            return searchRange[1];
        }

        //General check for size 2 array
        if ((searchRange[1] - searchRange[0] == 1
            && comparator.compare(key, a[searchRange[0]]) == 0
            && comparator.compare(key, a[searchRange[1]]) == -1)) {
            return searchRange[0];
        }

        //Check for size 1 array
        if (searchRange[1] - searchRange[0] == 0 && comparator.compare(key, a[searchRange[0]]) == 0) {
            return searchRange[0];
        }

        return -1;
    }

}
