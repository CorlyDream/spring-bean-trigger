package cc.corly.springbean.trigger;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

public class SpringBeanTriggerService implements TriggerService{
    private BeanFactory beanFactory;

    public SpringBeanTriggerService(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object trigger(String beanName, String method, List<String> args) throws InvocationTargetException, IllegalAccessException {
        Object bean = beanFactory.getBean(beanName);
        if (bean == null) {
            return "未找到对应bean：" + beanName;
        }
        Method[] methods = bean.getClass().getMethods();
        Method callMethod = null;
        for (Method m : methods) {
            if (m.getName().equals(method) && m.getParameterCount() == args.size()) {
                callMethod = m;
                break;
            }
        }
        if (callMethod == null) {
            return String.format("未查找到bean方法(%s)，请检查方法名和参数数量是否对应。", method);
        }
        Object ret = null;
        if (callMethod != null) {
            Object[] params = new Object[callMethod.getParameterCount()];
            Class<?>[] parameterTypes = callMethod.getParameterTypes();
            for (int i = 0; i < parameterTypes.length; i++) {
                if (parameterTypes[i].isAssignableFrom(String.class)) {
                    params[i] = args.get(i);
                }else {
                    params[i] = JSON.parseObject(args.get(i), parameterTypes[i]);
                }
            }
            ret = callMethod.invoke(bean, params);
        }
        return ret;
    }
}
