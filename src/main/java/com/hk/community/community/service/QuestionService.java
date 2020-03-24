package com.hk.community.community.service;

import com.hk.community.community.dto.PaginationDTO;
import com.hk.community.community.dto.QuestionDTO;
import com.hk.community.community.mapper.QuestionMapper;
import com.hk.community.community.mapper.UserMapper;
import com.hk.community.community.model.Question;
import com.hk.community.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: 何康先生
 * 时间: 2020-03-21 21:04
 * 描述:
 **/
@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO pagination = new  PaginationDTO();
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }



        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }
        pagination.setPagination(totalPage,page);
        Integer offset = size*(page -1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        return pagination;
    }


    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO pagination = new  PaginationDTO();
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage =null;
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }


        if (page > totalPage) {
            page = totalPage;
        }
        if (page < 1) {
            page = 1;
        }


        pagination.setPagination(totalPage,page);
        Integer offset = size*(page -1);
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        pagination.setQuestions(questionDTOList);

        return pagination;
    }
}
