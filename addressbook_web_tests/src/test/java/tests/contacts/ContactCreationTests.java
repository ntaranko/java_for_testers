package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {

    @Test
    public void canCreateContactWithName(){
        app.contacts().createContact(
                new ContactData("Masha", "Petrova"));
    }

    @Test
    public void canCreateContactWithEmail(){
        app.contacts().createContact(
                new ContactData("Natasha", "Taranko", "test@test.com"));
    }

    @Test
    public void canCreateContactWithEmailAndAddress(){
        app.contacts().createContact(
                new ContactData("Ivan", "Ivanov", "Minsk, Belarus","test@test.com"));
    }
}
