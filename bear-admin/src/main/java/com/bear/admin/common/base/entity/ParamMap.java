package com.bear.admin.common.base.entity;

import java.util.HashMap;

/**
 * Created by mby on 2019/4/18.
 */
public class ParamMap extends HashMap<String,Object> {

    public static ParamMap init(){
        return new ParamMap();
    }

    public ParamMap eq(String key,Object val){
        this.put(Symbol.eq.convert(key),val);
        return this;
    }
    public ParamMap gt(String key,Object val){
        this.put(Symbol.gt.convert(key),val);
        return this;
    }
    public ParamMap get(String key,Object val){
        this.put(Symbol.get.convert(key),val);
        return this;
    }
    public ParamMap lt(String key,Object val){
        this.put(Symbol.lt.convert(key),val);
        return this;
    }
    public ParamMap let(String key,Object val){
        this.put(Symbol.let.convert(key),val);
        return this;
    }
    public ParamMap like(String key,Object val){
        this.put(Symbol.like.convert(key),val);
        return this;
    }
    public ParamMap likeL(String key,Object val){
        this.put(Symbol.likeL.convert(key),val);
        return this;
    }
    public ParamMap likeR(String key,Object val){
        this.put(Symbol.likeR.convert(key),val);
        return this;
    }
    public ParamMap notLike(String key,Object val){
        this.put(Symbol.notLike.convert(key),val);
        return this;
    }
    public ParamMap in(String key,Object val){
        this.put(Symbol.in.convert(key),val);
        return this;
    }
    public ParamMap ne(String key,Object val){
        this.put(Symbol.ne.convert(key),val);
        return this;
    }

    public enum Symbol{
        eq,
        gt,
        get,
        lt,
        let,
        like,
        likeL,
        likeR,
        notLike,
        in,
        ne;
        public String convert(String key){
            return String.format("%s@%s",key,this.name());
        }
    }
}
