package com.example.demo.position;


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

    @GetMapping("/positions")
    public String showPositionList(Model model){
        List<Position> listPosition = service.listAll();
        model.addAttribute("listPositions", listPosition);
        return "positions";
    }

    @GetMapping("/positions/new")
    public String showNewForm(Model model){
        model.addAttribute("position", new Position());
        model.addAttribute("pageTitle", "Add new Position");
        return "position_form";
    }

    @PostMapping("positions/save")
    public String savePositions(Position position, RedirectAttributes ra){
        service.save(position);
        ra.addFlashAttribute("message", "The Positions has been added successfully.");
        return "redirect:/positions";
    }

    @GetMapping("/positions/edit/{position_id}")
    public String showEditForm(@PathVariable("position_id") Integer positionId, Model model, RedirectAttributes ra){
        try {
            Position position = service.get(positionId);
            model.addAttribute("position", position);
            model.addAttribute("pageTitle", "Edit Position (Position ID: " + positionId +")");
            return "position_form";
        } catch (PositionNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/positions";
        }
    }

    @GetMapping("/positions/delete/{position_id}")
    public String deletePosition(@PathVariable("position_id") Integer positionId, RedirectAttributes ra){
        try {
            service.delete(positionId);
            ra.addFlashAttribute("message", "The Position Code ID " + positionId + " has been deleted.");
        } catch (PositionNoFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/positions";
    }

}
