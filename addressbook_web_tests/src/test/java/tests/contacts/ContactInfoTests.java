package tests.contacts;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
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
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(
                ContactData::id,
                contact -> Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void testEmails() {
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
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var emails = app.contacts().getEmails(contact);
        var expected = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !"".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expected, emails);
    }

    @Test
    void testAddress() {
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
        var contacts = app.hbm().getContactList();
        var contact = contacts.get(0);
        var address = app.contacts().getAddress(contact);
        var expected = contact.address();
        Assertions.assertEquals(expected, address);
    }
}
