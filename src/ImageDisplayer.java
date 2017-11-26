import javax.media.jai.PlanarImage;
import java.awt.*;

public class ImageDisplayer extends Canvas implements ImageAppearanceListener {
    private PlanarImage image;

    public ImageDisplayer() {
        setSize(200, 100);
    }


    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        this.image = event.getImage();
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        if (image != null) {
            int height = image.getHeight();
            int width = image.getWidth();
            setSize(width, height);
            graphics.drawImage(image.getAsBufferedImage(), 0, 0, null);
        }
    }
}
