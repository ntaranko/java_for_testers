package model;

public record ContactData(String id,
                          String firstName,
                          String lastName,
                          String address,
                          String homePhone,
                          String mobilePhone,
                          String workPhone,
                          String secondaryPhone,
                          String email,
                          String email2,
                          String email3,
                          String photo) {
    public ContactData() {
        this("", "", "", "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        return new ContactData(
                id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withFirstName(String firstName) {
        return new ContactData(
                this.id,
                firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withLastName(String lastName) {
        return new ContactData(
                this.id,
                this.firstName,
                lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withAddress(String address) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withHomePhone(String homePhone) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withMobilePhone(String mobilePhone) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withWorkPhone(String workPhone) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withSecondaryPhone(String secondaryPhone) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withEmail(String email) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                email,
                this.email2,
                this.email3,
                this.photo);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                email2,
                this.email3,
                this.photo);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                email3,
                this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.homePhone,
                this.mobilePhone,
                this.workPhone,
                this.secondaryPhone,
                this.email,
                this.email2,
                this.email3,
                photo);
    }

   /* public ContactData(String id, String firstName, String lastName) {
        this(id, firstName, lastName,
                "", "", "","");
    }

    public ContactData(String id, String firstName, String lastName, String email) {
        this("", firstName, lastName,
                "", "", email,"");
    }

    public ContactData(String id, String firstName, String lastName, String address, String email) {
        this("", firstName, lastName,
                address, "", email,"");
    }*/

}
