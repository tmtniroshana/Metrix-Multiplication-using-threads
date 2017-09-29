import java.util.Arrays;

public class MatrixMultiplication extends Thread {

    String file01,file02; // file for read including metrix
    int startrow,endrow;
    static double[][] multiplymetrix; // output metrix

    public MatrixMultiplication(String file1,String file2,int start,int end){
        this.file01=file1;
        this.file02=file2;
        this.startrow=start;
        this.endrow=end;
    }


    public void run(){

        SingleThread metrix01 = new SingleThread(file01); //read the metrix file A.txt
        SingleThread metrix02 = new SingleThread(file02); // read the metrix file B.txt


            //get dimention of metrisess
            int row01 = metrix01.getRow();
            int colom01 = metrix01.getColom();

            int row02 = metrix02.getRow();
            int colom02 = metrix02.getColom();

            //if daimention is not valid
            if (colom01 != row02) System.out.println("Metrix dimension Error");

            if(multiplymetrix==null) {
                 multiplymetrix = new double[row01][colom02];
            }

            double sum = 0;

            //multiply metrix elimentwise
            for (int i = startrow; i < endrow; i++) {
                for (int j = 0; j < colom02; j++) {
                    for (int k = 0; k < row02; k++) {
                        sum = sum + (metrix01.getMetrix(i, k) * metrix02.getMetrix(k, j));
                    }
                    multiplymetrix [i][j] = sum;
                  // System.out.print(multiplymetrix[i][j] + " ");
                    sum = 0;
                }

            }
       //System.out.println();
    }



    public static void main(String [] args){

        long startTime = System.currentTimeMillis();//start calculate rinetime
        //create metrix  object
        SingleThread inputMetrix=new SingleThread("A.txt");
        SingleThread inputMetrixofB=new SingleThread("B.txt");

        int allrows=inputMetrix.getRow();
        int allcoloms=inputMetrixofB.getColom();

        //get number of threadA
        int numberOfThreads=Integer.parseInt(args[0]);

        MatrixMultiplication [] Met=new MatrixMultiplication[numberOfThreads];

        int threadStep=((allrows)/numberOfThreads);//
        int step=0;

        if (allrows<numberOfThreads) System.out.println("Threads are grater than number of Rows");

        for (int i=0;i<numberOfThreads-1;i++) {
            Met[i] = new MatrixMultiplication("A.txt","B.txt",step,step+threadStep);//set the thread
            Met[i].start();
            try {
                Met[i].join();//step by step output give last
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            step=step+threadStep;

        }
        // last thread
        Met[numberOfThreads-1]=new MatrixMultiplication("A.txt","B.txt",step,allrows);
        Met[numberOfThreads-1].start();
        try {
            Met[numberOfThreads-1].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(Arrays.deepToString(multiplymetrix));
        for (int i=0;i<allrows;i++) { //print the final metrix
            for (int j=0;j<allcoloms;j++) {
                System.out.print(multiplymetrix[i][j]+" ");
            }
            System.out.println();
        }
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime; //calculate runtime in this programme
        System.out.println(totalTime);

    }


}




