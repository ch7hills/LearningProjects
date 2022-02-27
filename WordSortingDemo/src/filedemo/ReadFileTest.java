
package filedemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ReadFileTest{
	
	public static void main(String[] args) throws IOException {
		File input = new File("E:\\Practice\\WordSortingDemo\\Test.txt");
		FileReader fReader =  new FileReader(input);
		char[] chars = new char[(int) input.length()];
		int res = fReader.read(chars);
		System.out.println(res);
		String content =  new String(chars);
		//System.out.println(input.exists());
		System.out.println(content);
		String content2 = new String(Files.readAllBytes(Paths.get("E:\\Practice\\WordSortingDemo\\Test.txt")));
		System.out.println(content2);
		
		Scanner in = new Scanner(new FileReader("E:\\Practice\\WordSortingDemo\\Test.txt"));
		StringBuilder sb = new StringBuilder();
		while(in.hasNext()) {
		    sb.append(in.next());
		}
		in.close();
		String content3 = sb.toString();
		System.out.println(content3);
		
		try(BufferedReader br = new BufferedReader(new FileReader("E:\\Practice\\WordSortingDemo\\Test.txt"))) {
		    StringBuilder sb2 = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb2.append(line);
		        sb2.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String content4 = sb2.toString();
		    System.out.println(content4);
		}
		
		String words = "Lorem ipsum dolor sit amet consectetur adipiscing elit";
		String[] wordArr = words.split(" ");
		List<String> wordList =  Arrays.asList(wordArr);
	}

}
