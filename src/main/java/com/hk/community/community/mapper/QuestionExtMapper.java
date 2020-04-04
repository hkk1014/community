package com.hk.community.community.mapper;

import com.hk.community.community.model.Question;
import org.apache.ibatis.annotations.Param;

public interface QuestionExtMapper {
 void incView(@Param("record") Question record);
 void incCommentCount(@Param("record")Question  record);
}