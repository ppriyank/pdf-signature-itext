import java.io.*;
import java.util.*;
import java.lang.*; 
import java.lang.Object ;
import java.security.GeneralSecurityException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.security.* ;
import java.security.cert.Certificate; 
import com.itextpdf.text.pdf.PdfSignatureAppearance;

import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.MakeSignature;

public class Signature
{
    

    public static final String src = "/home/pathak/java signature project/resource/setting.pdf";
    public static final String dest = "/home/pathak/signed/check1.pdf";
    public static final String d2 = "/home/pathak/signed/check2.pdf" ;
    public void sign( )
    throws GeneralSecurityException, IOException, DocumentException {
                sign_field name = new sign_field() ;
        individual_sign app = new individual_sign();

             String src1 = "/home/pathak/java signature project/resource/setting.pdf";
             String dest1 = "/home/pathak/signed/try this for multiple signature.pdf";
             String dest2 = "/home/pathak/signed/try this for multiple signature2.pdf";
             String dest3 = "/home/pathak/signed/try this for multiple signature3.pdf";
             
       name.field(src1,dest1 ,"priyank", 250 ,50 ,400 , 150)  ;
       name.field(dest1,dest2 ,"priyank2", 400 ,50 ,550 , 150)  ;
       name.field(dest2,dest3 ,"pryank3" , 100 ,50 ,250 , 150)  ;
       app.sign(PdfSignatureAppearance.CERTIFIED_FORM_FILLING, dest3, dest ,"priyank");
       app.sign(PdfSignatureAppearance.NOT_CERTIFIED, dest, d2 , "priyank2");
       app.sign(PdfSignatureAppearance.NOT_CERTIFIED, d2, "/home/pathak/signed/check3.pdf", "pryank3"); 
    
    
    }
    
    
    
}
