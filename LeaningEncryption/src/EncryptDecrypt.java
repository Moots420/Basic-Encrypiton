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
		exportEncryption("test","test.txt");
		
		try {
			importEncryption("test.txt");
		} catch (IOException e) {
			System.out.println();
		}
	    
	}
	

	private void importEncryption(String fileName) throws IOException {
		File file = new File(fileName);
		byte[] bytes = null;
		try {
			FileInputStream input = new FileInputStream(file);
			 bytes = input.readAllBytes();
			input.close();
		}catch (IOException e) {
			System.out.println("Could not load file");
			
		}
	
		String msg = new String(decryptString(bytes));
		System.out.println(msg);
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
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < msgBytes.length; i++) {
			msgBytes[i] = (byte) (msgBytes[i] + keyNumber); //multiply each byte by Key
		s.append(msgBytes[i]);
		}
		//String convertedString = Arrays.toString(msgBytes); //convert byte array to string
	
		return msgBytes;
		
	}
	
	private String decryptString(byte[] byteArr) {
		byte[] msgBytes = byteArr;
		
	
		for(int i = 0; i < msgBytes.length; i++) {
			
			msgBytes[i] = (byte) (msgBytes[i] -  keyNumber);
			
		}
		String decr = msgBytes.toString();
		return decr;

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
