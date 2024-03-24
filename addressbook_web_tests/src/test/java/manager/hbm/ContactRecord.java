package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    public int id;
    @Column(name = "firstname")
    public String firstName;
    @Column(name = "lastName")
    public String lastName;
    public String address;
    @Column(name = "home")
    public String homePhone;
    @Column(name = "mobile")
    public String mobilePhone;
    @Column(name = "work")
    public String workPhone;
    @Column(name = "phone2")
    public String secondaryPhone;
    public String email;

    public ContactRecord() {
    }

    public ContactRecord(int id, String firstName, String lastName, String address, String homePhone, String mobilePhone, String workPhone, String secondaryPhone, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.secondaryPhone = secondaryPhone;
        this.email = email;
    }
}
