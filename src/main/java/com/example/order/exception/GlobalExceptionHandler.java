package com.example.order.exception;


import com.example.order.common.util.provider.ExceptionEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理类
 */
//@Component
public class GlobalExceptionHandler implements HandlerExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.error("{} Error", request.getRequestURI(), ex);

        MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
        ModelAndView modelAndView = new ModelAndView(jsonView);

        modelAndView.addObject("code", ExceptionEnum.SYSTEM_SERVER_EXCEPTION.getCode());
        modelAndView.addObject("msg", ex.getMessage());

        return modelAndView;
    }
}
