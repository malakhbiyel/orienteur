package com.example.orienteur.controller;

import com.example.orienteur.model.Lesson;
import com.example.orienteur.repository.LessonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lessons")
public class lessonControllerbis {

    private final LessonRepository lessonRepository;

    public lessonControllerbis(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @GetMapping("/showById")
    public String showById(@RequestParam Long id, Model model) {
        Lesson lessonFound = lessonRepository.findById(id).orElse(null);
        model.addAttribute("lessonFound", lessonFound);
        return "lesson";
    }

    @GetMapping("/showAll")
    public String showAll(Model model) {
        model.addAttribute("lessons", lessonRepository.findAll());
        return "AllLessons";
    }

    @GetMapping("/lessonForm")
    public String lessonForm(Model model){
        model.addAttribute("formContent", new Lesson());
        return "createLesson";
    }

    @PostMapping("/createLesson")
    public String createLesson(@ModelAttribute Lesson lesson, Model model){
        if (lesson.getTitle() == null || lesson.getTitle().isEmpty()) {
            model.addAttribute("message", "Le titre est obligatoire.");
            return "createLesson";
        }

        if (lesson.getNote() < 0) {
            model.addAttribute("message", "La note doit être positive.");
            return "createLesson";
        }
        lessonRepository.save(lesson);
        model.addAttribute("message", "Leçon créée avec succès !");
        return "redirect:/lessons/AllLessons";
    }

}
