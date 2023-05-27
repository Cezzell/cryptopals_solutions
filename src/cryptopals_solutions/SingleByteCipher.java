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
		//DetectSingleKeyCipherFromFile();
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
	
public static Integer SolveSingleCipherReturnScore(String Ciphertext) throws Exception {
		
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
		 return maxScore;
	}

	public static void DetectSingleKeyCipherFromFile() throws Exception {
		
		int highestScoreIndex = 0;
		int highestScore = 0;
		int currentScore = 0;
		int currentIndex = 0;
		
		String line;
		String Ciphertext = null;
		
		BufferedReader Reader = new BufferedReader( new FileReader("/Users/ezzel/eclipse-workspace/cryptopals_solutions/src/SingleByteCiphers.txt"));
		
		try {
		  line = Reader.readLine();
		  currentIndex++;
		  while (line!=null){
			 currentScore = SolveSingleCipherReturnScore(line);
			 if(currentScore>highestScore) {
				 highestScoreIndex = currentIndex;
				 Ciphertext = line;
				 highestScore = currentScore;
			 }
			 line = Reader.readLine();
			 currentIndex++;
		  }
		}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Highest Scoring Plaintext Line: " + highestScoreIndex +"\nTranslated Plaintext: " + SolveSingleCipher(Ciphertext));
	}

}
