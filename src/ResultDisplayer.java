import java.awt.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ResultDisplayer extends TextArea implements ResultListener {
    private Result result;
    private PrintWriter printWriter;

    @Override
    public void resultChanged(ResultEvent event) {
        result = event.getResult();
        if (result != null) {

            //write result to file
            printWriter.println("Ergebnisse: ");
            printWriter.println("Es wurden " + result.get_amount() + " Loetstellen gefunden.");
            printWriter.println("Erwartet X \t Gefunden X \t ABWEICHUNG \t Erwartet Y \t Gefunden Y \t ABWEICHUNG \t X und Y in Toleranzberich \t Durchmesser");
            for (int i = 0; i < result.get_amount(); i++) {
                printWriter.print(result.getExpectedCoordinates().get(i)._x + "\t\t\t\t");
                printWriter.print(result.getCalculatedCoordinates().get(i)._x + "\t\t\t\t");
                printWriter.print(result.getAberrationX().get(i).toString() + "\t\t\t\t");
                printWriter.print(result.getExpectedCoordinates().get(i)._y + "\t\t\t\t");
                printWriter.print(result.getCalculatedCoordinates().get(i)._y + "\t\t\t\t");
                printWriter.print(result.getAberrationY().get(i).toString() + "\t\t\t\t");
                printWriter.print(result.getIsInToleranceRange().get(i).toString() + "\t\t\t\t\t\t");
                printWriter.println(result.getDiameters().get(i));
            }
            printWriter.flush();
            printWriter.close();

            //write result to text area
            StringBuilder sb = new StringBuilder();
            sb.append(result.get_amount());
            sb.append(" joints were found.");
            sb.append("\n");
            sb.append("expected X \t found X \t ABERRATION \t expected Y \t found Y \t ABERRATION \t X and Y in tolerance range \t diameter");
            sb.append("\n");
            for (int i = 0; i < result.get_amount(); i++) {
                sb.append(result.getExpectedCoordinates().get(i)._x);
                sb.append("\t\t");
                sb.append(result.getCalculatedCoordinates().get(i)._x);
                sb.append("\t\t");
                sb.append(result.getAberrationX().get(i));
                sb.append("\t");
                sb.append(result.getExpectedCoordinates().get(i)._y);
                sb.append("\t\t");
                sb.append(result.getCalculatedCoordinates().get(i)._y);
                sb.append("\t");
                sb.append(result.getAberrationY().get(i));
                sb.append("\t\t");
                sb.append(result.getIsInToleranceRange().get(i));
                sb.append("\t\t\t");
                sb.append(result.getDiameters().get(i));
                sb.append("\n");
            }
            this.setText(sb.toString());
            this.setEditable(false);
        }
        this.repaint();
    }

    public ResultDisplayer() throws FileNotFoundException {
        setSize(800, 300);
        printWriter = new PrintWriter("result.txt");
    }
}
