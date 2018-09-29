package compactador;

import java.io.File;
import java.io.IOException;

import FileHandler.FileHandler;
import RLE.RLE;
import BTW.BTW;

public class main {

	public static void main(String[] args) {
		String fileInput = "";
		String fileOutput = "resultado.txt";

		final String path = "alice29.txt";

		try {
			 /*
			 * PIPELINE:
			 * BWT -> RLE -> Huffman
			 */
			
			File inputFile  = new File("alice29.txt");

			StringBuilder BWTEncoded = BTW.encode();
			String RLEEncoded = RLE.encode(BWTEncoded);

			//System.out.println(RLEEncoded);


		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
