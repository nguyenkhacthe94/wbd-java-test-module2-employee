package com.codegym.emp.validation;

import com.codegym.emp.model.Employee;
import com.codegym.emp.service.EmployeeService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.sql.Date;

public class EmployeeValidator implements Validator {
    private EmployeeService employeeService;

    public EmployeeValidator(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Employee.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Employee employee = (Employee) target;
        String name = employee.getName();
        Byte gender = employee.getGender();
        Date birthday = employee.getBirthday();
        String phone = employee.getPhone();
        String idCard = employee.getIdCard();
        String address = employee.getAddress();

        ValidationUtils.rejectIfEmpty(errors, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(errors, "gender", "gender.empty");
        ValidationUtils.rejectIfEmpty(errors, "birthday", "birthday.empty");
        ValidationUtils.rejectIfEmpty(errors, "phone", "phone.empty");
        ValidationUtils.rejectIfEmpty(errors, "idCard", "idCard.empty");
        ValidationUtils.rejectIfEmpty(errors, "address", "address.empty");
    }
}
