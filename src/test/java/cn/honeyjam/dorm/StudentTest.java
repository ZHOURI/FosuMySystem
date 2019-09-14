package cn.honeyjam.dorm;

import cn.honeyjam.dorm.mapper.*;
import cn.honeyjam.dorm.pojo.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:application.xml"})
public class StudentTest {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private HealthMapper healthMapper;
    @Autowired
    private WaterMapper waterMapper;
    @Autowired
    private PunishMapper punishMapper;
    @Autowired
    private RoomPunishMapper roomPunishMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Test
    public void test1()
    {
        System.out.println(studentMapper);
        List<Student> students = studentMapper.selectAll();
        for (Student student:students)
        {
            System.out.println(student);
        }
        Student student1 = studentMapper.selectByPrimaryKey(20160390320L);
        System.out.println(student1);
//        Student student = new Student();
//        student.setId(20160390320L);
//        student.setPassword("123456");
        Student student2 = studentMapper.queryStudent(20160390320L, "123456");
        System.out.println(student2);
    }
    @Test
    public void RoomInfo()
    {
        Room room = new Room();
        room.setRoomId("D8-613");
        Room room1 = roomMapper.selectOne(room);
        System.out.println(room1);
        RoomPunish roomPunish = new RoomPunish();
        roomPunish.setRoomId(room.getRoomId());
        List<RoomPunish> roomPunishList = roomPunishMapper.select(roomPunish);
        for (RoomPunish roomPunish1:roomPunishList)
        {
            System.out.println(roomPunish1);
        }
    }
    @Test
    public void roomPunish()
    {
        List<RoomPunish> roomPunishList = roomPunishMapper.selectAll();
        for (RoomPunish roomPunish:roomPunishList)
        {
            System.out.println(roomPunish);
        }
    }
    @Test
    public void queryNoticeTest()
    {
        List<Notice> noticeList = noticeMapper.selectAll();
        for (Notice notice:noticeList)
        {
            System.out.println(notice);
        }
    }
    @Test
    public void testSwitch()
    {
        String name = "punih";
        switch (name)
        {
            case "notice":
                System.out.println("这是notice");break;
            case "punish":
                System.out.println("这是punish");break;
                default:
                    System.out.println("这是default");break;
        }
    }

}
