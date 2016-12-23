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
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.MakeSignature;


public class sign_image
{
   

    public static final String KEYSTORE = "/home/pathak/ks";
    public static final char[] PASSWORD = "priyank".toCharArray();
    public static final String src = "/home/pathak/java signature project/resource/setting.pdf";
    public static final String dest = "/home/pathak/signed/demo3.pdf";
    public static final String tmp = "/home/pathak/signed/temp";
    public static final String RESOURCE = "/home/pathak/java signature project/resource/logo.jpg-i10.gif";
    public static final String LOGO = "/home/pathak/java signature project/resource/S6.png" ;
    
        
    public void main()
        throws GeneralSecurityException, IOException, DocumentException {
    
    BouncyCastleProvider provider = new BouncyCastleProvider();
    Security.addProvider(provider);
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(new FileInputStream(KEYSTORE), PASSWORD);
    String alias = "demo";   
    //   String alias = (String)ks.aliases().nextElement();
    PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
    Certificate[] chain = ks.getCertificateChain(alias);
    
  //  SignHelloWorld app = new E04_SignHelloWorld();

    String provider1 =       provider.getName() ;
    CryptoStandard subfilter = CryptoStandard.CMS;
        
        PdfReader reader = new PdfReader(src);
        //        reader.open();
        FileOutputStream os = new FileOutputStream(dest);
        //        os = null ;
        //  St    ring alias = "demo";   
    String reason = "Test 1";
    String location = "Reliance-Jio" ;
    PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0' , new File(tmp),true);      

    PdfSignatureAppearance pdfSignatureAppearance  ;
    pdfSignatureAppearance = stamper.getSignatureAppearance();
    
    

     PdfSignatureAppearance appearance = stamper.getSignatureAppearance();           
     appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
     appearance.setSignatureGraphic(Image.getInstance(LOGO)) ;
     appearance.setImage(Image.getInstance(RESOURCE));
     appearance.setReason("This is only a demo");
     appearance.setLocation("JIo office 2nd floor");
     appearance.setContact("Jio Cloud Services");
     appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_FORM_FILLING);

       appearance.setVisibleSignature(new Rectangle(400, 50, 550, 150), 2 ,    "first");   
       
          
       //       appearance.setVisibleSignature(new Rectangle(450, 50, 550, 150), 2 ,    "first");
       boolean certified = false ;
       boolean graphic = false ;
        if (certified)
            appearance.setCertificationLevel(PdfSignatureAppearance.CERTIFIED_NO_CHANGES_ALLOWED);
        if (graphic) {
            appearance.setSignatureGraphic(Image.getInstance(LOGO));
            appearance.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC);
        }
        
        ExternalSignature es = new PrivateKeySignature(pk, "SHA-256", provider.getName() );   
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance, digest, es, chain,null, null, null, 0, subfilter);  
        
       
//            MakeSignature.signDetached(appearance, digest, es, chain,null, null, null, 0, subfilter);     
    // Creating the signature
  //  ExternalDigest digest = new BouncyCastleDigest();
  //  ExternalSignature signature =
  //  new PrivateKeySignature(pk, digestAlgorithm,  provider.getName());
   // MakeSignature.signDetached(appearance, digest, signature, chain,
   // null, null, null, 0, subfilter);

    }
}

