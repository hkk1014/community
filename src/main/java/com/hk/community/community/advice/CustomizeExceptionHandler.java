package com.hk.community.community.advice;

import com.alibaba.fastjson.JSON;
import com.hk.community.community.dto.ResultDTO;
import com.hk.community.community.exception.CustomExceptioin;
import com.hk.community.community.exception.CustomizeErrorCode;
import com.sun.deploy.net.HttpResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 作者: 何康先生
 * 时间: 2020-03-29 14:22
 * 描述:
 **/
@Log4j2
@ControllerAdvice
public class CustomizeExceptionHandler {

    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request,
                  Throwable e,
                  Model model,
                  HttpServletResponse response) {
        String contentType = request.getContentType();
        log.error("访问出错",e);

        if ("application/json".equals(contentType)) {
            //返回json
            ResultDTO resultDTO = null;
            if (e instanceof CustomExceptioin) {
                resultDTO = ResultDTO.errorOf((CustomExceptioin) e);
            } else {
                resultDTO = ResultDTO.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("UTF-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {

            }
            return null;
        } else {
            //错误页面跳转
            if (e instanceof CustomExceptioin) {
                model.addAttribute("message", e.getMessage());
            } else {
                model.addAttribute("message", CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView("error");
        }
    }


}
