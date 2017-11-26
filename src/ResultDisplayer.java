import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ResultDisplayer extends List implements ResultListener {
    private Result result;
    private PrintWriter _pWriter;

    @Override
    public void resultChanged(ResultEvent event) {
        result = event.getResult();
        if (result != null) {
            _pWriter.println("Ergebnisse: ");
            _pWriter.println("Es wurden " + result.get_amount() + " Loetstellen gefunden.");
            _pWriter.println("Erwartet X \t Gefunden X \t ABWEICHUNG \t Erwartet Y \t Gefunden Y \t ABWEICHUNG \t X und Y in Toleranzberich \t Durchmesser");
            for (int i = 0; i < result.get_amount(); i++) {
                _pWriter.print(result.getExpectedCoordinates().get(i)._x + "\t\t\t\t");
                _pWriter.print(result.getCalculatedCoordinates().get(i)._x + "\t\t\t\t");
                _pWriter.print(result.getAberrationX().get(i).toString() + "\t\t\t\t");
                _pWriter.print(result.getExpectedCoordinates().get(i)._y + "\t\t\t\t");
                _pWriter.print(result.getCalculatedCoordinates().get(i)._y + "\t\t\t\t");
                _pWriter.print(result.getAberrationY().get(i).toString() + "\t\t\t\t");
                _pWriter.print(result.getIsInToleranceRange().get(i).toString() + "\t\t\t\t\t\t");
                _pWriter.println(result.getDiameters().get(i));
            }
            _pWriter.flush();
            _pWriter.close();
        }
        this.repaint();
    }

    public ResultDisplayer() throws FileNotFoundException {
        setSize(600, 300);
        _pWriter = new PrintWriter("result.txt");
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
