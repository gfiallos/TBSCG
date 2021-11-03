package com.gf.notesapp.model.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity(name = "tnotes")
public class Note {
  @Lob
  private String content;
  private Timestamp date;
  @Id
  private Integer id;
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "tnotetags", joinColumns = @JoinColumn(name = "noteid"), inverseJoinColumns = @JoinColumn(name = "tagid"))
  private List<Tag> tags;
  @Column(length = 200)
  private String title;
}
