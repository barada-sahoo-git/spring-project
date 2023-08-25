package com.example.restapidemo.restcrudapidemo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "employee")
@JsonIgnoreProperties
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer empId;

    @Column(name = "emp_name")
    @NotNull(message = "employee name can not be missing or null")
    private String empName;
    @Column(name ="emp_age")
    @NotNull(message = "age can not be null or 0")
    private int empAge;
    @Column(name ="emp_addr")
    @NotNull(message = "address can not be null or missing")
    private String empAddress;


}
