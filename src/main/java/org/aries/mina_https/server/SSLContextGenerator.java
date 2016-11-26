package org.aries.mina_https.server;

import java.io.File;
import java.security.KeyStore;  
import javax.net.ssl.SSLContext;  
import org.apache.mina.filter.ssl.KeyStoreFactory;  
import org.apache.mina.filter.ssl.SslContextFactory;  
  
public class SSLContextGenerator  
{  
    public SSLContext getSslContext()  
    {  
        SSLContext sslContext = null;  
        try  
        {  
            File keyStoreFile = new File("/opt/workspace/IdeaProjects/httpsDemo/mina-https.keystore");
            File trustStoreFile = new File("/opt/workspace/IdeaProjects/httpsDemo/truststore");
  
            if (keyStoreFile.exists() && trustStoreFile.exists()) {  
                final KeyStoreFactory keyStoreFactory = new KeyStoreFactory();  
                System.out.println("Url is: " + keyStoreFile.getAbsolutePath());  
                keyStoreFactory.setDataFile(keyStoreFile);  
                keyStoreFactory.setPassword("123456");
  
                final KeyStoreFactory trustStoreFactory = new KeyStoreFactory();
                trustStoreFactory.setDataFile(trustStoreFile);
                trustStoreFactory.setPassword("123456");
  
                final SslContextFactory sslContextFactory = new SslContextFactory();  
                final KeyStore keyStore = keyStoreFactory.newInstance();  
                sslContextFactory.setKeyManagerFactoryKeyStore(keyStore);  
  
                final KeyStore trustStore = trustStoreFactory.newInstance();  
                sslContextFactory.setTrustManagerFactoryKeyStore(trustStore);  
                sslContextFactory.setKeyManagerFactoryKeyStorePassword("123456");
                sslContext = sslContextFactory.newInstance();  
                System.out.println("SSL provider is: " + sslContext.getProvider());  
            } else {  
                System.out.println("Keystore or Truststore file does not exist");  
            }  
        } catch (Exception ex) {  
            ex.printStackTrace();  
        }  
        return sslContext;  
    }  
}  