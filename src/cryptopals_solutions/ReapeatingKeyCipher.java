package cryptopals_solutions;

public class ReapeatingKeyCipher {

	public static CryptoToolFunctions Tools;
	
	public static void main(String[] args) throws Exception{

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
	
}
