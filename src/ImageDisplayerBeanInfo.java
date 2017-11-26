import java.beans.*;
import java.lang.reflect.Method;

public class ImageDisplayerBeanInfo extends SimpleBeanInfo{

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = ImageDisplayer.class;
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
