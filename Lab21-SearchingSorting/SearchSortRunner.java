import java.util.Arrays;

public class SearchSortRunner{
    public static void main(String args[]){
        
        //Linear Search
        
        System.out.println("The following methods search through this array:");
        SearchSort search = new SearchSort(10);
        System.out.println(Arrays.toString(search.getNums()));
        int index = (int)(Math.random() * 10);
        System.out.println("The methods will search for this number:");
        System.out.println(search.getNumAtIndex(index));
        System.out.println();
        
        System.out.println("Linear Search:");
        System.out.println(search.getNumAtIndex(index) + " is at index " + search.linearSearch(search.getNumAtIndex(index)));
        
        System.out.println("Linear Search Recursive:");
        System.out.println(search.getNumAtIndex(index) + " is at index " + search.linearSearchRecur(search.getNumAtIndex(index)));
        System.out.println();
        System.out.println();
        
        //Binary Search
        
        search.sort();
        System.out.println("The following methods search through this array:");
        System.out.println(Arrays.toString(search.getNums()));
        index = (int)(Math.random() * 10);
        System.out.println("The methods will search for this number:");
        System.out.println(search.getNumAtIndex(index));
        System.out.println();
        
        System.out.println("Binary Search:");
        System.out.println(search.getNumAtIndex(index) + " is at index " + search.binarySearch(search.getNumAtIndex(index)));
        System.out.println("Binary Search Recursive:");
        System.out.println(search.getNumAtIndex(index) + " is at index " + search.binarySearchRecursive(search.getNumAtIndex(index)));
        
        System.out.println();
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        
        //Bubble Sort
        
        SearchSort bubbles = new SearchSort(10);
        System.out.println("Bubble Sort:");
        bubbles.bubbleSort(true);
        System.out.println();
        
        //Selection Sort
        
        SearchSort selection = new SearchSort(10);
        System.out.println("Selection Sort:");
        selection.selectionSort(true);
        System.out.println();
        
        //Insertion Sort
        
        SearchSort insertion = new SearchSort(10);
        System.out.println("Insertion Sort:");
        insertion.insertionSort(true);
        System.out.println();
        
        //Merge Sort
        
        SearchSort merge = new SearchSort(10);
        System.out.println("Merge Sort: ");
        System.out.println(Arrays.toString(merge.getNums()));
        merge.mergeSort(merge.getNums(),merge.getNums().length);
        System.out.println(Arrays.toString(merge.getNums()));
    }
}