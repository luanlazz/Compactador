package RLE2;

import java.io.*;

class MainRLE {

	public static void main(String [] args) throws IOException {
		File f = new File("codificadoBWT.txt");
		RLE r = new RLE (f);
		StringBuffer s =	r.lire();
		r.compression(s);
		BitInputStream input = new BitInputStream(new FileInputStream("rl.txt"));
		r.decompression(input);
	}
}
