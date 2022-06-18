package Models;

public class User {
    private int userId;
    private String userEmailAddress;
    private String userName;
    private String phoneNumber;
    private String userPassword;

    public User(int userId, String userEmailAddress, String userName, String phoneNumber, String userPassword) {
        this.userId = userId;
        this.userEmailAddress = userEmailAddress;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.userPassword = userPassword;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserEmailAddress() {
        return userEmailAddress;
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.userEmailAddress = userEmailAddress;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
