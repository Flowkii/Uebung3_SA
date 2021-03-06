import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

public class CalcCentroidsFilter implements ImageAppearanceListener, Serializable {
    private Vector listeners;
    private PlanarImage image;
    private HashMap<Coordinate, Boolean> general;
    private LinkedList<ArrayList<Coordinate>> figures;

    public CalcCentroidsFilter() {
        listeners = new Vector();
        general = new HashMap<>();
        figures = new LinkedList<>();
    }

    @Override
    public void imageAppearanceChanged(ImageAppearanceEvent event) {
        this.image = event.getImage();
        process();
    }

    private void fireResultEvent(Result result) {
        Vector vector;
        vector = (Vector) listeners.clone();
        ResultEvent event = new ResultEvent(this, result);
        for (int i = 0; i < vector.size(); i++) {
            ResultListener listener = (ResultListener) vector.elementAt(i);
            listener.resultChanged(event);
        }
    }

    private void process() {
        BufferedImage bi = image.getAsBufferedImage();
        for (int x = 0; x < bi.getWidth(); x++) {
            for (int y = 0; y < bi.getHeight(); y++) {
                int p = bi.getRaster().getSample(x, y, 0);
                if (p == 255 && general.containsKey(new Coordinate(x, y)) == false) {
                    getNextFigure(bi, x, y);        //if there is a not visited white pixel, save all pixels belonging to the same figure
                }
            }
        }

        ArrayList<Coordinate> centroids = calculateCentroids();    //calculate the centroids of all figures
        Result result = new Result(centroids.size(), centroids, image);
        fireResultEvent(result);
    }

    public void addResultListener(ResultListener listener) {
        listeners.addElement(listener);
    }

    public void removeResultListener(ResultListener listener) {
        listeners.removeElement(listener);
    }

    private void getNextFigure(BufferedImage img, int x, int y) {
        ArrayList<Coordinate> figure = new ArrayList<Coordinate>();
        general.put(new Coordinate(x, y), true);
        figure.add(new Coordinate(x, y));

        addConnectedComponents(img, figure, x, y);

        figures.add(figure);
    }


    private void addConnectedComponents(BufferedImage img, ArrayList<Coordinate> figure, int x, int y) {
        if (x - 1 >= 0 && general.containsKey((new Coordinate(x - 1, y))) == false && img.getRaster().getSample(x - 1, y, 0) == 255) {
            general.put(new Coordinate(x - 1, y), true);
            figure.add(new Coordinate(x - 1, y));
            addConnectedComponents(img, figure, x - 1, y);
        }
        if (x + 1 < img.getWidth() && general.containsKey((new Coordinate(x + 1, y))) == false && img.getRaster().getSample(x + 1, y, 0) == 255) {
            general.put(new Coordinate(x + 1, y), true);
            figure.add(new Coordinate(x + 1, y));
            addConnectedComponents(img, figure, x + 1, y);
        }
        if (y - 1 >= 0 && general.containsKey((new Coordinate(x, y - 1))) == false && img.getRaster().getSample(x, y - 1, 0) == 255) {
            general.put(new Coordinate(x, y - 1), true);
            figure.add(new Coordinate(x, y - 1));
            addConnectedComponents(img, figure, x, y - 1);
        }
        if (y + 1 < img.getHeight() && general.containsKey((new Coordinate(x, y + 1))) == false && img.getRaster().getSample(x, y + 1, 0) == 255) {
            general.put(new Coordinate(x, y + 1), true);
            figure.add(new Coordinate(x, y + 1));
            addConnectedComponents(img, figure, x, y + 1);
        }
    }

    private ArrayList<Coordinate> calculateCentroids() {
        ArrayList<Coordinate> centroids = new ArrayList<Coordinate>();
        int i = 0;
        for (ArrayList<Coordinate> figure : figures) {
            LinkedList<Integer> xValues = new LinkedList<Integer>();
            LinkedList<Integer> yValues = new LinkedList<Integer>();

            for (Coordinate c : figure) {
                xValues.add(c._x);
                yValues.add(c._y);
            }

            Collections.sort(xValues);
            Collections.sort(yValues);

            int xMedian = xValues.get(xValues.size() / 2);
            int yMedian = yValues.get(yValues.size() / 2);

            centroids.add(new Coordinate(xMedian + (Integer) image.getProperty("offsetX"), yMedian + (Integer) image.getProperty("offsetY")));

            i++;
        }
        return centroids;
    }
}
