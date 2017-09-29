import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class SingleThread {

    int row,colom;
    private double[][] metrix;

    public SingleThread(String file) {


        FileReader fileread = null;
        BufferedReader reader = null;

        try {
            fileread = new FileReader(file);//read the file include metrix
            reader = new BufferedReader(fileread);

            String[] sizeOfmetrix = reader.readLine().split(" ");
            row = Integer.parseInt(sizeOfmetrix[0]);
            colom = Integer.parseInt(sizeOfmetrix[1]);

            double[][] tempmetrix = new double[row][colom];
            //get the value for 2D aray
            for (int i = 0; i < row; i++) {
                String[] temparray;
                String line = reader.readLine();
                temparray = line.split(" ");

                for (int j = 0; j < colom; j++) {

                    tempmetrix[i][j] = Double.parseDouble(temparray[j]);

                    System.out.print(tempmetrix [i][j]+" ");
                }
            }
            this.metrix = tempmetrix;



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public double getMetrix(int m,int p){//get metrix

        return metrix[m][p];
    }
    public int getRow(){
        return row;
    }
    public int getColom(){
        return colom;
    }


    public static void main(String [] args) {

        long startTime = System.currentTimeMillis();//start calculate runtime

       MetrixMultiple M1 = new MetrixMultiple("A.txt","B.txt");



        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println();//end of the calculate runtime
        System.out.println(totalTime);

    }
}



