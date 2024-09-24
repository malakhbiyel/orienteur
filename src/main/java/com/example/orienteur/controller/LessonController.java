package com.example.orienteur.controller;

import com.example.orienteur.model.Lesson;
import com.example.orienteur.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/lessons")
public class LessonController {

        @Autowired
        private LessonRepository lessonRepository;

        @GetMapping("/{id}")
        public Lesson findOne(@PathVariable Long id) {
            return lessonRepository.findById(id).orElse(null);
        }

        @PostMapping("/creation")
        @ResponseStatus(HttpStatus.CREATED)
        public Lesson createFromRequestParam(@RequestParam(name = "id") int id,
                                             @RequestParam(name = "title") String title,
                                             @RequestParam(name = "note") int note) {
            return lessonRepository.save(new Lesson(id, title,note));
        }

        @PostMapping("/creationFromJson")
        @ResponseStatus(HttpStatus.CREATED)
        public Lesson create(@RequestBody Lesson lesson) {
                return lessonRepository.save(lesson);
        }




}
