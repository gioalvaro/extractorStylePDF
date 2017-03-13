package ar.gov.ingar.PdfExtractor;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
/**
 * Hello world!
 *
 */
public class App 
{
	
    public static void main( String[] args ) throws IOException
    {
    	//Loading an existing document
        File file = new File("S_880001.pdf");
        PDDocument document = PDDocument.load(file);

        //Instantiate PDFTextStripper class
//        PDFTextStripper pdfStripper = new PDFTextStripper();
        PDFTextStripper pdfStripper = new PDFTextStripper() {
            String prevBaseFont = "";

            protected void writeString(String text, List<TextPosition> textPositions) throws IOException
            {
                StringBuilder builder = new StringBuilder();

                for (TextPosition position : textPositions)
                {
                    String baseFont = position.getFont().toString();
                    if (baseFont != null && !baseFont.equals(prevBaseFont))
                    {
                        builder.append('[').append(baseFont).append(']');
                        prevBaseFont = baseFont;
                    }
                    builder.append(position.getUnicode());
                }

                writeString(builder.toString());
            }
        };
        
        //
        pdfStripper.setStartPage(9);
        pdfStripper.setEndPage(9);        
        //Retrieving text from PDF document
        String text = pdfStripper.getText(document);
        System.out.println(text);
        
        //Retrieving format from PDF document
        

        //Closing the document
        document.close();

    }
}
