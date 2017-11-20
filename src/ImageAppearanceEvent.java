import javax.media.jai.PlanarImage;
import java.util.EventObject;

public class ImageAppearanceEvent extends EventObject{
    private PlanarImage image;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ImageAppearanceEvent(Object source, PlanarImage image) {
        super(source);
        this.image = image;
    }
}
