package com.ldg.api.util.minganci;

import java.util.Set;

/**
 * Created by liudo on 2017/4/12.
 */
public class MinganciUtil {
    private final static SensitivewordFilter filter = new SensitivewordFilter();
    public static Set<String> getMinganciSet(String validateStr){
        Set<String> set = filter.getSensitiveWord(validateStr, 1);
        return set;
    }
}
