import java.util.Scanner;
import java.io.*;

/** Driver class to create the heaps, preform the operations, and push the results to the output file. */
public class HeapDriver
{
    /** Main Method to run the program and test the preformHeapOperations method.
     @throws IOException for any issues with I/O usage. */
    public static void main(String[] args) throws IOException
    {
        char userResponse;
        Scanner keyboard = new Scanner(System.in);

        do {
            // ask if the user wants to read a sequence of integers from an input file.
            System.out.println("Would you like to read a sequence of integers from input files? (Y/N)");
            userResponse = keyboard.next().charAt(0);
            userResponse = Character.toUpperCase(userResponse);

            if ('Y' == userResponse)
            {
                // try to read a sequence of integers from "data_sorted.txt".
                int index = 0;
                String givenFile = "data_sorted.txt";
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
                        index++;
                    }
                    // when reading the given file is finished, close the file.
                    inputFile.close();
                }
                // perform heap operations on sorted data
                // and write the results into an output file.
                performHeapOperations(intArray);

                // try to read a sequence of integers from "data_random.txt".
                index = 0;
                givenFile = "data_random.txt";
                myFile = new File(givenFile);
                intArray = new int[100];

                // first, check to see if the given file exists.
                if (myFile.exists())
                {
                    Scanner inputFile = new Scanner(myFile);

                    // if the file exists, read a sequence of integers from the given file.
                    System.out.println("Reading a sequence of integers from " + givenFile + "...");
                    while (inputFile.hasNext())
                    {
                        intArray[index] = inputFile.nextInt();
                        index++;
                    }
                    // when reading the given file is finished, close the file.
                    inputFile.close();
                }

                // perform heap operations on random data
                // and write the results into an output file.
                performHeapOperations(intArray);
                break;

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

    /** Preforms heap operations using the given data sets and prints the results in the output file.
      @throws IOException for any issues with I/O usage */
    public static void performHeapOperations(int[] array) throws IOException
    {
        int index = 0;
        String fileName = "outputFile.txt";
        FileWriter fwriter = new FileWriter(fileName, true);
        PrintWriter outputFile = new PrintWriter(fwriter);

        // create a max-heap using the sequential insertions.
        MaxHeap sequentialHeap = new MaxHeap(array.length);
        for (; index < array.length; index++)
        {
            sequentialHeap.add(array[index]);
        }
        sequentialHeap.reheap(1);

        // output the first 10 integers of the array into the output file.
        outputFile.print("Heap built using sequential insertions: ");
        for (index = 0; index < 10; index++)
        {
            outputFile.print(sequentialHeap.getHeap()[index + 1] + ",");
        }
        outputFile.println("...");

        // output the number of swaps performed into the output file.
        outputFile.println("Number of swaps in the heap creation: " + sequentialHeap.getNumSwaps());

        // perform 10 removals on the heap.
        for (index = 0; index < 10; index++)
        {
            sequentialHeap.removeMax();
        }

        // output the first 10 integers of the resulting array into the output file.
        outputFile.print("Heap after 10 removals: ");
        for (index = 0; index < 10; index++)
        {
            outputFile.print(sequentialHeap.getHeap()[index + 1] + ",");
        }
        outputFile.println("...\n");

        // create a max-heap using the optimal method.
        MaxHeap optimalHeap = new MaxHeap(array);

        // output the first 10 integers of the array into the output file.
        outputFile.print("Heap built using optimal method: ");
        for (index = 0; index < 10; index++)
        {
            outputFile.print(optimalHeap.getHeap()[index + 1] + ",");
        }
        outputFile.println("...");

        // output the number of swaps performed into the output file.
        outputFile.println("Number of swaps in the heap creation: " + optimalHeap.getNumSwaps());

        // perform 10 removals on the heap.
        for (index = 0; index < 10; index++)
        {
            optimalHeap.removeMax();
        }

        // output the first 10 integers of the resulting array into the output file.
        outputFile.print("Heap after 10 removals: ");
        for (index = 0; index < 10; index++)
        {
            outputFile.print(optimalHeap.getHeap()[index + 1] + ",");
        }
        outputFile.println("...\n");

        // close the output file.
        outputFile.close();

    } // end of "performHeapOperations"

} // end of "HeapDriver" class
