import java.beans.*;
import java.lang.reflect.Method;

public class ROIFIlterBeanInfo extends SimpleBeanInfo {
    public ROIFIlterBeanInfo() {
    }

    @Override
    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class roiFilterClass = ROIFilter.class;
            String eventSetName = "imageAppearance";
            Class listenerClass = ImageAppearanceListener.class;
            String[] imageChanged = new String[]{"imageAppearanceChanged"};
            String addImageAppearanceListener = "addImageAppearanceListener";
            String removeImageAppearanceListener = "removeImageAppearanceListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(roiFilterClass, eventSetName, listenerClass, imageChanged, addImageAppearanceListener, removeImageAppearanceListener);
            return new EventSetDescriptor[]{setDescriptor};
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    @Override
    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class clazz = ROIFilter.class;
            Class[] parameterTypes = new Class[]{ImageAppearanceEvent.class};
            String methodName = "imageAppearanceChanged";
            Method method = clazz.getMethod(methodName, parameterTypes);
            ParameterDescriptor[] parameterDescriptors = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor methodDescriptor = new MethodDescriptor(method, parameterDescriptors);
            return new MethodDescriptor[]{methodDescriptor};
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    @Override
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor width = new PropertyDescriptor("width", ROIFilter.class);
            PropertyDescriptor height = new PropertyDescriptor("height", ROIFilter.class);
            PropertyDescriptor xOffset = new PropertyDescriptor("xOffset", ROIFilter.class);
            PropertyDescriptor yOffset = new PropertyDescriptor("yOffset", ROIFilter.class);
            return new PropertyDescriptor[]{width, height, xOffset, yOffset};
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
