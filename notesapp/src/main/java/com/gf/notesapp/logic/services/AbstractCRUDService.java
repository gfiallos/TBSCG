package com.gf.notesapp.logic.services;

import com.gf.notesapp.logic.exception.DataAlreadyAccepedException;
import com.gf.notesapp.logic.exception.DataValidationException;
import com.gf.notesapp.logic.exception.GenericException;
import com.gf.notesapp.logic.exception.NODataFoundException;
import com.gf.notesapp.logic.util.PaginationHelper;
import com.gf.notesapp.model.dto.inquiry.ListRequest;
import com.gf.notesapp.model.dto.inquiry.PageData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.CastUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Validated
@Transactional
public abstract class AbstractCRUDService<E, I, D, R, Q extends JpaRepository<E, I>> {
  private static final Logger LOG = LogManager.getLogger(AbstractCRUDService.class);
  protected Q repository;
  protected PaginationHelper paginationHelper;

  public D create(@Valid R pRequest) {
    this.validateExist(this.findReference(this.getIdFromRequest(pRequest)));
    var entity = this.mapRequest(pRequest);
    return this.mapData(this.save(entity));
  }

  public void delete(@Valid D pRequest) {
    this.repository.deleteById(this.getIdFromData(pRequest));
  }

  protected Optional<E> findReference(I pId) {
    if (null == pId) {
      return Optional.empty();
    }
    return this.repository.findById(pId);
  }

  public D get(@Valid I pIdentifier) {
    return this.mapData(this.findReference(pIdentifier)
        .orElseThrow(() -> new NODataFoundException(this.getClassType()
            .getSimpleName())));
  }

  private Class<E> getClassType() {
    try {
      var parameterizedType = (ParameterizedType) this.getClass()
          .getGenericSuperclass();
      return CastUtils.cast(Arrays.stream(parameterizedType.getActualTypeArguments())
          .findFirst()
          .orElseThrow());
    } catch (Exception e) {
      AbstractCRUDService.LOG.warn(this.getClass(), e);
      throw e;
    }
  }

  private Class<D> getDataClassType() {
    try {
      var parameterizedType = (ParameterizedType) this.getClass()
          .getGenericSuperclass();
      return CastUtils.cast(Arrays.stream(parameterizedType.getActualTypeArguments())
          .skip(2L)
          .findFirst()
          .orElseThrow());
    } catch (Exception e) {
      AbstractCRUDService.LOG.warn(this.getClass(), e);
      throw e;
    }
  }

  protected abstract I getIdFromData(D pRequest);

  protected abstract I getIdFromRequest(R pRequest);

  public PageData<D> list(ListRequest pRequest) {
    return this.paginationHelper.preparePageData(pRequest.getPagination(),
        this.preparePage(pRequest),
        this::mapData);
  }

  protected D mapData(E pRecord) {
    var data = this.prepareEmptyData();
    Arrays.stream(pRecord.getClass()
            .getDeclaredFields())
        .forEach(f -> this.setProperty(pRecord, data, f));
    return data;
  }

  protected abstract void mapDataEntity(D pRequest, E pEntity);

  protected E mapRequest(R pRequest) {
    var bean = this.prepareEmptyEntity();
    Arrays.stream(pRequest.getClass()
            .getDeclaredFields())
        .forEach(f -> this.setProperty(pRequest, bean, f));
    return bean;
  }

  public D modify(@Valid D pRequest) {
    var entity = this.findReference(this.getIdFromData(pRequest))
        .orElseThrow(() -> new NODataFoundException(this.getClassType()
            .getSimpleName()));
    this.mapDataEntity(pRequest, entity);
    return this.mapData(this.save(entity));
  }

  protected void prePersist(E pData) {
    // Empty
  }

  private D prepareEmptyData() {
    try {
      return this.getDataClassType()
          .getConstructor()
          .newInstance();
    } catch (Exception e) {
      AbstractCRUDService.LOG.warn(e, e);
      throw new DataValidationException(e.getMessage(), e);
    }
  }

  protected E prepareEmptyEntity() {
    try {
      return this.getClassType()
          .getConstructor()
          .newInstance();
    } catch (Exception e) {
      AbstractCRUDService.LOG.warn(e, e);
      throw new DataValidationException(e.getMessage(), e);
    }
  }

  protected Page<E> preparePage(ListRequest pRequest) {
    var bean = this.prepareEmptyEntity();
    return this.repository.findAll(this.paginationHelper.prepareExample(pRequest.getCriteria(), bean),
        this.paginationHelper.preparePageRequest(pRequest.getPagination(), pRequest.getOrder()));
  }

  protected E save(E pData) {
    this.prePersist(pData);
    return this.repository.save(pData);
  }

  @Autowired
  public void setPaginationHelper(PaginationHelper pPaginationHelper) {
    this.paginationHelper = pPaginationHelper;
  }

  private void setProperty(Object pOrigin, Object pTarget, Field pField) {
    try {
      this.paginationHelper.setProperty(pTarget,
          pField.getName(),
          this.paginationHelper.getProperty(pOrigin, pField.getName()));
    } catch (DataValidationException e) {
      AbstractCRUDService.LOG.info("Property not found:{}", pField::getName);
    } catch (Exception e) {
      throw new GenericException(e);
    }
  }

  @Autowired
  public void setRepository(Q pRepository) {
    this.repository = pRepository;
  }

  protected void validateExist(Optional<E> pReference) {
    pReference
        .ifPresent(r -> {
          throw new DataAlreadyAccepedException(this.getClassType()
              .getSimpleName()
              .toUpperCase(Locale.ROOT));
        });
  }

  protected E validateNotExists(Optional<E> pReference, Map<String, Serializable> pRef) {
    return pReference
        .orElseThrow(() -> new NODataFoundException(this.getClassType()
            .getSimpleName()
            .toUpperCase(Locale.ROOT), pRef));
  }
}
