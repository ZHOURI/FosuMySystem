package cn.honeyjam.dorm.service.impl;

import cn.honeyjam.dorm.mapper.*;
import cn.honeyjam.dorm.pojo.*;
import cn.honeyjam.dorm.pojo.Class;
import cn.honeyjam.dorm.service.ManageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManageServiceImpl implements ManageService {
    @Autowired
    private DormMapper dormMapper;
    @Autowired
    private FeesMapper feesMapper;
    @Autowired
    private HealthMapper healthMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private PunishMapper punishMapper;
    @Autowired
    private RepairMapper repairMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomPunishMapper roomPunishMapper;
    @Autowired
    private SalaryMapper salaryMapper;
    @Autowired
    private StayMapper stayMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private VisitorMapper visitorMapper;
    @Autowired
    private WaterMapper waterMapper;
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private CollegeMapper collegeMapper;
    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public Manager queryOwnInfo(Manager manager) {
        Salary salary = new Salary();
        salary.setWorkId(manager.getId());
        List<Salary> salaryList = salaryMapper.select(salary);
        manager.setSalaryList(salaryList);
        return manager;
    }

    @Override
    public PageResult<Student> queryStu(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Student> studentList = managerMapper.queryStudents(manager.getDormId(), map);
        Page<Student> pageInfo = (Page<Student>)studentList;
        current(currentPage,pageInfo);
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    private Map<String,Object> current(Integer currentPage,Page pageInfo)
    {
        Map<String,Object> map = new HashMap<>();
        int end = 0;
        int begin = 0;
        if(currentPage-4>0)
        {
            begin = currentPage - 4;
        }
        else
        {
            begin = 1;
        }
        if(currentPage+5<=pageInfo.getPages())
        {
            end = currentPage+5;
            if(currentPage+5<=10)
                end = 10;
        }
        else
        {
            end = pageInfo.getPages();
            if(end>=10)
                begin = end-9;
            else
                begin=1;
        }
        map.put("begin",begin);
        map.put("end",end);
        return map;
    }
    @Override
    public Boolean updatePassword(String password, Manager manager) {
        manager.setPassword(password);
        int i = managerMapper.updateByPrimaryKeySelective(manager);
        if(i>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public Student queryStuInfo(Long id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        Class aClass = new Class();
        aClass.setClassId(student.getClassId());
        student.setClassName(classMapper.selectOne(aClass).getClassName());
        College college = new College();
        college.setCollegeId(student.getCollegeId());
        student.setCollegeName(collegeMapper.selectOne(college).getCollegeName());
        Punish punish = new Punish();
        punish.setStuId(id);
        List<Punish> punishList = punishMapper.select(punish);
        Fees fees = new Fees();
        fees.setStuId(id);
        List<Fees> feesList = feesMapper.select(fees);
        Stay stay = new Stay();
        stay.setStuId(id);
        List<Stay> stayList = stayMapper.select(stay);
        student.setFeesList(feesList);
        student.setPunishes(punishList);
        student.setStayList(stayList);
        return student;
    }

    @Override
    public PageResult<Room> queryRoom(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        Example example = new Example(Room.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("dormId",manager.getDormId());
        for (String key:map.keySet())
        {
            if(key.equals("rows")||key.equals("currentPage"))
            {
                continue;
            }
            criteria.andLike(key,"%"+map.get(key)[0]+"%");
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Room> roomList = roomMapper.selectByExample(example);
        Page<Room> pageInfo = (Page<Room>)roomList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    @Override
    public Room queryRoomInfo(String id) {
        Room room = roomMapper.selectByPrimaryKey(id);
        RoomPunish roomPunish = new RoomPunish();
        roomPunish.setRoomId(id);
        List<RoomPunish> roomPunishList = roomPunishMapper.select(roomPunish);
        Health health = new Health();
        health.setRoomId(id);
        List<Health> healthList = healthMapper.select(health);
        Water water = new Water();
        water.setRoomId(id);
        List<Water> waterList = waterMapper.select(water);
        Repair repair = new Repair();
        repair.setRoomId(id);
        List<Repair> repairList = repairMapper.select(repair);
        Dorm dorm = new Dorm();
        dorm.setDormId(room.getDormId());
        Dorm dorm1 = dormMapper.selectOne(dorm);
        School school = new School();
        school.setSchoolId(room.getSchoolId());
        School school1 = schoolMapper.selectOne(school);
        room.setDormName(dorm1.getDormName());
        room.setSchoolName(school1.getSchoolName());
        room.setRepairList(repairList);
        room.setHealthList(healthList);
        room.setWaterList(waterList);
        room.setRoomPunishList(roomPunishList);
        return room;
    }
    @Override
    public PageResult<Water> queryWater(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Water> waterList = managerMapper.queryWater(manager.getDormId(), map);
        Page<Water> pageInfo = (Page<Water>)waterList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    @Override
    public PageResult<Health> queryHealth(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Health> healthList = managerMapper.queryHealth(manager.getDormId(), map);
        Page<Health> pageInfo = (Page<Health>)healthList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<Fees> queryFees(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Fees> feesList = managerMapper.queryFees(manager.getDormId(), map);
        Page<Fees> pageInfo = (Page<Fees>)feesList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<Punish> queryPunish(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Punish> punishList = managerMapper.queryPunish(manager.getDormId(), map);
        Page<Punish> pageInfo = (Page<Punish>)punishList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<Stay> queryStay(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Stay> stayList = managerMapper.queryStay(manager.getDormId(), map);
        Page<Stay> pageInfo = (Page<Stay>)stayList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<Repair> queryRepair(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Repair> repairList = managerMapper.queryRepair(manager.getDormId(), map);
        Page<Repair> pageInfo = (Page<Repair>)repairList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<Visitor> queryVisitor(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Visitor> visitorList = managerMapper.queryVisitor(manager.getDormId(), map);
        Page<Visitor> pageInfo = (Page<Visitor>)visitorList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    @Override
    public Notice queryNoticeOne(Integer id) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        return notice;
    }

    @Override
    public PageResult<Notice> queryNotice(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Notice> noticeList = managerMapper.queryNotice(manager.getDormId(), map);
        Page<Notice> pageInfo = (Page<Notice>)noticeList;
        current(currentPage,pageInfo);
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
    @Override
    public PageResult<RoomPunish> queryRoomPunish(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map) {
        if(currentPage==null)
        {
            currentPage = 1;
        }
        if(rows==null)
        {
            rows = 5;
        }
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<RoomPunish> waterList = managerMapper.queryRoomPunish(manager.getDormId(), map);
        Page<RoomPunish> pageInfo = (Page<RoomPunish>)waterList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }

    @Override
    public Boolean editStay(Integer id) {
        Stay stay= stayMapper.selectByPrimaryKey(id);
        if(stay.getStatus().equals("待审核"))
        {
            stay.setStatus("审核通过");
        }
        else
        {
            stay.setStatus("待审核");
        }
        int i = stayMapper.updateByPrimaryKeySelective(stay);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean editRepair(Integer id) {
        Repair repair= repairMapper.selectByPrimaryKey(id);
        if(repair.getStatus().equals("待审核"))
        {
            repair.setStatus("审核通过");
        }
        else
        {
            repair.setStatus("待审核");
        }
        int i = repairMapper.updateByPrimaryKeySelective(repair);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean addStuPunish(Punish punish) {
        punish.setCreatetime(new Date());
        int i = punishMapper.insert(punish);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Boolean addRoomPunish(RoomPunish roomPunish) {
        roomPunish.setCreatetime(new Date());
        int i = roomPunishMapper.insert(roomPunish);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean editFees(Integer id) {
        Fees fees = feesMapper.selectByPrimaryKey(id);
        if(fees.getPaystatus().equals("已缴费"))
        {
            fees.setPaystatus("未缴费");
            fees.setPaytime(null);
        }
        else
        {
            fees.setPaystatus("已缴费");
            fees.setPaytime(new Date());
        }
        int i = feesMapper.updateByPrimaryKeySelective(fees);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delStuPunish(Integer id) {
        int i = punishMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delStuFees(Integer id) {
        int i = feesMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Boolean delHealth(Integer id) {
        int i = healthMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delStay(Integer id) {
        int i = stayMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delWater(Integer id) {
        int i = waterMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delRoomPunish(Integer id) {
        int i = roomPunishMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delVisitor(Integer id) {
        int i = visitorMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean delNotice(Integer id) {
        int i = noticeMapper.deleteByPrimaryKey(id);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean editWater(Integer id) {
        Water water = waterMapper.selectByPrimaryKey(id);
        if(water.getPaystatus().equals("已缴费"))
        {
            water.setPaystatus("未缴费");
            water.setPaytime(null);
        }
        else
        {
            water.setPaystatus("已缴费");
            water.setPaytime(new Date());
        }
        int i = waterMapper.updateByPrimaryKeySelective(water);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean addRoomHealth(Health health) {
        health.setChecktime(new Date());
        int i = healthMapper.insert(health);
        if(i>0)
        {
            return true;
        }
        return false;
    }
    @Override
    public Boolean addRoomWater(Water water) {
        if(water.getPaystatus().equals("已缴费"))
        water.setPaytime(new Date());
        int i = waterMapper.insert(water);
        if(i>0)
        {
            return true;
        }
        return false;
    }

    @Override
    public Punish queryStuPunishOne(Integer id) {
        Punish punish = punishMapper.selectByPrimaryKey(id);
        return punish;
    }
    @Override
    public Water queryWaterOne(Integer id) {
        Water water = waterMapper.selectByPrimaryKey(id);
        return water;
    }
    @Override
    public Health queryHealthOne(Integer id) {
        Health health = healthMapper.selectByPrimaryKey(id);
        return health;
    }

    @Override
    public Visitor queryVisitorOne(Integer id) {
        Visitor visitor = visitorMapper.selectByPrimaryKey(id);
        return visitor;
    }

    @Override
    public RoomPunish queryRoomPunishOne(Integer id) {
        RoomPunish roomPunish = roomPunishMapper.selectByPrimaryKey(id);
        return roomPunish;
    }
    @Override
    public Fees queryFeesOne(Integer id) {
        Fees fees = feesMapper.selectByPrimaryKey(id);
        return fees;
    }

    @Override
    public Boolean updStuPunish(Punish punish) {
        int i = punishMapper.updateByPrimaryKeySelective(punish);
        if(i>0)
            return true;
        return false;
    }
    @Override
    public Boolean updFees(Fees fees) {
        int i = feesMapper.updateByPrimaryKeySelective(fees);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updWater(Water water) {
        int i = waterMapper.updateByPrimaryKeySelective(water);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updRoomPunish(RoomPunish roomPunish) {
        int i = roomPunishMapper.updateByPrimaryKeySelective(roomPunish);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updHealth(Health health) {
        int i = healthMapper.updateByPrimaryKeySelective(health);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updVisitor(Visitor visitor) {
        int i = visitorMapper.updateByPrimaryKeySelective(visitor);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updRepair(Repair repair) {
        int i = repairMapper.updateByPrimaryKeySelective(repair);
        if(i>0)
            return true;
        return false;
    }

    @Override
    public Boolean updNotice(Notice notice) {
        int i = noticeMapper.updateByPrimaryKeySelective(notice);
        if(i>0)
            return true;
        return false;
    }
}
