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
		String fileInput = args[0];
		String fileCompressed = "compactado.txt";
		String fileUncompressed = "descompactado.txt";
		boolean typeFile = false;

		// Arquivo entrada
		String file;

		// BWT
		byte[][] arrayByte;
		String encodeBWT;
		StringBuilder builderBWT = new StringBuilder();
		String fileOutBWT = "compBWT.txt";

		// RLE
		StringBuffer builderRLE = new StringBuffer();

		// Huffman
		String fileOutHuffman = "uncompHuffman.txt";

		// RLE
		try {
			/*
			 * PIPELINE:
			 * BWT -> RLE -> Huffman
			 */

			typeFile = FileHandler.typeFile(FileHandler.read(fileInput));

			if (typeFile) {
				// Codifica huffman
				HuffmanCompress huffman = new HuffmanCompress();
				huffman.start(new File(fileInput), new File(fileCompressed));

				// Decodifica huffman
				HuffmanDecompress huffmanDec = new HuffmanDecompress();
				huffmanDec.start(new File(fileCompressed), new File(fileUncompressed));
			} else {

				// ##### C O D I F I C A #####

				// ### BWT ###
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

				
				// ### RLE ###
				File f = new File(fileOutBWT);
				RLE rle = new RLE(f);
				builderRLE = rle.lire();
				rle.compression(builderRLE);

				// ### Huffman ###
				HuffmanCompress huffman = new HuffmanCompress();
				huffman.start(new File("compRLE.txt"), new File(fileCompressed));

				// ##### D E C O D I F I C A ####

				// ### Huffman ###
				HuffmanDecompress huffmanDec = new HuffmanDecompress();
				huffmanDec.start(new File(fileCompressed), new File(fileOutHuffman));

				// ### RLE ###
				BitInputStream input = new BitInputStream(new FileInputStream(fileOutHuffman));
				StringBuilder buffer = rle.decompression(input);

				// ### BWT ###		
				// Separa os blocos novamente para decodificar
				byte[] fileBt = FileHandler.readByte("uncompRLE.txt");
				encodeBWT = new String (fileBt, "UTF-8");

				builderBWT.delete(0, builderBWT.length());

				String block[] = buffer.toString().split("\\@");
				// Decodifica por blocos
				for (int i = 0; i < block.length; i++) {
					builderBWT.append(BWT.decode(block[i]));
				}

				// Grava o arquivo descompactado
				FileHandler.write(fileUncompressed, builderBWT.toString());	
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
