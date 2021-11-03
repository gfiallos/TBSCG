package com.gf.notesapp.logic.services;

import com.gf.notesapp.model.dto.TagData;
import com.gf.notesapp.model.entities.Tag;
import com.gf.notesapp.model.repository.TagsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TagsService extends AbstractCRUDService<Tag, String, TagData, TagData, TagsRepository> {


  @Override
  protected String getIdFromData(TagData pRequest) {
    return pRequest.getName();
  }

  @Override
  protected String getIdFromRequest(TagData pRequest) {
    return pRequest.getName();
  }

  public List<Tag> getTags(List<String> pTags) {
    var data = new ArrayList<Tag>();
    Optional.ofNullable(pTags)
        .ifPresent(t -> t.forEach(tag -> data.add(this.validateNotExists(this.findReference(tag),
            Map.of("TAG", tag)))));
    return data;
  }

  @Override
  protected void mapDataEntity(TagData pRequest, Tag pEntity) {
    pEntity.setDescription(pRequest.getDescription());
  }

  @Override
  protected void prePersist(Tag pData, boolean pDelete) {
    if (pDelete) {
      this.repository.deleteTagReferences(pData.getName());
    }
  }
}
