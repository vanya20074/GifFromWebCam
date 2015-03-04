import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SendFile {

    private static String url = "http://cs609931.vk.com/upload.php?act=add_doc&mid=173197559&aid=0&gid=0&hash=7a26e40333c1049726f1829f67318bbc&rhash=a091c3993db580416498c0ab8e9414c5&api=1";

    public static void main(String[] args) throws IOException {

        /*File file = new File("/");
        if (file.isDirectory()) System.out.println("Dir");
        else if (file.isFile()) System.out.println("File");
        else System.out.println("Error!");*/


    }

    public static String exec(String command) {

        String[] cmd = {"/bin/sh", "-c", command};
       // String[] cmd = {"/bin/sh", "-c", "cd .. && ls"};
        try {
            String line;
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader bri = new BufferedReader
                    (new InputStreamReader(p.getInputStream()));
            BufferedReader bre = new BufferedReader
                    (new InputStreamReader(p.getErrorStream()));
            while ((line = bri.readLine()) != null) {
                System.out.println(line);
            }
            bri.close();
            p.waitFor();
            //System.out.println("Done.");
        } catch (Exception err) {
            err.printStackTrace();
        }
        return null;
    }
} 