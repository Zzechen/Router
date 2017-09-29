package com.zzc.library;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created : zzc
 * Time : 2017/9/29
 * Email : zzc1259@163.com
 * Description : ${desc}
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.TYPE})
public @interface Path {
    String[] path();
}
