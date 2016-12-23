import java.security.KeyStore ; //.TrustedCertificateEntry;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Object ;
import java.security.cert.Certificate; 
import java.security.cert.X509Certificate ;

import java.lang.Throwable ;
import java.lang.Exception ;
import java.security.KeyStoreException;
//import java.security ;
import java.math.BigInteger;
import java.io.*;
import java.util.*;
import java.lang.*; 
import java.security.*;
import java.lang.Object;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.io.BufferedInputStream;
import java.security.cert.CertificateFactory;

public class EncryptDecrypt
{

    protected KeyStore ks;
    public EncryptDecrypt(String keystore, String ks_pass)
    throws GeneralSecurityException, IOException {
        initKeyStore(keystore, ks_pass);
    }
    public void initKeyStore(String keystore, String ks_pass)
    throws GeneralSecurityException, IOException {
        ks = KeyStore.getInstance(KeyStore.getDefaultType());  
      ks.load(new FileInputStream(keystore), ks_pass.toCharArray());

    }
    public X509Certificate getCertificate(String alias)
    throws KeyStoreException {
        return (X509Certificate) ks.getCertificate(alias);
    }
        public Key getPublicKey(String alias)
    throws GeneralSecurityException, IOException {
        return getCertificate(alias).getPublicKey();
    }
    public Key getPrivateKey(String alias, String pk_pass)
    throws GeneralSecurityException, IOException {
        return ks.getKey(alias, pk_pass.toCharArray());

    }
    public byte[] encrypt(Key key, String message)
    throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cipherData = cipher.doFinal(message.getBytes());
        return cipherData;
    }
    public String decrypt(Key key, byte[] message)
    throws GeneralSecurityException {
    Cipher cipher = Cipher.getInstance("RSA");
    cipher.init(Cipher.DECRYPT_MODE, key);
    byte[] cipherData = cipher.doFinal(message);
    return new String(cipherData);
    }
    

        // put your code here
        public static void main(String[] args)
        throws GeneralSecurityException, IOException {
            EncryptDecrypt app =
          //  new EncryptDecrypt("/usr/lib/jvm/java-7-oracle/jre/lib/jfxrt.jar", "priyank");
            new EncryptDecrypt("/home/pathak/ks", "priyank");
             Key publicKey = app.getPublicKey("demo");
             System.out.println(publicKey);
             
            Key privateKey = app.getPrivateKey("demo", "priyank");
                           System.out.println (privateKey);
                           
            System.out.println("Let's encrypt: " + args + " with a public key");
//            System.out.println("Let's encrypt 'secret message' with a public key");
            /*
            byte[] encrypted = app.encrypt(publicKey, arg);
            System.out.println("Encrypted message: "
            + new BigInteger(1, encrypted).toString(16));
            System.out.println("Let's decrypt it with the corresponding private key");
            String decrypted = app.decrypt(privateKey, encrypted);
            System.out.println(decrypted);
            System.out.println("You can also encrypt the message with a private key");
            encrypted = app.encrypt(privateKey, "secret message");
            System.out.println("Encrypted message: "
            + new BigInteger(1, encrypted).toString(16));
            System.out.println("Now you need the public key to decrypt it");
            decrypted = app.decrypt(publicKey, encrypted);
            System.out.println(decrypted);
            */
    }
}
    
