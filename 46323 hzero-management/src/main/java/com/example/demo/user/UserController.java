package com.example.demo.user;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Add New Employee");
        return "user_form";
    }

    @PostMapping("users/save")
    public String saveUser(User user, RedirectAttributes ra){
        service.save(user);
        ra.addFlashAttribute("message", "The Employee has been added successfully.");
        return "redirect:/users";
    }

    @GetMapping("/users/new/edit/{employee_code}")
    public String showEditForm(@PathVariable("employee_code") Integer employee_code, Model model, RedirectAttributes ra){
        try {
            User user = service.get(employee_code);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit Employee (Employee Code: " + employee_code +")");
            return "user_form";
        } catch (UserNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/users";
        }
    }

    @GetMapping("/users/delete/{employee_code}")
    public String deleteUser(@PathVariable("employee_code") Integer employee_code, RedirectAttributes ra){
        try {
            service.delete(employee_code);
            ra.addFlashAttribute("message", "The Employee Code (Employee Code " + employee_code + " has been deleted.");
        } catch (UserNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/users";
    }


}
