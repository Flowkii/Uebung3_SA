import java.beans.*;
import java.lang.reflect.Method;

public class MedianFilterBeanInfo extends SimpleBeanInfo {

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class medianFilterClass = MedianFilter.class;
            String filter = "medianFilter";
            Class listenerClass = ImageAppearanceListener.class;
            String[] imageChanged = new String[]{"imageAppearanceChanged"};
            String addImageAppearanceListener = "addImageAppearanceListener";
            String removeImageAppearanceListener = "removeImageAppearanceListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(medianFilterClass, filter, listenerClass, imageChanged, addImageAppearanceListener, removeImageAppearanceListener);
            EventSetDescriptor[] setDescriptors = new EventSetDescriptor[]{setDescriptor};
            return setDescriptors;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class var1 = MedianFilter.class;
            Class[] var2 = new Class[]{ImageAppearanceEvent.class};
            String var3 = "imageAppearanceChanged";
            Method var4 = var1.getMethod(var3, var2);
            ParameterDescriptor[] var5 = new ParameterDescriptor[]{new ParameterDescriptor()};
            MethodDescriptor var6 = new MethodDescriptor(var4, var5);
            MethodDescriptor[] var7 = new MethodDescriptor[]{var6};
            return var7;
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }
    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor widmedianValueh = new PropertyDescriptor("medianValue", MedianFilter.class);
            PropertyDescriptor[] var3 = new PropertyDescriptor[]{widmedianValueh};
            return var3;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
