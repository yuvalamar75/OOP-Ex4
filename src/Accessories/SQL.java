package  Accessories;
import GUI.SQLDisplay;

import java.lang.reflect.Array;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;

public class SQL {
    private static double[] clickModePoints= new double[9];
    private static double[] algorithmModePoints = new double[9];
    private static double[] generalPoints = new double[9];
    private static int[] clickModecounter = new int[9];
    private static int[] algorithmModeCounter = new int[9];
    private static int[] generalCounter = new int[9];
    private static NumberFormat formatter = new DecimalFormat("#0.000");
    private static double algorithmAVG = 0;
    private static double generalAVG = 0;
    private static double clickModeAVG = 0;
    private static  String algorithmAVGS = "";
    private static String clickModeAVGS = "";
    private static  String generalAVGS = "";
    private static String results;
    private static SQLDisplay display;
    private boolean getDataRun;
    // In constructr -> open new SQLDisplay.
    public SQL(){

        System.out.println("int sql constructor");
        getDataRun = false;
        display = new SQLDisplay();
        getdata();
        getStatistics();
    }

    /**
     * This function get the data from the data-base,then calculate the average of the points for each map.
     * @return build array with all the data from the sql.
     */
    public  void getdata() {
        System.out.println("int get data func");
        //if (!getDataRun) {
        getDataRun = true;
        String results = "";
        String jdbcUrl = "jdbc:mysql://ariel-oop.xyz:3306/oop"; //?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
        String jdbcUser = "student";
        String jdbcPassword = "student";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword);
            Statement statement = connection.createStatement();

            //select data
            String allCustomersQuery = "SELECT * FROM logs;";
            ResultSet resultSet = statement.executeQuery(allCustomersQuery);
            System.out.println("FirstID\t\tSecondID\tThirdID\t\tLogTime\t\t\t\tPoint\t\tSomeDouble");
            while (resultSet.next()) {
                //Adding the data for map 1
                if (resultSet.getDouble("SomeDouble") == 2128259830) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[0] = algorithmModePoints[0] + resultSet.getDouble("Point");
                        algorithmModeCounter[0]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[0] = clickModePoints[0] + resultSet.getDouble("Point");
                        clickModecounter[0]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[0] = generalPoints[0] + resultSet.getDouble("Point");
                        generalCounter[0]++;
                    }
                }
                //Adding the data for map 2
                if (resultSet.getDouble("SomeDouble") == 1149748017) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[1] = algorithmModePoints[1] + resultSet.getDouble("Point");
                        algorithmModeCounter[1]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[1] = clickModePoints[1] + resultSet.getDouble("Point");
                        clickModecounter[1]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[1] = generalPoints[1] + resultSet.getDouble("Point");
                        generalCounter[1]++;
                    }

                }
                //Adding the data for map 3
                if (resultSet.getDouble("SomeDouble") == -683317070) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[2] = algorithmModePoints[2] + resultSet.getDouble("Point");
                        algorithmModeCounter[2]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[2] = clickModePoints[2] + resultSet.getDouble("Point");
                        clickModecounter[2]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[2] = generalPoints[2] + resultSet.getDouble("Point");
                        generalCounter[2]++;
                    }

                }
                //Adding the data for map 4
                if (resultSet.getDouble("SomeDouble") == 1193961129) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[3] = algorithmModePoints[3] + resultSet.getDouble("Point");
                        algorithmModeCounter[3]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[3] = clickModePoints[3] + resultSet.getDouble("Point");
                        clickModecounter[3]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[3] = generalPoints[3] + resultSet.getDouble("Point");
                        generalCounter[3]++;
                    }

                }
                //Adding the data for map 5
                if (resultSet.getDouble("SomeDouble") == 1577914705) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[4] = algorithmModePoints[4] + resultSet.getDouble("Point");
                        algorithmModeCounter[4]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[4] = clickModePoints[4] + resultSet.getDouble("Point");
                        clickModecounter[4]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[4] = generalPoints[4] + resultSet.getDouble("Point");
                        generalCounter[4]++;
                    }

                }
                //Adding the data for map 6
                if (resultSet.getDouble("SomeDouble") == -1315066918) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[5] = algorithmModePoints[5] + resultSet.getDouble("Point");
                        algorithmModeCounter[5]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[5] = clickModePoints[5] + resultSet.getDouble("Point");
                        clickModecounter[5]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[5] = generalPoints[5] + resultSet.getDouble("Point");
                        generalCounter[5]++;
                    }

                }
                //Adding the data for map 7
                if (resultSet.getDouble("SomeDouble") == -1377331871) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[6] = algorithmModePoints[6] + resultSet.getDouble("Point");
                        algorithmModeCounter[6]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[6] = clickModePoints[6] + resultSet.getDouble("Point");
                        clickModecounter[6]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[6] = generalPoints[6] + resultSet.getDouble("Point");
                        generalCounter[6]++;
                    }

                }
                //Adding the data for map 8
                if (resultSet.getDouble("SomeDouble") == 306711633) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[7] = algorithmModePoints[7] + resultSet.getDouble("Point");
                        System.out.println(resultSet.getDouble("Point"));
                        algorithmModeCounter[7]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[7] = clickModePoints[7] + resultSet.getDouble("Point");
                        clickModecounter[7]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[7] = generalPoints[7] + resultSet.getDouble("Point");
                        generalCounter[7]++;
                    }

                }
                //Adding the data for map 9
                if (resultSet.getDouble("SomeDouble") == 919248096) {
                    //If its our game and its played in algorithm mode
                    if (resultSet.getInt("FirstID") == 308522416 && resultSet.getInt("SecondID") == 311229488) {
                        algorithmModePoints[8] = algorithmModePoints[8] + resultSet.getDouble("Point");
                        algorithmModeCounter[8]++;
                    }
                    //If its out game and its played in click mode
                    else if (resultSet.getInt("FirstID") == 311229488 && resultSet.getInt("SecondID") == 308522418) {
                        clickModePoints[8] = clickModePoints[8] + resultSet.getDouble("Point");
                        clickModecounter[8]++;
                    }
                    //If its not our game palyed.
                    else {
                        generalPoints[8] = generalPoints[8] + resultSet.getDouble("Point");
                        generalCounter[8]++;
                    }

                }

            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException: " + sqle.getMessage());
            System.out.println("Vendor Error: " + sqle.getErrorCode());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getStatistics() {
        results = "Map number \t\t Algorithm Mode AVG \t\t  Click Mode AVG \t\t General average \t\t Total Map Played \n";
        //Getting the statistc from the data in the sql.
        for (int i = 0; i < 9; i++) {

            if (algorithmModeCounter[i] == 0) {
                algorithmAVG = 0;
                algorithmAVGS = formatter.format(algorithmAVG);
            } else {
                algorithmAVG = algorithmModePoints[i] / algorithmModeCounter[i];
                algorithmAVGS = formatter.format(algorithmAVG);
            }
            if (clickModecounter[i] == 0) {
                clickModeAVG = 0;
                clickModeAVGS = formatter.format(clickModeAVG);
            } else {
                clickModeAVG = clickModePoints[i] / clickModecounter[i];
                clickModeAVGS = formatter.format(clickModeAVG);

            }
            if (generalCounter[i] == 0) {
                generalAVG = 0;
                generalAVGS = formatter.format(generalAVG);
            } else {
                generalAVG = generalPoints[i] / generalCounter[i];
                generalAVGS = formatter.format(generalAVG);

            }
            int totalGamePlayed = clickModecounter[i] + algorithmModeCounter[i];
            results = results + "   " + (i + 1) + ")" + "\t\t\t\t" + algorithmAVGS + "\t\t\t       " + clickModeAVGS + "\t\t\t     " + generalAVGS + "\t\t\t        " + totalGamePlayed + "\n";
            // frame.getTextArea().append(results);


        }
       for(int i = 0 ; i < 9 ; i++){
            clickModecounter[i] = 0;
            clickModePoints[i] = 0;
            algorithmModeCounter[i] = 0;
            algorithmModePoints[i] = 0 ;
            generalCounter[i] = 0;
            generalPoints[i] = 0;

        }
        display.getText().append(results);
    }
    //}
}







