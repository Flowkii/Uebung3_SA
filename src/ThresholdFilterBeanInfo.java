import java.beans.*;
import java.lang.reflect.Method;

public class ThresholdFilterBeanInfo extends SimpleBeanInfo {
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor high, low, constant, band;
            Class clazz = ThresholdFilter.class;
            high = new PropertyDescriptor("thresholdHigh", clazz);
            low = new PropertyDescriptor("thresholdLow", clazz);
            constant = new PropertyDescriptor("thresholdConst", clazz);
            band = new PropertyDescriptor("band", clazz);
            PropertyDescriptor propertyDescriptor[] = {high, low, constant, band};
            return propertyDescriptor;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor eventSetDescriptor;
            Class clazz = ThresholdFilter.class;
            String event = "imageAppearance";
            Class listener = ImageAppearanceListener.class;
            String names[] = {"imageAppearanceChanged"};
            String add = "addImageAppearanceListener";
            String remove = "removeImageAppearanceListener";
            eventSetDescriptor = new EventSetDescriptor(clazz, event, listener, names, add, remove);
            EventSetDescriptor esd[] = {eventSetDescriptor};
            return esd;
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = ThresholdFilter.class;
            Class parameterTypes[] = new Class[1];
            parameterTypes[0] = ImageAppearanceEvent.class;
            String name = "imageAppearanceChanged";
            Method method = clazz.getMethod(name, parameterTypes);
            ParameterDescriptor parameterDescriptor[] = new ParameterDescriptor[1];
            parameterDescriptor[0] = new ParameterDescriptor();
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptor);
            return new MethodDescriptor[]{methodDescriptor};
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
