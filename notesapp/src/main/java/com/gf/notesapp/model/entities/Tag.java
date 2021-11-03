package com.gf.notesapp.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;


@Data
@Entity(name = "ttags")
public class Tag {
  @Column(length = 200)
  private String description;
  @Id
  @Column(length = 60)
  private String name;
  @ManyToMany(mappedBy = "tags")
  private List<Note> notes;
}

