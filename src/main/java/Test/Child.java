package Test;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/28
 */
public class Child extends Base {


    public Child() {
        super();
    }

    @Override
    protected void setName() {
        System.out.println("Child: setName");
        this.name = "child: " + "张三";
    }

    public static void main(String[] args) {
        Child child = new Child();
     /*   System.out.println(child.name);*/
    }
}
