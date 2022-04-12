import java.util.Scanner;
import java.io.*;

public class HeapDriver
{
    public static void main(String[] args) throws IOException
    {
        char userResponse;
        Scanner keyboard = new Scanner(System.in);

        do {
            // ask if the user wants to read a sequence of integers from an input file.
            System.out.println("Would you like to read a sequence of integers from an input file? (Y/N)");
            userResponse = keyboard.next().charAt(0);
            userResponse = Character.toUpperCase(userResponse);

            if ('Y' == userResponse)
            {
                // try to read a sequence of integers from "data.txt".
                int index = 0;
                String givenFile = "data.txt";
                File myFile = new File(givenFile);
                int[] intArray = new int[100];


                // first, check to see if the given file exists.
                if (myFile.exists())
                {
                    Scanner inputFile = new Scanner(myFile);

                    // if the file exists, read a sequence of integers from the given file.
                    System.out.println("Reading a sequence of integers from " + givenFile + "...");
                    while (inputFile.hasNext())
                    {
                        intArray[index] = inputFile.nextInt();
                    }
                    // when reading the given file is finished, close the file.
                    inputFile.close();
                }
                else
                {
                    System.out.println("Failed to open " + givenFile + "...");
                    break;
                }

                // perform heap operations and write the results into an output file.
                performHeapOperations(intArray);

            }
            else if ('N' == userResponse)
            {
                System.out.println("Terminating the program... Goodbye!");
                break;
            }
            else
            {
                System.out.println("Please enter a valid response...");
            }
        } while ('N' != userResponse);

    } // end of "main"

    private static void performHeapOperations(int[] array) throws IOException
    {
        int index = 0;
        String fileName = "outputFile.txt";
        FileWriter fwriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fwriter);

        // create a max-heap using the sequential insertions.
        MaxHeap<Integer> sequentialHeap = new MaxHeap<>();

        // output the first 10 integers of the array into the output file.
        outputFile.print("Heap built using sequential insertions: ");
        for (; index < 10; index++)
        {
            outputFile.print(array[index] + ",");
        }
        System.out.println("...");

        // output the number of swaps performed into the output file.
        outputFile.println("Number of swaps in the heap creation: " + sequentialHeap.getNumSwaps());

        // perform 10 removals on the heap.
        for (index = 0; index < 10; index++)
        {
            sequentialHeap.removeMax();
        }

        // output the first 10 integers of the resulting array into the output file.
        outputFile.print("Heap after 10 removals: ");
        for (int i = 0; i < 10; i++)
        {
            outputFile.print(array[i] + ",");
        }
        System.out.println("...\n");

        // create a max-heap using the optimal method.
        MaxHeap<Integer> optimalHeap = new MaxHeap<>();

        // output the first 10 integers of the array into the output file.
        outputFile.print("Heap built using optimal method: ");
        for (int i = 0; i < 10; i++)
        {
            outputFile.print(array[i] + ",");
        }
        System.out.println("...");

        // output the number of swaps performed into the output file.
        outputFile.println("Number of swaps in the heap creation: " + optimalHeap.getNumSwaps());

        // perform 10 removals on the heap.
        for (index = 0; index < 10; index++)
        {
            optimalHeap.removeMax();
        }

        // output the first 10 integers of the resulting array into the output file.
        outputFile.print("Heap after 10 removals: ");
        for (int i = 0; i < 10; i++)
        {
            outputFile.print(array[i] + ",");
        }
        System.out.println("...");

    } // end of "performHeapOperations"

} // end of "HeapDriver" class
