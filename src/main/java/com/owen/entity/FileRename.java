package com.owen.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FileRename {

    private List<String> namePattern;
    private String targetName;

}
