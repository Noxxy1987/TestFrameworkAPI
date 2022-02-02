package utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    public final static TestContext INSTANCE = new TestContext();
    private Map<String, Object> testContext;


    private TestContext() {
        testContext = new HashMap<>();
    }

    public void add(String objectKey, Object objectValue) {
        if (this.testContext.containsKey(objectKey)) {
            Logger.log("Replacing key: %s value on testContext.", objectKey);
            this.testContext.remove(objectKey);
        }
        Logger.log("Adding key: %s to TestContext", objectKey);
        this.testContext.put(objectKey, objectValue);
    }

    public Object get(String objectKey) {
        return this.testContext.get(objectKey);
    }

    public void purge() {
        Logger.log("Purge TestContext.");
        this.testContext.clear();
    }
}
