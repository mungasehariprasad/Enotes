package com.coderhari.service;

import com.coderhari.entity.Notes;
import com.coderhari.entity.User;

public interface NotesService {
    public Notes saveNotes(Notes notes);

    public Notes getNotesById(int id);

    public java.util.List<Notes> getNotesByUser(User user);

    public Notes updateNotes(Notes notes);

    public boolean deleteNotes(int id);

}
