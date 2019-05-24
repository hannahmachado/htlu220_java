package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class AnagramUtil {
	
	public static String sort(String inputString){
		char[] letters = inputString.toLowerCase().toCharArray();
		
		for (int i=0; i<letters.length; i++){
			
			char index= letters[i];
			int j=i;
			
			while(j>0 && letters[j-1]>index){
				letters[j]= letters [j-1];
				j--;
			}
			letters[j]=index;
		}
		
		String output= "";
		for (int i=0; i<letters.length; i++){
			output += letters[i];
		}
		return output; //change so that it returns the sorted version of inputString
		
	}
    
	public static boolean areAnagrams (String string1, String string2){
		if(sort(string1).equals(sort(string2))){
			return true;
		}
		return false; 
	}
	
	public static void insertionSort(String[] inputList){
		
		OrderStrings comparator = new OrderStrings();
		
		if(inputList.length<=1)
			return;
		
		for(int i=1; i< inputList.length; i++){
			
			String index= inputList[i];
			int j=i;
			
			while(j>0 && comparator.compare(inputList[j-1],index)>0){
				inputList[j]= inputList[j-1];
				j--;
			}
			inputList[j]=index;		
		}
	}
	
	//to do for assignment
	public static String[] getLargestAnagramGroup (String[] inputList){
		
		insertionSort(inputList);
		OrderStrings comparator = new OrderStrings();
		
			 int maxAnagramGroup=1;
		     int maxStart=0;
		     int maxEnd=0;
		
		     int currentAnagramGroup=1;
		     int currentStart = 0;
		     int currentEnd= 0;
		
		     for (int i=1; i<inputList.length; i++){  	 
		      if(comparator.compare(inputList[i-1], inputList[i])==0){
        		 currentAnagramGroup++;
        		 currentEnd++;
        	  } else{
        		  currentAnagramGroup=1;
        		  currentStart=i;
        		  currentEnd=i+1;
        	  }
		      
		      if (currentAnagramGroup > maxAnagramGroup){
		    	 maxAnagramGroup= currentAnagramGroup;
		         maxStart= currentStart;
		         maxEnd= currentEnd;
		      }
             }
	
		return(Arrays.copyOfRange(inputList, maxStart, maxEnd));    
	}
	
	public static String[] getLargestAnagramGroup (String filename){
		String[] words_copy = readFile(filename);
		String[] s3 = getLargestAnagramGroup(words_copy);
		return s3; //change this to the appropriate return object
	}
	
	public static String[] readFile(String filename)
	{
		ArrayList<String> results = new ArrayList<String>();
		try
		{
			BufferedReader input = new BufferedReader(new FileReader(filename));
			while(input.ready())
			{
				results.add(input.readLine());
			}
			input.close();
		}
		catch(Exception e)
		{e.printStackTrace();}
		String[] retval = new String[1];
		return results.toArray(retval);
	}	
	
}
