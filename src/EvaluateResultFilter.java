import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class EvaluateResultFilter implements ResultListener {
    private Result result;
    private List<Coordinate> _expectedCoordinates;
    private int tolerance;
    private String path;
    private Vector listeners;

    public EvaluateResultFilter() {
        path = "path";
        tolerance = 3;
        listeners = new Vector();
    }

    @Override
    public void resultChanged(ResultEvent event) {
        result = event.getResult();
        try {
            readExpectedCoordinates(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        process();
    }

    private void process() {
        ArrayList<Coordinate> resultCoordinates = result.getCalculatedCoordinates();

        for (int i = 0; i < resultCoordinates.size(); i++) {
            Coordinate resultCoordinate = resultCoordinates.get(i);
            Coordinate expectedCoordinate = _expectedCoordinates.get(i);
            int aberrationX = Math.abs(resultCoordinate._x - expectedCoordinate._x);
            int aberrationY = Math.abs(resultCoordinate._y - expectedCoordinate._y);
            if (aberrationX <= tolerance && aberrationY <= tolerance) {  // liegt im Toleranzbereich
                result.addResultInfo(expectedCoordinate, aberrationX, aberrationY, true);
            } else {
                result.addResultInfo(expectedCoordinate, aberrationX, aberrationY, false);
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

    private void readExpectedCoordinates(String expectedResultPath) throws IOException {
        FileReader fileReader = new FileReader(expectedResultPath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        _expectedCoordinates = new ArrayList<>();
        String line = bufferedReader.readLine();

        String[] splittedLine = line.split("\\[");
        String[] coordinates = splittedLine[1].split(", ");
        for (int i = 0; i < coordinates.length; i++) {
            String x_y_Coordinates = coordinates[i].replaceAll("[^\\d,]", "");
            String[] x_y_Coordinates_splitted = x_y_Coordinates.split(",");
            Coordinate coordinate = new Coordinate(Integer.parseInt(x_y_Coordinates_splitted[0]), Integer.parseInt(x_y_Coordinates_splitted[1]));
            _expectedCoordinates.add(coordinate);
        }

    }

    public void addResultListener(ResultListener listener) {
        listeners.addElement(listener);
    }

    public void removeResultListener(ResultListener listener) {
        listeners.removeElement(listener);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getTolerance() {
        return tolerance;
    }

    public void setTolerance(int tolerance) {
        this.tolerance = tolerance;
    }
}
