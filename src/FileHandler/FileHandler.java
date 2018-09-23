package FileHandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
	public String read(String path) throws IOException {
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";
		String content = "";
		while (true) {
			if (linha != null) {
				content += linha + "\n";
			}else
				break;

			linha = buffRead.readLine();
		}

		buffRead.close();

		return content;
	}

	public static void write(String path, String str) throws IOException {

		BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));

		buffWrite.append(str);
		buffWrite.close();

	}

	public static void writeByte(String path, byte[] bt) throws IOException {

		DataOutputStream dos = new DataOutputStream (new FileOutputStream (path));

		dos.write(bt);
		
		dos.flush();
		dos.close();

	}

	public byte[] readByte(String path) throws IOException {
		byte[] content = null;
		
		try {
			Path fileLocation = Paths.get(path);
			content = Files.readAllBytes(fileLocation);
			
		} catch (IOException e) {
			//Catch the IO error and print out the message
			System.out.println("Error message: " + e.getMessage());
		}

		return content;

	}
}

