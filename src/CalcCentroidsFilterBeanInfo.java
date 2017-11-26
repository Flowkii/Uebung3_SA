import java.beans.*;
import java.lang.reflect.Method;

public class CalcCentroidsFilterBeanInfo extends SimpleBeanInfo{
    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        return new PropertyDescriptor[]{ };
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor eventSetDescriptor;
            Class clazz = CalcCentroidsFilter.class;
            String event = "result";
            Class listener = ResultListener.class;
            String names[] = {"resultChanged"};
            String add = "addResultListener";
            String remove = "removeResultListener";
            eventSetDescriptor = new EventSetDescriptor(clazz, event, listener, names, add, remove);
            return new EventSetDescriptor[]{eventSetDescriptor};
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = CalcCentroidsFilter.class;
            Class parameterTypes[] = new Class[1];
            parameterTypes[0] = ResultEvent.class;
            String name = "resultChanged";
            Method method = clazz.getMethod(name, parameterTypes);
            ParameterDescriptor parameterDescriptor[] = new ParameterDescriptor[1];
            parameterDescriptor[0] = new ParameterDescriptor();
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptor);

            Class parameterTypes2[] = new Class[1];
            parameterTypes2[0] = ImageAppearanceEvent.class;
            String name2 = "imageAppearanceChanged";
            Method method2 = clazz.getMethod(name2, parameterTypes2);
            ParameterDescriptor parameterDescriptor2[] = new ParameterDescriptor[1];
            parameterDescriptor[0] = new ParameterDescriptor();
            MethodDescriptor methodDescriptor2 = new MethodDescriptor(method2, parameterDescriptor2);

            return new MethodDescriptor[]{methodDescriptor, methodDescriptor2};
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
