package com.example.orienteur.repository;

import com.example.orienteur.model.Lesson;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LessonRepository extends CrudRepository<Lesson, Long> {
    @Override
    Optional<Lesson> findById(Long id);

    @Override
    <S extends Lesson> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    Iterable<Lesson> findAll();
}
