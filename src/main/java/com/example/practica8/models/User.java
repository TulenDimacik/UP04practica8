package com.example.practica8.models;


import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usersurname;
    private String username;
    private String usernamee;
    private String userpatronymic;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    @OneToOne(optional = true, mappedBy = "user")//та же аннотация mappedBY сторона которая ссылается на переменную в Person
    private Employee employee;

    public User() {
    }

    public User(String usersurname, String username, String usernamee, String userpatronymic, String password, boolean active, Set<Role> roles, Employee employee) {
        this.usersurname = usersurname;
        this.username = username;
        this.usernamee = usernamee;
        this.userpatronymic = userpatronymic;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsersurname() {
        return usersurname;
    }

    public void setUsersurname(String usersurname) {
        this.usersurname = usersurname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernamee() {
        return usernamee;
    }

    public void setUsernamee(String usernamee) {
        this.usernamee = usernamee;
    }

    public String getUserpatronymic() {
        return userpatronymic;
    }

    public void setUserpatronymic(String userpatronymic) {
        this.userpatronymic = userpatronymic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

}
