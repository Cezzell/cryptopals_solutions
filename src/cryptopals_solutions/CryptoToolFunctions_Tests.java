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

	@Test
	public void FixedXOR_Test() throws Exception {
		String Buffer1 = "1c0111001f010100061a024b53535009181c";
		String Buffer2 = "686974207468652062756c6c277320657965";
		String Check = "746865206b696420646f6e277420706c6179";
		String Xor = Tools.FixedXOR(Buffer1, Buffer2);
		assertEquals(Xor,Check);	
	}
	
	@Test
	public void PlaintextFrequencyScore_Test() {
		String TestString = "Each word accounted for.";
		int ActualScore = 117;
		int Score = Tools.PlaintextFrequencyScore(TestString);
		assertEquals(ActualScore,Score);
	}
	
}
