package cc.corly.springbean.trigger;

import java.util.List;

public interface TriggerService {
    Object trigger(String beanName, String method, List<String> args) throws Exception;
}
