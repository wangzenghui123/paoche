package com.example.paoche.vo.resp;

import lombok.Data;

import java.util.List;

@Data
public class PermissionRespNodeVO {
    //"主键id"
    private String id;

    // "跳转地址")
    private String url;

    //"菜单权限名称")
    private String title;

    // "子集集合")
    private List<?> children;

    // "默认展开")
    private boolean spread=true;

    //"节点是否选中")
    private boolean checked;

}
