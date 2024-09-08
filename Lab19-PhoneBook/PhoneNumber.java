import java.util.Objects;

public class PhoneNumber {
    String phoneNumber;
    public PhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Phone number: " + phoneNumber;
    }

}