import java.beans.*;
import java.lang.reflect.Method;

public class EvaluateResultFilterBeanInfo extends SimpleBeanInfo {
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class clazz = EvaluateResultFilter.class;
            String eventSetName = "result";
            Class listenerClass = ResultListener.class;
            String[] names = new String[]{"resultChanged"};
            String add = "addResultListener";
            String remove = "removeResultListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(clazz, eventSetName, listenerClass, names, add, remove);
            return new EventSetDescriptor[]{setDescriptor};
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = EvaluateResultFilter.class;
            Class[] parameterTypes = new Class[]{ResultEvent.class};
            String name = "resultChanged";
            Method method = clazz.getMethod(name, parameterTypes);
            ParameterDescriptor[] parameterDescriptors = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptors);
            return new MethodDescriptor[]{methodDescriptor};
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor path, tolerance;
            Class clazz = EvaluateResultFilter.class;
            path = new PropertyDescriptor("path", clazz);
            tolerance = new PropertyDescriptor("tolerance", clazz);
            return new PropertyDescriptor[]{path, tolerance};
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
