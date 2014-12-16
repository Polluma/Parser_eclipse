package frazeusz.parser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.apache.tika.*;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;

import frazeusz.crawler.*;
import frazeusz.patternMatcher.*;

public class Parser {

	private ArrayList<String> temp = new ArrayList<String>();
	private IPatternMatcherParser patternMatcherInterface;
	
	public Parser(IPatternMatcherParser PMInterface)
	{
		this.patternMatcherInterface = PMInterface;
		temp.add("");
	}
	
	public List<URL> process(Page page) throws IOException
	{	
		//TODO Debug
		
		System.out.println("Processing");
		
		//Debug end
		
		InputStream check = new ByteArrayInputStream(page.getContentData());
		Tika tike = new Tika();
		String type = null;
		try {
			type = tike.detect(check);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if((type.compareTo("text/html") == 0) || (type.compareTo("application/xhtml+xml") == 0))
		{
			return getURLsFromHTML(page);
		}
		if(type.compareTo("application/pdf") == 0)
		{
			return getURLsFromPDF(page);
		}
		if(type.compareTo("text/plain") == 0)
		{
	        return getURLsfromTXT(page);
		}
		return null;
		
	}
	
	public List<URL> getURLsFromHTML(Page p) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		InputStream streamForUrl = new ByteArrayInputStream(p.getContentData());
		Document doc = Jsoup.parse(streamForUrl,null,p.getContentType());
		Elements links = doc.select("a[href]");
		for(Element link : links)
		{
			if(link.attr("abs:href") != "")
			{
				URLsList.add(new URL(link.attr("abs:href")));
				
				//TODO Debug
				System.out.println(link.attr("abs:href"));
				
				//Debug end
			}
		}
		patternMatcherInterface.searchText(getTextFromHTML(doc));
		return URLsList;
	}
	
	public List<String> getTextFromHTML(Document doc) throws IOException
	{
		ArrayList<String> textList = new ArrayList<String>();
		textList.add(doc.body().text());
		textList.removeAll(temp);
		return textList;
	}
	
	private List<URL> getURLsFromPDF(Page p) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		InputStream streamForUrl = new ByteArrayInputStream(p.getContentData());
		PdfReader reader = new PdfReader(streamForUrl);
		String rawTextFromPdf = "";
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
        	rawTextFromPdf += PdfTextExtractor.getTextFromPage(reader, i);
        }
        Pattern patt = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)");
        Matcher m = patt.matcher(rawTextFromPdf);
        while(m.find())
        {
        	if(m.group() != "")
        	{
        		URLsList.add(new URL(m.group()));
        	}
        	
        }
        m.replaceAll(" ");
        patternMatcherInterface.searchText(getTextFromPDF(rawTextFromPdf));
		return URLsList;
	}
	
	public List<String> getTextFromPDF(String rawTextFromPdf)
	{
		ArrayList<String> textList = new ArrayList<String>();
		String fragmented_text[] = rawTextFromPdf.split("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)");
        for(String s : fragmented_text)
        {
        	if(s.length() != 0)
        	{
        		textList.add(s);
        	}
        }
        textList.removeAll(temp);
        return textList;
	}
	
	public List<URL> getURLsfromTXT(Page p) throws IOException
	{
		ArrayList<URL> URLsList = new ArrayList<URL>();
		InputStream streamForUrl = new ByteArrayInputStream(p.getContentData());
		BufferedReader reader = new BufferedReader(new InputStreamReader(streamForUrl));
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
        patternMatcherInterface.searchText(getTextfromTxt(rawText));
        return URLsList;
	}
	
	public List<String> getTextfromTxt(String rawText)
	{
		ArrayList<String> textList = new ArrayList<String>();
        String fragmented_text[] = rawText.split("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)");
        for(String s : fragmented_text)
        {
        	textList.add(s);
        }
		return textList;
	}
	
	//TODO obsluga formatu doc
	
	
}
