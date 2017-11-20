import javax.media.jai.PlanarImage;
import java.awt.*;

public class ImageDisplayer extends Canvas implements ImageAppearanceListener {
    private PlanarImage image;


    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        this.image = event.getImage();
        this.repaint();
    }

    @Override
    public void paint(Graphics graphics) {
        Dimension dimension = this.getSize();
        int height = dimension.height;
        int width = dimension.width;
        graphics.drawImage(image.getAsBufferedImage(), 0, 0, null);
    }
}
