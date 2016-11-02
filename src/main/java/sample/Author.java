package sample;

import java.lang.annotation.Repeatable;

/**
 * Created by Administrator on 2016/7/9.
 */
@Repeatable(Authors.class)
public @interface Author {
    String name();
}
@interface Authors{
    Author[] value();
}
