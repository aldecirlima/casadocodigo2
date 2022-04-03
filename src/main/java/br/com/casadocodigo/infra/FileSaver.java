package br.com.casadocodigo.infra;

import java.io.IOException;

import javax.servlet.http.Part;

public class FileSaver {

	private static final String SERVER_PATH = "D:\\eclipse-workspace\\casadocodigo\\src\\main\\resources";

	public String write(Part arquivo, String path) {
		String relativePath = path + "\\" + arquivo.getSubmittedFileName();
		try {
			arquivo.write(SERVER_PATH + "\\" + relativePath);
			return relativePath;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
