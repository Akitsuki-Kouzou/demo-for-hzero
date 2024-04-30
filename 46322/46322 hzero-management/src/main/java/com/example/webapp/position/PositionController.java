package com.example.webapp.position;

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
    public String showUserList(Model model){
        List<Position> listPosition = service.listAll();
        model.addAttribute("listPosition", listPosition);

        return "position";
    }

    @GetMapping("/position/new")
    public String showNewForm(Model model){
        model.addAttribute("position", new Position());
        model.addAttribute("pageTitle", "Add New Position");
        return "positionForm";
    }

    @PostMapping("/position/save")
    public String savePosition(Position position, RedirectAttributes ra){
        service.save(position);
        ra.addFlashAttribute("message", "The position has been saved successfully.");
        return "redirect:/position";
    }

    @GetMapping("/position/edit/{positionId}")
    public String showEditForm(@PathVariable("positionId") Integer positionId, Model model, RedirectAttributes ra){
        try {
            Position position = service.get(positionId);
            model.addAttribute("position", position);
            model.addAttribute("pageTitle", "Edit Position (ID: " + positionId + ")" );
            return "positionForm";
        } catch (PositionNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/position";
        }
    }

    @GetMapping("/position/delete/{positionId}")
    public String deletePosition(@PathVariable("positionId") Integer positionId, RedirectAttributes ra){
        try {
            service.delete(positionId);
            ra.addFlashAttribute("message", "The position ID " + positionId + " has been deleted.");
        } catch (PositionNotFoundException e){
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/position";
    }
}
