public class MyHashTable<K, V> {
        Entry[] objects;

        public MyHashTable()
        {
            objects = new Entry[11];
        }

        public V put(K key, V value) {
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
                return (V) oldVal.phoneNumber;
            }
            else
            {
                Entry oldVal = objects[index];
                while (oldVal.next != null) {
                    oldVal = oldVal.next;
                }
                oldVal.next = newVal;
                return (V) oldVal.phoneNumber;
            }
        }

        public V get(K key) {
            int index = Math.abs(key.hashCode() % objects.length);
            Entry temp = objects[index];
            while(temp != null) {
                if (temp.person.equals(key)) {
                    return (V) temp.phoneNumber;
                }
                temp = temp.next;
            }
            return null;
        }

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

        public V remove(K person) {
            int index = Math.abs(person.hashCode() % objects.length);
            Entry temp = objects[index];
            
            V tempor = null;
            if (temp == null)
            {
                return null;
            }
            
            if (temp.person.equals(person))
            {
                tempor = (V) objects[index].phoneNumber;
                objects[index] = objects[index].next;
                return tempor;
            }
            
            while (temp.next != null)
            {
                if (temp.next.person.equals(person))
                {
                    tempor = (V) temp.next.phoneNumber;
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

        class Entry<K, V> {
            K person;
            V phoneNumber;
            Entry next;
            public Entry(K name, V number)
            {
                this.person = name;
                this.phoneNumber = number;
            }

            public void set(K otherN, V otherNum)
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