package cryptopals_solutions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class CryptoToolFunctions_Tests {

	static CryptoToolFunctions Tools;
	
	@BeforeAll
	static void setup() {
		Tools = new CryptoToolFunctions();
	}
	
	@Test
	public void HexToBase64_Test() throws Exception {
		String hex = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d";
		String B64Answer = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t";
		String Base64 = Tools.HexToBase64(hex);
		assertEquals(B64Answer, Base64);
	}

}
