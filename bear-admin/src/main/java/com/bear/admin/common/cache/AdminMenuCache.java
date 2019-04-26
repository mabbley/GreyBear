package com.bear.admin.common.cache;

import com.bear.admin.system.entity.Menu;
import com.bear.admin.system.service.MenuService;
import com.bear.common.core.cache.GuavaAbstractLoadingCache;
import com.bear.common.core.cache.ICache;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mby on 2019/4/25.
 */
public class AdminMenuCache extends GuavaAbstractLoadingCache<Long, List<Menu>> implements ICache<Long, List<Menu>> {

    @Autowired
    private MenuService menuService;

    @Override
    protected List<Menu> fetchData(Long key) {
        return null;
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
