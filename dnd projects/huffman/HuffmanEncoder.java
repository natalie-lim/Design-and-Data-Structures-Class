import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanEncoder {
	private  HashMap<Character, String> codes = new HashMap<Character, String>();
	
	public HuffmanEncoder (String codeFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(codeFile));
		for (int i = 0; i < 128; i ++) {
			String line = br.readLine();
			if (line != null) {
				codes.put((char) (i), line);
			}
		}
		br.close();
	}
	
	public String encodeChar (char input) {
		return codes.get(input);
	}
	
	public void encodeFile (String fileToCompress) throws IllegalArgumentException, IOException {
		BufferedReader br = new BufferedReader (new FileReader(fileToCompress));
		PrintWriter pw = new PrintWriter (fileToCompress + ".huf");
		String current = "";
		String code = "";
		String eight = "";
		while (br.ready()) {
			char c = (char) br.read();
			code = encodeChar(c);
			current += code;
			while (current.length() >= 8) {
				eight = current.substring(0, 8);
				current = current.substring(8);
				int bit = Integer.parseInt(eight, 2);
				char b = (char) bit;
				pw.print(b);
			}
		}
		int idx = 0;
		while (current.length() < 8) {
			current += "0";
			idx++;
		}
		pw.print((char) Integer.parseInt(current, 2));
		pw.print(idx);
		br.close();
		pw.close();
	}
	
	public void encodeLong (String fileToCompress, String encodedFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(fileToCompress));
		PrintWriter pw = new PrintWriter (encodedFile);
		while (br.ready()) {
			char c = (char) br.read();
			pw.print(encodeChar(c));
		}
		br.close();
		pw.close();
	}
	
}
