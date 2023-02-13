package vissoft.test.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Contact")
@Data
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Contact_ID")
    private int contactID;
    @Column(name = "from_UserID")
    private int from_UserID;
    @Column(name = "to_UserID")
    private int to_UserID;

    public Contact() {

    }

    public Contact(int from_UserID, int to_UserID) {
        this.from_UserID = from_UserID;
        this.to_UserID = to_UserID;
    }

    public int getContactID() {
        return this.contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public int getFrom_UserID() {
        return this.from_UserID;
    }

    public void setFrom_UserID(int from_UserID) {
        this.from_UserID = from_UserID;
    }

    public int getTo_UserID() {
        return this.to_UserID;
    }

    public void setTo_UserID(int to_UserID) {
        this.to_UserID = to_UserID;
    }

}
