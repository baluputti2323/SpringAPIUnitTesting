package Sping8.hours.com.example.SpringAPIUnit.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserDetails {
@jakarta.persistence.Id
@GeneratedValue(strategy = GenerationType.AUTO)
    private long Id;
    private String name;
    private String role;
public UserDetails(){}
    public long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public UserDetails(String name, String role) {
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDetails{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
