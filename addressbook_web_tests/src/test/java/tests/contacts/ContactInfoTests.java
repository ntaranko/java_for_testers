package tests.contacts;

import common.CommonFunctions;
import io.qameta.allure.Allure;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(new ContactData()
                        .withFirstName(CommonFunctions.randomString(5))
                        .withLastName(CommonFunctions.randomString(10))
                        .withAddress(CommonFunctions.randomString(15))
                        .withHomePhone(CommonFunctions.randomString(12))
                        .withMobilePhone(CommonFunctions.randomString(12))
                        .withWorkPhone(CommonFunctions.randomString(12))
                        .withEmail(CommonFunctions.randomEmail(10))
                        .withEmail2(CommonFunctions.randomEmail(10))
                        .withEmail3(CommonFunctions.randomEmail(10)));
            }
        });

        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(
                ContactData::id,
                contact -> Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Allure.step("Validating result", step -> {
            Assertions.assertEquals(expected, phones);
        });

    }

    @Test
    void testContactData() {
        Allure.step("Checking precondition", step -> {
            if (app.hbm().getContactCount() == 0) {
                app.contacts().createContact(new ContactData()
                        .withFirstName(CommonFunctions.randomString(5))
                        .withLastName(CommonFunctions.randomString(10))
                        .withAddress(CommonFunctions.randomString(15))
                        .withHomePhone(CommonFunctions.randomString(12))
                        .withMobilePhone(CommonFunctions.randomString(12))
                        .withWorkPhone(CommonFunctions.randomString(12))
                        .withEmail(CommonFunctions.randomEmail(10))
                        .withEmail2(CommonFunctions.randomEmail(10))
                        .withEmail3(CommonFunctions.randomEmail(10)));
            }
        });

        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);

        var phones = app.contacts().getPhones(contact);
        var expectedPhones = Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Allure.step("Validating result for phones", step -> {
            Assertions.assertEquals(expectedPhones, phones);
        });

        var emails = app.contacts().getEmails(contact);
        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Allure.step("Validating result for emails", step -> {
            Assertions.assertEquals(expectedEmails, emails);
        });

        var address = app.contacts().getAddress(contact);
        var expectedAddress = contact.address();
        Allure.step("Validating result for address", step -> {
            Assertions.assertEquals(expectedAddress, address);
        });

    }
}
