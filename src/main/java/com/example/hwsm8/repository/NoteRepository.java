package com.example.hwsm8.repository;


import com.example.hwsm8.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    /**
     * Метод поиска записи по id
     * @param id - номер записи
     * @return возвращает найденную запись
     */
    Optional<Note> findById(Long id);
}
