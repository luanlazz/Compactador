package BWT;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import FileHandler.FileHandler;

public class mainBWT {

	public static void main(String[] args) {
		String fileInput = "alice29.txt";
		String fileOutput = "codificadoBWT.txt";
		String fileOutput2 = "decodificadoBWT.txt";

		byte[][] arrayByte;
		String encode = "";
		StringBuilder builder = new StringBuilder();

		try {

			File inputFile  = new File(fileInput);

			arrayByte = FileHandler.splitFile(inputFile, 30000);

			for (int i = 0; i < arrayByte.length; i++) {
				encode = new String (arrayByte[i], "UTF-8");
				if (i == (arrayByte.length -1)) {
					encode = BWT.encode(encode, true);
				} else {
					encode = BWT.encode(encode, false);
				}
				builder.append(encode);

			}
			
			encode = builder.toString();
			
			byte[] bt = encode.getBytes(); 

			FileHandler.writeByte(fileOutput, bt);

			
			
			byte[] file = FileHandler.readByte(fileOutput);
			
			encode = new String (file, "UTF-8");

			builder.delete(0, builder.length());

			String block[] = encode.split("\\@");

			// Decodifica por blocos
			for (int i = 0; i < block.length; i++) {
				builder.append(BWT.decode(block[i]));
			}

			System.out.println(builder);
			// Grava o arquivo descompactado
			FileHandler.write(fileOutput2, builder.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

}
