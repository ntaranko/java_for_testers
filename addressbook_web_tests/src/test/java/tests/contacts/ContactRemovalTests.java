package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    void canRemoveContact() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactData("Name", "LastName"));
        }
        int contactCount = app.contacts().getCount();
        app.contacts().removeContacts();
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount - 1, newContactCount);
    }

    @Test
    void canRemoveAllContactsAtOnes() {
        if (app.contacts().getCount() == 0) {
            app.contacts().createContact(
                    new ContactData("Name", "LastName"));
        }
        app.contacts().removeAllContacts();
        Assertions.assertEquals(0, app.contacts().getCount());

    }
}
