package tests.contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void testPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(
                ContactData::id,
                contact -> Stream.of(contact.homePhone(), contact.mobilePhone(), contact.workPhone(), contact.secondaryPhone())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }
}
