package tests.contacts;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ContactsGroupsConnectionsTests extends TestBase {
    public static Stream<Arguments> contactAndGroupProvider() {
        return Stream.of(
                arguments(new ContactData()
                                .withFirstName(CommonFunctions.randomString(5))
                                .withLastName(CommonFunctions.randomString(10))
                                .withAddress(CommonFunctions.randomString(15))
                                .withMobilePhone(CommonFunctions.randomString(12))
                                .withEmail(CommonFunctions.randomEmail(10)),
                        new GroupData()
                                .withName(CommonFunctions.randomString(5))
                                .withHeader(CommonFunctions.randomString(10))
                                .withFooter(CommonFunctions.randomString(5))));
    }


    @ParameterizedTest
    @MethodSource("contactAndGroupProvider")
    void canRemoveContactFromGroup(ContactData contact, GroupData group) {
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(contact);
        }

        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(group);
        }
        // Precondition: connect group and contact
        var contactList = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contactList.size());
        var contactToRemove = contactList.get(index);
        var groupToRemove = app.hbm().getGroupList().get(0);
        app.contacts().addContactToGroup(contactToRemove, groupToRemove);
        // Precondition end------

        var oldRelated = app.hbm().getContactsInGroup(groupToRemove);
        app.contacts().removeContactFromGroup(contactToRemove, groupToRemove);
        var newRelated = app.hbm().getContactsInGroup(groupToRemove);

        var expectedList = new ArrayList<>(oldRelated);
        expectedList.remove(contactToRemove);

        Assertions.assertEquals(newRelated, expectedList);
    }

    @ParameterizedTest
    @MethodSource("contactAndGroupProvider")
    void canAddContactToGroup(ContactData contact, GroupData group) {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(group);
        }

        var groupToAdd = app.hbm().getGroupList().get(0);

        var oldRelatedContactList = app.hbm().getContactsInGroup(groupToAdd);
        var notRelatedContactList = app.contacts().getNotRelatedContacts(app.hbm().getContactList(), oldRelatedContactList);

        if (notRelatedContactList.isEmpty()) {
            app.contacts().createContact(contact);
        }

        notRelatedContactList = app.contacts().getNotRelatedContacts(app.hbm().getContactList(), oldRelatedContactList);
        var rnd = new Random();
        var index = rnd.nextInt(notRelatedContactList.size());
        var contactToAdd = notRelatedContactList.get(index);

        app.contacts().addContactToGroup(contactToAdd, groupToAdd);
        var newRelatedContactList = app.hbm().getContactsInGroup(groupToAdd);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelatedContactList.sort(compareById);

        var expectedList = new ArrayList<ContactData>(oldRelatedContactList);
        expectedList.add(contactToAdd);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelatedContactList, expectedList);
    }



    //DOESN'T work
    @ParameterizedTest
    @MethodSource("contactAndGroupProvider")
    void canAddContactToGroupHbm(ContactData contact, GroupData group) {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(group);
        }

        var groupToAdd = app.hbm().getGroupList().get(0);
        var notRelatedContactList = app.hbm().getContactsNotInGroup(groupToAdd);

        if (notRelatedContactList.isEmpty()) {
            app.contacts().createContact(contact);
            notRelatedContactList.add(contact);
        }

        var oldRelated = app.hbm().getContactsInGroup(groupToAdd);
        var rnd = new Random();
        var index = rnd.nextInt(notRelatedContactList.size());
        var contactToAdd = notRelatedContactList.get(index);

        app.contacts().addContactToGroup(contactToAdd, groupToAdd);
        var newRelated = app.hbm().getContactsInGroup(groupToAdd);
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);

        var expectedList = new ArrayList<ContactData>(oldRelated);
        expectedList.add(contactToAdd);
        expectedList.sort(compareById);
        Assertions.assertEquals(newRelated, expectedList);
    }
}
