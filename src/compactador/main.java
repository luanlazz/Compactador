package compactador;

import java.io.IOException;

import FileHandler.FileHandler;
import RLE.RLE;
import BWT.BWT;
import Huffman.*;

public class main {

	public static void main(String[] args) {
		String file = "";

		final String path = "alice29.txt";

		try {
			file = new FileHandler().read(path);
			
			/*
			 * PIPELINE:
			 * BWT -> RLE -> Huffman
			 */

			String BWTEncoded = BWT.encode(file);
			String RLEEncoded = RLE.encode(BWTEncoded);
			
			System.out.println(RLEEncoded);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
