package cn.honeyjam.dorm;

import cn.honeyjam.dorm.mapper.CollegeMapper;
import cn.honeyjam.dorm.pojo.College;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application.xml"})
public class CollegeTest {
    @Autowired
    private CollegeMapper collegeMapper;
    @Test
    public void test1()
    {
        College college1 = new College();
        college1.setCollegeId(2);
        List<College> colleges = collegeMapper.selectAll();
        System.out.println(collegeMapper.selectByPrimaryKey(college1));
        for(College college:colleges)
        {
            System.out.println(college);
        }
    }
//    逆向工程
//    @Test
//    public void test2()
//    {
//        System.out.println(collegeMapper.selectByPrimaryKey(2));
//    }
}
