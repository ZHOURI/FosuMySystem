package cn.honeyjam.dorm.common;

import org.springframework.stereotype.Component;

@Component
public class Identify {
    public static String queryIdentify(Object object)
    {
        if(object.toString().contains("学生"))
        {
            return "学生";
        }
        else
        {
            return "宿管";
        }
    }

}
