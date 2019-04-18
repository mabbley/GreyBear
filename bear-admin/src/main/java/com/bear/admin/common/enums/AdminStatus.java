package com.bear.admin.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by mby on 2019/4/17.
 */
@Getter
@AllArgsConstructor
public enum AdminStatus implements IEnum<Integer> {

    NORMAL(1, "正常"),
    LOCKING(2, "锁定"),
    DISABLE(3, "禁用");

    private Integer code;
    private String label;

    @Override
    public Integer getValue() {
        return code;
    }
}
