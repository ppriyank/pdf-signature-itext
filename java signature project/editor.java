import java.io.*;
import java.util.*;
import java.lang.*; 
import java.lang.Object ;
import java.security.GeneralSecurityException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.security.* ;
import java.security.* ; //.TrustedCertificateEntry;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;


public class editor
{
    
    public static final String dest = "/home/pathak/signed/edited demo1.pdf";
    public static final String file = "/home/pathak/signed/%s.pdf";
    public static final String tmp = "/home/pathak/signed/temp";

    
    public void main(String filename)
        throws GeneralSecurityException, IOException, DocumentException {
        
            String src = String.format(file, filename);
    
        PdfReader reader = new PdfReader(src);
        PdfStamper stamper = new PdfStamper(reader,
          new FileOutputStream(dest))    ;
          
          for(int i=1; i<= reader.getNumberOfPages(); i++){

                    
           stamper.addAnnotation(
                                PdfAnnotation.createText(
                                                            stamper.getWriter(),
                                                            new Rectangle(30f, 750f, 80f, 800f),
                                                            "Edit Info", "The page was edited with this information so that signature fails",
                                                            true,
                                                            null)
                                ,
                                reader.getNumberOfPages()
                             );
                             
   
      }

      
         
         stamper.close();
   }
}


