import com.sun.javafx.geom.Matrix3f;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import java.awt.image.renderable.ParameterBlock;
import java.io.Serializable;
import java.util.Vector;

public class OpeningFilter  implements Serializable,ImageAppearanceListener{

    private Vector listeners=new Vector();


    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);

    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);

    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        float[] kernelMatrix = {
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0
        };

        KernelJAI kernel = new KernelJAI(12, 12, kernelMatrix);

        ParameterBlock p = new ParameterBlock();
        p.addSource(event.getImage());
        p.add(kernel);

        PlanarImage output = JAI.create("erode", p);


        p = new ParameterBlock();
        p.addSource(output);
        p.add(kernel);
        output = JAI.create("dilate", p);
        Vector var1;
        try {
            var1 = (Vector) this.listeners.clone();
        } catch (Throwable var6) {
            throw var6;
        }

        ImageAppearanceEvent var2 = new ImageAppearanceEvent(event.getSource(),output);

        for (int var3 = 0; var3 < var1.size(); ++var3) {
            ImageAppearanceListener var4 = (ImageAppearanceListener) var1.elementAt(var3);
            var4.imageAppearanceChanged(var2);
        }
    }
}
