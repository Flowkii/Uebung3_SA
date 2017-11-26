import java.awt.*;

public class ResultDisplayer extends List implements ResultListener {
    private Result result;

    @Override
    public void resultChanged(ResultEvent event) {
        result = event.getResult();
        this.repaint();
    }

    public ResultDisplayer() {
        setSize(600, 300);
    }

    @Override
    public void paint(Graphics graphics) {
        if (result != null) {
            this.add(result.get_amount() + "joints were found.");
            for (int i = 0; i < result.get_amount(); i++) {
                this.add(String.valueOf(result.getExpectedCoordinates().get(i)._x) + " " +
                        String.valueOf(result.getCalculatedCoordinates().get(i)._x) + " " +
                        result.getAberrationX().get(i).toString() + " " +
                        String.valueOf(result.getExpectedCoordinates().get(i)._y) + " " +
                        String.valueOf(result.getCalculatedCoordinates().get(i)._y) + " " +
                        result.getAberrationY().toString() + " " +
                        result.getIsInToleranceRange().get(i).toString() + " " +
                        String.valueOf(result.getDiameters().get(i)));
            }
        }
    }
}
