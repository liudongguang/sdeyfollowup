package com.ldg.api.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by LiuDongguang on 2017/4/10.
 */
public class LdgNumberUtil {
    private static DecimalFormat df=(DecimalFormat) NumberFormat.getInstance();
    public static String getBaifenbi(double baifenNum){
        df.applyPattern("##.##%");
        return df.format(baifenNum);
    }
}
