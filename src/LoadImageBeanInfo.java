import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class LoadImageBeanInfo extends SimpleBeanInfo {

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        Class imgClass = LoadImage.class;
        String imageSource = "imageSource";
        Class listenerClass = ImageAppearanceListener.class;
        String[] names = new String[]{"imageAppearanceChanged"};
        String addListener = "addImageAppearanceListener";
        String removeListener = "removeImageAppearanceListener";
        EventSetDescriptor eventSetDescriptor = null;
        try {
            eventSetDescriptor = new EventSetDescriptor(imgClass, imageSource, listenerClass, names, addListener, removeListener);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        EventSetDescriptor[] result = new EventSetDescriptor[]{eventSetDescriptor};
        return result;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        Class clazz = LoadImage.class;
        try {
            PropertyDescriptor prop1 = new PropertyDescriptor("path", clazz);
            PropertyDescriptor[] prop2 = new PropertyDescriptor[]{prop1};
            return prop2;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
