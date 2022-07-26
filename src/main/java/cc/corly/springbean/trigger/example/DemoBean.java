package cc.corly.springbean.trigger.example;

import java.util.HashMap;
import java.util.Map;

public class DemoBean {
    public Map<String, String> testCall(String p) {
        Map<String, String> map = new HashMap<>();
        map.put("param", p);
        return map;
    }
}
