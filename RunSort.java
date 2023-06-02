package ca.concordia.comp352;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class RunSort {

    static int reverseCounter, structuralCompare, length3, swapCounter,insertionCompare = 0 ;

    public static void main(String[] args) {

        Scanner inputStream;
        String numberSequence = null;
        String [] numb;
        int [] arr_of_unordered_numbers;

        try {
            String path = args[0];

            inputStream = new Scanner(new FileInputStream(path));

            while(inputStream.hasNextLine())
                numberSequence = inputStream.nextLine();

            System.out.print(numberSequence);


            numb = numberSequence.split(" ");
            arr_of_unordered_numbers = new int [numb.length];


            for (int i =0; i < numb.length; i++ )
                arr_of_unordered_numbers [i] = Integer.parseInt(numb[i]) ;

            structuringPass(arr_of_unordered_numbers);

            System.out.println("\nWe sorted in INCR order");
            System.out.println("We counted " + length3 + " DEC run of length 3");
            System.out.println("We performed " + reverseCounter + " reversals of runs in INCR order");
            System.out.println("We performed " + structuralCompare + " compares during structuring");
            insertionSort(arr_of_unordered_numbers);
            System.out.println("We performed " + (insertionCompare + structuralCompare) + " compares overall");
            System.out.println("We performed " + swapCounter + " swaps overall");


            for(int n: arr_of_unordered_numbers)
                System.out.print(n + " ");

        }catch (FileNotFoundException e) {
            System.out.print("The file could not be found");
        }
    }

    // This method reverses the ascending run
    // The endIndex represents the index of the term breaking the run
    public static void reverseAscending(int [] arr, int startIndex, int endIndex){
        reverseCounter++;

            for (int current = startIndex, nextValue = endIndex -1 ; current < nextValue; current++, nextValue--) {
                if (arr[current] < arr[nextValue]){
                    swapCounter++;
                    int temp1 = arr[current];
                    arr[current] = arr[nextValue];
                    arr[nextValue] = temp1;
                }
            }
    }

    private static void insertionSort(int[] array) {

        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i-1;

            while (j>= 0 && array[j] > temp){
                swapCounter++;
                array[j+1] = array[j];
                j--;
                insertionCompare++;

                // When j is negative that means the edge of the array was reached hence that comparison would be useless and should be discarded
                if(j==-1)
                    insertionCompare--;
            }
            insertionCompare++;
            array[j+1] = temp;
        }
    }


    public static void structuringPass(int [] array){
        int skip;
        int max = 0;
        for (int i = 0; i < array.length-1; i+=(skip)) {
            int curr = i;
            int next = i+1;

            structuralCompare++;

            if(array[next] >= array[curr]){
                while (array[next] > array[curr]){
                    next++;
                    curr++;
                    structuralCompare++;
                }
                reverseAscending(array, i, next);
            }

            else {
                while (array[next] < array[curr]){
                    next++;
                    curr++;
                    structuralCompare++;
                }
                if(next - i == 3 && array[i] > array[curr])
                    length3++;
            }
            skip = next - i;
        }
    }

}
