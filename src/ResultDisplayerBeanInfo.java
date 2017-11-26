import java.beans.MethodDescriptor;
import java.beans.ParameterDescriptor;
import java.beans.SimpleBeanInfo;
import java.lang.reflect.Method;

public class ResultDisplayerBeanInfo extends SimpleBeanInfo {
    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = ResultDisplayer.class;
            Class parameterTypes[] = new Class[1];
            parameterTypes[0] = ResultEvent.class;
            String name = "resultChanged";
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
