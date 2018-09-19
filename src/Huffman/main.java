package Huffman;

public class main {

	public static void main(String[] args) {
		String test = "banana";

		// we will assume that all our characters will have
		// code less than 256, for simplicity
		int[] charFreqs = new int[256];
		// read each character and record the frequencies
		for (char c : test.toCharArray())
			charFreqs[c]++;

		// build tree
		HuffmanTree tree = HuffmanCode.buildTree(charFreqs);

		// print out results
		System.out.println("SYMBOL\tWEIGHT\tHUFFMAN CODE");
		HuffmanCode.printCodes(tree, new StringBuffer());

	}

}
