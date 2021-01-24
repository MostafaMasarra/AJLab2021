/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajlabsession9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author bader-aul
 *
 * Program Description:
 *
 * ======================
 *
 * Prompts user to enter year, gender, and name => to display the ranking of the
 * name for the given year
 *
 * Divide code into 4 parts: 1. Use Scanner to get input from user 1. Read data
 * from text file 2. Lookup given information (year, gender, name) 3. Return
 * ranking
 */
public class TextIO {

    private static int year;
    private static String gender;
    private static String name;
    private static String filename;
    private static int ranking;

    //function to get the appropriate file name based on given year
    public static String getFileName(int year) {
        return "files/Babynamesranking" + year + ".txt";
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //define scanner to get input from user
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Baby Name Ranking File Base");

        System.out.print("Please enter a year between 2001 and 2010 to search in: ");
        year = scan.nextInt();

        //adding a while loop to keep checking the year
        while (year > 2010 || year < 2001) {
            System.out.print("Please enter a year between 2001 and 2010 to search in: ");
            year = scan.nextInt();
        }

        //to go to the next line
        scan.nextLine();

        System.out.print("Please choose a gender. Type M for male and F for female: ");

        //applying toLowerCase to take both lower and upper cases into consideration
        gender = scan.next();

        while (!gender.equalsIgnoreCase("m") && !gender.equalsIgnoreCase("f")) {
            System.out.print("Please choose a gender. Type M for male and F for female: ");
            gender = scan.next().toLowerCase();
        }

        scan.nextLine();

        System.out.print("Please enter a baby name: ");
        name = scan.next();

        //closing the input stream
        scan.close();

        //get file name based on given year
        filename = getFileName(year);

        try {
            File file = new File(filename);
            //read from defined file
            Scanner fileScan = new Scanner(file);

            if (gender.equalsIgnoreCase("m")) {
                //next line of code makes sure the program doesn't throw an exception when it reaches the end of the file
                while (fileScan.hasNext()) {
                    ranking = fileScan.nextInt();
                    if (fileScan.next().equalsIgnoreCase(name)) {
                        System.out.println(name + " is ranked #" + ranking + " in year " + year);
                        //this to break the loop
                        break;
                        //or this to exit the program
                        //System.exit(0);
                    } else {
                        //move forward number of babies
                        //fileScan.nextInt();
                        //move forward female baby name
                        //fileScan.next();
                        //move forward number of babies
                        //fileScan.nextInt();

                        //nextLine replaces the above 3 methods (check documentation)
                        fileScan.nextLine();
                    }
                }

                //file has reached end with loop not broken (or program not exited)
                //so here we implement any code that should be done after the search is complete
                System.out.println(name + " was not found in the " + year + " rankings.");
                fileScan.close();
            } else {
                while (fileScan.hasNext()) {
                    ranking = fileScan.nextInt();
                    //skip over first two tokens relating to male
                    fileScan.next();
                    fileScan.next();
                    if (fileScan.next().equalsIgnoreCase(name)) {
                        System.out.println(name + " is ranked #" + ranking + " in year " + year);
                        //to quit app when value is found
                        System.exit(0);
                    } else {
                        fileScan.nextLine();
                    }
                }
                System.out.println(name + " was not found in the " + year + " rankings.");
                fileScan.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Oops. File was not found");
            e.printStackTrace();
        }
    }

}
