package com.hk.community.community.service;

import com.hk.community.community.dto.CommentDTO;
import com.hk.community.community.enums.CommentTypeEnum;
import com.hk.community.community.enums.NotificationEnum;
import com.hk.community.community.enums.NotificationTypeEnum;
import com.hk.community.community.exception.CustomExceptioin;
import com.hk.community.community.exception.CustomizeErrorCode;
import com.hk.community.community.mapper.*;
import com.hk.community.community.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 作者: 何康先生
 * 时间: 2020-04-02 22:08
 * 描述:
 **/
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CommentExtMapper commentExtMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
    public void insert(Comment comment, User commentator) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomExceptioin(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }
        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomExceptioin(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }
        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            //回复评论
            Comment dbComment = commentMapper.selectByPrimaryKey(comment.getParentId());
            if (dbComment == null) {
                throw new CustomExceptioin(CustomizeErrorCode.COMMENT_NOT_FOUND);
            }

            Question question = questionMapper.selectByPrimaryKey(dbComment.getParentId());
            if (question == null) {
                throw new CustomExceptioin(CustomizeErrorCode.QUESTON_NOT_FOUND);

            }
            commentMapper.insert(comment);
            //增加评论数
            comment.setId(comment.getParentId());
            comment.setCommentCount(1L);
            commentExtMapper.incCommentCount(comment);
            //创建通知
            createNotify(comment, dbComment.getCommentator(), commentator.getName(), question.getTitle(), NotificationEnum.REPLY_COMMENT.getType());
        } else {
            //回复问题
            Question question = questionMapper.selectByPrimaryKey(comment.getParentId());
            if (question == null) {
                throw new CustomExceptioin(CustomizeErrorCode.QUESTON_NOT_FOUND);

            }
            commentMapper.insert(comment);
            question.setCommentCount(1);
            questionExtMapper.incCommentCount(question);

            createNotify(comment, question.getCreator(), commentator.getName(), question.getTitle(), NotificationEnum.REPLY_QUESTION.getType());

        }
    }

    private void createNotify(Comment comment, Long receiver, String notifierName, String outerTitle, int type) {
        Notification notification = new Notification();
        notification.setGmtCreate(System.currentTimeMillis());
        notification.setType(type);
        notification.setOuterid(comment.getParentId());
        notification.setNotifier(comment.getCommentator());
        notification.setStatus(NotificationTypeEnum.UNREAD.getStatus());
        notification.setReceiver(receiver);
        notification.setNotifierName(notifierName);
        notification.setOuterTitle(outerTitle);
        notificationMapper.insert(notification);
    }

    public List<CommentDTO> listByTagretId(Long id, CommentTypeEnum type) {
        CommentExample example = new CommentExample();
        example.createCriteria().andParentIdEqualTo(id).andTypeEqualTo(type.getType());

        example.setOrderByClause("gmt_create desc");
        List<Comment> comments = commentMapper.selectByExample(example);


        if (comments.size() == 0) {
            return new ArrayList<>();
        }
        Set<Long> commentators = comments.stream().map(comment -> comment.getCommentator()).collect(Collectors.toSet());
        //获取去重的评论人
        List<Long> userIds = new ArrayList();
        userIds.addAll(commentators);
        UserExample example1 = new UserExample();
        example.createCriteria().andIdIn(userIds);

        //获取评论人并转换为map
        List<User> users = userMapper.selectByExample(example1);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(user -> user.getId(), user -> user));
        //转换comment为commentDTO
        List<CommentDTO> commentDTOS = comments.stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            BeanUtils.copyProperties(comment, commentDTO);
            Long commentator = commentDTO.getCommentator();
            User user = userMap.get(commentator);
            commentDTO.setUser(user);
            return commentDTO;
        }).collect(Collectors.toList());
        return commentDTOS;
    }
}
