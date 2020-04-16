package com.hk.community.community.dto;

import lombok.Data;

/**
 * 作者: hekang
 * 时间: 2020-04-16 20:51
 * 描述:
 **/
@Data
public class HotTagDTO  implements  Comparable{
    private String name;
    private Integer priority;

    @Override
    public int compareTo(Object o) {
        return this.getPriority()-((HotTagDTO)o).getPriority();
    }
}
