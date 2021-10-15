package com.example.focusify.application.mapper;

import com.example.focusify.application.model.response.ErrorResponse;
import com.example.focusify.domain.exception.BusinessException;
import com.example.focusify.usecase.todo.exception.TodoNotFoundException;
import com.example.focusify.usecase.user.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BusinessExceptionMapper implements ExceptionMapper<BusinessException> {

  private final Map<Class<? extends BusinessException>, Function<BusinessException, Response>>
      exceptionMapper;

  public BusinessExceptionMapper() {
    this.exceptionMapper = configureExceptionMapper();
  }

  private Map<Class<? extends BusinessException>, Function<BusinessException, Response>>
      configureExceptionMapper() {

    final var handlerMap =
        new HashMap<Class<? extends BusinessException>, Function<BusinessException, Response>>();

    handlerMap.put(TodoNotFoundException.class, this::notFound);
    handlerMap.put(UserNotFoundException.class, this::notFound);

    return handlerMap;
  }

  private Response notFound(BusinessException businessException) {
    return Response.ok(errorResponse(businessException))
        .status(Status.NOT_FOUND.getStatusCode())
        .build();
  }

  private ErrorResponse errorResponse(BusinessException businessException) {
    return new ErrorResponse(businessException.getMessages());
  }

  @Override
  public Response toResponse(BusinessException businessException) {
    return this.exceptionMapper.get(businessException.getClass()).apply(businessException);
  }
}
