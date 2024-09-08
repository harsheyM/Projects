import com.sun.security.jgss.GSSUtil;

public class PhoneBookTester {

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        Person Rin = new Person("Rin");
        Person Rasika = new Person("Rasika");
        Person Jamie = new Person("Jamie");
        Person[] people = {Rin, Rasika, Jamie};

        PhoneNumber rinNum = new PhoneNumber("617");
        PhoneNumber rasikaNum = new PhoneNumber("911");
        PhoneNumber jamieNum = new PhoneNumber("111");
        PhoneNumber[] phone = {rinNum, rasikaNum, jamieNum};

        book.put(Rin, rinNum);
        book.put(Rasika, rasikaNum);
        book.put(Jamie, jamieNum);
        book.put(Rin, rasikaNum);

        System.out.print(book.toString());
    }

}