package cryptopals_solutions;

import java.lang.*;

public class SingleByteCipher {
	
	public static CryptoToolFunctions Tools;

	public static void main(String[] args) throws Exception{
		String CipherText = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736";
		SolveSingleCipher(CipherText);
	}
	
	public static void SolveSingleCipher(String Ciphertext) throws Exception {
		
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
		
		System.out.println("Best Guess at Text: \n" + Tools.ConvertHexStringToPlaintext(Plaintext));

	}
}