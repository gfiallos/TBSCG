package com.gf.notesapp.controller;

import com.gf.notesapp.logic.services.AbstractCRUDService;
import com.gf.notesapp.model.dto.inquiry.ListRequest;
import com.gf.notesapp.model.dto.inquiry.PageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public abstract class AbstractCRUDRestController<E, I, D, R, S extends AbstractCRUDService<E, I, D, R, ?>> {
  protected S dataService;

  @PostMapping("/")
  public D create(@RequestBody R pRequest) {
    return this.dataService.create(pRequest);
  }

  @DeleteMapping("/")
  public void delete(@RequestBody D pRequest) {
    this.dataService.delete(pRequest);
  }

  public D get(@RequestBody I pIdentifier) {
    return this.dataService.get(pIdentifier);
  }

  @PostMapping("/list")
  public PageData<D> list(@RequestBody ListRequest pRequest) {
    return this.dataService.list(pRequest);
  }

  @PutMapping("/")
  public D modify(@RequestBody D pRequest) {
    return this.dataService.modify(pRequest);
  }

  @Autowired
  public void setDataService(S pDataService) {
    this.dataService = pDataService;
  }
}
