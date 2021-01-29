package ajlabsession09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BabyNamesIO {

    private static final String boys[][] = new String[10][1000];
    private static final String girls[][] = new String[10][1000];
    public String username;

    public static void main(String[] args) {
        readNames();

        //print a sample of data saved in array
        for (int i = 0; i < 5; i++) {
            int year = 2001 + i;
            System.out.println("Top 5 names in year " + year);
            for (int j = 0; j < 5; j++) {
                System.out.print("[" + i + ", " + j + "]: ");
                System.out.println("boy name: " + boys[i][j] + "\t-\tgirl name: " + girls[i][j]);
            }
        }

        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the year: ");
            int year = Integer.parseInt(input.nextLine());
            System.out.println("Enter the gender: ");
            String gender = input.nextLine();
            System.out.println("Enter the name: ");
            String name = input.nextLine();

            if (year < 2001 || year > 2010) {
                System.out.println("Wrong Year Input!");
                System.exit(0);
            }

            int ret = SearchArray(year, gender, name);
            if (ret != -1) {
                System.out.println(name + " is ranked #" + (ret + 1) + " in year " + year);
            } else {
                System.out.println("the name " + name + " is not ranked in year " + year);
            }
        }
    }

    public static void readNames() {

        for (int i = 0; i < 10; i++) {
            int year = 2001 + i;
            String fName = TextIO.getFileName(year);

            try {
                Scanner fscan = new Scanner(new File(fName));

                int j = 0;

                while (fscan.hasNext()) {
                    fscan.nextInt();
                    boys[i][j] = fscan.next();
                    fscan.next();
                    girls[i][j] = fscan.next();
                    fscan.next();
                    j++;
                }
                fscan.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static int SearchArray(int year, String gender, String name) {
        int row = year - 2001;
        String[][] arrayToSearchIn = null;

        if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("m")) {
            arrayToSearchIn = boys;

        } else if (gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("f")) {
            arrayToSearchIn = girls;

        } else {
            System.out.println("Wrong Gender Input");
            System.exit(0);
        }

        for (int i = 0; i < 1000; i++) {
            if (arrayToSearchIn[row][i].equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
}
