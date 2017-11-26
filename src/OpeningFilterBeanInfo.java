import java.beans.*;
import java.lang.reflect.Method;

public class OpeningFilterBeanInfo extends SimpleBeanInfo {
    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class roiFilterClass = OpeningFilter.class;
            String eventSetName = "imageAppearance";
            Class listenerClass = ImageAppearanceListener.class;
            String[] imageChanged = new String[]{"imageAppearanceChanged"};
            String addImageAppearanceListener = "addImageAppearanceListener";
            String removeImageAppearanceListener = "removeImageAppearanceListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(roiFilterClass, eventSetName, listenerClass, imageChanged, addImageAppearanceListener, removeImageAppearanceListener);
            EventSetDescriptor[] setDescriptors = new EventSetDescriptor[]{setDescriptor};
            return setDescriptors;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = OpeningFilter.class;
            Class[] parameterTypes = new Class[]{ImageAppearanceEvent.class};
            String methodName = "imageAppearanceChanged";
            Method method = clazz.getMethod(methodName, parameterTypes);
            ParameterDescriptor[] parameterDescriptors = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptors);
            MethodDescriptor[] methodDescriptors = new MethodDescriptor[]{methodDescriptor};
            return methodDescriptors;
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {

            Class clazz = OpeningFilter.class;
            PropertyDescriptor cycles = new PropertyDescriptor("cycles", clazz);
            PropertyDescriptor propertyDescriptor[] = {cycles};
            return propertyDescriptor;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

}
