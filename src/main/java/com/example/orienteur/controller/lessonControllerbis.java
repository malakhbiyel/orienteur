package com.example.orienteur.controller;

import com.example.orienteur.model.Lesson;
import com.example.orienteur.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class lessonControllerbis {

    @Autowired
    private LessonRepository lessonRepository;

    @GetMapping("/showById")
    public String showById(@RequestParam Long id, Model model) {
        Lesson lessonFound = lessonRepository.findById(id).orElse(null);
        model.addAttribute("lessonFound", lessonFound);
        return "lesson";
    }

    @GetMapping("/showAll")
    public String showAll(Model model) {
        Iterable<Lesson> lessons = lessonRepository.findAll();
        model.addAttribute("lessons", lessons);
        return "lessons";
    }

    @GetMapping("/lessonForm")
    public String lessonForm(Model model){
        model.addAttribute("formContent", new Lesson());
        return "createLesson";
    }
    
}
