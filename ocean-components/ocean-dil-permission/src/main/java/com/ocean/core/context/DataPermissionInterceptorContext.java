package com.ocean.core.context;

import com.ocean.core.rule.DataPermissionRule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataPermissionInterceptorContext {
    public static final ThreadLocal<List<DataPermissionRule>> RULES = ThreadLocal.withInitial(Collections::emptyList);
    public static final ThreadLocal<Boolean> REWRITE = ThreadLocal.withInitial(() -> Boolean.FALSE);

    public static void init(List<DataPermissionRule> rules){
        RULES.set(rules);
        REWRITE.set(false);
    }

    public static void clear(){
        RULES.remove();
        REWRITE.remove();
    }
    public static boolean getRewrite(){
        return REWRITE.get();
    }
    public static void setRewrite(boolean rewrite){
        REWRITE.set(rewrite);
    }

    public static List<DataPermissionRule> getRules(){
        return RULES.get();
    }
}
