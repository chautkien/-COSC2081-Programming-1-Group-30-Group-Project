package group_assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class DataDisplay {

    // Create attributes
    Summary s;

    // Create constructor for DataDisplay class
    public DataDisplay(Summary s) {
        this.s = s;
    }

    // Method to check display option
    public void display(int option){

        if (option == 1){
            tabularDisplay();
        }

        if (option == 2) {
            chartDisplay();
        }
    }

    //Method to display data by table
    public void tabularDisplay() {

        System.out.println("+-----------------------------+----------------+");
        System.out.println("| GROUP                       | STATISTICS     |");
        System.out.println("+-----------------------------+----------------+");

        // Loop through each group
        for (String[] processedDatum : s.getProcessedData()) {
            System.out.printf("| %-28.21s| %-15.10s|\n", processedDatum[0], processedDatum[1]);
        }
        System.out.println("+-----------------------------+----------------+");
    }

    // Method to display data by chart
    public void chartDisplay() {
        float maxValue = getMaxValue(s.getProcessedData()); // Get largest value from data
        for (int y = 0; y < 24; y++) { // Iterate the program through y=24 times to get y-axis
            System.out.print("\n|");
            String blank = " ";
            for (String[] value : s.getProcessedData()) { // Iterate through data
                int a = 78 / s.getProcessedData().length;
                int space = Math.round(a) - 1; // Amount of spaces between two data points
                if (y == (23 - Math.round(23 / maxValue * Integer.parseInt(value[1])))) { // prints '*' if line is equals to formula
                    System.out.print('*');
                }
                if (y == 23) { // change blank to '_' to get the x-axis
                    blank = "_";
                }
                if (y != (23 - Math.round(23 / maxValue * Integer.parseInt(value[1])))) { // prints blank if line is not equals to formula
                    System.out.print(blank);
                }
                while (space > 0) { // create spaces between two points of each line
                    System.out.print(blank);
                    space--;
                }
            }
        }
    }

    //Method to get largest value from data
    public int getMaxValue(String[][] data){
        int max=0;
        for(String[] value : data){
            if(max < Integer.parseInt(value[1])){
                max = Integer.parseInt(value[1]);
            }
        }
        return max;
    }


}
