package com.fsd.employee.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "emp")
public class Employee {
    @Indexed(unique = true)
    @Field("employeeId")
    private String employeeId;
    @Field("firstName")
    private String firstName;
    @Field("lastName")
    private String lastName;
    @Field("emailId")
    private String emailId;
    @Field("active")
    private Boolean active;

    public Employee() {
    }


    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    public Boolean getActive() {
        return active;
    }


    public void setActive(Boolean active) {
        this.active = active;
    }


    @Override
    public String toString() {
        return "Employee [" + (employeeId != null ? "employeeId=" + employeeId + ", " : "")
                + (firstName != null ? "firstName=" + firstName + ", " : "")
                + (lastName != null ? "lastName=" + lastName + ", " : "")
                + (emailId != null ? "emailId=" + emailId + ", " : "") + (active != null ? "active=" + active : "")
                + "]";
    }


}
