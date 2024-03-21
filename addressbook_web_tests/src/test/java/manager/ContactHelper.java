package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToHomePage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
        submitContactCreation();
        returnToHomePage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToHomePage();
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectToGroup(group);
        addToGroup();
        openHomePage();
    }

    private void selectToGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void addToGroup() {
        click(By.name("add"));
    }

    public void removeContacts(ContactData contact) {
        openHomePage();
        selectContact(contact);
        removeSelectedContacts();
        openHomePage();
    }

    private void removeSelectedContacts() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification(ContactData contact) {
        var id = contact.id();
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void returnToHomePage() {
        click(By.linkText("home page"));
    }

    private void openHomePage() {
        click(By.linkText("home"));
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContactForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("email"), contact.email());
        // attach(By.name("photo"), contact.photo());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }

    public int getCount() {
        openHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        openHomePage();
        //selectAllContacts();
        selectAllContactsCheckbox();
        removeSelectedContacts();
    }

    private void selectAllContacts() {
        var checkboxes = manager.driver.findElements(By.name("selected[]"));
        for (var checkbox : checkboxes) {
            checkbox.click();
        }
    }

    private void selectAllContactsCheckbox() {
        click(By.id("MassCB"));
    }

    public List<ContactData> getList() {
        openHomePage();
        var contacts = new ArrayList<ContactData>();
        var rows = manager.driver.findElements(By.cssSelector("tr[name='entry']"));
        for (var row : rows) {
            var lastName = row.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstName = row.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = row.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");

            contacts.add(new ContactData().withId(id)
                    .withFirstName(firstName)
                    .withLastName(lastName));
        }
        return contacts;
    }


    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupToFilter(group);
        selectContact(contact);
        removeSelectedContactFromGroup();
        openHomePage();
    }

    private void removeSelectedContactFromGroup() {
        click(By.name("remove"));
    }

    private void selectGroupToFilter(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public List<ContactData> getNotRelatedContacts(List<ContactData> contactList, List<ContactData> relatedContactList) {
        var notRelatedContactList = new ArrayList<ContactData>();
        for (ContactData contactData : contactList) {
            if (!relatedContactList.contains(contactData)) {
                notRelatedContactList.add(contactData);
            }
        }
        return notRelatedContactList;
    }
}
