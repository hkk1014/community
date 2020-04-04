package com.hk.community.community.controller;

import com.hk.community.community.dto.CommentCreateDTO;
import com.hk.community.community.dto.ResultDTO;
import com.hk.community.community.exception.CustomizeErrorCode;
import com.hk.community.community.mapper.CommentMapper;
import com.hk.community.community.model.Comment;
import com.hk.community.community.model.User;
import com.hk.community.community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 作者: 何康先生
 * 时间: 2020-03-31 23:42
 * 描述:
 **/
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;



    @Autowired
    private CommentService commentService;
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object post(@RequestBody CommentCreateDTO commentDTO,
                       HttpServletRequest request) {


        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NOT_LOGIN);
        }
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(1L);
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return ResultDTO.okOf();
    }
}
