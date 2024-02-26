package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(
                    new ContactData("Name", "LastName"));
        }
        app.contacts().removeContact();
    }
}
