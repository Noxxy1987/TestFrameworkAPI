package utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    public final static TestContext INSTANCE = new TestContext();
    private Map<String, Object> testContext;

    private TestContext() {
        testContext = new HashMap<>();
    }

    public Object get(String objectKey) {
        return this.testContext.get(objectKey);
    }

//    public Object[] getAllElements() {
//        return this.testContext.entrySet().toArray();
//    }

    public void add(String objectKey, Object objectValue) {
        if (this.testContext.containsKey(objectKey)) {
            Logger.log("Replacing key: %s value on TestRunContext.", objectKey);
            this.testContext.remove(objectKey);
        }
        Logger.log("Adding key: %s to TestRunContext.", objectKey);
        this.testContext.put(objectKey, objectValue);
    }

    public void remove(String objectKey) {
        Logger.log("Remove key: %s from .", objectKey);
        this.testContext.remove(objectKey);
    }

    public boolean contains(String objectKey) {
        return this.testContext.containsKey(objectKey);
    }

    public void purge() {
        Logger.log("Purge TestContext.");
        this.testContext.clear();
    }
}
