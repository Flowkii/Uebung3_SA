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
        String[] var5 = new String[]{"imageAppearanceChanged"};
        String addListener = "addImageAppearanceListener";
        String removeListener = "removeImageAppearanceListener";
        EventSetDescriptor var1 = null;
        try {
            var1 = new EventSetDescriptor(imgClass, imageSource, listenerClass, var5, addListener, removeListener);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        EventSetDescriptor[] result = new EventSetDescriptor[]{var1};
        return result;
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        Class var = LoadImage.class;
        try {
            PropertyDescriptor prop1 = new PropertyDescriptor("path", var);
            PropertyDescriptor[] prop2 = new PropertyDescriptor[]{prop1};
            return prop2;
        } catch (IntrospectionException e) {
            e.printStackTrace();
            return null;
        }
    }
}
