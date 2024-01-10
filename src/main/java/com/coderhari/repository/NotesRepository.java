package com.coderhari.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhari.entity.Notes;
import com.coderhari.entity.User;

public interface NotesRepository extends JpaRepository<Notes, Integer> {
    public List<Notes> findByUser(User user);

}
