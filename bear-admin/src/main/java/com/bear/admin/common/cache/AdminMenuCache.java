package com.bear.admin.common.cache;

import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.service.MenuService;
import com.bear.common.core.cache.GuavaAbstractLoadingCache;
import com.bear.common.core.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mby on 2019/4/25.
 */
@Component
public class AdminMenuCache extends GuavaAbstractLoadingCache<Long, List<Menu>> implements ICache<Long, List<Menu>> {

    @Autowired
    private MenuService menuService;

    @Override
    protected List<Menu> fetchData(Long key) {
        return menuService.findByAdmin(key);
    }

    @Override
    public List<Menu> get(Long aLong) {
        return getValue(aLong);
    }

    @Override
    public void clear() {
        clearAll();
    }
}
