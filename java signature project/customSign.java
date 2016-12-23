import java.io.*;
import java.util.*;
import java.lang.*; 
import java.lang.Object ;
import java.security.GeneralSecurityException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;  
import java.security.KeyStore ; //.TrustedCertificateEntry;
import com.itextpdf.text.pdf.security.* ;
import java.security.* ; //.TrustedCertificateEntry;
import java.security.cert.Certificate; 
import java.security.cert.X509Certificate ;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard ;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.AcroFields;



public class customSign
    {
        public static final String reason = "only for demo purpose";
        public static final String location = "Jio office 2nd Floor";
        public static final String name = "SIGNAME";
        
        
    public static final String KEYSTORE = "/home/pathak/ks";
    public static final char[] PASSWORD = "priyank".toCharArray();
    public static final String src = "/home/pathak/signed/Signature field.pdf";
    public static final String dest = "/home/pathak/signed/Field filled with sign.pdf";
    public static final String tmp = "/home/pathak/signed/temp";
    public static final String RESOURCE = "/home/pathak/java signature project/resource/logo.jpg-i10.gif";
    
        
    public void sign( )
    throws GeneralSecurityException, IOException, DocumentException 
    {   
        
        BouncyCastleProvider provider = new BouncyCastleProvider();
        Security.addProvider(provider);
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(new FileInputStream(KEYSTORE), PASSWORD);
        String alias = "demo";   
        PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
        Certificate[] chain = ks.getCertificateChain(alias);
        String provider1 =       provider.getName() ;
        CryptoStandard subfilter = CryptoStandard.CMS;
    
        
        // Creating the reader and the stamper
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0');
        // Creating the appearance
        PdfSignatureAppearance appearance = stamper.getSignatureAppearance();
        appearance.setReason(reason);
        appearance.setLocation(location);
        appearance.setImage(Image.getInstance(RESOURCE));
        appearance.setVisibleSignature(name);
        // Creating the appearance for layer 0
        PdfTemplate n0 = appearance.getLayer(0);
        float x = n0.getBoundingBox().getLeft();
        float y = n0.getBoundingBox().getBottom();
        float width = n0.getBoundingBox().getWidth();
        float height = n0.getBoundingBox().getHeight();
        n0.setColorFill(BaseColor.LIGHT_GRAY);
        n0.rectangle(x, y, width, height);
        n0.fill();
        // Creating the appearance for layer 2
        PdfTemplate n2 = appearance.getLayer(2);
        ColumnText ct = new ColumnText(n2);
        ct.setSimpleColumn(n2.getBoundingBox());
        Paragraph p = new Paragraph("signature is for demo purpose only");
        ct.addElement(p);
        ct.go();
        // Creating the signature
         
            ExternalSignature es = new PrivateKeySignature(pk, "SHA-256", provider.getName() );   
            ExternalDigest digest = new BouncyCastleDigest();
            MakeSignature.signDetached(appearance, digest, es, chain,null, null, null, 0, subfilter);     
            
            
   
            }
}
