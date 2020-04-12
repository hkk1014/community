package com.hk.community.community.service;

import com.hk.community.community.dto.NotificationDTO;
import com.hk.community.community.dto.PaginationDTO;
import com.hk.community.community.enums.NotificationEnum;
import com.hk.community.community.mapper.NotificationMapper;
import com.hk.community.community.mapper.UserMapper;
import com.hk.community.community.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 作者: hekang
 * 时间: 2020-04-11 22:44
 * 描述:
 **/
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO<NotificationDTO> pagination = new PaginationDTO();

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);
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
        pagination.setPagination(totalPage, page);
        Integer offset = size * (page - 1);
        NotificationExample example1 = new NotificationExample();
        example1.createCriteria()
                .andReceiverEqualTo(userId);
        example1.setOrderByClause("gmt_create desc");
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example1, new RowBounds(offset, size));

        if (notifications.size() == 0) {
            return pagination;
        }


        List<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = NotificationDTO.builder().build();
            BeanUtils.copyProperties(notification, notificationDTO);
            notificationDTO.setType(NotificationEnum.nameOfType(notification.getType()));
            notificationDTOS.add(notificationDTO);
        }

        pagination.setData(notificationDTOS);
        return pagination;
    }

    public Long unreadCount(Long userId) {
        NotificationExample example = new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        long unreadCount = notificationMapper.countByExample(example);
        return unreadCount;
    }
}
