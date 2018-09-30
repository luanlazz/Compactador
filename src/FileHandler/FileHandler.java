package FileHandler;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandler {
	public static String read(String path) throws IOException {
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

	public static byte[] readByte(String path) throws IOException {
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

	public static byte[][] splitFile(File file, int num) throws IOException{
		try (InputStream input = new BufferedInputStream(new FileInputStream(file))){
			long sizeFile = Files.size(file.toPath());
			long sizeBlock = num;
			if (sizeFile < num)
				sizeBlock = sizeFile;
			
			int sizeBuffer = (int) (sizeFile / sizeBlock);
			if ((sizeBuffer * sizeBlock) < sizeFile)
				sizeBuffer += 1;

			byte[][] arrayByte = new byte[sizeBuffer][(int)sizeBlock];

			for (int i = 0; i < arrayByte.length; i++) {

				for (int j = 0; j < arrayByte[i].length; j++) {
					int b = input.read();
					if (b == -1)
						break;
					arrayByte[i][j] = (byte) b;
				}

			}
			return arrayByte;
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static byte[] splitInverse(byte[][] arrayByte) throws IOException{
		byte[] arrayByte2 = new byte[(arrayByte.length*200)];
		int index = 0;

		for (int i = 0; i < arrayByte.length; i++) {

			for (int j = 0; j < arrayByte[i].length; j++) {
				arrayByte2[index++] = (arrayByte[i][j]);
			}

		}
		return arrayByte2;
	}


}

