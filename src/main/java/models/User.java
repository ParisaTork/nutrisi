package models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return this.id; }

    public String getName() { return this.name; }

    public String getEmail() {return this.email; }

    public String getPassword() {return this.password; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return  Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
