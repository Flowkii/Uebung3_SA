import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.Serializable;
import java.util.Vector;

public class MedianFilter implements Serializable, ImageAppearanceListener {

    private Vector listeners;
    private int medianValue;
    private PlanarImage inputImage;
    private PlanarImage outputImage;

    public MedianFilter() {
        listeners = new Vector();
        medianValue = 4;
    }

    public int getMedianValue() {
        return medianValue;
    }

    public void setMedianValue(int medianValue) {
        this.medianValue = medianValue;
        process();

    }

    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);

    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);

    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        this.inputImage = event.getImage();
        process();
    }

    public void process() {
        if (inputImage != null) {
            ParameterBlock parameterBlock = new ParameterBlock();
            parameterBlock.addSource(inputImage);
            parameterBlock.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
            parameterBlock.add(medianValue);
            RenderedImage renderedImage = JAI.create("MedianFilter", parameterBlock);
            outputImage = PlanarImage.wrapRenderedImage(renderedImage);
            fireImageAppearanceEvent();
        }
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
}
