import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ResultDisplayer extends TextArea implements ResultListener {
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
        StringBuilder sb = new StringBuilder();
        if (result != null) {
            sb.append(result.get_amount());
            sb.append(" joints were found.");
            sb.append("Erwartet X \t Gefunden X \t ABWEICHUNG \t Erwartet Y \t Gefunden Y \t ABWEICHUNG \t X und Y in Toleranzberich \t Durchmesser");
            for (int i = 0; i < result.get_amount(); i++) {
                sb.append(result.getExpectedCoordinates().get(i)._x);
                sb.append("\t\t\t\t");
                sb.append(result.getCalculatedCoordinates().get(i)._x);
                sb.append("\t\t\t\t");
                sb.append(result.getAberrationX().get(i));
                sb.append("\t\t\t\t");
                sb.append(result.getExpectedCoordinates().get(i)._y);
                sb.append("\t\t\t\t");
                sb.append(result.getCalculatedCoordinates().get(i)._y);
                sb.append("\t\t\t\t");
                sb.append(result.getAberrationY());
                sb.append("\t\t\t\t");
                sb.append(result.getIsInToleranceRange().get(i));
                sb.append("\t\t\t\t\t\t");
                sb.append(result.getDiameters().get(i));
            }
        }
        this.setEditable(false);
    }
}
