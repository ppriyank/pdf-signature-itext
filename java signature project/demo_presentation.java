import java.security.cert.Certificate; 
import java.security.cert.X509Certificate ;
    
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

import java.security.cert.CertificateFactory;
import java.security.cert.CertPath;
import com.itextpdf.text.log.SysoLogger ;
import com.itextpdf.text.log.LoggerFactory;
public class demo_presentation
{

    
    public static final String KEYSTORE = "/home/pathak/java signature project/resource/final/ks";
    public static final char[] PASSWORD = "priyank".toCharArray();
    public static final String src = "/home/pathak/java signature project/resource/setting.pdf";
    public static final String file = "/home/pathak/signed/%s.pdf";
    public static final String temp1 = "/home/pathak/signed/temp.pdf";
    public static final String tmp = "/home/pathak/signed/temp";
    public static final String RESOURCE = "/home/pathak/java signature project/resource/S6.png";

    
    public void sign(String filename, String location, String reason)
    throws GeneralSecurityException, IOException, DocumentException {
   
    BouncyCastleProvider provider = new BouncyCastleProvider();
    Security.addProvider(provider);
    KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
    ks.load(new FileInputStream(KEYSTORE), PASSWORD);
       
    String dest = String.format(file, filename);
    
    
    String alias = "final" ;
    PrivateKey pk = (PrivateKey) ks.getKey(alias, PASSWORD);
    Certificate[] chain = ks.getCertificateChain(alias);
   
    /*
     List mylist = new ArrayList();
    for (int i = 0; i < chain.length; i++) {
      mylist.add(chain[i]);
    }
    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    CertPath cp = cf.generateCertPath(mylist);
    System.out.println(cp);
   */
  
   String provider1 = provider.getName() ;
   CryptoStandard subfilter = CryptoStandard.CMS;
        
        PdfReader reader = new PdfReader(src);
        FileOutputStream os = new FileOutputStream(dest);
        PdfStamper stamper = PdfStamper.createSignature(reader, os, '\0' , new File(tmp),true );      
    
    
     
     PdfSignatureAppearance appearance2 = stamper.getSignatureAppearance();            
     appearance2.setSignatureGraphic(Image.getInstance(RESOURCE));
     appearance2.setRenderingMode(PdfSignatureAppearance.RenderingMode.GRAPHIC_AND_DESCRIPTION);
     appearance2.setReason(reason);
     appearance2.setLocation(location);
     appearance2.setVisibleSignature(new Rectangle(350, 50, 550, 150), 2 ,    "first");   
    
     
     for (int i = 0; i < chain.length; i++) {
         X509Certificate cert = (X509Certificate)chain[i];
         System.out.println(String.format("[%s] %s", i, cert.getSubjectDN()));
         System.out.println(CertificateUtil.getCRLURL(cert));
        }
     
        FileInputStream is = new FileInputStream("/home/pathak/java signature project/resource/final/revoke.crl");
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buf = new byte[1024];
    while (is.read(buf) != -1) baos.write(buf);
    CrlClient crlClient = new CrlClientOffline(baos.toByteArray());
    List<CrlClient> crlList = new ArrayList<CrlClient>();
    crlList.add(crlClient);

     
/*

        for (int i = 0; i < chain.length; i++) {
        X509Certificate cert = (X509Certificate)chain[i];
        System.out.println(String.format("[%s] %s", i, cert.getSubjectDN()));
        System.out.println(CertificateUtil.getOCSPURL(cert));
        }
        
        OcspClient ocspClient = new OcspClientBouncyCastle();
 
        for (int i = 0; i < chain.length; i++) {
            X509Certificate cert = (X509Certificate)chain[i];
            System.out.println(String.format("[%s] %s", i, cert.getSubjectDN()));
            System.out.println(CertificateUtil.getTSAURL(cert));
        }
  */
 
        ExternalSignature es = new PrivateKeySignature(pk, "SHA-256", provider.getName() );   
        ExternalDigest digest = new BouncyCastleDigest();
        MakeSignature.signDetached(appearance2, digest, es, chain ,  crlList, null, null, 0, subfilter);  
        
    
    /*
   
    MakeSignature.signDetached(appearance, digest, pks, chain,
    crlList, ocspClient, tsaClient, estimatedSize, subfilter);
    */
    }
    

}

