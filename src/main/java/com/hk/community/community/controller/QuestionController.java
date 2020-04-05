package com.hk.community.community.controller;

import com.hk.community.community.dto.CommentDTO;
import com.hk.community.community.dto.QuestionDTO;
import com.hk.community.community.enums.CommentTypeEnum;
import com.hk.community.community.service.CommentService;
import com.hk.community.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 作者: 何康先生
 * 时间: 2020-03-25 22:09
 * 描述:
 **/
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(value = "id") Long id,
                           Model model) {

        QuestionDTO questionDTO = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectrelated(questionDTO);
        List<CommentDTO> commentList=commentService.listByTagretId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        model.addAttribute("comments",commentList);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
