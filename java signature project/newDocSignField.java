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

public class newDocSignField
{
    // instance variables - replace the example below with your own
    public void createPdf() throws IOException, DocumentException {
    // step 1: Create a Document
    String filename = "/home/pathak/test.pdf" ;
    Document document = new Document();
    // step 2: Create a PdfWriter
    PdfWriter writer = PdfWriter.getInstance(
    document, new FileOutputStream("/home/pathak/test.pdf"));
    // step 3: Open the Document
    document.open();
    // step 4: Add content
    document.add(new Paragraph("Hello World!"));
    // create a signature form field
    PdfFormField field = PdfFormField.createSignature(writer);
    field.setFieldName("SIGNAME");
    // set the widget properties
    field.setPage();
    field.setWidget(
    new Rectangle(72, 732, 144, 780), PdfAnnotation.HIGHLIGHT_INVERT);
    field.setFlags(PdfAnnotation.FLAGS_PRINT);
    // add it as an annotation
    writer.addAnnotation(field);
    // maybe you want to define an appearance
    PdfAppearance tp = PdfAppearance.createAppearance(writer, 72, 48);
    tp.setColorStroke(BaseColor.BLUE);
    tp.setColorFill(BaseColor.LIGHT_GRAY);
    tp.rectangle(0.5f, 0.5f, 71.5f, 47.5f);
    tp.fillStroke();
    tp.setColorFill(BaseColor.BLUE);
    ColumnText.showTextAligned(tp, Element.ALIGN_CENTER,
    new Phrase("SIGN HERE"), 36, 24, 25);
    field.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, tp);
       }
    }
    
    