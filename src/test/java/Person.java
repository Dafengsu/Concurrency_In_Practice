import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: Dafengsu
 * @date: 2019/10/30
 */
public class Person {
    private String name;
    private List<Person> secretaries;

    public Person(String name) {
        this.name = name;
        secretaries = new ArrayList<>();
    }

    public void addSecretary(Person secretary) {
        secretaries.add(secretary);
    }

    public Person getSecretary(int index) {
       return secretaries.get(index);
    }

    public static void main(String[] args) {
        Person person0 = new Person("张三");
        Person person1 = new Person("李四");
        Person person2 = new Person("王五");
        person0.addSecretary(person1);
        person1.addSecretary(person2);
        Person[] people0 = {person0, person1, person2};
        Person[] people1 = Arrays.copyOf(people0, people0.length);
        Person[] people2 = people0.clone();
    }
}
