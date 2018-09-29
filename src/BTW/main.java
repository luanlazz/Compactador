package BTW;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import FileHandler.FileHandler;

public class main {

	public static void main(String[] args) {
		File inputFile  = new File("alice29.txt");
		StringBuffer[] str;
		
		try {
			str = FileHandler.splitFile(inputFile, 200);
			str.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		/*try (InputStream input = new BufferedInputStream(new FileInputStream(inputFile))){
			while (true) {
				int b = input.read();
				if (b == -1)
					break;
				str.append(b);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/

		/*String encode = BTW.encode(str);
		System.out.println(encode);
		String decode = BTW.decode(encode);
		System.out.println(decode);*/
	}

}
