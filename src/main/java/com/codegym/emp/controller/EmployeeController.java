package com.codegym.emp.controller;

import com.codegym.emp.model.Employee;
import com.codegym.emp.model.Group;
import com.codegym.emp.service.EmployeeService;
import com.codegym.emp.service.GroupService;
import com.codegym.emp.service.impl.EmployeeServiceImpl;
import com.codegym.emp.validation.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private GroupService groupService;

    @ModelAttribute("groups")
    public Iterable<Group> groups() {
        return groupService.findAll();
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("employee", new Employee());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveEmployee(@Valid @ModelAttribute("employee")Employee employee, BindingResult bindingResult) {
        new EmployeeValidator(employeeService).validate(employee, bindingResult);
        ModelAndView modelAndView = new ModelAndView("/create");

        if (!bindingResult.hasFieldErrors()) {
            employeeService.save(employee);
            modelAndView.addObject("employee", new Employee());
            modelAndView.addObject("message", "new Employee is successfully created");
        }
        modelAndView.addObject("message", "Check your information");
        return modelAndView;
    }

    @GetMapping("")
    public ModelAndView showEmployeeList() {
        ModelAndView modelAndView = new ModelAndView("/list");
        Iterable<Employee> employees = employeeService.findAll();
        modelAndView.addObject("employeeList", employees);
        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/delete");
        Employee employee = employeeService.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public ModelAndView deleteEmployee(@PathVariable("id")Long id) {
        employeeService.delete(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        modelAndView.addObject("message", "Deleted Employee");
        return modelAndView;
    }

    @GetMapping("/{id}/edit")
    public ModelAndView showEditForm(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/edit");
        Employee employee = employeeService.findById(id);
        modelAndView.addObject("employee", employee);
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public ModelAndView updateEmployee(@ModelAttribute("employee")Employee employee) {
        employeeService.save(employee);
        ModelAndView modelAndView = new ModelAndView("/edit");
        modelAndView.addObject("employee", employee);
        modelAndView.addObject("message", "Updated employee information");
        return modelAndView;
    }
}
