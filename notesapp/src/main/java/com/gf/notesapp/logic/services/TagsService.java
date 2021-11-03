package com.gf.notesapp.logic.services;

import com.gf.notesapp.model.dto.TagData;
import com.gf.notesapp.model.entities.Tag;
import com.gf.notesapp.model.repository.TagsRepository;
import org.springframework.stereotype.Service;

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

  @Override
  protected void mapDataEntity(TagData pRequest, Tag pEntity) {
    pEntity.setDescription(pRequest.getDescription());
  }

  @Override
  protected void prePersist(Tag pData) {
  }
}
