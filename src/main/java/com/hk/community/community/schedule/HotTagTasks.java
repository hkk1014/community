package com.hk.community.community.schedule;

import com.hk.community.community.cache.HotTagCache;
import com.hk.community.community.mapper.QuestionMapper;
import com.hk.community.community.model.Question;
import com.hk.community.community.model.QuestionExample;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 作者: hekang
 * 时间: 2020-04-15 23:06
 * 描述:
 **/
@Slf4j
@Component
public class HotTagTasks {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private QuestionMapper questionMapper;


    @Autowired
    HotTagCache hotTagCache;
    //三个小时
    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)
    //@Scheduled(cron = "0 0 1 * * *")//凌晨一点运行
    public void hotTagSchedule() {
        int offset = 0;
        int limit = 20;

        log.info("hotTagSchedule start {}", dateFormat.format(new Date()));
        List<Question> list = new ArrayList<>();

        Map<String, Integer> pririties = new HashMap<>();
        while (offset == 0 || list.size() == limit) {
            list = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, limit));
            for (Question question : list) {


                List<String> tags = Arrays.stream(question.getTag().split(",")).filter(tag -> tag != null).collect(Collectors.toList());
                for (String tag : tags) {
                    Integer priority = pririties.get(tag);
                    if (priority != null) {
                        pririties.put(tag, priority + 5 + question.getCommentCount());
                    } else {
                        pririties.put(tag, 5 + question.getCommentCount());
                    }
                }

            }
            offset += limit;
        }
//        hotTagCache.setTags(pririties);

        hotTagCache.updateTage(pririties);
        log.info("hotTagSchedule stop {}", dateFormat.format(new Date()));


    }

}
