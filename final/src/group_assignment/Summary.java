package group_assignment;

public class Summary {

    // Create attributes
    Data d;
    private String[][] processedData;

    // Create constructor for Summary class
    public Summary(Data data) {
        d = data;
    }

    // Method to summarize rawData to be processed
    public void summarize(int groupNum, int metric, int type){

        // Create variables
        int index = 0;
        String startDate;
        String endDate;
        int statisticType1;
        int statisticType2 = 0;
        int vaccinatedBefore = 0;
        String[][] groupedData = new String[groupNum][2]; //2D array to store groups and elements in a group
        int[] numOfEach = divideEvenly(d.getterRawData().size(),groupNum); // Number elements of each group
        String[] token, token1, token2;

        // Looping to add groups to container
        for (int i = 0; i < numOfEach.length; i++) {
            token = (d.getterRawData().get(index)).split(","); // Variable refers to array of values in each group
            startDate = token[0]; //First date value in each group
            statisticType1 = 0;

            // While loop to add processed data to groups
            while (numOfEach[i] > 0) {

                token1 = (d.getterRawData().get(index)).split(","); // Variable refers to array of values in each group
                endDate = token1[0]; // Last date value in each group

                // Check the new total result type of each groups
                if (type == 1){

                    // Check the metric to calculate results
                    if (metric == 3){
                        // Calculation for vaccinated collum
                        statisticType1 = Integer.parseInt(token1[3]) - vaccinatedBefore;
                    } else {
                        // Calculation for new cases and new deaths values
                        statisticType1 += Integer.parseInt(token1[metric]);
                    }

                    groupedData[i][1] = Integer.toString(statisticType1); // Add results to groups

                } else {
                    if (metric == 3){
                        statisticType2 = Integer.parseInt(token1[3]);
                    } else {
                        statisticType2 += Integer.parseInt(token1[metric]);
                    }

                    groupedData[i][1] = Integer.toString(statisticType2);

                }

                groupedData[i][0] = startDate + "-" + endDate; // Add time range to groups

                numOfEach[i]--;
                index++;
            }

            token2 = (d.getterRawData().get(index-1)).split(",");
            vaccinatedBefore = Integer.parseInt(token2[3]);
        }

        processedData = groupedData;
    }

    // Method to divide data to many groups with nearly evenly number of elements
    public int[] divideEvenly(int size, int groupNum){

        int[] numOfEach = new int[groupNum];
        int zp, pp;
        int mod = size % groupNum;

        // Condition to check if number of group is divisible by size of data or not
        if (mod == 0) {

            // If it is divisible, then number of elements equals between groups
            for (int i = 0; i < groupNum; i++){
                numOfEach[i] = size / groupNum;
            }
        } else {
            zp = groupNum - mod; // Number of group with lower number of elements
            pp = size / groupNum; // Number of elements in minor groups

            for (int i = 0; i < groupNum; i++) {

                // Condition to check if it is minor or major groups
                if (i >= zp)
                    numOfEach[i] = pp + 1;
                else
                    numOfEach[i] = pp;
            }
        }
        return numOfEach;
    }

    // Method to get processedData from another class
    public String[][] getProcessedData() {
        return processedData;
    }
}
