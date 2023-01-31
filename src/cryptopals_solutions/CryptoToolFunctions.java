package cryptopals_solutions;
import java.util.*;
import java.util.Base64.Encoder;

import org.apache.commons.codec.binary.Hex;


public class CryptoToolFunctions {
	
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
		
		String DecodedString = new String(DecodedBytes);
		
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


}
