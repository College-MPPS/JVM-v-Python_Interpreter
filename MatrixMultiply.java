import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Kyle Truebody
 * 
 * Testing the overheads and transaction costs of multiplying 
 * two dimension arrays (matrix). This will be a compared to 
 * python lists results.
 *
 */


public class MatrixMultiply {
    /*
     * @param int[][] a         - first subject
     * @param int[][] b         - second subject
     * @param int[][] c         - resulting subject
     * @param ArrayList results - store results over iteration
     * @param File File         - save file path
     * 
     */    
    static int[][] a;
    static int[][] b;
    static int[][] c;
    static  ArrayList<String> results = new ArrayList<String>();
    static File file = new File("java_Results.dat");
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        for(int e = 0; e <=6000; e +=1000){ 

            a = new int[e][e];
            b = new int[e][e];
            c = new int[e][e];

            a = populate(a);
            b = populate(b);

            //transaction timing start 
            long startTime = System.nanoTime();
            multiply(a,b,c);
            //calculate the time taken to multiply 
            long stopTime = System.nanoTime();

            long resultTime = stopTime - startTime;
            //convert time to seconds (1e9)
            double seconds = ((double)resultTime / 1000000000);

            //convert to string and store the formated results in arraylist
            String strTime = new DecimalFormat("#.#########").format(seconds);
            String epoch = Integer.toString(e) ;
            results.add(epoch + "\t " + strTime);

            System.out.println("Time taken : " + resultTime + " Count : " + e);
            System.out.println("solution Time: " + new DecimalFormat("#.#########").format(seconds));
        } 
        writeToFile(results);
    }
     
    /*
    * Matrixs Multiply
    
    * @param A - first matrix
    * @param B - second matrix
    * @param C - the resulting matrixs
    *
    * @return - result of the matrices multiplication
    */
    public static int[][] multiply(int A[][],int B[][],int C[][]){
        
        for(int x = 0;x < A.length;x++){
            for(int y = 0;y < A[0].length;y++){
                for(int z = 0; z < B.length;z++){
                    C[x][y] += A[x][z] * B[y][z]; 
                }
            }
        }
        return C;
    }
    
    /*
    * Populate matrix as an identity matrix.
    
    * @param empty - place holder for the empty matrix
    * @return - returns the populated matrix
    */
    public static int[][] populate(int empty[][]){
        
        for(int x = 0;x < empty.length;x++){
            for(int y = 0; y < empty[0].length;y++){
                if(x==y){
                    empty[x][y] = 1;
                }
                else{
                    empty[x][y] = 0;
                }
            }
        }
        return empty;
    }
    
    /*
    * Write the results to a .dat file
    *
    * @param results - list of results already normalized.
    */
    public static void writeToFile(ArrayList<String> results){
        
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for(String str : results){
                fw.write(str + "\r\n");
            }
            bw.close();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
