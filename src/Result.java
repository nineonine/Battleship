import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class Result
{
  
   
   
     public void displayResults(){
   //display highscore
   }
   public void sortBoard(){
   //sort the high score
   }
   
   
   public void saveFile(String winner, String date, int attempts){
   //save result to text file
      String Test;
      Test = winner + " " + attempts + " " + date;
      try{
      
         
         
			File file = new File("E:/Test.txt");
 
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Test);
         bw.newLine();
			bw.close();
         }
         catch(IOException a){
            a.printStackTrace();
         }
 
	}
   public void loadFile(){
   //load text file
      
    
            String [] array = new String[10];

            try (BufferedReader br = new BufferedReader(new FileReader("E:/Test.txt")))
            {
            
                  String Lines;
                  int i = 0;
                  while ((Lines = br.readLine()) != null) {
                  array[i++] = Lines;
            }
            
            } catch (IOException e) {
            
                  e.printStackTrace();
            }
            for(int x = 0; x < array.length; x++)
            {
               System.out.println(array[x]);
            }
   
   
   }


}
