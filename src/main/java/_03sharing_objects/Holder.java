package _03sharing_objects;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/30
 */
public class Holder {
    private int n;



    public Holder(int n){

        StuffIntoPublic.holder = this;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.n = n;

    }

    public void assertSanity() {
        int i = n;
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i != n) {
            throw new AssertionError("This statement is false");
        } else {
            System.out.println("No");
        }
    }


}


