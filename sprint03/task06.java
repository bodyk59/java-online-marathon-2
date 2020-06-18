import java.util.*;

/*
 * Create class hierarchy that represent Address Book,
 * where can be save records in format: "(first name, last name) => address":
 * 1. Records in the Addres Book should be represented as objects of the NameAddressPair type.
 * 2. The pair "(first name, last name)" is key for access to "address" in the Address Book.
 * 3. The key "(first name, last name)" should be immuteble and in Address Book cannot be two records with same key.
 * 4. The capacity of Address Book must grow twice when has no place for save the next record.
 * 5. The sortedBy(...) method should sorted records by ascending or descending using for this Arrays.sort(...) method.
 * 6. The Comparator should be implemented as an anonymous class.
 * 7. Sorting at first be by firstName field, and if the names match then by lastName field.
 * 8 .The next() method from AddressBookIterator class should return record as String in next format:
 *    "First name: <first name>, Last name: <last name>, Address: <address>".
 */

/**
 * @author Bogdan Kurchak
 */
enum SortOrder {
    ASC, DESC;
}

class AddressBook implements Iterable {
    private NameAddressPair[] addressBook;
    private int counter = 0;

    public AddressBook(int capacity) {
        addressBook = new NameAddressPair[capacity];
    }

    public boolean create(String firstName, String lastName, String address) {
        if (counter == addressBook.length) {
            NameAddressPair[] copy = new NameAddressPair[addressBook.length * 2];
            System.arraycopy(addressBook, 0, copy, 0, addressBook.length);
            addressBook = new NameAddressPair[copy.length];

            if (counter >= 0) {
                System.arraycopy(copy, 0, addressBook, 0, counter);
            }
        }

        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.firstName.equals(firstName)
                    && addressBook[i].person.lastName.equals(lastName)) {
                return false;
            }
        }

        addressBook[counter] = new NameAddressPair(new NameAddressPair.Person(firstName, lastName), address);
        counter++;

        return true;
    }

    public String read(String firstName, String lastName) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                return addressBook[i].address;
            }
        }

        return null;
    }

    public boolean update(String firstName, String lastName, String address) {
        NameAddressPair.Person person = new NameAddressPair.Person(firstName, lastName);
        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.equals(person)) {
                addressBook[i].address = address;
                return true;
            }
        }
        return false;
    }

    public boolean delete(String firstName, String lastName) {
        List<NameAddressPair> list = new ArrayList<>();
        int previousSize = counter;

        for (int i = 0; i < counter; i++) {
            if (addressBook[i].person.firstName.equals(firstName)
                    && addressBook[i].person.lastName.equals(lastName)) {
                continue;
            }
            list.add(addressBook[i]);
        }
        addressBook = new NameAddressPair[list.size()];
        for (int i = 0; i < addressBook.length; i++) {
            addressBook[i] = list.get(i);
        }
        if (previousSize != addressBook.length) {
            counter--;
        }
        return previousSize != addressBook.length;
    }

    public int size() {
        return counter;
    }

    public void sortedBy(SortOrder order) {
        switch (order) {
            case ASC:
                Arrays.sort(addressBook, (address1, address2) -> address1.person.firstName.compareTo(address2.person.firstName) == 0
                        ? address1.person.lastName.compareTo(address2.person.lastName)
                        : address1.person.firstName.compareTo(address2.person.firstName));
                break;
            case DESC:
                Arrays.sort(addressBook, (address1, address2) -> address2.person.firstName.compareTo(address1.person.firstName) == 0
                        ? address2.person.lastName.compareTo(address1.person.lastName)
                        : address2.person.firstName.compareTo(address1.person.firstName));
                break;
        }
    }

    @Override
    public Iterator iterator() {
        return new AddressBookIterator();
    }

    private class AddressBookIterator implements Iterator {
        private int counter = 0;

        @Override
        public boolean hasNext() {
            return counter != addressBook.length;
        }

        @Override
        public String next() {
            NameAddressPair nameAddressPair = addressBook[counter++];
            return "First name: " + nameAddressPair.person.firstName
                    + ", Last name: " + nameAddressPair.person.lastName
                    + ", Address: " + nameAddressPair.address;
        }
    }

    private static class NameAddressPair {
        private final Person person;
        private String address;

        private NameAddressPair(Person person, String address) {
            this.person = person;
            this.address = address;
        }

        private static class Person {
            private String firstName;
            private String lastName;

            private Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Person person = (Person) o;
                return Objects.equals(firstName, person.firstName) &&
                        Objects.equals(lastName, person.lastName);
            }
        }

        @Override
        public String toString() {
            return "First name: " + person.firstName +
                    ", Last name: " + person.lastName +
                    ", Address: " + address;
        }
    }
}
