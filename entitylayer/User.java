package entitylayer;

public abstract class User {
    String userID;
    String name;
    String password;
    String role;

    public User(String userID, String name, String password, String role) {
        this.userID = userID;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    // Getters
    public String getUserID() { return userID; }
    public String getName() { return name; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    // Setters
    public void setUserID(String userID) { this.userID = userID; }
    public void setName(String name) { this.name = name; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }

}
