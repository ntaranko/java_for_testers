package tests.contacts;

import io.qameta.allure.Allure;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(
                        new ContactData("", "Sergey", "Petrov", "email@test.ru", "+375292217200", "", "", "", "test@test.ru", "", "", ""));
            }
        });

        var testData = new ContactData("", "New First Name", "New Last Name", "", "", "", "", "", "", "", "", "");
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contactToModify = oldContacts.get(index);
        Allure.step("Contact modification", step -> {
            app.contacts().modifyContact(contactToModify, testData);
        });
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(newContacts, expectedList);
        });
    }
}
