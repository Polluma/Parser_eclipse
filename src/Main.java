
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.util.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import frazeusz.crawler.*;
import frazeusz.parser.*;
import frazeusz.patternMatcher.IPatternMatcherParser;


public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) throws IOException {
		
		
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		
		Page page = new Page("UTF-8", byte_in);
		
		IPatternMatcherParser pa =  new IPatternMatcherParser() {
			
			@Override
			public void searchText(List<String> l) {
				// TODO Auto-generated method stub
				System.out.println("Text:");
				for(String s: l)
				{
					System.out.println(s);
				}
				return;
			}
		};
		Parser p = new Parser(pa);
		List<URL> lista = p.process(page);
		System.out.println("URLs:");
		/*for(URL s: lista)
		{
			System.out.println(s.toString());
		}*/
		in.close();
	}

}


