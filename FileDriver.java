import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.*;

public class FileDriver {


    static void menu() {
        System.out.println("Enter choice");
        System.out.println("   1.  Write to file");
        System.out.println("   2.  Display file");
        System.out.println("   3.  Exit");
    }
    public static void main(String[] args) throws Exception {
        int choice=0;
        String input;
        Scanner s = new Scanner(System.in);

        while (choice != 3) {
            menu();
            System.out.print("Enter choice ");
            choice = s.nextInt();
            System.out.println("");
            if(choice == 1) {
                File fout = new File("proj4.txt");
                FileOutputStream fos = new FileOutputStream(fout, true);
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));

                System.out.println("Enter something to save to your file.");
                input = s.nextLine();
                input = s.nextLine();
                bw.write(input);
                bw.newLine();
                bw.close();
                System.out.println("");
            }
            else if (choice == 2) {
                System.out.println("Importing the data from your file:");
                System.out.println("");
                BufferedReader reader = new BufferedReader(new FileReader("proj4.txt"));
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
                reader.close();
                System.out.println("");
            }
            else if(choice == 3) {
                System.out.println("Goodbye.");
                break;
            }
            else {
                System.out.println("Please enter a valid option.");
            }
        }
    }

}
