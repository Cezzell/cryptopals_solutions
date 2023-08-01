package cryptopals_solutions;

import java.io.BufferedReader;
import java.io.*;

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
		// Create Reader for pulling from file
		BufferedReader Reader = new BufferedReader(new FileReader("/Users/ezzel/eclipse-workspace/cryptopals_solutions/src/RepeatingKeyCiphers.txt"));
		String FullFile = "";
		String BytePositionString = "";
		byte keyByte;
		String Line = "";
		String KeyFile = "";
		String DecodedText = "";
		
		// Read in the first three lines of the file for finding Keysize
		while((Line = Reader.readLine()) != null) {
			FullFile = FullFile.concat(Line);		
		}
		
		String HexCiphertext = Tools.Base64ToHex(FullFile);
		String Ciphertext = Tools.ConvertHexStringToPlaintext(HexCiphertext);

		// Compute average Hamming distance for the different keysizes
		int keysize = BreakRepeatingKeyCipherKeySize(Ciphertext);
		byte[] Key = new byte[keysize];
		
		// For each byte in key, create string
		for(int i = 0; i < keysize; i++) {
			BytePositionString = CreateStringByKeyPosition(keysize, i, Ciphertext);
			BytePositionString = Tools.ConvertPlaintextToHexString(BytePositionString);
			keyByte = SingleByteCipher.SolveSingleCipherReturnKey(BytePositionString);
			Key[i] = keyByte;
		}
		
		System.out.println(Tools.ConvertBytesToHex(Key));
		BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/ezzel/eclipse-workspace/cryptopals_solutions/src/SolvedRepeatingKeyCiphers.txt"));
		KeyFile = Tools.CreateRepeatingKeyHexString(Key, HexCiphertext.length());
		
		DecodedText = Tools.FixedXOR(HexCiphertext, KeyFile);
		DecodedText = Tools.ConvertHexStringToPlaintext(DecodedText);
		System.out.println(DecodedText);
		writer.write(DecodedText);
		writer.flush();
		writer.close();
		Reader.close();
		
	}
	
	public static String CreateStringByKeyPosition(int keysize, int position, String Ciphertext) {
		
		int counter = 0;
		String KeyPositionComposite = "";
		
		for(int i = 0; i < Ciphertext.length(); i++)
		{
			if(counter == position) {
				KeyPositionComposite = KeyPositionComposite.concat(Ciphertext.substring(i,i+1));
			}
			counter++;
			if(counter == keysize) {
				counter = 0;
			}
		}
		
		return KeyPositionComposite;
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
	
	
	public static int BreakRepeatingKeyCipherKeySize(String Ciphertext) throws Exception {
		
		// Work through Keysizes between 2 and 40
		int keysize;
		double MinHammingDistance = 1500000000;
		double[] KeySizeHammingScores = new double[40];
		double CummulativeDistance = 0;
		
		for(keysize = 2; keysize < 40; keysize++) {
			// Reset the Distance counter
			CummulativeDistance = 0;
			// Cut the start of the string into smaller pieces of size keySize
			String Cipher1 = Ciphertext.substring(0, keysize);
			String Cipher2 = Ciphertext.substring(keysize,2*keysize);
			String Cipher3 = Ciphertext.substring(2*keysize,3*keysize);
			String Cipher4 = Ciphertext.substring(3*keysize,4*keysize);
			
			
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher1, Cipher2)/(keysize);
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher2, Cipher3)/(keysize);
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher1, Cipher3)/(keysize);
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher1, Cipher4)/(keysize);
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher2, Cipher4)/(keysize);
			CummulativeDistance+= Tools.ComputeHammingDistance(Cipher3, Cipher4)/(keysize);
			
			KeySizeHammingScores[keysize] = CummulativeDistance/6;
		}
		
		for(int i = 2; i<40; i++) {
			System.out.println(KeySizeHammingScores[i]);
			if (KeySizeHammingScores[i] < MinHammingDistance) {
				MinHammingDistance = KeySizeHammingScores[i];
				keysize = i;
			}
		}
		
		System.out.println("Smallest Hamming Distance is keysize: " +keysize);
		return keysize;
		
	}
}
