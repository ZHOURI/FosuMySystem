package cn.honeyjam.dorm;

import cn.honeyjam.dorm.mapper.VisitorMapper;
import cn.honeyjam.dorm.pojo.Visitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application.xml"})
public class visitorTest {
    @Autowired
    private VisitorMapper visitorMapper;
    @Test
    public void test1()
    {
//        Visitor visitor = new Visitor();
//        visitor.setId(1);
        Visitor visitor1 = visitorMapper.selectByPrimaryKey(1);
        System.out.println(visitor1);
    }
    @Test
    public void test2()
    {
        Map<String,Integer> map = new HashMap<>();
        map.put("k1",123);
        map.put("k2",234);
        map.put("k3",345);
        Set<String> keySet = map.keySet();
        Iterator<String> iterator = keySet.iterator();
        while(iterator.hasNext())
        {
            String key = iterator.next();
            System.out.println(key+"------"+map.get(key));
        }
    }
    @Test
    public void test3()
    {
        String str = "LEETCODEISHIRING";

        StringBuffer buffer = new StringBuffer("");
        int len = str.length();
        int n=0,row=0,col=0,num=3,t;
        while(n<len)
        {
            t=row;
            while(t<len)
            {
                buffer.append(str.charAt(t));
                if(row==1)
                {
                    if(t+(num-1)*2-2<len)
                    {
                        buffer.append(str.charAt(t+(num-1)*2-2));
                        n=n+1;
                    }
                    else
                    {
                        t=t+(num-1)*2;
                        n=n+1;
                        break;
                    }
                }
                if(row==num-2&&row>3)
                {
                    if(t+2<len)
                    {
                        buffer.append(str.charAt(t+2));
                        n=n+1;
                    }
                    else
                    {
                        t=t+(num-1)*2;
                        n=n+1;
                        break;
                    }
                }
                t=t+(num-1)*2;
                n=n+1;
            }
            row = row + 1;
        }
    }
}
