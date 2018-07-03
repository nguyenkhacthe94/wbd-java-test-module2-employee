package com.codegym.emp.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "group_employee")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "group_employee_name")
    private String groupName;
    @Column(name = "group_employee_desc")
    private String groupDesc;

    @OneToMany
    private List<Employee> employeeList;

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Group(String groupName, String groupDesc, List<Employee> employeeList) {

        this.groupName = groupName;
        this.groupDesc = groupDesc;
        this.employeeList = employeeList;
    }

    public Group() {
    }

    public Group(String groupName, String groupDesc) {
        this.groupName = groupName;
        this.groupDesc = groupDesc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }
}
