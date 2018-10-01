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
		String fileOutBWT = "outBWT";
		
		// RLE
		StringBuilder builderRLE = new StringBuilder();
		String fileOutRLE = "outRLE";
		
		// Huffman
		String fileOutHuffman = "outHuffman";

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
			arrayByte = FileHandler.splitFile(inputFile, 30000);

			// Codifica o array com os blocos
			for (int i = 0; i < arrayByte.length; i++) {
				encodeBWT = new String (arrayByte[i], "ISO-8859-1");
				encodeBWT = BWT.encode(encodeBWT);
				builderBWT.append(encodeBWT);
			}
			
			// Grava o arquivo compactado
			FileHandler.write(fileOutBWT, builderBWT.toString());

			// RLE
			File f = new File(fileOutBWT);
			RLE rle = new RLE(f);
			builderRLE = rle.lire();
			rle.compression(builderRLE, fileOutRLE);
			
			// Codifica huffman
			HuffmanCompress huffman = new HuffmanCompress();
			huffman.start(new File(fileOutRLE), new File(fileCompressed));

			// ##### D E C O D I F I C A ####

			// Decodifica huffman
			HuffmanDecompress huffmanDec = new HuffmanDecompress();
			huffmanDec.start(new File(fileCompressed), new File(fileOutHuffman));

			// Decodifica RLE
			BitInputStream input = new BitInputStream(new FileInputStream(fileOutHuffman));
			rle.decompression(input, fileOutRLE);

			// Decodifica BWT
			// lê o arquivo
			file = FileHandler.read(fileOutRLE);
			
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
