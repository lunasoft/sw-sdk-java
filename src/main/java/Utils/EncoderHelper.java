package Utils;

import org.mozilla.universalchardet.UniversalDetector;


import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * Created by asalvio on 10/02/2017.
 */
public class EncoderHelper {

    public static boolean isNotUTF8( String input ) {
        //convenience overload for the most common UTF-8 misinterpretation
        //which is also the case in your question
        return lector( input, "Windows-1252");
    }

    public static boolean lector( String input, String encoding) {

        CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
        CharsetEncoder encoder = Charset.forName(encoding).newEncoder();
        ByteBuffer tmp;
        try {
            tmp = encoder.encode(CharBuffer.wrap(input));
        }

        catch(CharacterCodingException e) {
            return false;
        }

        try {
            decoder.decode(tmp);
            return true;
        }
        catch(CharacterCodingException e){
            return false;
        }
    }

    public static boolean isValidUTF8( String input ) {




        try {
            byte[] buf = new byte[4096];
            int nread;
            FileInputStream fis = new FileInputStream(input);
            UniversalDetector detector = new UniversalDetector(null);
            while((nread= fis.read(buf)) > 0 && !detector.isDone()){
                detector.handleData(buf, 0, nread);
            }
            String encoding = detector.getDetectedCharset();
            if (encoding != null) {
                System.out.println("Detected encoding = " + encoding);
            } else {
                System.out.println("No encoding detected.");
            }

// (5)
            detector.reset();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }

    }

    public static String getDetectedEncoding(File file) throws IOException {
        InputStream is=null;
        String encoding=null;
        try {
            is=new FileInputStream(file);
            UniversalDetector detector=new UniversalDetector(null);
            byte[] buf=new byte[4096];
            int nread;
            while ((nread=is.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf,0,nread);
            }
            detector.dataEnd();
            encoding=detector.getDetectedCharset();
        }
        catch (  IOException e) {
        }
        finally {
            is.close();
            if (encoding == null) {
                return Charset.defaultCharset().name();
            }
        }
        return encoding;
    }

    public static boolean detectBadCharacters(String s) {
        return (s == null) ? false : s.matches("[^A-Za-z0-9 ]");
    }

}
