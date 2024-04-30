package com.example.demo.department;

import com.example.demo.user.UserNoFoundException;
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

    @GetMapping("/departments")
    public String showDepartmentList(Model model){
        List<Department> listDepartment = service.listAll();
        model.addAttribute("listDepartment", listDepartment);
        return "departments";
    }

    @GetMapping("/departments/new")
    public String showNewForm(Model model){
        model.addAttribute("department", new Department());
        model.addAttribute("pageTitle", "Add new Department");
        return "department_form";
    }

    @PostMapping("departments/save")
    public String saveDepartments(Department department, RedirectAttributes ra){
        service.save(department);
        ra.addFlashAttribute("message", "The Department has been added successfully.");
        return "redirect:/departments";
    }

    @GetMapping("/departments/edit/{department_id}")
    public String showEditForm(@PathVariable("department_id") Integer departmentId, Model model, RedirectAttributes ra){
        try {
            Department department = service.get(departmentId);
            model.addAttribute("department", department);
            model.addAttribute("pageTitle", "Edit Department (Department ID: " + departmentId +")");
            return "department_form";
        } catch (DepartmentNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/departments";
        }
    }

    @GetMapping("/departments/delete/{department_id}")
    public String deleteDepartment(@PathVariable("department_id") Integer departmentId, RedirectAttributes ra){
        try {
            service.delete(departmentId);
            ra.addFlashAttribute("message", "The Department Code ID " + departmentId + " has been deleted.");
        } catch (DepartmentNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/departments";
    }
}
