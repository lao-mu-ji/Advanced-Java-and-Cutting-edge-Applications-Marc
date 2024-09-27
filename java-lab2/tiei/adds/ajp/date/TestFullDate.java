//
// Advanced Java Programming
// TIEI - Fall 2024
//
package tiei.ajp.date;

import java.util.*;

/**
 * A class to test interactively the class FullDate
 */
public class TestFullDate {

    /**
     * The main function
     */
    public static void main(String[] args) throws BadDateException {
        Scanner input = new Scanner(System.in);
        System.out.println("Day counter (FullDate)\n");
        do {
            FullDate date1 = getDate(input,"Enter first date (year month day): ");
            FullDate date2 = getDate(input,"Enter second date (year month day): ");
            System.out.println("There are " + date1.daysToDate(date2) + " days between " + date1 + " " + date2 + "\n");
        } while ( more(input) );
    }


    // prompt the user with a suitable message, read a year, a month
    // and a day, and return the corresponding FullDate object
    private static FullDate getDate(Scanner input, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return new FullDate(input.nextInt(),input.nextInt(),input.nextInt());
            }
            catch ( BadDateException bde ) {
                System.out.println(bde.getMessage());
            }
        }
    }

    // prompt the user and read the answer
    private static boolean more(Scanner input) {
        while ( true ) {
            System.out.print("More? ");
            String answer = input.next().trim().toLowerCase();
            if ( answer.equals("yes") )
                return true;
            else if ( answer.equals("no") )
                return false;
            else
                System.out.println("(Please enter 'yes' or 'no') ");
        }
    }
}
