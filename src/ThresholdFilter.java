import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.Serializable;
import java.util.Vector;

public class ThresholdFilter implements ImageAppearanceListener, Serializable {
    private double thresholdLow;
    private double thresholdHigh;
    private double thresholdConst;
    private int band;
    private PlanarImage image;
    private Vector listeners;

    public ThresholdFilter() {
        thresholdLow = 0;
        thresholdHigh = 50;
        thresholdConst = 255;
        band = 255;
        listeners = new Vector();
    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        this.image = event.getImage();
        run();
    }

    protected void fireImageAppearanceEvent() {
        Vector vector;
        vector = (Vector) listeners.clone();
        ImageAppearanceEvent event = new ImageAppearanceEvent(this, image);
        for (int i = 0; i < vector.size(); i++) {
            ImageAppearanceListener listener = (ImageAppearanceListener) vector.elementAt(i);
            listener.imageAppearanceChanged(event);
        }
    }

    private void run() {
        double[] lowVal = new double[band];
        double[] highVal = new double[band];
        double[] constant = new double[band];

        for (int i = 0; i < band; i++) {
            lowVal[i] = thresholdLow;
            highVal[i] = thresholdHigh;
            constant[i] = thresholdConst;
        }
        ParameterBlock parameterBlock = new ParameterBlock();
        parameterBlock.addSource(image);
        parameterBlock.add(lowVal);
        parameterBlock.add(highVal);
        parameterBlock.add(constant);
        RenderedImage dest = JAI.create("threshold", parameterBlock);
        image = PlanarImage.wrapRenderedImage(dest);
        fireImageAppearanceEvent();
    }

    public double getThresholdLow() {
        return thresholdLow;
    }

    public void setThresholdLow(double thresholdLow) {
        this.thresholdLow = thresholdLow;
    }

    public double getThresholdHigh() {
        return thresholdHigh;
    }

    public void setThresholdHigh(double thresholdHigh) {
        this.thresholdHigh = thresholdHigh;
    }


    public double getThresholdConst() {
        return thresholdConst;
    }

    public void setThresholdConst(double thresholdConst) {
        this.thresholdConst = thresholdConst;
    }

    public int getBand() {
        return band;
    }

    public void setBand(int band) {
        this.band = band;
    }

    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);
    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);
    }
}
