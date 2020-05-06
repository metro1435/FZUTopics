package com.fzutopic.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 存储前端发来的评论(回复)踩赞信息
 * itemid:被踩赞对象的id
 * likes：0：踩  1：赞
 * sort：0：表示是对话题评论回复踩赞 1:表示是对话题评论踩赞 2：表示对课程（教师）评论踩赞
 *
 * @author 呼叫哆啦A梦
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikesSort {
    String itemid;
    int likes;
    int sort;
}
