package parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.tika.*;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import crawler.*;
import PatternMatcher.*;

public class Parser {

	private ArrayList<String> temp = new ArrayList<String>();
	private IPatternMatcherParser pa;
	
	public Parser(IPatternMatcherParser pa)
	{
		this.pa = pa;
		temp.add("");
	}
	
	public ArrayList<URL> process(Page page) throws IOException
	{
	    // TODO wywalic input, wyciagnac Stream z klasy Page
		InputStream input = new ByteArrayInputStream(page.getContentData());
	    InputStream check = new ByteArrayInputStream(page.getContentData());
		Tika tike = new Tika();
		String type = null;
		try {
			type = tike.detect(check);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if((type.compareTo("text/html") == 0) || (type.compareTo("application/xhtml+xml") == 0))
		{
			return getURLsfromHTML(page);
		}
		if(type.compareTo("application/pdf") == 0)
		{
			return getURLsfromPDF(page,input);
		}
		if(type.compareTo("text/plain") == 0)
		{
	        return getURLsfromTXT(page, input);
		}
		return null;
		
	}
	
	private ArrayList<URL> getURLsfromHTML(Page p) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		InputStream forUrl = new ByteArrayInputStream(p.getContentData());
		Document doc = Jsoup.parse(forUrl,null,p.getContentType());
		Elements links = doc.select("a[href]");
		for(Element link : links)
		{
			if(link.attr("abs:href") != "")
			{
				URLsList.add(new URL(link.attr("abs:href")));
			}
		}
		pa.searchText(getTextFromHTML(p));
		return URLsList;
	}
	
	public ArrayList<String> getTextFromHTML(Page p) throws IOException
	{
		ArrayList<String> textList = new ArrayList<String>();
		InputStream forTxt = new ByteArrayInputStream(p.getContentData());
		Document doc = Jsoup.parse(forTxt,null,p.getContentType());
		Elements links = doc.select("a[href]");
		for(Element link : links)
		{
			textList.add(link.text());
		}
		textList.add(doc.body().text());
		textList.removeAll(temp);
		return textList;
	}
	
	private ArrayList<URL> getURLsfromPDF(Page p, InputStream in) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		ArrayList<String> textList = new ArrayList<String>();
		PdfReader reader = new PdfReader(in);
		String rawTextFromPdf = "";
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
        	rawTextFromPdf += PdfTextExtractor.getTextFromPage(reader, i);
        }
        Pattern patt = Pattern.compile("http://\\S{3,}\\s?");
        Matcher m = patt.matcher(rawTextFromPdf);
        while(m.find())
        {
        	if(m.group() != "")
        	{
        		URLsList.add(new URL(m.group()));
        	}
        	
        }
        m.replaceAll(" ");
        String fragmented_text[] = rawTextFromPdf.split("http://\\S{3,}\\s?");
        for(String s : fragmented_text)
        {
        	textList.add(s);
        }
        pa.searchText(textList);
		return URLsList;
	}
	
	private ArrayList<URL> getURLsfromTXT(Page p, InputStream in) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		ArrayList<String> textList = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line + "\n");
        }
        String rawText = out.toString();
        reader.close();
        
        Pattern patt = Pattern.compile("http://\\S{3,}\\s?");
        Matcher m = patt.matcher(rawText);
        while(m.find())
        {
        	if(m.group() != "")
        	{
        		URLsList.add(new URL(m.group()));
        	}
        	
        }
        m.replaceAll(" ");
        String fragmented_text[] = rawText.split("http://\\S{3,}\\s?");
        for(String s : fragmented_text)
        {
        	textList.add(s);
        }
        pa.searchText(textList);
        return URLsList;
	}
	
	private Parser(){};
}
