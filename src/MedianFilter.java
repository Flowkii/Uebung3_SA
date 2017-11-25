import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.Serializable;
import java.util.Vector;

public class MedianFilter implements Serializable,ImageAppearanceListener {

    private Vector listeners= new Vector();
    private int medianValue=4;

    public int getMedianValue() {
        return medianValue;
    }

    public void setMedianValue(int medianValue) {
        this.medianValue = medianValue;
    }
    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);

    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);

    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {

        ParameterBlock parameterBlock = new ParameterBlock();
        parameterBlock.addSource(event.getImage());
        parameterBlock.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
        parameterBlock.add(medianValue);
        RenderedImage renderedImage = JAI.create("MedianFilter", parameterBlock);
        PlanarImage image=PlanarImage.wrapRenderedImage(renderedImage);

        Vector var1;
        try {
            var1 = (Vector) this.listeners.clone();
        } catch (Throwable var6) {
            throw var6;
        }

        ImageAppearanceEvent var2 = new ImageAppearanceEvent(this, image);

        for (int var3 = 0; var3 < var1.size(); ++var3) {
            ImageAppearanceListener var4 = (ImageAppearanceListener) var1.elementAt(var3);
            var4.imageAppearanceChanged(var2);
        }
    }
}
