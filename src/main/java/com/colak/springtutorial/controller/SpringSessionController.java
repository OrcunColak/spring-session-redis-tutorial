package com.colak.springtutorial.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SpringSessionController {

    private static final String ATTRIBUTE_NAME = "MY_SESSION_MESSAGES";

    // http://localhost:8080/
    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        @SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute(ATTRIBUTE_NAME);

        if (messages == null) {
            messages = new ArrayList<>();
        }
        model.addAttribute("sessionMessages", messages);
        model.addAttribute("sessionId", session.getId());

        return "index";
    }

    // http://localhost:8080/persistMessage
    @PostMapping("/persistMessage")
    public String persistMessage(@RequestParam("msg") String msg, HttpServletRequest request) {
        @SuppressWarnings("unchecked")
        List<String> msgs = (List<String>) request.getSession().getAttribute(ATTRIBUTE_NAME);
        if (msgs == null) {
            msgs = new ArrayList<>();
            request.getSession().setAttribute(ATTRIBUTE_NAME, msgs);
        }
        msgs.add(msg);
        request.getSession().setAttribute("MY_SESSION_MESSAGES", msgs);
        return "redirect:/";
    }

    // http://localhost:8080/destroy
    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }
}
