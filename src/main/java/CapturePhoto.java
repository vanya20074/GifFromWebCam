import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CapturePhoto implements Runnable {


    private static final int PHOTOS_COUNT = 1000;
    private static final String PHOTOS_DIR = "photos";
    private static final String OUT_GIF_FILE = "lonftime.gif";
    private static final int CAPTURE_DELAY = 10000;
    private static final int SLIDE_DELAY = 50;

    public static void main(String[] args) throws InterruptedException, IOException {
        Runnable r = new CapturePhoto();
        Thread t = new Thread(r);
        t.start();


        }

    @Override
    public void run() {
        Webcam webcam = Webcam.getDefault();
      //  webcam.setViewSize(new Dimension(640, 480));
        webcam.setViewSize(new Dimension(320, 240));
        AnimatedGifEncoder enc = new AnimatedGifEncoder();
        enc.setRepeat(0);
        /**
         * 10 - default
         * 1 - max
         */
        enc.setQuality(20);
        enc.start(PHOTOS_DIR + "/" + OUT_GIF_FILE);
        enc.setDelay(SLIDE_DELAY);
        BufferedImage image = null;
        webcam.open();
        for (int i = 0; i < PHOTOS_COUNT; i++) {

            try {
                image = webcam.getImage();
                ImageIO.write(image, "PNG", new File(PHOTOS_DIR + "/img/photo" + i + ".png"));
                System.out.println("photos/img/photo" + i + ".png was made");
                Thread.sleep(CAPTURE_DELAY);
            } catch (Exception e) {
                e.printStackTrace();
            }
            enc.addFrame(image);

        }
        webcam.close();
        if (enc.finish())System.out.println("Gif animation was created");
    }
}
