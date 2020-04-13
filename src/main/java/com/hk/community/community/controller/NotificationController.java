package com.hk.community.community.controller;

import com.hk.community.community.dto.NotificationDTO;
import com.hk.community.community.enums.NotificationEnum;
import com.hk.community.community.model.Notification;
import com.hk.community.community.model.User;
import com.hk.community.community.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者: hekang
 * 时间: 2020-04-12 11:22
 * 描述:
 **/
@Controller
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @GetMapping("/notifaction/{id}")
    public String question(HttpServletRequest request,
                           @PathVariable(value = "id") Long id,
                           Model model) {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/";
        }
        NotificationDTO notificationDTO = notificationService.read(id, user);

        if(NotificationEnum.REPLY_COMMENT.getType()==notificationDTO.getType()
                ||NotificationEnum.REPLY_QUESTION.getType()==notificationDTO.getType()){
            return "redirect:/question/"+notificationDTO.getOuterid();
        }else{
            return  "redirect:/";
        }

    }

}
