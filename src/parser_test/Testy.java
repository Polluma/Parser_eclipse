package parser_test;

import static org.junit.Assert.*;

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
import org.junit.BeforeClass;
import org.junit.Test;

import frazeusz.crawler.Page;
import frazeusz.parser.Parser;
import frazeusz.patternMatcher.IPatternMatcherParser;



public class Testy {

	private static Parser parser;
	
	@BeforeClass
	public static void init_parser()
	{
		IPatternMatcherParser pa =  new IPatternMatcherParser() {
			
			@Override
			public void searchText(List<String> l) {
				return;
			}
		};
		parser = new Parser(pa);
	}
	
	@Test
	public void test_0() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNotNull(parser.process(page));
	}
	
	@Test
	public void test_1() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = new ArrayList<String>();
		list_to_test.add("tekst tekst tekst tekst tekst");
		assertEquals(list_to_test, parser.getTextFromHTML(doc));	
	}
	
	@Test
	public void test_2() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test2.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = new ArrayList<URL>();
		list_to_test.add(new URL("http://www.onet.pl"));
		list_to_test.add(new URL("http://www.kurshtml.edu.pl/html/odsylacz_do_adresu_internetowego,zielony.html"));
		assertEquals(list_to_test, parser.getURLsFromHTML(page));	
	}
	
	@Test
	public void test_3() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test2.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = new ArrayList<String>();
		list_to_test.add("onet jakas");
		assertEquals(list_to_test, parser.getTextFromHTML(doc));	
	}
	
	@Test
	public void test_4() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/img_1.jpg");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		assertNull(parser.process(page));
	}
	
	@Test
	public void test_5() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = new ArrayList<String>();
		assertEquals(list_to_test, parser.getTextFromHTML(doc));
	}
	
	@Test
	public void test_6() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = new ArrayList<URL>();
		assertEquals(list_to_test, parser.getURLsFromHTML(page));
	}
	
	@Test
	public void test_7() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = new ArrayList<String>();
		list_to_test.add("tekst onet koszykowka tekst tekst interia tekst");
		assertEquals(list_to_test, parser.getTextFromHTML(doc));
	}
	
	@Test
	public void test_8() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/test4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = new ArrayList<URL>();
		list_to_test.add(new URL("http://www.onet.pl"));
		list_to_test.add(new URL("http://www.gwiazdybasketu.pl"));
		list_to_test.add(new URL("http://www.interia.pl"));
		assertEquals(list_to_test, parser.getURLsFromHTML(page));	
	}
	
	@Test
	public void test_9() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_10() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/1.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_11() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/2.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_12() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_13() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/3.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_14() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_15() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/4.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_16() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/5.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_17() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/5.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_18() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/6.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_19() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/6.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_20() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/7.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_21() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/7.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_22() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/8.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertTrue(list_to_test.size() == 0);
	}
	
	@Test
	public void test_23() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/8.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertFalse(list_to_test.size() == 0);
	}
	
	@Test
	public void test_24() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/9.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		InputStream forUrl = new ByteArrayInputStream(page.getContentData());
		Document doc = Jsoup.parse(forUrl,null,page.getContentType());
		List<String> list_to_test = parser.getTextFromHTML(doc);
		assertNotNull(list_to_test);
		assertTrue(list_to_test.size() == 0);
	}
	
	@Test
	public void test_25() throws IOException 
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/www/9.html");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page("UTF-8", byte_in);
		List<URL> list_to_test = parser.process(page);
		assertNotNull(list_to_test);
		assertTrue(list_to_test.size() == 0);
	}
	
	//testy dla PDFow
	
	@Test
	public void test_26() throws IOException
	{
		String rawTextFromPdf = new String("Tekst\ntekst\ntekst\ntekst\ntekst");
		List<String> text_from_pdf = parser.getTextFromPDF(rawTextFromPdf);
		assertNotNull(text_from_pdf);
		List<String> list_to_test = new ArrayList<String>();
		list_to_test.add("Tekst\ntekst\ntekst\ntekst\ntekst");
		assertEquals(text_from_pdf, list_to_test);
	}
	
	@Test
	public void test_27() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/pdf/1.pdf");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page(null, byte_in);
		List<URL> urls_from_pdf = parser.process(page);
		List<URL> list_to_test = new ArrayList<URL>();
		assertNotNull(urls_from_pdf);
		assertEquals(list_to_test, urls_from_pdf);
	}
	
	@Test
	public void test_28() throws IOException
	{
		InputStream in = new FileInputStream("/home/polluma/Dokumenty/TO2/pdf/2.pdf");
		byte[] byte_in = IOUtils.toByteArray(in);
		Page page = new Page(null, byte_in);
		List<URL> urls_from_pdf = parser.process(page);
		List<URL> list_to_test = new ArrayList<URL>();
		assertNotNull(urls_from_pdf);
		assertEquals(list_to_test, urls_from_pdf);
	}
}
