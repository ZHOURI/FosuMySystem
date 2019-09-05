package cn.honeyjam.dorm;

import cn.honeyjam.dorm.mapper.VisitorMapper;
import cn.honeyjam.dorm.pojo.Visitor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
}
