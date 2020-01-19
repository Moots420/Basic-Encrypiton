import java.util.Random;

public class keyHandler {

         private char[] Key = new char[256];
         private String alphabet;
        
         
         public keyHandler() {
        	 alphabet = "abcedfghijklmnopqrstuvwxyz0123456789';.,<>?:[]{}-=_+|!#$%^&*()@";
        	 
         }
         
         public char[] makeKey() {
        	 //Returns a key 256 characters long
        	 //Key is a Random character from alphabet string
        	 
        	 for (int i = 0; i<Key.length; i++) {
        		 Random r = new Random();
        		 char k = alphabet.charAt(r.nextInt(alphabet.length()));
        		 Key[i] = k;
        		
        		 
        	 }
        	 System.out.println("Local Key Generated.");
        	 return Key;
        	 

         }
	
}
