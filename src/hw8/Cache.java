package hw8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache {
    String fileNamePrefix() default "data";
    boolean zip() default true;
    CacheType cacheType() default CacheType.FILE;
    int maxListSize() default 100_000;
}