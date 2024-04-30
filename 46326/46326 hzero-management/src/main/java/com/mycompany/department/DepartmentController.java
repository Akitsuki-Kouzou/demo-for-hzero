package com.mycompany.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired private DepartmentService service;

    @GetMapping("/departments")
    public String showDepartmentList(Model model) {
        List<Department> listDepartments = service.listAll();
        model.addAttribute("listDepartments", listDepartments);

        return "departments";
    }

    @GetMapping("/departments/new")
    public String showNewForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Add New Department");
        return "department_form";
    }

    @PostMapping("/departments/save")
    public String saveUser(Department department, RedirectAttributes ra) {
        service.save(department);
        ra.addFlashAttribute("message", "The department has been saved successfully.");
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Department user = service.get(id);
            model.addAttribute("department", user);
            model.addAttribute("pageTitle", "Edit Department (ID: " + id + ")");

            return "department_form";
        } catch (DepartmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/departments";
        }
    }


    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The departments ID " + id + " has been deleted.");
        } catch (DepartmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/departments";
    }


}
