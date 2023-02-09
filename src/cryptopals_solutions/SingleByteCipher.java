package cryptopals_solutions;

import java.lang.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class SingleByteCipher {
	
	public static CryptoToolFunctions Tools;

	public static void main(String[] args) throws Exception{
		
		//Test Scenario for solving single key cipher
		//String CipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
		//System.out.println("Best Guess at Text: \n" + SolveSingleCipher(CipherText));
		
		DetectSingleKeyCipherFromFile();
	}
	
	public static String SolveSingleCipher(String Ciphertext) throws Exception {
		
		int length = Ciphertext.length();
		length = length/2;
		
		int maxScore = 0;
		int maxIndex = 0;
		int currentScore;
		
		String KeyString;
		String Plaintext;
		
		byte[] keys = new byte[256];
		
		for(Integer i = 0; i < 256; i++) {
			keys[i] = i.byteValue();
		}
		
		for(int j = 0; j< 256; j++) {
			KeyString = Tools.CreateSingleKeyHexString(keys[j], length);
			Plaintext = Tools.FixedXOR(KeyString, Ciphertext);
			Plaintext = Tools.ConvertHexStringToPlaintext(Plaintext);
			currentScore = Tools.PlaintextFrequencyScore(Plaintext);
			
			if(currentScore > maxScore) {
				maxScore = currentScore;
				maxIndex = j;
			}
		}
		
		KeyString = Tools.CreateSingleKeyHexString(keys[maxIndex], length);
		Plaintext = Tools.FixedXOR(KeyString, Ciphertext);
		
		 return Tools.ConvertHexStringToPlaintext(Plaintext);
	}
	
	public static void DetectSingleKeyCipherFromFile() throws Exception {
		
		int lineNumber = 1;
		int highestScore = 0;
		String Ciphertext;
		String Plaintext;
		String line;
		
		BufferedReader Reader = new BufferedReader( new FileReader("/Users/ezzel/eclipse-workspace/cryptopals_solutions/src/SingleByteCiphers.txt"));
		
		try {
		  line = Reader.readLine();
		  while (line!=null){
			  System.out.println("Guessed Plaintext of Line "+ lineNumber +": " + SolveSingleCipher(line));
			  lineNumber++;
			  line = Reader.readLine();
		  }
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//String line = FileRead.read(null)
	}

}
