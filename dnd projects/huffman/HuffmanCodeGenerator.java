import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class HuffmanCodeGenerator {
	private HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
	private HuffmanTree tree;
	private HashMap<Character, String> codes = new HashMap<Character, String>();
	
	public HuffmanCodeGenerator (String inputFile) throws IOException {
		BufferedReader br = new BufferedReader (new FileReader(inputFile));
		while (br.ready()) {
			char current = (char) br.read();
			if (!freq.containsKey(current)) {
				freq.put(current, 1);
			} else {
				freq.replace(current, (freq.get(current) + 1));
			}
		}
		br.close();
		tree = new HuffmanTree (freq);
	}
	
	public int getFrequency (char c) {
		if (freq.containsKey(c)) {
			return freq.get(c);
		} else {
			return 0;
		}
	}
	
	public void makeCodeFile(String codeFile) throws IOException {
		PrintWriter pw  = new PrintWriter(codeFile);
		for (int i = 0; i < 128; i++) {
			char c = (char) i;
			if (!freq.containsKey(c)) {
				pw.print("\n");
			} else {
				pw.print(c + ": " + getCode(c) + "\n");
			}
		}
		pw.close();
	}
	
	public String getCode (char c) {
		storeCodes(tree.getRoot(), "");
		return codes.get(c);
	}
	
	public void storeCodes (HNode current, String code) {
		if (current.getKey() != null) {
			codes.put(current.getKey(), code);
		}
		if (current.hasLeft()) {
			storeCodes (current.getLeft(), code + "0");
		}
		if (current.hasRight()) {
			storeCodes (current.getRight(), code + "1");
		}
	}
}
