/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pileuptolowaveragequalbed;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 *
 * @author wim
 */
public class PileUpToLowAverageQualBed {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        try {
            PrintWriter writer = new PrintWriter("/home/wim/Analysis/rat_founders/BAC/Ilumina_LE_dedup_realigned_pileup_BACRegion_noRepeatPlus1FlankRegion_noAverageLowQualPositions.bed", "UTF-8");
            
           
            
            
            
            
            // Open the file that is the first 
            // command line parameter
            FileInputStream fstream = new FileInputStream("/home/wim/Analysis/rat_founders/BAC/Ilumina_LE_dedup_realigned_pileup_BACRegion_noRepeatPlus1FlankRegion.txt");
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                
                if(strLine.contains("REDUCE RESULT")){continue;}
                
                String[] splitLine = strLine.split(" ");
                String chrom = splitLine[0];
                Integer pos = new Integer (splitLine[1]);
                
                String reads = splitLine[7];
                
                String[] readsSplit = reads.split(",");
                Integer numberOfReads = readsSplit.length;
                
                Integer readCounter = 0;
                Integer mappingQualSum = 0;
                
                for(String read : readsSplit)
                {
                    String[] readSplit = read.split("@");
                    Integer mappingQual = new Integer(readSplit[3]);
                    mappingQualSum = mappingQualSum + mappingQual;
                    readCounter++;  
                }
                
                Integer averageMappingQual = mappingQualSum / readCounter;  
                if(averageMappingQual < 35 || numberOfReads > 150)
                {
                   // System.out.println(chrom+"\t"+(pos-1)+"\t"+pos);
                    writer.println(chrom+"\t"+(pos-1)+"\t"+pos);
           
                    
                    
                    String blaat = "blaat";
                
                }
               
                
                
              //  System.out.println(strLine);
            }
            //Close the input stream
            in.close();
            writer.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }






        // TODO code application logic here
    }
}
