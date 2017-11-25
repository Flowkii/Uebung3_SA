import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import java.io.Serializable;
import java.util.Vector;

public class LoadImage implements Serializable {

    private String path = "path";
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
        if (path != null && !path.isEmpty()) {
            planarImage = JAI.create("fileload", path);
            fireImageAppearanceEvent();
        }
    }

    protected synchronized void fireImageAppearanceEvent() {
        synchronized (this) {
        }

        Vector vector;
        try {
            vector = (Vector) this.listeners.clone();
        } catch (Throwable throwable) {
            throw throwable;
        }

        ImageAppearanceEvent event = new ImageAppearanceEvent(this, this.planarImage);

        for (int i = 0; i < vector.size(); ++i) {
            ImageAppearanceListener var4 = (ImageAppearanceListener) vector.elementAt(i);
            var4.imageAppearanceChanged(event);
        }

    }
}
