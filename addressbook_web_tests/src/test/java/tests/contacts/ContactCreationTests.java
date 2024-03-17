package tests.contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        /*for (var firstName : List.of("", "First Name")) {
            for (var lastName : List.of("", "Last Name")) {
                for (var address : List.of("", "Minsk Address")) {
                    for (var email : List.of("", "test@test.com")) {
                        {
                            result.add(new ContactData().withFirstName(firstName)
                                    .withLastName(lastName).withAddress(address)
                                    .withEmail(email));
                        }
                    }
                }
            }
        }*/

        for (int i = 0; i < 5; i++) {
            result.add(new ContactData().withFirstName(CommonFunctions.randomString(i * 5))
                    .withLastName(CommonFunctions.randomString(i * 5))
                    .withAddress(CommonFunctions.randomString(i * 5))
                    .withMobilePhone(CommonFunctions.randomString(i * 5))
                    .withEmail(CommonFunctions.randomEmail(i * 5)));

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
        expectedList.add(contact.withId(newContacts.get(newContacts.size() - 1).id())
                .withAddress("")
                .withMobilePhone("")
                .withEmail(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> jsonContactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        /*for (var firstName : List.of("", "First Name")) {
            for (var lastName : List.of("", "Last Name")) {
                for (var address : List.of("", "Minsk Address")) {
                    for (var email : List.of("", "test@test.com")) {
                        {
                            result.add(new ContactData().withFirstName(firstName)
                                    .withLastName(lastName).withAddress(address)
                                    .withEmail(email));
                        }
                    }
                }
            }
        }*/

        ObjectMapper mapper = new ObjectMapper();
        var json = Files.readString(Paths.get("contacts.json"));
        var value = mapper.readValue(json, new TypeReference<List<ContactData>>() {
        });
        result.addAll(value);
        return result;
    }

    @ParameterizedTest
    @MethodSource("jsonContactProvider")
    public void canCreateMultipleContactFromJson(ContactData contact) {
        var oldContacts = app.contacts().getList();
        app.contacts().createContact(contact); //check methods (id)
        var newContacts = app.contacts().getList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId)
                .withAddress("")
                .withMobilePhone("")
                .withEmail(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    public static List<ContactData> negativeContactProvider() {
        var result = new ArrayList<ContactData>(List.of(
                new ContactData("", "first name'", "last name", "", "", "", "")));
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

    @Test
    public void canCreateContactWithPhoto() {
        app.contacts().createContact(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withEmail(CommonFunctions.randomEmail(10))
                .withPhoto(randomFile("src/test/resources/images/")));
    }


    public static List<ContactData> singleContactProvider() {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(15))
                .withMobilePhone(CommonFunctions.randomString(12))
                .withEmail(CommonFunctions.randomEmail(10)));
    }

    @ParameterizedTest
    @MethodSource("singleContactProvider")
    public void canCreateContactHibernate(ContactData contact) {
        var oldContacts = app.hbm().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.hbm().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);

        var maxId = newContacts.get(newContacts.size() - 1).id();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canCreateContactInGroup(){
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(5))
                .withLastName(CommonFunctions.randomString(10))
                .withAddress(CommonFunctions.randomString(15))
                .withMobilePhone(CommonFunctions.randomString(12))
                .withEmail(CommonFunctions.randomEmail(10));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(
                    new GroupData("", "Test group", "test group header", "test group footer"));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContact(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());



    }
}
