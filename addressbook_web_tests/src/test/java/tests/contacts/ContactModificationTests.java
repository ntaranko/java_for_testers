package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {

        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(
                    new ContactData("Sergey", "Petrov", "email@test.ru"));
        }
        app.contacts().modifyContact(
                new ContactData("New First Name", "New Last Name"));
    }

}
