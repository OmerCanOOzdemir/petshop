package com.example.petshop.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Firstname field cannot be empty")
    private String firstname;
    @NotEmpty(message = "Lastname field cannot be empty")
    private String lastname;

    @NotEmpty(message = "Email field cannot be empty")
    @Email(message = "Put your mail")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password field cannot be empty")
    @Size(min = 6,message = "Size of password must be longer than 6 characters")
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_name", nullable=true,referencedColumnName = "name")
    private Role role;



    public User() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}

