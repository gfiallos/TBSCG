package com.gf.notesapp.controller;

import com.gf.notesapp.logic.services.AbstractCRUDService;
import com.gf.notesapp.model.dto.inquiry.ListRequest;
import com.gf.notesapp.model.dto.inquiry.PageData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCRUDRestController<E, I, D, R, S extends AbstractCRUDService<E, I, D, R, ?>> {
  protected S dataService;

  @PostMapping("/")
  @Operation(summary = "Create a new entity")
  public D create(@RequestBody R pRequest) {
    return this.dataService.create(pRequest);
  }

  @DeleteMapping("/")
  @Operation(summary = "Delete an entity")
  public void delete(@RequestBody D pRequest) {
    this.dataService.delete(pRequest);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get an entity by id")
  @Parameter(name = "id", description = "The id of the entity to retrieve", required = true)
  public D get(@PathVariable("id") I pIdentifier) {
    return this.dataService.get(pIdentifier);
  }

  @PostMapping("/list")
  @Operation(summary = "Get a list of entities")
  public PageData<D> list(@RequestBody ListRequest pRequest) {
    return this.dataService.list(pRequest);
  }

  @PutMapping("/")
  @Operation(summary = "Update an entity")
  public D modify(@RequestBody D pRequest) {
    return this.dataService.modify(pRequest);
  }

  @Autowired
  public void setDataService(S pDataService) {
    this.dataService = pDataService;
  }
}
