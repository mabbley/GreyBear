package com.bear.admin.common.base.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultView<T> implements Serializable {

    private Integer code;
    private String message;
    private T data;

    public static <T> ResultView result(Integer code, String message, T data) {
        return new ResultView<>(code, message, data);
    }

    public static <T> ResultView result(boolean flag) {
        if (flag) {
            return result(200, "操作成功", null);
        }
        return result(-200, "操作失败", null);
    }

    public static <T> ResultView result(Integer param) {
        if (param>0) {
            return result(200, "操作成功", null);
        }
        return result(-200, "操作失败", null);
    }

    public static <T> ResultView result(T t) {
        if (null != t) {
            return result(200, "操作成功", null);
        }
        return result(-200, "操作失败", null);
    }

    public static <T> ResultView result(List<T> t) {
        if (null != t) {
            return result(200, "操作成功", t);
        }
        return result(-200, "操作失败", Lists.newArrayList());
    }

    public static <T> ResultView page(Integer code, String message, IPage<T> page) {
        PageView<T> pageView = new PageView<>();
        if (null != page) {
            pageView.setRows(page.getRecords());
            pageView.setTotal(page.getTotal());
        }else{
            pageView.setRows(Lists.newArrayList());
            pageView.setTotal(0L);
        }
        return result(code, message, pageView);

    }

    public static <T> ResultView page(IPage<T> page) {
        return page(200, "分页查询成功", page);
    }
}
