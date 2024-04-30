package com.example.employee;

import com.example.position.Position;
import com.example.position.PositionNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired private EmployeeService service;

    @GetMapping("/employees")
    public String showPositionList(Model model) {
        List<Employee> listEmployees = service.listAll();
        model.addAttribute("listEmployees", listEmployees);

        return "employee";
    }

    @GetMapping("/employees/new")
    public String showNewForm(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Add New Employee");
        return "employee_form";
    }

    @PostMapping("/employees/save")
    public String saveUser(Employee employee, RedirectAttributes ra) {
        service.save(employee);
        ra.addFlashAttribute("message", "The employee has been saved successfully.");
        return "redirect:/employees";
    }

    @GetMapping("/employees/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Employee employee = service.get(id);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Edit Employee (ID: " + id + ")");

            return "employee_form";
        } catch (EmployeeNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/employee";
        }
    }

    @GetMapping("/employees/delete/{id}")
    public String deletePosition(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The employees ID " + id + " has been deleted.");
        } catch (EmployeeNotFoundException  e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/employees";
    }
}