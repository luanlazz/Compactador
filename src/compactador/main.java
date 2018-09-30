package compactador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import BWT.BWT;
import FileHandler.FileHandler;
import Huffman.*;
import RLE2.RLE;
import RLE2.BitInputStream;

public class main {

	public static void main(String[] args) {
		String fileInput = "alice29.txt";
		String fileCompressed = "compactado.txt";
		String fileUncompressed = "descompactado.txt";

		// Arquivo entrada
		String file;

		// BWT
		byte[][] arrayByte;
		String encodeBWT;
		StringBuilder builderBWT = new StringBuilder();

		// RLE
		String decodeRLE;
		StringBuilder builderRLE = new StringBuilder();

		// HUFFMAN
		StringBuilder builderHuffman = new StringBuilder();

		// RLE
		try {
			/*
			 * PIPELINE:
			 * BWT -> RLE -> Huffman
			 */

			// ##### C O D I F I C A #####

			// Codifica BWT
			File inputFile  = new File(fileInput);

			// Divide arquivo em blocos de X caracteres
			arrayByte = FileHandler.splitFile(inputFile, 20000);

			// Codifica o array com os blocos
			for (int i = 0; i < arrayByte.length; i++) {
				encodeBWT = new String (arrayByte[i], "ISO-8859-1");
				encodeBWT = BWT.encode(encodeBWT);
				builderBWT.append(encodeBWT);
			}
			
			// Grava o arquivo compactado
			FileHandler.write(fileCompressed, builderBWT.toString());

			// RLE
			File f = new File(fileCompressed);
			RLE r = new RLE(f);
			StringBuffer s = r.lire();
			r.compression(s);
			
			// Codifica huffman
			HuffmanCompress huffman = new HuffmanCompress();
			huffman.start(new File(fileCompressed), new File(fileCompressed));

			// ##### D E C O D I F I C A ####

			// Decodifica huffman
			HuffmanDecompress huffmanDec = new HuffmanDecompress();
			huffmanDec.start(new File(fileCompressed), new File(fileUncompressed));

			// lê o arquivo
			file = FileHandler.read(fileUncompressed);

			// Decodifica RLE
			BitInputStream input = new BitInputStream(new FileInputStream("rl.txt"));
			r.decompression(input);

			// Decodifica BWT
			// Separa os blocos novamente para decodificar
			String block[] = file.split("(\\@)");

			builderBWT.delete(0, builderBWT.length());
			// Decodifica por blocos
			for (int i = 0; i < block.length; i++) {
				builderBWT.append(BWT.decode(block[i]));
			}

			// Grava o arquivo descompactado
			FileHandler.write(fileUncompressed, builderBWT.toString());


		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
