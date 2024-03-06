package model;

public record ContactData(String id, String firstName,
                          String lastName,
                          String companyName,
                          String address,
                          String homePhone,
                          String mobilePhone,
                          String workPhone,
                          String email) {
    public ContactData(){
        this("", "", "", "", "", "", "", "", "");
    }

    public ContactData(String id, String firstName, String lastName) {
        this(id, firstName, lastName, "",
                "", "", "", "", "");
    }

    public ContactData(String id, String firstName, String lastName, String email) {
        this("", firstName, lastName, "",
                "", "", "", "", email);
    }

    public ContactData(String id,String firstName, String lastName, String address, String email) {
        this("", firstName, lastName, "",
                address, "", "", "", email);
    }


}
