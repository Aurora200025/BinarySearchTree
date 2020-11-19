package BinarySearchTree;

public class Person implements Comparable<Person> {

    private int age;

    public Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person person) {
//        if (age > person.age) {
//            return 1;
//        }
//        if (age < person.age) {
//            return -1;
//        }
//        return 0;
        return age - person.age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
