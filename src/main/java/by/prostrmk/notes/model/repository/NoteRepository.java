package by.prostrmk.notes.model.repository;

import by.prostrmk.notes.model.entity.Note;
import by.prostrmk.notes.model.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NoteRepository extends MongoRepository<Note,String> {

    List<Note> findNotesByUser(User user);
    Note findNoteById(String id);

}
