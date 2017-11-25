import java.beans.*;
import java.lang.reflect.Method;

public class ROIFIlterBeanInfo extends SimpleBeanInfo {
    public ROIFIlterBeanInfo() {
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            Class roiFilterClass = ROIFilter.class;
            String filter = "roiFilter";
            Class listenerClass = ImageAppearanceListener.class;
            String[] imageChanged = new String[]{"imageAppearanceChanged"};
            String addWaveformListener = "addWaveformListener";
            String removeWaveformListener = "removeWaveformListener";
            EventSetDescriptor setDescriptor = new EventSetDescriptor(roiFilterClass, filter, listenerClass, imageChanged, addWaveformListener, removeWaveformListener);
            EventSetDescriptor[] setDescriptors = new EventSetDescriptor[]{setDescriptor};
            return setDescriptors;
        } catch (Exception var9) {
            var9.printStackTrace();
            return null;
        }
    }

    public MethodDescriptor[] getMethodDescriptors() {
        try {
            Class var1 = ROIFilter.class;
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
            PropertyDescriptor width = new PropertyDescriptor("width", ROIFilter.class);
            PropertyDescriptor height = new PropertyDescriptor("height", ROIFilter.class);
            PropertyDescriptor xOffset = new PropertyDescriptor("xOffset", ROIFilter.class);
            PropertyDescriptor yOffset = new PropertyDescriptor("yOffset", ROIFilter.class);
            PropertyDescriptor[] var3 = new PropertyDescriptor[]{width, height, xOffset, yOffset};
            return var3;
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
