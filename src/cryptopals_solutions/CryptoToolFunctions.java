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
		System.out.println("Hex String: " + hex);
		
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
		
		System.out.println("Hex String Decoded: " + DecodedString);
		
		String B64Encoded = Base64.getEncoder().encodeToString(DecodedBytes);
		
		System.out.println("Base64 Encoded String: " + B64Encoded);
		
		return B64Encoded;
	}

}
