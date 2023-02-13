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
@Table(name = "user")
@Data
public class User implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private int userID;
    @Column(name = "User_Name")
    private String userName;
    @Column(name = "User_Email")
    private String userEmail;
    @Column(name = "User_Balance")
    private int userBalance;

    public User() {
    }

    public User(int userID, String userName, String userEmail, int userBalance) {
        this.userID = userID;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userBalance = userBalance;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public int getUserBalance() {
        return this.userBalance;
    }

    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

}
