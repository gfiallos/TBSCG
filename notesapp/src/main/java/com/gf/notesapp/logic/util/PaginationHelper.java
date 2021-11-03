package com.gf.notesapp.logic.util;

import com.gf.notesapp.logic.exception.DataValidationException;
import com.gf.notesapp.logic.exception.GenericException;
import com.gf.notesapp.model.domain.OrderType;
import com.gf.notesapp.model.dto.inquiry.Criteria;
import com.gf.notesapp.model.dto.inquiry.OrderRequest;
import com.gf.notesapp.model.dto.inquiry.PageData;
import com.gf.notesapp.model.dto.inquiry.Pagination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@Scope("prototype")
public class PaginationHelper {
  private static final Logger LOG = LogManager.getLogger(PaginationHelper.class);
  private ConversionService conversionService;

  private <T> T convert(Object pValue, Class<T> pType) {
    return this.conversionService.convert(pValue, pType);
  }


  private PageRequest getManageOrder(Pagination pPagination, List<OrderRequest> pOrder) {
    if (pOrder.isEmpty()) {
      return PageRequest.of(pPagination.getPageNumber(), pPagination.getPageSize());
    }
    return PageRequest.of(pPagination.getPageNumber(),
        pPagination.getPageSize(),
        this.prepareSort(pOrder));
  }

  public Object getProperty(Object pBean, String pName) {
    try {
      var method = Optional.ofNullable(BeanUtils.getPropertyDescriptor(pBean.getClass(), pName))
          .orElseThrow(() -> new DataValidationException("FIELD NOT FOUND", "FIELD", pName))
          .getReadMethod();
      return method.invoke(pBean);
    } catch (DataValidationException e) {
      PaginationHelper.LOG.info("Property not found:{}", () -> pName);
      return null;
    } catch (Exception e) {
      throw new GenericException(e);
    }
  }

  private <T> ExampleMatcher manageExampleMatcher(T pBean, ExampleMatcher pMatcher, Criteria pCriteria) {
    this.setProperty(pBean, pCriteria.getName(), pCriteria.getValue());
    return pMatcher.withIgnoreCase()
        .withMatcher(pCriteria.getName(), this.prepareMatcher(pCriteria));

  }

  public <T> Example<T> prepareExample(List<Criteria> pCriteria, T pBean) {
    var matcher = new AtomicReference<>(ExampleMatcher.matching()
        .withIgnoreNullValues());
    if (pCriteria != null && !pCriteria.isEmpty()) {
      pCriteria.forEach(c -> matcher.set(this.manageExampleMatcher(pBean, matcher.get(), c)));
    }
    return Example.of(pBean, matcher.get());
  }

  private ExampleMatcher.GenericPropertyMatcher prepareMatcher(Criteria pCriteria) {
    switch (pCriteria.getOperator()) {
      case equals:
        return ExampleMatcher.GenericPropertyMatchers.exact();
      case greaterThan:
      case greaterThanOrEqualTo:
        return ExampleMatcher.GenericPropertyMatchers.startsWith();
      case like:
      case lessThan:
      case lessThanOrEqualTo:
      default:
        return ExampleMatcher.GenericPropertyMatchers.contains();
    }
  }

  public <T, R> PageData<R> preparePageData(Pagination pPagination, Page<T> pPage, Function<T, R> pMap) {
    return new PageData<R>().setHasMoreData(pPage.getTotalPages() > (pPagination.getPageNumber() + 1))
        .setTotalCount(pPage.getTotalElements())
        .setTotalPages(pPage.getTotalPages())
        .setContent(pPage.getContent()
            .stream()
            .map(pMap)
            .collect(Collectors.toList()));
  }

  public <T> PageData<T> preparePageData(Pagination pPagination, List<T> pPage) {
    var hasMoreData = false;
    var data = pPage;
    if (pPage.size() > pPagination.getPageSize()) {
      data = pPage.stream()
          .limit(pPagination.getPageSize())
          .collect(Collectors.toList());
      hasMoreData = true;
    }
    return new PageData<T>().setHasMoreData(hasMoreData)
        .setTotalCount(((long) (pPagination.getPageNumber() + 1) * pPagination.getPageSize()) + (hasMoreData ? 1L : 0L))
        .setTotalPages(pPagination.getPageNumber() + 1 + (hasMoreData ? 1 : 0))
        .setContent(data);
  }

  public PageRequest preparePageRequest(Pagination pPagination) {
    return PageRequest.of(pPagination.getPageNumber(), pPagination.getPageSize());
  }

  public PageRequest preparePageRequest(Pagination pPagination, List<OrderRequest> pOrder) {
    var pageRequest = new AtomicReference<>(PageRequest.of(pPagination.getPageNumber(), pPagination.getPageSize()));
    Optional.ofNullable(pOrder)
        .ifPresent(lo -> pageRequest.set(this.getManageOrder(pPagination, pOrder)));
    return pageRequest.get();
  }

  private Sort prepareShortElement(Sort pActual, OrderRequest pRequest) {
    var sort = Sort.by(pRequest.getName());
    if (pRequest.getType() == OrderType.DESC) {
      sort = sort.descending();
    } else {
      sort = sort.ascending();
    }
    var act = sort;
    return Optional.ofNullable(pActual)
        .map(s -> s.and(act))
        .orElse(act);
  }

  private Sort prepareSort(List<OrderRequest> pOrder) {
    var sort = new AtomicReference<Sort>();
    pOrder.forEach(s -> sort.set(this.prepareShortElement(sort.get(), s)));
    return sort.get();
  }

  @Autowired
  public void setConversionService(ConversionService pConversionService) {
    this.conversionService = pConversionService;
  }


  public <T> void setProperty(T pBean, String pName, Object pValue) {
    try {
      var method = Optional.ofNullable(BeanUtils.getPropertyDescriptor(pBean.getClass(), pName))
          .orElseThrow(() -> new DataValidationException("FIELD NOT FOUND", "FIELD", pName))
          .getWriteMethod();
      method.invoke(pBean, this.convert(pValue, method.getParameterTypes()[0]));
    } catch (DataValidationException e) {
      PaginationHelper.LOG.info("Property not found:{}", () -> pName);
    } catch (Exception e) {
      throw new GenericException(e);
    }
  }
}
