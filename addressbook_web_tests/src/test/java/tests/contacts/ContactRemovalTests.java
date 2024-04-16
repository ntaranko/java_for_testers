package tests.contacts;

import io.qameta.allure.Allure;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    void canRemoveContact() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(
                        new ContactData("", "New First Name", "New Last Name", "", "", "", "", "", "", "", "", ""));
            }
        });
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        Allure.step("Removing Contact", step -> {
            app.contacts().removeContacts(oldContacts.get(index));
        });
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.remove(index);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newContacts, expectedList);
        });
    }

    @Test
    void canRemoveAllContactsAtOnes() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(
                        new ContactData("", "Name", "LastName", "", "", "", "", "", "", "", "", ""));
            }
        });
        Allure.step("Removing all contacts", step -> {
            app.contacts().removeAllContacts();
        });
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(0, app.hbm().getContactCount());
        });
    }
}
