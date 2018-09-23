package FileHandler;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String file = "";
		String fileOutput = "resultado.txt";

		final String path = "alice29.txt";

		try {
			String umaString = "1010001";
			byte[] umByteArray = umaString.getBytes();
			
			FileHandler.writeByte(fileOutput, umByteArray);
			
			byte[] file1 = new FileHandler().readByte("resultado.txt"); 
			
			file = new String (file1, "ISO-8859-1");
			
			System.out.println(file);

		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
