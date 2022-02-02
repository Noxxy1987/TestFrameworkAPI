package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Employee{
    @JsonProperty("id")
    private int id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private int salary;
    @JsonProperty("employee_age")
    private int age;
    @JsonProperty("profile_image")
    private String image;
}
