import java.util.EventObject;

public class ResultEvent extends EventObject{
    private Result result;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ResultEvent(Object source, Result result) {
        super(source);
        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
