
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import org.apache.poi.util.IOUtils;

import PatternMatcher.IPatternMatcherParser;

import parser.*;
import crawler.*;


public class Main {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) throws IOException {
		
		
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/9.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		
		Page page = new Page("UTF-8", byte_in);
		
		IPatternMatcherParser pa =  new IPatternMatcherParser() {
			
			@Override
			public void searchText(ArrayList<String> l) {
				// TODO Auto-generated method stub
				System.out.println("Text:\n");
				for(String s: l)
				{
					System.out.println(s);
				}
				return;
			}
		};
		Parser p = new Parser(pa);
		ArrayList<URL> lista = p.process(page);
		System.out.println("URLs:\n");
		for(URL s: lista)
		{
			System.out.println(s.toString());
		}
		
		in.close();
		
		

	}

}
