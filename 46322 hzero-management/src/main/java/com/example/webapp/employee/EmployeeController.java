package com.example.webapp.employee;

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

    @GetMapping("/employee")
    public String showUserList(Model model){
        List<Employee> listEmployee = service.listAll();
        model.addAttribute("listEmployee", listEmployee);

        return "employee";
    }

    @GetMapping("/employee/new")
    public String showNewForm(Model model){
        model.addAttribute("employee", new Employee());
        model.addAttribute("pageTitle", "Add New Employee");
        return "employeeForm";
    }

    @PostMapping("employee/save")
    public String saveEmployee(Employee employee, RedirectAttributes ra){
        service.save(employee);
        ra.addFlashAttribute("message", "The employee has been saved successfully.");
        return "redirect:/employee";
    }

    @GetMapping("/employee/edit/{employeeId}")
    public String showEditForm(@PathVariable("employeeId") Integer employeeId, Model model, RedirectAttributes ra){
        try {
            Employee employee = service.get(employeeId);
            model.addAttribute("employee", employee);
            model.addAttribute("pageTitle", "Edit Employee (ID: " + employeeId + ")" );
            return "employeeForm";
        } catch (EmployeeNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/employee";
        }
    }

    @GetMapping("/employee/delete/{employeeId}")
    public String deleteEmployee(@PathVariable("employeeId") Integer employeeId, RedirectAttributes ra){
        try {
            service.delete(employeeId);
            ra.addFlashAttribute("message", "The employee ID " + employeeId + " has been deleted.");
        } catch (EmployeeNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/employee";
    }
}
