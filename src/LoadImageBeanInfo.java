import java.beans.*;
import java.lang.reflect.Method;

public class LoadImageBeanInfo extends SimpleBeanInfo {

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        Class imgClass = LoadImage.class;
        String eventSetName = "imageAppearance";
        Class listenerClass = ImageAppearanceListener.class;
        String[] names = new String[]{"imageAppearanceChanged"};
        String addListener = "addImageAppearanceListener";
        String removeListener = "removeImageAppearanceListener";
        EventSetDescriptor eventSetDescriptor = null;
        try {
            eventSetDescriptor = new EventSetDescriptor(imgClass, eventSetName, listenerClass, names, addListener, removeListener);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        EventSetDescriptor[] eventSetDescriptors = new EventSetDescriptor[]{eventSetDescriptor};
        return eventSetDescriptors;
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
