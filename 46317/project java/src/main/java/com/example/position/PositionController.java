package com.example.position;

import com.example.position.Position;
import com.example.position.PositionNotFoundException;
import com.example.employee.Employee;
import com.example.employee.EmployeeNotFoundException;
import com.example.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PositionController {
    @Autowired private PositionService service;

    @GetMapping("/position")
    public String showPositionList(Model model) {
        List<Position> listPosition = service.listAll();
        model.addAttribute("listposition", listPosition);

        return "position";
    }

    @GetMapping("/position/new")
    public String showNewForm(Model model) {
        model.addAttribute("position", new Position());
        model.addAttribute("pageTitle", "Add New Position");
        return "position_form";
    }

    @PostMapping("/position/save")
    public String saveUser(Position position, RedirectAttributes ra) {
        service.save(position);
        ra.addFlashAttribute("message", "The Position has been saved successfully.");
        return "redirect:/position";
    }

    @GetMapping("/position/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Position position = service.get(id);
            model.addAttribute("position", position);
            model.addAttribute("pageTitle", "Edit Position (ID: " + id + ")");

            return "position_form";
        } catch (PositionNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/position";
        }
    }

    @GetMapping("/position/delete/{id}")
    public String deletePosition(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The position ID " + id + " has been deleted.");
        } catch (PositionNotFoundException  e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/position";
    }
}