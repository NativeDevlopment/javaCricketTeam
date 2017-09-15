package com.cricteam.controllers.filter;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.cricteam.models.Response;

@ControllerAdvice
@Order(10)
public class ResponseBodyHandler implements ResponseBodyAdvice<Object>{
	private final Logger log = LoggerFactory.getLogger(ResponseBodyHandler.class);
	@Autowired(required = false)
	HttpServletRequest req;

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
		String message = (String) req.getAttribute("message");
		String path = req.getRequestURI();
		log.info("responseBody ResponseBodyHandler " + body);
		return body instanceof Response|| body instanceof LinkedHashMap ? body : new Response(body, message, path);
	}

	@Override
	public boolean supports(MethodParameter arg0, Class<? extends HttpMessageConverter<?>> arg1) {
		// TODO Auto-generated method stub
		return true;
	}
}
