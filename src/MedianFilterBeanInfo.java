import java.beans.*;
import java.lang.reflect.Method;

public class MedianFilterBeanInfo extends SimpleBeanInfo {

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class medianFilterClass = MedianFilter.class;
            String eventSetName = "imageAppearance";
            Class listenerClass = ImageAppearanceListener.class;
            String[] imageChanged = new String[]{"imageAppearanceChanged"};
            String addImageAppearanceListener = "addImageAppearanceListener";
            String removeImageAppearanceListener = "removeImageAppearanceListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(medianFilterClass, eventSetName, listenerClass, imageChanged, addImageAppearanceListener, removeImageAppearanceListener);
            EventSetDescriptor[] setDescriptors = new EventSetDescriptor[]{setDescriptor};
            return setDescriptors;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = MedianFilter.class;
            Class[] parameterTypes = new Class[]{ImageAppearanceEvent.class};
            String name = "imageAppearanceChanged";
            Method method = clazz.getMethod(name, parameterTypes);
            ParameterDescriptor[] parameterDescriptors = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptors);
            MethodDescriptor[] methodDescriptors = new MethodDescriptor[]{methodDescriptor};
            return methodDescriptors;
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("medianValue", MedianFilter.class);
            PropertyDescriptor[] propertyDescriptors = new PropertyDescriptor[]{propertyDescriptor};
            return propertyDescriptors;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
