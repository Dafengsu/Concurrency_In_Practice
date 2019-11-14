package _16the_java_memory_model;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/14
 */
public class ResourceFactory {
    private static class ResourceHolder {
        public static Resource resource = new Resource();
    }

    public static Resource getResource() {
        return ResourceFactory.ResourceHolder.resource;
    }

    static class Resource {
    }
}
