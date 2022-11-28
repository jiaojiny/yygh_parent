package com.atguigu.yygh.vo.cmn;

import java.util.ArrayList;
import java.util.List;

public class DictVo {

    private Long id;
    private String name;
    private List<DictVo> children = new ArrayList<>();
}
