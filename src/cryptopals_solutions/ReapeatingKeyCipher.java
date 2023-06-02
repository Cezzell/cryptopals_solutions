package cryptopals_solutions;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReapeatingKeyCipher {

	public static CryptoToolFunctions Tools;
	
	public static void main(String[] args) throws Exception{
/***
		String Plaintext = "Burning 'em, if you ain't quick and nimble\n"
				+ "I go crazy when I hear a cymbal";
		byte[] Key = new byte[3];
		Key[0] = 'I';
		Key[1] = 'C';
		Key[2] = 'E';
		
		String PlaintextHexString = Tools.ConvertPlaintextToHexString(Plaintext);
		
		String Ciphertext = EncryptWithRepeatingKey(PlaintextHexString, Key);
		String CiphertextTest = "0b3637272a2b2e63622c2e69692a23693a2a3c6324202d623d63343c2a26226324272765272"
				+ "a282b2f20430a652e2c652a3124333a653e2b2027630c692b20283165286326302e27282f";
		System.out.println("Ciphertext from encryption: " + Ciphertext + "\nCiphertext Expected: " + CiphertextTest);
		
		if (Ciphertext.equals(CiphertextTest)) {
			System.out.println("Cipher matches.");
		}
***/
		
		BufferedReader Reader = new BufferedReader( new FileReader("/Users/ezzel/eclipse-workspace/cryptopals_solutions/src/RepeatingKeyCiphers.txt"));
		
	}
	
	
	public static String EncryptWithRepeatingKey(String Plaintext, byte[] Key) throws Exception {
		
		// Determine the length of the Plaintext
		int textLength = Plaintext.length();
		
		// Create the KeyString
		String KeyString = Tools.CreateRepeatingKeyHexString(Key, textLength);
		
		// X-OR the key bytes and plaintext bytes
		String CipherText = Tools.FixedXOR(Plaintext, KeyString);
		
		// Return Encrypted CipherText
		return CipherText;
 	}
	
	public static String BreakRepeatingKeyCipher(String Base64Ciphertext) throws Exception {
		
		// Work through Keysizes between 2 and size of line
		int keysize;
		int MinHammingDistance = 1500000000;
		String HexCiphertext = Tools.Base64ToHex(Base64Ciphertext);
		String Ciphertext = Tools.ConvertHexStringToPlaintext(HexCiphertext);
		int[] KeySizeHammingScores = new int[40];
		
		for(keysize = 2; keysize < 40; keysize++) {
			// Cut the start of the string into smaller pieces of size keySize
			String Cipher1 = Ciphertext.substring(0, keysize);
			String Cipher2 = Ciphertext.substring(keysize,2*keysize);
			
			KeySizeHammingScores[keysize] = Tools.ComputeHammingDistance(Cipher1,Cipher2)/keysize;
		}
		
		for(int i = 2; i<40; i++) {
			if (KeySizeHammingScores[i]<MinHammingDistance) {
				MinHammingDistance = KeySizeHammingScores[i];
				keysize = i;
			}
		}
		
		System.out.println("Smallest Hamming Distance is keysize: " +keysize);
		return HexCiphertext;
		
	}
}
