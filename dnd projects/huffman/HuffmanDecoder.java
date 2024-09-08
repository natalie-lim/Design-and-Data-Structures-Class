import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanDecoder {

	private HashMap<String, Character> isCode = new HashMap<String, Character>();
	
	public HuffmanDecoder (String codeFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(codeFile));
		for (int i = 0; i < 128; i ++) {
			String code = br.readLine();
			if (code != null) {
				isCode.put(code, (char) i);
			}
		}
		br.close();
	}
	
	//return whether a given string is a single code
	public boolean isCode (String binary) {
		if (binary.equals("")) {
			return false;
		} else if (isCode.get(binary) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	public char decodeChar (String binary) {
		return isCode.get(binary);
	}
	
	public void decodeFile(String encodedFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(encodedFile));
		PrintWriter pw = new PrintWriter (encodedFile.substring(0, encodedFile.length()-4));
		if (!encodedFile.substring(encodedFile.length() - 4).equals(".huf")) {
			pw.close();
			br.close();
			throw new IllegalArgumentException ("Must be a .huf file");
		}
		String binary = "";
		int first = br.read();
		int second;
		String code= "";
		while (br.ready()) {
			String noLeading = Integer.toBinaryString(first);
			while (noLeading.length() < 8) {
				noLeading = "0" + noLeading;
			}
			binary += noLeading;
			second = br.read();
			if (!br.ready()) {
				int padding = Integer.parseInt("" + (char) second);
				binary = binary.substring(0, binary.length()-padding);
				code = "";
			}
			for (int i = 0; i < binary.length(); i++) {
				code += binary.substring(i, i+1);
				if (isCode(code)) {
					char c = decodeChar(code);
					pw.print(c);
					binary = binary.substring(i + 1);
					code = "";
					i = -1;
				}
			}
			code = "";
			first = second;
		}
		br.close();
		pw.close();
	}
	
	
	public void decodeLong (String encodedFile, String decodedFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(encodedFile));
		PrintWriter pw = new PrintWriter (decodedFile);
		String current = "";
		while (br.ready()) {
			if (isCode(current)) {
				pw.print (decodeChar (current));
				current = "";
			} else {
				current += (char) br.read();
			}
		}
		pw.print(decodeChar (current));
		pw.close();
		br.close();
	}
}
