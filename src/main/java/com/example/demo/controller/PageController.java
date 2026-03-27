package com.example.demo.controller;

import java.util.*;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Feedback;
import com.example.demo.repository.FeedbackRepository;
import com.example.demo.repository.AcademicRepository;

@Controller
public class PageController {

    @Autowired
    private FeedbackRepository feedbackRepo;

    @Autowired
    private AcademicRepository academicRepo;

    // ================= HOME =================
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("semesters", academicRepo.findSemesters());
        model.addAttribute("branches", academicRepo.findBranches());
        model.addAttribute("sections", academicRepo.findSections());

        return "home";
    }

    // ================= START =================
    @PostMapping("/start")
    public String start(@RequestParam String semester,
                        @RequestParam String branch,
                        @RequestParam String section,
                        HttpSession session,
                        Model model) {

        String token = UUID.randomUUID().toString();
        session.setAttribute("token", token);

        model.addAttribute("token", token);
        model.addAttribute("step", 1);
        model.addAttribute("semester", semester);
        model.addAttribute("branch", branch);
        model.addAttribute("section", section);

        return "feedback";
    }

    // ================= NEXT (MULTI STEP) =================
    @PostMapping("/next")
    public String next(@RequestParam int step,
                       @RequestParam String semester,
                       @RequestParam String branch,
                       @RequestParam String section,
                       @RequestParam(required = false) Integer teaching,
                       @RequestParam(required = false) Integer knowledge,
                       HttpSession session,
                       Model model) {

        List<Feedback> list = (List<Feedback>) session.getAttribute("data");
        if (list == null) list = new ArrayList<>();

        Feedback f = new Feedback();
        f.setSemester(semester);
        f.setBranch(branch);
        f.setSection(section);
        f.setTeaching(teaching != null ? teaching : 0);
        f.setKnowledge(knowledge != null ? knowledge : 0);

        String teacher =
                step == 1 ? "Kumar" :
                step == 2 ? "Rao" :
                step == 3 ? "Patel" : "Sharma";

        f.setTeacher(teacher);

        list.add(f);
        session.setAttribute("data", list);

        int nextStep = step + 1;

        String token = (String) session.getAttribute("token");

        model.addAttribute("token", token);
        model.addAttribute("step", nextStep);
        model.addAttribute("semester", semester);
        model.addAttribute("branch", branch);
        model.addAttribute("section", section);

        if (nextStep > 4) return "review";

        return "feedback";
    }

    // ================= SUBMIT =================
    @PostMapping("/submit")
    public String submit(HttpSession session) {

        String token = (String) session.getAttribute("token");

        boolean exists = feedbackRepo.existsByToken(token);

        if (exists) {
            return "redirect:/";
        }

        List<Feedback> list = (List<Feedback>) session.getAttribute("data");

        if (list != null) {
            for (Feedback f : list) {
                f.setToken(token);
                feedbackRepo.save(f);
            }
        }

        session.removeAttribute("data");
        session.removeAttribute("token");

        return "success";
    }
}