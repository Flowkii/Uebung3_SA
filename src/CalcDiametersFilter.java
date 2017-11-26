import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

public class CalcDiametersFilter implements ResultListener {
    private Result result;
    private Vector listeners;

    public CalcDiametersFilter() {
        listeners = new Vector();
    }


    @Override
    public void resultChanged(ResultEvent event) {
        this.result = event.getResult();
        process();
    }

    public void addResultListener(ResultListener listener) {
        listeners.addElement(listener);
    }

    public void removeResultListener(ResultListener listener) {
        listeners.removeElement(listener);
    }

    private void process() {
        ArrayList<Coordinate> coordinates = result.getCalculatedCoordinates();
        PlanarImage image = result.getImage();

        if (coordinates != null && !coordinates.isEmpty() && image != null) {
            BufferedImage bi = image.getAsBufferedImage();

            for (Coordinate coordinate : coordinates) {
                int x = coordinate._x - (Integer) image.getProperty("offsetX");
                int y = coordinate._y - (Integer) image.getProperty("offsetY");
                int p = bi.getRaster().getSample(x + 1, y, 0);
                int radius = 0;
                while (p == 255) {
                    x++;
                    p = bi.getRaster().getSample(x, y, 0);
                    radius++;
                }
                result.addDiameter(radius * 2);  // Result den Durchmesser hinzufügen
            }
        }
        fireResultEvent();
    }

    private void fireResultEvent() {
        Vector vector;
        vector = (Vector) listeners.clone();
        ResultEvent event = new ResultEvent(this, result);
        for (int i = 0; i < vector.size(); i++) {
            ResultListener listener = (ResultListener) vector.elementAt(i);
            listener.resultChanged(event);
        }
    }
}
