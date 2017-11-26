import com.sun.javafx.geom.Matrix3f;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.RenderedOp;
import javax.media.jai.operator.DilateDescriptor;
import javax.media.jai.operator.ErodeDescriptor;
import java.awt.image.renderable.ParameterBlock;
import java.io.Serializable;
import java.util.Vector;

public class OpeningFilter implements Serializable, ImageAppearanceListener {

    private Vector listeners = new Vector();
    private PlanarImage inputImage;
    private PlanarImage outputImage;
    private int kernelX;
    private int kernelY;
    private float[] kernelMatrix = {0, 1, 0,
            1, 1, 1,
            0, 1, 0};
    private int cycles;

    public OpeningFilter() {
        kernelX = 3;
        kernelY = 3;
        cycles = 5;
    }


    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
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
            KernelJAI kernel = new KernelJAI(kernelX, kernelY, kernelMatrix);

            RenderedOp renderedImage = ErodeDescriptor.create(inputImage, kernel, null);
            for (int i = 0; i < cycles - 1; i++) {
                renderedImage = ErodeDescriptor.create(renderedImage, kernel, null);
            }
            for (int i = 0; i < cycles; i++) {
                renderedImage = DilateDescriptor.create(renderedImage, kernel, null);
            }
            outputImage = renderedImage;
            fireImageAppearanceEvent();
        }
    }
}
