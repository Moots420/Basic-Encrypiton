import java.io.BufferedReader;
import java.io.FileNotFoundException;
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}
	

//	private void importEncryption(String fileName) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(fileName));
//		StringBuilder sb = new StringBuilder();
//		byte[] test;
//		while(br.readLine() != null) {
//			sb.append(br.readLine());
//			test.
//		}
//		
//		br.close();
//		String encryptedText = sb.toString();
//		System.out.println(encryptedText);
//		byte[] msg = encryptedText.getBytes();
//		//String msg = decryptString(encryptedText);
//		
//		for(int i = 0 ; i<msg.length; i++ ) {
//		System.out.print(msg[i] + " ");
//		
//		}
//	}
	
	private void exportEncryption(String str, String fileName) {
		byte[]  EncryptedStr = encryptString(str);
		try {
			PrintWriter out = new PrintWriter(fileName);
			for(int i = 0; i< EncryptedStr.length;i++) {
			out.println(EncryptedStr[i]);
			}
			out.close();
			System.out.println("Encryption Saved to: " + fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Could not export");
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
	
	private byte[] decryptString(String encryptedText) {
		byte[] msgBytes = encryptedText.getBytes();
		
	
		for(int i = 0; i < msgBytes.length; i++) {
			
			msgBytes[i] = (byte) (msgBytes[i] -  keyNumber);
			
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
