import java.util.EventListener;

public interface ResultListener extends EventListener{
    void resultChanged(ResultEvent event);
}
