package com.bear.admin.common.base.entity;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mby on 2019/4/17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchParam implements Serializable {

    private Integer pageNo = 1;//当前页
    private Integer pageSize = 10;//每页记录数
    private Map<String, Object> param = new HashMap<>();

    private Integer direction;//排序  0=ASC  1=DESC
    private List<String> sortProperties = new ArrayList<>();//排序字段

    public void addParam(String key, Object value) {
        this.param.put(key, value);
    }

    public boolean isSort() {
        if(CollectionUtils.isEmpty(sortProperties)){
            return false;
        }
        return true;
    }

    public void addSort(Integer direction, List<String> sortProperties) {
        this.direction = direction;
        if (null != this.sortProperties) {
            this.sortProperties.addAll(sortProperties);
        } else {
            this.sortProperties = Lists.newArrayList();
            this.sortProperties.addAll(sortProperties);
        }
    }

    public void addSortDesc(List<String> sortProperties) {
        this.addSort(1, sortProperties);
    }

    public void addSortAsc(List<String> sortProperties) {
        this.addSort(0, sortProperties);
    }

    public void addSortAsc(String sortProperties) {
        this.addSort(0, Lists.newArrayList(sortProperties));
    }

    public void addSortDesc(String sortProperties) {
        this.addSort(1, Lists.newArrayList(sortProperties));
    }

    public <T>Page<T> toPageParam(){
        Page<T> page = new Page<>();
        page.setCurrent(pageNo);
        page.setSize(pageSize);
        if(isSort()){
            if(direction == null || direction == 0){
                page.setAscs(getSortProperties());
            }else{
                page.setDescs(getSortProperties());
            }
        }
        return page;
    }
}
