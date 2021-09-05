package group_assignment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data {

    //Initialize Variables
    public String area;
    public String startDate;
    public String endDate;
    private final ArrayList<String> rawData = new ArrayList<>();
    String file = "covid_data.csv";
    String line;

    //Constructor for Data class
    public Data(String a, String sd, String ed) {
        area = a;
        startDate = sd;
        endDate = ed;
    }

    // Method to get raw data from csv file
    public void getData() {

        //Create date format for date elements in csv file
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        try {
            // Read the file by BufferedReader object
            BufferedReader br = new BufferedReader(new FileReader(file));

            // Loop through each line in csv file to search for needed data
            while ((line = br.readLine()) != null) {

                String[] values = line.split(","); // Split each value in a line

                // Condition to search for matching areas (only location collum)
                if (values[2].equalsIgnoreCase(area)) {

                    Date curDate = df.parse(values[3]);
                    Date startDate = df.parse(this.startDate);
                    Date endDate = df.parse(this.endDate);

                    // Condition to search for matching dates
                    if (!curDate.after(endDate) && !curDate.before(startDate)) {

                        // Condition to check for new deaths null values
                        if(values[5].equals("")) {
                            values[5] = Integer.toString(0);
                        }

                        //Condition to check for vaccinated null values
                        if(values[6].equals("")) {
                            values[6] = Integer.toString(0);
                        }

                        rawData.add(values[3] + "," + values[4] + "," + values[5] + "," + values[6]);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    // Method to get number of elements in selected data
    public int getDataSize(){
        return rawData.size();
    }

    // Method to get private variable rawData
    public ArrayList<String> getterRawData() {
        return rawData;
    }
}