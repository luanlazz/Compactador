package compactador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import BWT.BWT;
import FileHandler.FileHandler;
import Huffman.*;
import RLE.*;
import RLE.BitInputStream;

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
		String fileOutBWT = "outBWT.txt";
		
		// RLE
		StringBuffer builderRLE = new StringBuffer();
		
		// Huffman
		String fileOutHuffman = "outHuffman.txt";

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
				encodeBWT = new String (arrayByte[i], "UTF-8");
				if (i == (arrayByte.length -1)) {
					encodeBWT = BWT.encode(encodeBWT, true);
				} else 
					encodeBWT = BWT.encode(encodeBWT, false);
				builderBWT.append(encodeBWT);
			}
			
			// Grava o arquivo compactado
			encodeBWT = builderBWT.toString();
			
			byte[] bt = encodeBWT.getBytes(); 

			FileHandler.writeByte(fileOutBWT, bt);

			// RLE
			File f = new File(fileOutBWT);
			RLE rle = new RLE(f);
			builderRLE = rle.lire();
			rle.compression(builderRLE);
			
			// Codifica huffman
			HuffmanCompress huffman = new HuffmanCompress();
			huffman.start(new File("rl.txt"), new File(fileCompressed));

			// ##### D E C O D I F I C A ####

			// Decodifica huffman
			HuffmanDecompress huffmanDec = new HuffmanDecompress();
			huffmanDec.start(new File(fileCompressed), new File(fileOutHuffman));
			
			// Decodifica RLE
			BitInputStream input = new BitInputStream(new FileInputStream(fileOutHuffman));
			StringBuilder buffer = rle.decompression(input);

			// Decodifica BWT			
			// Separa os blocos novamente para decodificar
			byte[] fileBt = FileHandler.readByte("outRLE.txt");
			
			encodeBWT = new String (fileBt, "UTF-8");

			builderBWT.delete(0, builderBWT.length());

			String block[] = buffer.toString().split("\\@");

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
