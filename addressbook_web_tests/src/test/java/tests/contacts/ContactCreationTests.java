package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

       public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "First Name")) {
            for (var lastName : List.of("", "Last Name")) {
                for (var address : List.of("", "Minsk Address")) {
                    for (var email : List.of("", "test@test.com")) {
                        {
                            result.add(new ContactData(firstName, lastName, address, email));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomEmail(i * 10)));

        }
        return result;
    }



    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(newContactCount, contactCount + 1);
    }

    public static List<ContactData> negativeContactProvider() {
           var result = new ArrayList<ContactData>(List.of(
                   new ContactData("first name'", "last name")));
           return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(newContactCount, contactCount);

    }
}
