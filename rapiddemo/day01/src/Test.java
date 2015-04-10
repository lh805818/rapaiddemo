import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qince on 2014/12/22.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("hello,intelij ideal!");
        System.out.println("Hello world");

        String str = "admin";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(str.getBytes(Charset.forName("utf8")));
            String digestMsg = byte2Str(bytes);
            System.out.println(digestMsg);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String byte2Str(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return "";
        StringBuffer buf = new StringBuffer();
        for (int i=0;i<bytes.length;i++) {
            if ((bytes[i] & 0xff) < 10) {
                buf.append("0");
            }
            buf.append(Long.toHexString(bytes[i] & 0xff));
        }

        return buf.toString().toUpperCase();
    }
}
