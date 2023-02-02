package cryptopals_solutions;
import java.util.*;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.Hex;

public class CryptoToolFunctions {
	
	private static final HashMap<Character,Integer> FrequencyMap;
		static {
			FrequencyMap = new HashMap<Character,Integer>(); 
			FrequencyMap.put('a', 8);
			FrequencyMap.put('b', 2);
			FrequencyMap.put('c', 4);
			FrequencyMap.put('d', 3);
			FrequencyMap.put('e', 11);
			FrequencyMap.put('f', 2);
			FrequencyMap.put('g', 2);
			FrequencyMap.put('h', 3);
			FrequencyMap.put('i', 8);
			FrequencyMap.put('j', 1);
			FrequencyMap.put('k', 1);
			FrequencyMap.put('l', 5);
			FrequencyMap.put('m', 3);
			FrequencyMap.put('n', 7);
			FrequencyMap.put('o', 7);
			FrequencyMap.put('p', 3);
			FrequencyMap.put('q', 1);
			FrequencyMap.put('r', 8);
			FrequencyMap.put('s', 6);
			FrequencyMap.put('t', 7);
			FrequencyMap.put('u', 4);
			FrequencyMap.put('v', 1);
			FrequencyMap.put('w', 1);
			FrequencyMap.put('x', 1);
			FrequencyMap.put('y', 2);
			FrequencyMap.put('z', 1);
		}
	
	public static String HexToBase64(String hex) throws Exception {
	/*
	 * @arguments - String following standard Hexadecimal conventions
	 * @return - String converted to Base64 encoding
	 * 
	 */
		
		Hex decoder = new Hex();
		
		byte[] DecodedBytes = null;
		
		try {
			DecodedBytes = decoder.decodeHex(hex);
		}
		catch(Exception e)
		{
			System.out.println("Exception occured in decoding hex string:");
			e.printStackTrace();
			System.exit(1);
		}
		
		String B64Encoded = Base64.getEncoder().encodeToString(DecodedBytes);
		
		return B64Encoded;
	}
	
	public static String FixedXOR(String Buffer1, String Buffer2) throws Exception {
		/*
		 * @arguments - two buffers which are hex strings of equal length
		 * @return - XOR'd bytes of the two hex strings
		 * 
		 * 
		 */
		
		
		byte[] bytes1 = null;
		byte[] bytes2 = null;
		
		try {
			bytes1 = Hex.decodeHex(Buffer1);
			bytes2 = Hex.decodeHex(Buffer2);
		}
		catch(Exception e) {
			System.out.println("Error in hex decoding:");
			e.printStackTrace();
			System.exit(1);
		}
		
		byte[] bytesXor = new byte[bytes1.length];
		
		for(int i = 0; i < bytesXor.length; i++) {
			bytesXor[i] = (byte) (bytes1[i] ^ bytes2[i]);
		}
		
		String answer = Hex.encodeHexString(bytesXor);
		
		return answer;
	}
	
	public static int PlaintextFrequencyScore(String Plaintext) {
		
		int CharValue = 0;
		int TotalValue = 0;
		
		String lower = Plaintext.toLowerCase();
		
		char[] brokenPlain = lower.toCharArray();
		
		for(char c: brokenPlain) {
			if (FrequencyMap.containsKey(c)) {
				CharValue = FrequencyMap.get(c);
				TotalValue += CharValue;
			}
		}
		
		return TotalValue;
	}


}
