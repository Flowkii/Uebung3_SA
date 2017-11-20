import java.beans.*;
import java.lang.reflect.Method;

public class ImageDisplayerBeanInfo extends SimpleBeanInfo {
    public ImageDisplayerBeanInfo(){
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        EventSetDescriptor[] eventSetDescriptors = new EventSetDescriptor[0];
        return eventSetDescriptors;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        Class imageDisplacer = ImageDisplayer.class;
        Class[] imageEvent = new Class[]{ImageAppearanceEvent.class};
        String imageCanged = "imageAppearanceChanged";
        Method method = null;
        try {
            method = imageDisplacer.getMethod(imageCanged, imageEvent);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        ParameterDescriptor[] para1 = new ParameterDescriptor[]{new ParameterDescriptor()};
        MethodDescriptor methodD1 = new MethodDescriptor(method, para1);
        MethodDescriptor[] methodD2 = new MethodDescriptor[]{methodD1};
        return methodD2;
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
