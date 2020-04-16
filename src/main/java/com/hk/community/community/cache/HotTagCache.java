package com.hk.community.community.cache;

import com.hk.community.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 作者: hekang
 * 时间: 2020-04-15 23:30
 * 描述:
 **/
@Component
@Data
public class HotTagCache {
    private List<String> hots = new ArrayList<>();

    public void updateTage(Map<String, Integer> tags) {
        int max = 10;
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        tags.forEach(
                (name, priority) -> {
                    HotTagDTO hotTagDTO = new HotTagDTO();
                    hotTagDTO.setName(name);
                    hotTagDTO.setPriority(priority);
                    if (priorityQueue.size() < max) {
                        priorityQueue.add(hotTagDTO);
                    } else {
                        //查找最小元素
                        HotTagDTO minHot = priorityQueue.peek();
                        if (hotTagDTO.compareTo(minHot) > 0) {
                            //拿出最小元素
                            priorityQueue.poll();
                            priorityQueue.add(hotTagDTO);

                        }

                    }
                }
        );


        List<String> sortedTags = new ArrayList<>();

        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedTags.add(0, poll.getName());

            poll = priorityQueue.poll();


        }
        hots = sortedTags;
        System.out.println(hots);

    }
}
