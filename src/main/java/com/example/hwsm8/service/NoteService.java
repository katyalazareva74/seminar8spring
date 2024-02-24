package com.example.hwsm8.service;

import com.example.hwsm8.aspects.TrackUserAction;
import com.example.hwsm8.model.Note;
import com.example.hwsm8.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository repository;

    /**
     * Метод поиска всех записей
     * @return - возвращает список записей
     */
    @TrackUserAction
    public List<Note> findAll(){
        return repository.findAll();
    }

    /**
     * Метод создания записи
     * @param note - сама запись
     * @return - возвращает созданную запись
     */

    @TrackUserAction
    public Note createNote(Note note){
        return repository.save(note);
    }
    /**
     * Метод поиска запи по id
     * @param id - номер записи
     * @return - возвращает найденную запись
     */
    public Optional<Note> findById(Long id){
        return repository.findById(id);
    }

    /**
     * Метод удаления записи по id
     * @param id - номер записи
     */
     public void deleteNote(Long id){
        repository.deleteById(id);
    }
}
