import java.util.Arrays;

public class PhoneBook implements IMap{

    Entry[] objects;

    public PhoneBook()
    {
        objects = new Entry[11];
    }

    @Override
    public PhoneNumber put(Person key, PhoneNumber value) {
        int index = Math.abs(key.hashCode() % objects.length);
            Entry newVal = new Entry(key, value);
            if (objects[index] == null)
            {
                objects[index] = newVal;
                return null;
            }
            else if(objects[index].person.equals(key))
            {
                Entry oldVal = objects[index];
                objects[index] = newVal;
                return oldVal.phoneNumber;
            }
            else
            {
                Entry oldVal = objects[index];
                while (oldVal.next != null) {
                    oldVal = oldVal.next;
                }
                oldVal.next = newVal;
                return oldVal.phoneNumber;
            }
    }

    @Override
    public PhoneNumber get(Person key) {
        int index = Math.abs(key.hashCode() % objects.length);
            Entry temp = objects[index];
            while(temp != null) {
                if (temp.person.equals(key)) {
                    return temp.phoneNumber;
                }
                temp = temp.next;
            }
            return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (int i = 0; i < objects.length; i++)
        {
            if (objects[i] != null)
            {
                size++;
            }
        }
        return size;
    }

    @Override
    public PhoneNumber remove(Person person) {
        int index = Math.abs(person.hashCode() % objects.length);
            Entry temp = objects[index];
            
            PhoneNumber tempor = null;
            if (temp == null)
            {
                return null;
            }
            
            if (temp.person.equals(person))
            {
                tempor = objects[index].phoneNumber;
                objects[index] = objects[index].next;
                return tempor;
            }
            
            while (temp.next != null)
            {
                if (temp.next.person.equals(person))
                {
                    tempor = temp.next.phoneNumber;
                    temp.next = temp.next.next;
                    break;
                }
                else
                {
                    temp = temp.next;
                }
            }
            return tempor;
    }

    @Override
    public String toString() {
        String book = "";
        for (int i = 0; i < objects.length; i++)
        {
            if (objects[i] == null)
            {
                book += "null" + "\n";
            }
            else {
                book += objects[i].toString() + "\n";
            }
        }
        return book;
    }

    class Entry {
        Person person;
        PhoneNumber phoneNumber;
        Entry next;
        public Entry(Person name, PhoneNumber number)
        {
            this.person = name;
            this.phoneNumber = number;
        }

        public void set(Person otherN, PhoneNumber otherNum)
        {
            this.person = otherN;
            this.phoneNumber = otherNum;
        }

        @Override
        public String toString() {
            return person + " + " + phoneNumber;
        }
    }
}