package cn.honeyjam.dorm;

import cn.honeyjam.dorm.mapper.DormMapper;
import cn.honeyjam.dorm.mapper.ManagerMapper;
import cn.honeyjam.dorm.pojo.Dorm;
import cn.honeyjam.dorm.pojo.Manager;
import cn.honeyjam.dorm.pojo.Notice;
import cn.honeyjam.dorm.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application.xml"})
public class ManagerTest {
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private DormMapper dormMapper;
    @Test
    public void updPass()
    {
        Manager manager = new Manager();
        manager.setPassword("123");
        managerMapper.updateByPrimaryKeySelective(manager);
    }
    @Test
    public void queryStuTest()
    {
        Manager manager = new Manager();
        manager.setDormId(6);
        Map<String,String[]> map = new HashMap<>();
        List<Student> studentList = managerMapper.queryStudents(6,map);
        for (Student student:studentList)
        {
            System.out.println(student);
        }
    }
    @Test
    public void StringTest()
    {
        StringBuffer buffer = new StringBuffer("123133");
        buffer.append("aaaaa");
        System.out.println(buffer.toString());
    }
    @Test
    public void noticeTest()
    {
        Map<String,String[]> map = new HashMap<>();
        List<Notice> noticeList = managerMapper.queryNotice(8,map);
        for(Notice notice:noticeList)
        {
            System.out.println(notice);
        }
    }
}
