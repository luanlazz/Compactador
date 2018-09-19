package compactador;

import FileHandler.FileHandler;

import java.io.IOException;

public class main {

	public static void main(String[] args) {
		
		String file = "";

		final String path = "alice29.txt";

		try {
			file = new FileHandler().read(path);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		

	}

}
