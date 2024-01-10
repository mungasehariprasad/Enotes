package com.coderhari.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coderhari.entity.Notes;
import com.coderhari.entity.User;
import com.coderhari.repository.UserRepository;
import com.coderhari.service.NotesService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotesService service;

    @ModelAttribute
    public User getUser(Principal principal, Model model) {
        String email = principal.getName();
        User user = userRepository.findByEmail(email);
        model.addAttribute("user", user);
        return user;

    }

    @GetMapping("/addNotes")
    public String addNotes() {
        return "addnotes";
    }

    @GetMapping("/viewNotes")
    public String viewNotes(Model model, Principal principal) {
        User user = getUser(principal, model);
        List<Notes> notesByUser = service.getNotesByUser(user);
        model.addAttribute("notesList", notesByUser);
        return "viewnotes";
    }

    @GetMapping("/editNodes/{id}")
    public String editNodes(@PathVariable int id, Model model) {
        Notes notes = service.getNotesById(id);
        model.addAttribute("n", notes);
        return "editnotes";
    }

    @PostMapping("/saveNotes")
    public String saveNotes(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(principal, model));

        Notes saveNotes = service.saveNotes(notes);
        if (saveNotes != null) {
            session.setAttribute("msg", "Notes Save Success");

        } else {
            session.setAttribute("msg", "something wrpng on server");

        }
        return "redirect:/user/addNotes";

    }

    @PostMapping("/uptadeNotes")
    public String uptadeNotes(@ModelAttribute Notes notes, HttpSession session, Principal principal, Model model) {
        notes.setDate(LocalDate.now());
        notes.setUser(getUser(principal, model));

        Notes saveNotes = service.saveNotes(notes);
        if (saveNotes != null) {
            session.setAttribute("msg", "Notes update Success");

        } else {
            session.setAttribute("msg", "something wrpng on server");

        }
        return "redirect:/user/viewNotes";

    }

    @GetMapping("/deleteNotes/{id}")
    public String deleteNotes(@PathVariable int id, HttpSession session) {
        boolean f = service.deleteNotes(id);
        if (f) {
            session.setAttribute("msg", "Notes delete Success");

        } else {
            session.setAttribute("msg", "something wrpng on server");

        }
        return "redirect:/user/viewNotes";
    }
}
