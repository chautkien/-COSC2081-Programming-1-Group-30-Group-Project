package group_assignment;

// Import necessary libraries
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        running();
    }

    public static void running() {

        // Create variables
        String sd = "";
        String ed = "";
        String area;
        String finish;
        int timeChoice;
        int d, w;
        int groupOption;
        int numOfGroup = 0;
        int numOfDay;
        int metric;
        int resultType;
        int displayOption;

        // Create objects
        Scanner input = new Scanner(System.in);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();

        //AREA SELECTION
        System.out.println("\n===============================================================================================");
        System.out.print("\nEnter region/country: ");
        area = input.next();

        //TIME RANGE SELECTION
        System.out.println("\n===============================================================================================");
        System.out.println("\nEnter time range. There are 3 ways of specifying time range:");
        System.out.println("1. A pair of start date and end date (inclusive) (e.g., 1/1/2021 and 8/1/2021)");
        System.out.println("2. A number of days or weeks from a particular date (e.g., 2 days from 1/20/2021 means there are 3 days 1/20/2021, 1/21/2021, and 1/22/2021)");
        System.out.println("3. A number of days or weeks to a particular date (e.g., 1 week to 1/8/2021 means there are 8 days from 1/1/2021 to 1/8/2021)");
        System.out.print("Enter your choice: ");
        timeChoice = input.nextInt();

        while (timeChoice != 1 && timeChoice != 2 && timeChoice != 3){
            System.out.print("Invalid choice. Please enter again!: ");
            timeChoice = input.nextInt();
        }

        //TIME RANGE CONDITIONS
        if (timeChoice == 1) {
            System.out.println("\n===============================================================================================");
            System.out.print("\nEnter start date (MM/dd/yyyy): ");
            sd = input.next();

            System.out.print("Enter end date (MM/dd/yyyy): ");
            ed = input.next();
        }

        if (timeChoice == 2) {
            System.out.println("\n===============================================================================================");
            System.out.print("\nEnter start date (MM/dd/yyyy): ");
            sd = input.next();

            System.out.print("Enter number of days: ");
            d = input.nextInt();
            System.out.print("Enter number of weeks: ");
            w = input.nextInt();

            try {
                c.setTime(df.parse(sd));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DAY_OF_MONTH, w * 7 + d);
            ed = df.format(c.getTime());
        }

        if (timeChoice == 3) {
            System.out.println("\n===============================================================================================");
            System.out.print("\nEnter end date (MM/dd/yyyy): ");
            ed = input.next();

            System.out.print("Enter number of days: ");
            d = input.nextInt();
            System.out.print("Enter number of weeks: ");
            w = input.nextInt();

            try {
                c.setTime(df.parse(ed));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            c.add(Calendar.DAY_OF_MONTH, -(w * 7 + d));
            sd = df.format(c.getTime());
        }

        //GET DATA FROM TIME RANGE
        Data d1 = new Data(area, sd, ed);
        d1.getData();

        //SELECT GROUPING OPTIONS
        System.out.println("\n===============================================================================================");
        System.out.println("\nSelect grouping options:");
        System.out.println("1. No groups");
        System.out.println("2. Grouping by a number of groups");
        System.out.println("3. Grouping by a number of days");
        System.out.print("Enter your choice: ");
        groupOption = input.nextInt();

        while (groupOption != 1 && groupOption != 2 && groupOption != 3){
            System.out.print("Invalid grouping choice. Please enter again!: ");
            groupOption = input.nextInt();
        }

        //CONDITIONS FOR GROUP
        if(groupOption == 1){
            numOfGroup = d1.getDataSize();
        }

        if(groupOption == 2){
            System.out.print("\nEnter number of groups: ");
            numOfGroup = input.nextInt();
        }

        if(groupOption == 3){
            System.out.print("Enter number of days: ");
            numOfDay = input.nextInt();

            while (d1.getDataSize() % numOfDay != 0){
                System.out.print("Invalid number of days, please enter again: ");
                numOfDay = input.nextInt();
            }
            numOfGroup = d1.getDataSize() / numOfDay;
        }

        //SELECT METRIC
        System.out.println("\n===============================================================================================");
        System.out.println("\nSelect your metric:");
        System.out.println("1. Positive Cases");
        System.out.println("2. Deaths");
        System.out.println("3. People Vaccinated");
        System.out.print("Enter your choice: ");
        metric = input.nextInt();

        //METRIC CONDITION
        while (metric <= 0 || metric > 3){
            System.out.print("\nInvalid metric, please enter again: ");
            metric = input.nextInt();
        }

        //SELECT RESULT TYPE
        System.out.println("\n===============================================================================================");
        System.out.println("\nSelect your result type:");
        System.out.println("1. New Total: total new cases/new deaths/new vaccinated people in a group");
        System.out.println("2. Up To: total cases/deaths/vaccinated from the beginning up to the last date of a group");
        System.out.print("Enter your choice: ");
        resultType = input.nextInt();

        //RESULT TYPE CONDITION
        while (resultType <= 0 || resultType > 2){
            System.out.print("\nInvalid type, please enter again: ");
            resultType = input.nextInt();
        }

        //Create summary object
        Summary sum = new Summary(d1);
        sum.summarize(numOfGroup, metric, resultType);

        //SELECT DISPLAY OPTION
        System.out.println("\n===============================================================================================");
        System.out.println("\nSelect your display option:");
        System.out.println("1. Tabular Display");
        System.out.println("2. Chart Display");
        System.out.print("Enter your choice: ");
        displayOption = input.nextInt();

        //DISPLAY OPTION CONDITION
        while (displayOption <= 0 || displayOption > 2){
            System.out.print("Invalid option, please enter again: ");
            displayOption = input.nextInt();
        }

        // Create display object
        System.out.println("\n\n====================================== RESULT ========================================");

        DataDisplay dis = new DataDisplay(sum);
        dis.display(displayOption);

        // Asking users to continue or not?
        System.out.print("\n\nDo you want to search for another data? (Y/N): ");
        finish = input.next();

        // Input finish condition
        while (!finish.equalsIgnoreCase("y") && !finish.equalsIgnoreCase("n")){
            System.out.print("\nInvalid type, please enter again: ");
            finish = input.next();
        }
        // If answer equals yes rerun the program
        if (finish.equalsIgnoreCase("y")) {
            running();
        }
    }
}
