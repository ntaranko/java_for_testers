package manager;

import model.ContactData;
import org.openqa.selenium.By;

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

    public void modifyContact(ContactData contact){
        openHomePage();
        selectContact();
        initContactModification();
        fillContactForm(contact);
        submitContactModification();
        returnToHomePage();
    }

    public void removeContact(){
        openHomePage();
        selectContact();
        removeSelectedContact();
        openHomePage();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void initContactModification() {
        click(By.xpath("(//img[@alt=\'Edit\'])[2]"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
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
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("company"), contact.companyName());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.homePhone());
        type(By.name("mobile"), contact.mobilePhone());
        type(By.name("work"), contact.workPhone());
        type(By.name("email"), contact.email());
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    public boolean isContactPresent() {
        openHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }
}
