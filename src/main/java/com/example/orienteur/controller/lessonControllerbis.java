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
    public String createLesson(@ModelAttribute Lesson lesson, Model model) {
        model.addAttribute("lesson", lesson);
        lessonRepository.save(lesson);
        return "redirect:/lessons/showById?id="+lesson.getId();
    }

    @GetMapping("/sortAll")
    public String sortAllLessons(Model model) {
        model.addAttribute("lessonsSorted", lessonRepository.findAllByOrderByNoteAsc());
        return "AllLessonsSorted";
    }

}
