import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.util.Vector;

public class LoadImage implements Serializable {

    private String path = "set Path";
    private Vector listeners = new Vector();
    private PlanarImage planarImage;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        run();
    }

    public void addImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.addElement(listener);

    }

    public void removeImageAppearanceListener(ImageAppearanceListener listener) {
        listeners.removeElement(listener);

    }

    public void run() {

        planarImage = JAI.create("fileload", path);
        fireImageAppearanceEvent();


    }

    protected synchronized void fireImageAppearanceEvent() {
        synchronized (this) {
        }

        Vector var1;
        try {
            var1 = (Vector) this.listeners.clone();
        } catch (Throwable var6) {
            throw var6;
        }

        ImageAppearanceEvent var2 = new ImageAppearanceEvent(this, this.planarImage);

        for (int var3 = 0; var3 < var1.size(); ++var3) {
            ImageAppearanceListener var4 = (ImageAppearanceListener) var1.elementAt(var3);
            var4.imageAppearanceChanged(var2);
        }

    }
}
