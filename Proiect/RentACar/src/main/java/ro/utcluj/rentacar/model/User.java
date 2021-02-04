/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.utcluj.rentacar.model;

/**
 *
 * @author Martin
 */
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private double age;
    private double driveingExperience;
    private String cnp;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getDriveingExperience() {
        return driveingExperience;
    }

    public void setDriveingExperience(double driveingExperience) {
        this.driveingExperience = driveingExperience;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }
    
        public String toString() {
        return "User{" + "id=" + id + ",, firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", driveingExperience=" + driveingExperience + ", cnp=" + cnp + '}'+"\n";
    }
}
