package cryptopals_solutions;
import java.util.*;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.Hex;

public class CryptoToolFunctions {
	
	
	/*
	 * This Hashmap represents the character frequencies for the english language.
	 * 
	 * Src: https://en.wikipedia.org/wiki/Letter_frequency
	 * 
	 * Space value was increased until correct plaintext was found
	 */
	private static final HashMap<Character,Integer> FrequencyMap;
		static {
			FrequencyMap = new HashMap<Character,Integer>(); 
			FrequencyMap.put('a', 820);
			FrequencyMap.put('b', 150);
			FrequencyMap.put('c', 280);
			FrequencyMap.put('d', 430);
			FrequencyMap.put('e', 1300);
			FrequencyMap.put('f', 220);
			FrequencyMap.put('g', 200);
			FrequencyMap.put('h', 610);
			FrequencyMap.put('i', 700);
			FrequencyMap.put('j', 15);
			FrequencyMap.put('k', 77);
			FrequencyMap.put('l', 400);
			FrequencyMap.put('m', 240);
			FrequencyMap.put('n', 670);
			FrequencyMap.put('o', 750);
			FrequencyMap.put('p', 190);
			FrequencyMap.put('q', 9);
			FrequencyMap.put('r', 600);
			FrequencyMap.put('s', 630);
			FrequencyMap.put('t', 910);
			FrequencyMap.put('u', 280);
			FrequencyMap.put('v', 98);
			FrequencyMap.put('w', 240);
			FrequencyMap.put('x', 15);
			FrequencyMap.put('y', 200);
			FrequencyMap.put('z', 7);
			FrequencyMap.put(' ', 300);
		}
		
		// Initialize a hex object for decoding
		private static final Hex Hex;
		static {
			Hex = new Hex();
		}
	
	public static String HexToBase64(String hex) throws Exception {
		
		// Prepare byte array for processing
		byte[] DecodedBytes = null;
		
		// Try to decode bytes and handle exception on decoder failure
		try {
			DecodedBytes = Hex.decodeHex(hex);
		}
		catch(Exception e)
		{
			System.out.println("Exception occured in decoding hex string:");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Encode to Base64 and return
		String B64Encoded = Base64.getEncoder().encodeToString(DecodedBytes);
		return B64Encoded;
	}
	
	public static String FixedXOR(String Buffer1, String Buffer2) throws Exception {
		
		// Prepare byte arrays for processing String inputs
		byte[] bytes1 = null;
		byte[] bytes2 = null;
		
		
		// Attempt to decode bytes from hex string arguments, handle decoder errors
		try {
			bytes1 = Hex.decodeHex(Buffer1);
			bytes2 = Hex.decodeHex(Buffer2);
		}
		catch(Exception e) {
			System.out.println("Error in hex decoding:");
			e.printStackTrace();
			System.exit(1);
		}
		
		// Create a byte array for output
		byte[] bytesXor = new byte[bytes1.length];
		
		// Compute the XOR operation on the individual bytes
		for(int i = 0; i < bytesXor.length; i++) {
			bytesXor[i] = (byte) (bytes1[i] ^ bytes2[i]);
		}
		
		// Encode the bytes back to a String of Hex values
		String answer = Hex.encodeHexString(bytesXor);
		return answer;
	}
	
	public static int PlaintextFrequencyScore(String Plaintext) {
		
		// Initialize counters and placeholder variables
		int CharValue = 0;
		int TotalValue = 0;
		
		// Set string to lowercase for hashmap lookup
		String lower = Plaintext.toLowerCase();
		
		// Break apart the String into a character array for processing
		char[] brokenPlain = lower.toCharArray();
		
		// Loop through characters and sum the value of the characters based upon frequency
		for(char c: brokenPlain) {
			if (FrequencyMap.containsKey(c)) {
				CharValue = FrequencyMap.get(c);
				TotalValue += CharValue;
			}
		}
		
		return TotalValue;
	}
	
	public static String CreateSingleKeyHexString(byte key, int length) {
		
		// Create character Array for creating strings of characters
		byte[] CipherArray = new byte[length];
		
		
		// Fill array with specified character
		for(int i = 0; i < CipherArray.length; i++) {
			CipherArray[i] = key;
		}
		
		// Convert to String and return
		String KeyString = ConvertBytesToHex(CipherArray);
		return KeyString;	
	}
	
	public static String ConvertBytesToHex(byte[] bytes) {
		
		// Convert an array of bytes to a hex string and returns
		String HexString = Hex.encodeHexString(bytes);
		return HexString;
	}
	
	public static String ConvertHexStringToPlaintext(String HexString) throws Exception {
		
		byte[] PlainBytes = new byte[HexString.length()];
		String Plaintext;
		
		try {
		PlainBytes = Hex.decodeHex(HexString);
		}
		catch(Exception e) {
			System.out.println("Error in hex decoding:");
			e.printStackTrace();
			System.exit(1);
		}
		
		Plaintext = new String(PlainBytes);
		return Plaintext;
	}

}
