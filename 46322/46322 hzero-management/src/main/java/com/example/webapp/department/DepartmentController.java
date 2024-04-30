package com.example.webapp.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired private DepartmentService service;

    @GetMapping("/department")
    public String showUserList(Model model){
        List<Department> listDepartment = service.listAll();
        model.addAttribute("listDepartment", listDepartment);

        return "department";
    }

    @GetMapping("/department/new")
    public String showNewForm(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Add New Department");
        return "departmentForm";
    }

    @PostMapping("/department/save")
    public String saveDepartment(Department department, RedirectAttributes ra){
        service.save(department);
        ra.addFlashAttribute("message", "The department has been saved successfully.");
        return "redirect:/department";
    }

    @GetMapping("/department/edit/{departmentId}")
    public String showEditForm(@PathVariable("departmentId") Integer departmentId, Model model, RedirectAttributes ra){
        try {
            Department department = service.get(departmentId);
            model.addAttribute("department", department);
            model.addAttribute("pageTitle", "Edit Department (ID: " + departmentId + ")" );
            return "departmentForm";
        } catch (DepartmentNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/department";
        }
    }

    @GetMapping("/department/delete/{departmentId}")
    public String deleteDepartment(@PathVariable("departmentId") Integer departmentId, RedirectAttributes ra){
        try {
            service.delete(departmentId);
            ra.addFlashAttribute("message", "The department ID " + departmentId + " has been deleted.");
        } catch (DepartmentNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/department";
    }
}
