package parser_test;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import org.apache.poi.util.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import crawler.Page;

import PatternMatcher.IPatternMatcherParser;

import parser.Parser;

public class Testy {

	private static Parser parser;
	
	@BeforeClass
	public static void init_parser()
	{
		IPatternMatcherParser pa =  new IPatternMatcherParser() {
			
			@Override
			public void searchText(ArrayList<String> l) {
				// TODO Auto-generated method stub
				return;
			}
		};
		parser = new Parser(pa);
	}
	
	@Test
	public void test_1() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_2() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/2.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_3() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_4() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_5() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/5.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_6() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/6.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_7() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/img_1.jpg");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNull(parser.process(page));
	}
	
	@Test
	public void test_8() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/7.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_9() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/8.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_10() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/9.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertEquals(new ArrayList<URL>(), parser.process(page));
	}
	
	@Test
	public void test_11() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.getTextFromHTML(page));
	}
	
	@Test
	public void test_12() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/2.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.getTextFromHTML(page));
	}
	
	@Test
	public void test_13() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.getTextFromHTML(page));
	}
	
	@Test
	public void test_14() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.getTextFromHTML(page));
	}

	@Test
	public void test_15() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/5.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.getTextFromHTML(page));
	}
	
	@Test
	public void test_16() throws IOException {
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/9.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertEquals(new ArrayList<String>(), parser.getTextFromHTML(page));
	}
}
