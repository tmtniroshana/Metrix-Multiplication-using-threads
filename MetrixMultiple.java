public class MetrixMultiple {

        private double [][] multiplymetrix;

    public MetrixMultiple(String file1,String file2 ){

        InputArgument metrix01 = new InputArgument(file1);
        InputArgument metrix02 = new InputArgument(file2);


            int row01 = metrix01.getRow();
            int colom01 = metrix01.getColom();

            int row02 = metrix02.getRow();
            int colom02 = metrix02.getColom();


            if (colom01!=row02)System.out.println("Metrix dimension Error");


            double [][] multiplymetrix = new double[row01][colom02];

            double sum=0;


        for (int i = 0; i < row01; i++) { //multiply the metrix
            for (int j = 0; j < colom02; j++) {
                for (int k = 0; k < row02; k++) {
                    sum = sum + (metrix01.getMetrix(i, k) * metrix02.getMetrix(k, j));
                }
                multiplymetrix[i][j] = sum;
                //System.out.print(multiplymetrix[i][j]+" ");
                sum = 0;
            }

        }
    }
    

}
