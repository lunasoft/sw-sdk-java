package Tests.assets;
import org.apache.commons.ssl.PKCS8Key;
import javax.xml.bind.DatatypeConverter;

import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.security.Signature;



/**
 * Created by asalvio on 30/11/2017.
 */
public class Sign {
    private String getKey() {
        try {
            File file = new File("src/test/java/Tests/assets/key.key"); //path: es la ruta del archivo de llave privada
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] fileBytes = new byte[fileInputStream.available()];
            fileInputStream.read(fileBytes);
            fileInputStream.close();
            String fileString = DatatypeConverter.printBase64Binary(fileBytes);
            return fileString;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }

    public String getSign(String cadena, String keyWord){
        try {
            String archivoLlavePrivada = this.getKey();
            InputStream myInputStream = new ByteArrayInputStream(DatatypeConverter.parseBase64Binary(archivoLlavePrivada));
            PKCS8Key pkcs8 = new PKCS8Key(myInputStream, keyWord.toCharArray());
            java.security.PrivateKey pk = pkcs8.getPrivateKey();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(pk);
            signature.update(cadena.getBytes("UTF-8"));
            return new String(DatatypeConverter.printBase64Binary(signature.sign()));
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    public  String getCadena(String xml) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File("src/test/java/Tests/assets/cadenaoriginal_3_3.xslt"));
            Transformer transformer = transformerFactory.newTransformer(xslt);
            Source xmlSource = new StreamSource(new StringReader(xml));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Result out = new StreamResult(baos);
            transformer.transform(xmlSource, out);
            byte[] cadenaOriginalArray = baos.toByteArray();
            String cadOrig = new String(cadenaOriginalArray, "UTF-8");
            return cadOrig;
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
