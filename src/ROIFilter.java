import javax.media.jai.PlanarImage;
import java.awt.*;
import java.io.Serializable;
import java.util.Vector;

public class ROIFilter implements Serializable, ImageAppearanceListener{

    private PlanarImage image;
    private int width =200;
    private int height =100;
    private int xOffset=0;
    private int yOffset=10;
    private Rectangle rectangle;
    private Vector listeners = new Vector();


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
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getxOffset() {
        return xOffset;
    }

    public void setxOffset(int xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }

    public void setyOffset(int yOffset) {
        this.yOffset = yOffset;
    }
    public void getRectangle(){
        rectangle= new Rectangle(xOffset, yOffset, width, height);
    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {

        image = PlanarImage.wrapRenderedImage(event.getImage().getAsBufferedImage(rectangle, event.getImage().getColorModel()));

        getRectangle();
        Double xDouble = rectangle.getX();
        Integer x = xDouble.intValue();
        Double yDouble = rectangle.getX();
        Integer y = yDouble.intValue();
        image.setProperty("offsetX", x);
        image.setProperty("offsetY", y);

        synchronized (this) {
        }

        Vector var1;
        try {
            var1 = (Vector) this.listeners.clone();
        } catch (Throwable var6) {
            throw var6;
        }

        ImageAppearanceEvent var2 = new ImageAppearanceEvent(event.getSource(),image);

        for (int var3 = 0; var3 < var1.size(); ++var3) {
            ImageAppearanceListener var4 = (ImageAppearanceListener) var1.elementAt(var3);
            var4.imageAppearanceChanged(var2);
        }

    }
}
