package com.bear.admin.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mby on 2019/4/18.
 */
@Getter
@AllArgsConstructor
public enum MenuType implements IEnum<Integer> {
    ML(1,"目录"),MENU(2,"菜单"),BUTTON(3,"按钮");

    private Integer code;
    private String label;

    @Override
    public Integer getValue() {
        return code;
    }
}
