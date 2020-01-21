import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main { 
	private static EncryptDecrypt cryptoHandler;
	private static keyHandler Crypto;
	private static char[] localKey;
	public static void main(String[] args) {
		Crypto = new keyHandler();
		checkKey();
		cryptoHandler = new EncryptDecrypt(localKey);
    cryptoHandler.EncryptFile("test.txt", "This is how to test encryption. If local key is deleted, you wont be able to decrypt this file.");
	cryptoHandler.DecryptFile("test.txt");
}
	public static void printKey() {
		System.out.println("Your local key is:");
		for (int i = 0; i<localKey.length; i++) {
			System.out.print(localKey[i]);
		}
		System.out.println("");
		
	}
	public static void checkKey() {
		System.out.println("Checking Local Key.");
        try {
			localKey = loadKey();
		} catch (IOException e) {
			localKey = Crypto.makeKey();
			saveKey(localKey);
		}
		
	}
	
	public static void saveKey(char[] Key) {
		try {
			PrintWriter out = new PrintWriter("LocalKey.txt");
			out.println(Key);
			out.close();
			 System.out.println("Key Saved!");
		} catch (FileNotFoundException e) {
			System.out.println("Could not save key.");
		}
	}
	
	public static char[] loadKey() throws IOException {
		char[] Key = new char[256];
	
			BufferedReader br = new BufferedReader(new FileReader("LocalKey.txt"));
			String k = br.readLine();
			br.close();
			for(int i = 0; i<k.length();i++) {
				Key[i] = k.charAt(i);
			}
			System.out.println("Loaded key.");
	
		return Key;
	}
}
