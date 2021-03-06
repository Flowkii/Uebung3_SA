import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class ROIFilter implements Serializable, ImageAppearanceListener {
    private PlanarImage inputImage;
    private PlanarImage outputImage;
    private int width;
    private int height;
    private int xOffset;
    private int yOffset;
    private Rectangle rectangle;
    private Vector listeners;

    public ROIFilter() {
        width = 500;
        height = 100;
        xOffset = 30;
        yOffset = 40;
        listeners = new Vector();
    }


    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);

    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);

    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        process();
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        process();

    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
        process();

    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
        process();
    }

    public void getRectangle() {
        rectangle = new Rectangle(xOffset, yOffset, width, height);
    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        inputImage = event.getImage();
        process();
    }

    private void fireImageAppearanceEvent() {
        Vector vector;
        vector = (Vector) listeners.clone();
        ImageAppearanceEvent event = new ImageAppearanceEvent(this, outputImage);
        for (int i = 0; i < vector.size(); i++) {
            ImageAppearanceListener listener = (ImageAppearanceListener) vector.elementAt(i);
            listener.imageAppearanceChanged(event);
        }
    }

    private void process() {
        if (inputImage != null) {
            outputImage = PlanarImage.wrapRenderedImage(inputImage.getAsBufferedImage(rectangle, inputImage.getColorModel()));
            getRectangle();
            Double xDouble = rectangle.getX();
            Integer x = xDouble.intValue();
            Double yDouble = rectangle.getY();
            Integer y = yDouble.intValue();
            outputImage.setProperty("offsetX", x);
            outputImage.setProperty("offsetY", y);
            fireImageAppearanceEvent();
        }
    }
}
