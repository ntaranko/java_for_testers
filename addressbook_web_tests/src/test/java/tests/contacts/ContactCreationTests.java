package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "First Name")) {
            for (var lastName : List.of("", "Last Name")) {
                for (var address : List.of("", "Minsk Address")) {
                    for (var email : List.of("", "test@test.com")) {
                        {
                            result.add(new ContactData("", firstName, lastName, address, email));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData("",
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10), randomEmail(i * 10)));

        }
        return result;
    }


    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact); //check methods (id)
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var expectedList = new ArrayList<>(oldContacts);
        var contactWithId = new ContactData(     //create intermediate contact with id, firstName, lastName
                newContacts.get(newContacts.size()-1).id(),
                contact.firstName(),
                contact.lastName()
        );
        expectedList.add(contactWithId); //add contact with id
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "last name")));
        return result;
    }

    @ParameterizedTest
    @MethodSource("negativeContactProvider")
    public void canNotCreateContact(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact);
        var newContacts = app.contacts().getList();
        Assertions.assertEquals(oldContacts, newContacts);

    }
}
