package RLE;

import java.io.File;
import java.io.IOException;

import FileHandler.FileHandler;

public class mainRLE {

	public static void main(String[] args) {
		String example = "aaaabbbbbcccccr";
		
		// lê o arquivo
		String file = null;
		try {
			file = FileHandler.read("compactado.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Decodifica RLE
		String decodeRLE = RLE.decode(file);
		
		/*
		StringBuilder encode = RLE.encode(example);
		System.out.println(encode);
		String decode = RLE.decode(encode.toString());
		System.out.println(decode);
		*/
	}

}
