package useZIP4J;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class testUnzip {
	public static void main(String args[]) {
		String source = "/home/cc/Downloads/福建省.zip";
		String destination = "/home/cc/Desktop";
		String append = "/"+System.nanoTime();
		File dir = new File(destination+append);
		dir.mkdir();
		try {
			ZipFile zipFile = new ZipFile(source);
			zipFile.extractAll(destination+append);
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}
