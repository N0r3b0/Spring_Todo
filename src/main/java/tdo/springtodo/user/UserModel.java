package tdo.springtodo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idu;
    private String username;
    private String password;
    private String role;  // roles: Admin, User
    public UserModel() {

    }
    public UserModel(Long idu, String username, String password, String role) {
        this.idu = idu;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setIdu(Long idu) {
        this.idu = idu;
    }
    public Long getIdu() {
        return idu;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
}