package swing;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/11/7
 */
public class SwingTest {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        final JComboBox<Integer> combo = new JComboBox<>();
        combo.insertItemAt(Integer.MAX_VALUE, 0);
        combo.setPrototypeDisplayValue(combo.getItemAt(0));
        combo.setSelectedIndex(0);
        Random generator = new Random();
        JPanel panel = new JPanel();

        JButton goodButton = new JButton("Good");
        goodButton.addActionListener(event ->new Thread(()->{
            System.out.println("当前线程是" + Thread.currentThread().getName());
           /* EventQueue.invokeLater(()->{*/
                for (int i = 0; i < 5000; i++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
              /*  System.out.println("当前线程是" + Thread.currentThread().getName());*/
           /* });*/
            int i = Math.abs(generator.nextInt());
            combo.insertItemAt(i, 0);
        }).start()
      );
        panel.add(goodButton);

        panel.add(combo);
        jFrame.add(panel);
        jFrame.pack();
        jFrame.setSize(200, 200);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
