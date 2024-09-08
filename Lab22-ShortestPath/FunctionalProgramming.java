import java.util.*;
import java.util.stream.Collectors;

public class FunctionalProgramming {
    public static void main(String[] args) {
        List<String> names = new ArrayList<String>();

        //2
        names.add("Harshey");
        names.add("Jamie");
        names.add("Rin");
        names.add("Rasika");
        names.add("Amy");

        names.forEach(n -> System.out.println(n));

        //3
        List<Integer> nums = new ArrayList<Integer>();
        nums.add(5);
        nums.add(8);
        nums.add(9);
        nums.add(10);

        nums.removeIf(a -> a % 2 == 0);

        //4
        List<Integer> odd = nums.stream()
                .filter(a -> a % 2 == 1)
                .collect(Collectors.toList());
        System.out.println(odd);

        long countOdd = nums.stream()
                .count();
        System.out.println(countOdd);


        //5
        List<String> begA = names.stream()
                .filter(name -> name.charAt(0) == 'A')
                .collect(Collectors.toList());
        System.out.println(begA);

        //6
        List<Integer> doubleUp = nums.stream()
                .map(num -> num * 2)
                .collect(Collectors.toList());
        System.out.println(doubleUp);

        //8
        List<Double> taxes = nums.stream()
                .map(num -> num * 0.12)
                .collect(Collectors.toList());
        System.out.println(doubleUp);

        //9
        long count = nums.stream()
                .count();
        System.out.println(count);

        //10
        List<Double> taxes2 = nums.stream()
                .map(num -> num * 0.12)
                .collect(Collectors.toList());
        long sum = nums.stream()
                .count();
        System.out.println(sum);

        //11
        int maxValue = nums.stream()
                .mapToInt(num -> num / 1)
                .max()
                .getAsInt();
        System.out.println(maxValue);

        //12
        class Person {
            String name;
            int age;

            Person(String name, int age) { this.name = name; this.age  = age; }

            @Override
            public String toString() { return this.name + ", " + this.age; }

            int getAge() { return this.age; }
            String getName() { return this.name; }
        }
        List<Person> users = new ArrayList<>();
        users.add(new Person("Sarah",   40));
        users.add(new Person("Peter",   50));
        users.add(new Person("Lucy",    60));
        users.add(new Person("Albert",  20));
        users.add(new Person("Charlie", 30));

        int maxAge = users.stream()
                .mapToInt(person1 -> person1.getAge())
                .max()
                .getAsInt();
        System.out.println(maxValue);

        //13
        Collections.sort(users, Comparator.comparing(Person::getName));
        Collections.sort(users, Comparator.comparing(Person::getAge));
        
        Comparator<Student> comparatorPeople = Comparator.comparing(Person::getAge)
              .thenComparing(Person::getName)
              .thenComparing(Comparator.comparingInt(Person::getAge).reversed());
        List<Person> organized = new ArrayList<Person>();
        
        Collections.sort(organized, comparatorPeople);
        System.out.println(organized);
        
        System.out.println(users);
    }
}
