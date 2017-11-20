import java.beans.EventSetDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImageDisplayerBeanInfo extends SimpleBeanInfo {

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        Class displayClass = ImageDisplayer.class;
        String imageDisplayer = "imageDisplayer";
        Class listenerClass = ImageAppearanceListener.class;
        String[] var5 = new String[]{"imageAppearanceChanged"};
        String addListener = "addImageAppearanceListener";
        String removeListener = "removeImageAppearanceListener";
        EventSetDescriptor var1 = null;
        try {
            var1 = new EventSetDescriptor(displayClass, imageDisplayer, listenerClass, var5, addListener, removeListener);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return new EventSetDescriptor[]{var1};
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        Class clazz = LoadImage.class;
        try {
            PropertyDescriptor prop1 = new PropertyDescriptor("imageDisplayer", clazz);
            PropertyDescriptor[] prop2 = new PropertyDescriptor[]{prop1};
            return prop2;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
