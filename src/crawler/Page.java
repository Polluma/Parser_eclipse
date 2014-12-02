package crawler;

public class Page{
   private String contentType;
   private byte[] contentData;
   
   public byte[] getContentData()
   {
	   return contentData;
   }
   
   public String getContentType()
   {
	   return contentType;
   }
   
   public Page(String t, byte[] i)
   {
	   contentType = t;
	   contentData = i;
   }
   

}