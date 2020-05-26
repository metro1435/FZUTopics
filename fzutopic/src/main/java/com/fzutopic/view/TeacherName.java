package com.fzutopic.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherName {
    private String teacherid;
    private String teachername;
}
