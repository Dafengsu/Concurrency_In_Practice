package _16the_java_memory_model;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/14
 */
public class UnsafeLazyInitialization {
    private static Resource resource;

    public static Resource getInstance() {
        if (resource == null) {
            resource = new Resource();
        }
        return resource;
    }

    static class Resource {
    }

}
