import java.io.*;
import java.util.*;
import java.lang.*; 
import java.lang.Object ;
import java.security.GeneralSecurityException;
import com.itextpdf.text.Document;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.security.* ;
import java.security.* ; //.TrustedCertificateEntry;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;

import com.itextpdf.text.pdf.PdfAppearance;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.BaseColor;


//import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;


public class signfield
{
    public static final String src = "/home/pathak/java signature project/resource/setting.pdf";
    public static final String dest = "/home/pathak/signed/Signature field.pdf";
    public static final String tmp = "/home/pathak/signed/temp";

    
    public void main()
        throws GeneralSecurityException, IOException, DocumentException {
    
        PdfReader reader = new PdfReader(src);
        FileOutputStream outs = new FileOutputStream(dest );
        PdfStamper stamper = new PdfStamper(reader, outs );
        PdfContentByte OverContent = stamper.getOverContent(1);  
         
       
            PdfFormField field = PdfFormField.createSignature(stamper.getWriter());
            field.setFieldName("SIGNAME");

            /*    
            field.setWidget(new Rectangle(72, 732, 144, 780), PdfAnnotation.HIGHLIGHT_OUTLINE);
            field.setFlags(PdfAnnotation.FLAGS_PRINT);
            // add the annotation
            stamper.addAnnotation(field, 1);
            */
         
                    field.setFieldName("SIGNAME");
                    field.setPage(2);
                    field.setWidget(new Rectangle(450, 50, 550, 150), PdfAnnotation.HIGHLIGHT_OUTLINE);
                    field.setFlags(PdfAnnotation.FLAGS_PRINT);
            
            
               
                PdfAppearance tp = PdfAppearance.createAppearance(stamper.getWriter(), 72, 48);
                tp.setColorStroke(BaseColor.BLUE);
                tp.setColorFill(BaseColor.LIGHT_GRAY);
                tp.rectangle(450, 50, 550, 150);
                tp.fillStroke();
                tp.setColorFill(BaseColor.BLUE);
                ColumnText.showTextAligned(tp, Element.ALIGN_CENTER, new Phrase("SIGN HERE"), 36, 24, 25);
                field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, tp);
                    stamper.addAnnotation(field ,2);
        
                
         stamper.close();
         
      }

      
         
         
   }



