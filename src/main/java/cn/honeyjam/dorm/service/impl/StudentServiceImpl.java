package cn.honeyjam.dorm.service.impl;

import cn.honeyjam.dorm.mapper.*;
import cn.honeyjam.dorm.pojo.*;
import cn.honeyjam.dorm.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PunishMapper punishMapper;
    @Autowired
    private FeesMapper feesMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private HealthMapper healthMapper;
    @Autowired
    private WaterMapper waterMapper;
    @Autowired
    private RoomPunishMapper roomPunishMapper;
    @Autowired
    private StayMapper stayMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private RepairMapper repairMapper;
    @Override
    public Student queryOwnInfo(Long userId) {
        Student student = new Student();
        student.setId(userId);
        Student student1 = studentMapper.selectOne(student);
        Punish punish = new Punish();
        punish.setStuId(userId);
        List<Punish> punishes = punishMapper.select(punish);
        student1.setPunishes(punishes);
        Fees fees = new Fees();
        fees.setStuId(userId);
        List<Fees> feesList = feesMapper.select(fees);
        student1.setFeesList(feesList);
        return student1;
    }

    public Boolean updatePassword(String password,Long userId) {
        Student student = studentMapper.selectByPrimaryKey(userId);
        student.setPassword(password);
        int i = studentMapper.updateByPrimaryKeySelective(student);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Room queryRoomInfo(String roomId) {
        Room room = new Room();
        room.setRoomId(roomId);
        Room roomInfo = roomMapper.selectOne(room);
        RoomPunish roomPunish = new RoomPunish();
        roomPunish.setRoomId(roomId);
        List<RoomPunish> roomPunishList = roomPunishMapper.select(roomPunish);
        roomInfo.setRoomPunishList(roomPunishList);
        Water water = new Water();
        water.setRoomId(roomId);
        List<Water> waterList = waterMapper.select(water);
        roomInfo.setWaterList(waterList);
        Health health = new Health();
        health.setRoomId(roomId);
        List<Health> healthList = healthMapper.select(health);
        roomInfo.setHealthList(healthList);
        return roomInfo;
    }
    @Override
    public boolean saveStayForm(Stay stay) {
        int insert = stayMapper.insertSelective(stay);
        if(insert>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public PageResult<Stay> queryStay(Long id, Integer currentPage, Integer rows, Map<String,String[]>map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        Example example = new Example(Stay.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("stuId",id);
        for (String key:map.keySet())
        {
            if(key.equals("rows")||key.equals("currentPage"))
            {
                continue;
            }
            criteria.andLike(key,"%"+map.get(key)[0]+"%");
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Stay> stays = stayMapper.selectByExample(example);
        Page<Stay> pageInfo = (Page<Stay>)stays;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult());
    }

    @Override
    public PageResult<Fees> queryFees(Long id, Integer currentPage, Integer rows, Map<String,String[]>map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        Example example = new Example(Fees.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("stuId",id);
        for (String key:map.keySet())
        {
            if(key.equals("rows")||key.equals("currentPage")||key.equals("ids"))
            {
                continue;
            }
            criteria.andLike(key,"%"+map.get(key)[0]+"%");
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Fees> fees = feesMapper.selectByExample(example);
        Page<Fees> pageInfo = (Page<Fees>)fees;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    public List<Punish> queryPunish(Long id) {
        Punish punish = new Punish();
        punish.setStuId(id);
        List<Punish> punishList = punishMapper.select(punish);
        return punishList;
    }

    @Override
    public Repair queryRepairOne(Integer id) {
        Repair repair = repairMapper.selectByPrimaryKey(id);
        return repair;
    }

    @Override
    public PageResult<Repair> queryRepair(Repair repair, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        Example example = new Example(Repair.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("roomId",repair.getRoomId());
        for (String key:map.keySet())
        {
            if(key.equals("rows")||key.equals("currentPage")||key.equals("ids"))
            {
                continue;
            }
            criteria.andLike(key,"%"+map.get(key)[0]+"%");
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Repair> Repair = repairMapper.selectByExample(example);
        Page<Repair> pageInfo = (Page<Repair>)Repair;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    @Override
    public boolean saveRepairForm(Student student,Repair repair) {
        repair.setRoomId(student.getRoomId());
        repair.setReporter(student.getStuName());
        repair.setReporttime(new Date());
        repair.setStatus("待受理");
        int insert = repairMapper.insert(repair);
        if(insert>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public List<Notice> queryNotice() {
        List<Notice> noticeList = noticeMapper.selectAll();
        return noticeList;
    }
}
