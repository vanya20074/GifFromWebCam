import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class CapturePhoto implements Runnable {

    private static final int PHOTOS_COUNT = 100;
    private static final String PHOTOS_DIR = "photos";
    private static final String OUT_GIF_FILE = "test5.gif";
    private static final int CAPTURE_DELAY = 10000;
    private static final int SLIDE_DELAY = 200;

    public static void main(String[] args) throws InterruptedException, IOException {
        Runnable r = new CapturePhoto();
        Thread t = new Thread(r);
        t.start();


        }

    @Override
    public void run() {
        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(new Dimension(640, 480));
        AnimatedGifEncoder enc = new AnimatedGifEncoder();
        enc.setRepeat(0);
        enc.start(PHOTOS_DIR + "/" + OUT_GIF_FILE);
        enc.setDelay(SLIDE_DELAY);
        BufferedImage image = null;

        for (int i = 0; i < PHOTOS_COUNT; i++) {
            webcam.open();
            try {
                image = webcam.getImage();
                ImageIO.write(image, "PNG", new File(PHOTOS_DIR + "/img/photo" + i + ".png"));
                System.out.println("photos/img/photo" + i + ".png was made");

            } catch (Exception e) {
                e.printStackTrace();
            }
            enc.addFrame(image);
            webcam.close();
            try {
                Thread.sleep(CAPTURE_DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (enc.finish())System.out.println("Gif animation was created");
    }
}
