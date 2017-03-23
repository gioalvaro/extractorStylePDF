package ar.gov.ingar.PdfExtractor;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Apache Commons IO
import org.apache.commons.*;
import org.apache.commons.io.FileUtils;

//PDFBOX Library
//import org.apache.pdfbox.pdfparser.PDFParser;
//import org.apache.pdfbox.pdmodel.*;
//import org.apache.pdfbox.pdmodel.font.PDFont;
//import org.apache.pdfbox.text.PDFTextStripper;
//import org.apache.pdfbox.text.TextPosition;

//Tika Library
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.parser.pdf.PDFParserConfig;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class App 
{
	@SuppressWarnings("deprecation")
	public static void leerArhivoPDFBOX(String mfile) throws IOException{
//		String dfile = mfile;
//    	//Loading an existing document
//        File file = new File(mfile);
//        PDDocument document = PDDocument.load(file);
//
//        //Instantiate PDFTextStripper class
////        PDFTextStripper pdfStripper = new PDFTextStripper();
//        PDFTextStripper pdfStripper = new PDFTextStripper() {
//            String prevBaseFont = "";
//
//            protected void writeString(String text, List<TextPosition> textPositions) throws IOException
//            {
//                StringBuilder builder = new StringBuilder();
//
//                for (TextPosition position : textPositions)
//                {
//                    String baseFont = position.getFont().toString();
//                    if (baseFont != null && !baseFont.equals(prevBaseFont))
//                    {
//                        builder.append('[').append(baseFont).append(']');
//                        prevBaseFont = baseFont;
//                    }
//                    builder.append(position.getUnicode());
//                }
//
//                writeString(builder.toString());
//            }
//        };
//        
//        //
////        pdfStripper.setStartPage(3);
////        pdfStripper.setEndPage(3);        
//        //Retrieving text from PDF document
//        String text = pdfStripper.getText(document);
//        System.out.println(text);
//        FileUtils.writeStringToFile(new File("PDFBOXParser.txt"), text);
//        
//        //Retrieving format from PDF document
//        
//
//        //Closing the document
//        document.close();
	}
    @SuppressWarnings("deprecation")
	public static void leerArchivoTika(String mfile) throws IOException, TikaException{
    	File file = new File(mfile);
    	BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        InputStream inputstream = new FileInputStream(file);
        ParseContext pcontext = new ParseContext();
        org.apache.tika.parser.pdf.PDFParser pdfparser = new PDFParser(); 
        try {
			pdfparser.parse(inputstream,handler,metadata,pcontext);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        //getting the content of the document
        System.out.println("Contents of the PDF :" + handler.toString());
        FileUtils.writeStringToFile(new File("TikaParser.txt"), handler.toString()); 
        
//        getting metadata of the document
        System.out.println("Metadata of the PDF:");
        String[] metadataNames = metadata.names();
        
        for(String name : metadataNames) {
           System.out.println(name+ " : " + metadata.get(name));
        }
    }
	public static void extraerNER(String mfile) throws IOException{
    	
    }
//    public static void extraerTexto(String mfile);
//    public static void extraerCodigo(String mfile);
//    public static void generarOWL(String destination);
//    public static void ensamblaje();
	public static void main( String[] args ) 
    {
    	try {
    		leerArchivoTika("ISO.pdf");
			String input = "product_requirement";
			Pattern p = Pattern.compile("^\\w+_\\w+_*\\w*$");
			Matcher m = p.matcher(input);
			while(m.find())
			{				
			    System.out.println(m.group()); //is your string. do what you want
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TikaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	

    }
}
