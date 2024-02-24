package com.example.hwsm8.controller;

import com.example.hwsm8.aspects.TrackUserAction;
import com.example.hwsm8.exception.ResourceNotFoundException;
import com.example.hwsm8.model.Note;
import com.example.hwsm8.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/notes")
public class NoteController {
    private final NoteService service;

    /**
     * Запрос возвращает все записи
     * @return - возвращает список записей
     */
    @GetMapping
    public List<Note> findAllNotes(){
        return  service.findAll();
    }

    /**
     * Запрос создает запись
     * @param note - сама запись
     * @return - возращает сделанную запись
     */
    //@TrackUserAction
    @PostMapping
    public Note addNote(@RequestBody Note note){
        return service.createNote(note);
    }

    /**
     * Запрос на поиск записи по id
     * @param id - номер записи
     * @return - возвращает найденную запись по номеру, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @TrackUserAction
    @GetMapping("{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
           return ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
    }

    /**
     * Запрос на редактирование записи по номеру (id)
     * @param id - номер записи
     * @param note - новая запись
     * @return - возвращает отредактированную запись, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает иод ошибку 404 (ресурс не найден) на стороне клиента
     */
    @TrackUserAction
    @PutMapping("{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note note) throws ResourceNotFoundException {
        note.setId(id);
        ResponseEntity<Note> note1 = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.createNote(note);
        return ResponseEntity.ok(note);
    }

    /**
     * Запрос на удаление записи по id
     * @param id - номер записи
     * @return - если запись найдена, то возвращается код 200, а если запись не найдена, то
     * выбрасывается исключение ResourceNotFoundException
     * @throws ResourceNotFoundException - это исключение выдает код ошибки 404 (ресурс не найден) на стороне клиента
     */
    @TrackUserAction
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        ResponseEntity<Note> note = ResponseEntity.ok(service.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note not found with id " + id)));
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }
}
