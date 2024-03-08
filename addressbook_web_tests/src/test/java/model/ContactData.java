package model;

public record ContactData(String id,
                          String firstName,
                          String lastName,
                          String address,
                          String mobilePhone,
                          String email,
                          String photo) {
    public ContactData() {
        this("", "", "", "", "", "","");
    }

    public ContactData withId (String id){
        return new ContactData(id,
                this.firstName,
                this.lastName,
                this.address,
                this.mobilePhone,
                this.email,
                this.photo);
    }

    public ContactData withFirstName (String firstName){
        return new ContactData(this.id,
                firstName,
                this.lastName,
                this.address,
                this.mobilePhone,
                this.email,
                this.photo);
    }

    public ContactData withLastName (String lastName){
        return new ContactData(this.id,
                this.firstName,
                lastName,
                this.address,
                this.mobilePhone,
                this.email,
                this.photo);
    }

    public ContactData withAddress (String address){
        return new ContactData(this.id,
                this.firstName,
                this.lastName,
                address,
                this.mobilePhone,
                this.email,
                this.photo);
    }

    public ContactData withMobilePhone (String mobilePhone){
        return new ContactData(this.id,
                this.firstName,
                this.lastName,
                this.address,
                mobilePhone,
                this.email,
                this.photo);
    }

    public ContactData withEmail (String email){
        return new ContactData(this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.mobilePhone,
                email,
                this.photo);
    }

    public ContactData withPhoto (String photo){
        return new ContactData(this.id,
                this.firstName,
                this.lastName,
                this.address,
                this.mobilePhone,
                this.email,
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
