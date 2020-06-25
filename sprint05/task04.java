package com.softserve.academy;

import java.util.Objects;

/*
 * Create class Person with private fields firstName,  lastName, idCode. Create two classes inherited
 * from RuntimeException: NameException and CodeException. NameException will be thrown if the first name or last name
 * is invalid (don't start from uppercase or contains not only letters and symbols "-" and " ").
 * CodeException will be thrown if idCode is invalid (valid idCode contains exactly 10 digits).
 * In class Person create getters methods that throw NameException or CodeException if appropriate
 * arguments are not valid. Create static method buildPerson(String firstName, String lastName, String idCode)
 * that returns Person if all arguments are valid otherwise it thrown IllegalArgumentException with message about
 * all invalid arguments.  Override method toString() for Person class that returns representation of Person in form:
 * firstName lastName: idCode. Override equals and hashCode methods for Person class.
 * For example
 * Person p = new Person();
 * p.setFirstName("joe") throw NameException with message “Incorrect value joe for firstName
 * (should start from upper case and contains only alphabetic characters and -, space symbol;)”
 * p.setIdCode("2") throw CodeException with message "Incorrect value 2 for code (should contains exactly 10 digits)"
 * Person.buildPerson("Joe", "KlarK", "AS-2") throw IllegalArgumentException with message
 * "Incorrect value KlarK for lastName (should start from upper case and contains only alphabetic characters and -, _;);
 * Incorrect value AS-2 for code (should contains exactly 10 digits)"
 */

/**
 * @author Bogdan Kurchak
 */
class Person {
    private String firstName;
    private String lastName;
    private String idCode;

    private final static String errorIdMessage = "Incorrect value %s for code (should contains exactly 10 digits)";
    private final static String errorNamesMessage = "Incorrect value %s for %s (should start from upper case " +
            "and contains only alphabetic characters and symbols -, _)";

    public static Person buildPerson(String firstName, String lastName, String idCode) {
        Person person = new Person();
        StringBuilder errorMessage = new StringBuilder();

        try {
            person.setFirstName(firstName);
        } catch (NameException e) {
            errorMessage.append(String.format(errorNamesMessage, firstName, "firstName")).append("; ");
        }

        try {
            person.setLastName(lastName);
        } catch (NameException e) {
            errorMessage.append(String.format(errorNamesMessage, lastName, "lastName")).append("; ");
        }

        try {
            person.setIdCode(idCode);
        } catch (CodeException e) {
            errorMessage.append(String.format(errorIdMessage, idCode));
        }

        if (!errorMessage.toString().isEmpty())
            throw new IllegalArgumentException(errorMessage.toString());

        return person;
    }

    public void setFirstName(String firstName) throws NameException {
        validation(firstName, "firstName");
        this.firstName = firstName;
    }

    public void setLastName(String lastName) throws NameException {
        validation(lastName, "lastName");
        this.lastName = lastName;
    }

    public void setIdCode(String idCode) throws CodeException {
        validation(idCode);
        this.idCode = idCode;
    }

    private static void validation(String checkedElement) throws CodeException {
        if (!checkedElement.matches("^[0-9]{10}$"))
            throw new CodeException(String.format(Person.errorIdMessage, checkedElement));
    }

    private static void validation(String checkedElement,
                                   String additionalParameter) throws NameException {
        if (!checkedElement.matches("^[A-Z][a-z\\s\\-]+$"))
            throw new NameException(String.format(Person.errorNamesMessage, checkedElement, additionalParameter));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (!idCode.equals(person.idCode)) return false;
        if (!Objects.equals(firstName, person.firstName)) return false;
        return Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (idCode != null ? idCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("%s %s: %s", firstName, lastName, idCode);
    }
}

class NameException extends RuntimeException {
    public NameException(String message) {
        super(message);
    }
}

class CodeException extends RuntimeException {
    public CodeException(String message) {
        super(message);
    }
}
