package Test;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class Base {
    protected String name;

    public Base() {
        setName();
    }

    protected void setName() {
        System.out.println("Base: setName");
        this.name = "张三";
    }
}
