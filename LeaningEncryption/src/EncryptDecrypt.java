import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class EncryptDecrypt {
	private char[] localKey;
	private long keyNumber;
   
	public EncryptDecrypt(char[] localKey) {
		
		
		this.localKey = localKey; // Brings in 256 random characters
		keyNumber = makeKeyNumber(localKey); 
		System.out.println("ID Key is:  "+ keyNumber);
		
	
	    
	}
	
	public void EncryptFile(String fileName, String Message) {
		exportEncryption(Message,fileName);
		
	}
	
	public void DecryptFile(String fileName) {
		try {
			importEncryption(fileName);
		} catch (IOException e) {
			System.out.println("Failed to load file.");
		}
		
	}

	private void importEncryption(String fileName) throws IOException {
		File file = new File(fileName);
		byte[] bytes = null;
		try {
			FileInputStream input = new FileInputStream(file);
            bytes = input.readAllBytes();
			input.close();
			System.out.println("File " + fileName + " Loaded");
		}catch (IOException e) {
			System.out.println("Could not load file");
			
		}
	    
		byte[] decryptedBytes = decryptString(bytes);
	    
		
		System.out.println("AFTER DECRYPTION: " + new String(decryptedBytes)   );
	}
	
	private void exportEncryption(String str, String fileName) {
		byte[]  EncryptedStr = encryptString(str);
		File file = new File(fileName);
	   try {
			FileOutputStream  output = new FileOutputStream(file); 
			output.write(EncryptedStr);
			output.flush();
			output.close();
			System.out.println("File written to: " + fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not Write file");
		}
	}

	
	private byte[] encryptString(String message) {
		byte[] msgBytes = message.getBytes(); //convert to bytes
		
		for(int i = 0; i < msgBytes.length; i++) {
			msgBytes[i] = (byte) (msgBytes[i] + keyNumber); //multiply each byte by Key
		}
		System.out.println("ENCRYPTED TEXT:  " + new String(msgBytes));
		return msgBytes;
		
	}
	
	private byte[] decryptString(byte[] byteArr) {
		byte[] msgBytes = byteArr;
		
	
		for(int i = 0; i < msgBytes.length; i++) {
			
			msgBytes[i] = (byte) (msgBytes[i]- keyNumber );
			
		}
		
		return msgBytes;

	}
	

	
	private long makeKeyNumber(char[] key) {
	int keyNum = 0;
		for(int i = 0; i<key.length; i++) {   //Builds String from character array
			int ascii = (int) key[i];
			keyNum += ascii;
		}
		return keyNum; //returns long
	}

}
