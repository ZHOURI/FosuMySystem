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
        delSelect(map.get("uids"),"student");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Student> studentList = null;
        studentList = managerMapper.queryStudents(manager.getDormId(), map);
        if(studentList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            studentList = managerMapper.queryStudents(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
        Page<Student> pageInfo = (Page<Student>)studentList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
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
        delSelect(map.get("uids"),"notice");
        List<Room> roomList = null;
        roomList = roomMapper.selectByExample(example);
        if(roomList.size()<=0)
        {
            roomList = roomMapper.selectByExample(example);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"water");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Water> waterList = null;
        waterList = managerMapper.queryWater(manager.getDormId(), map);
        if(waterList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            waterList = managerMapper.queryWater(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"health");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Health> healthList = null;
        healthList = managerMapper.queryHealth(manager.getDormId(), map);
        if(healthList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            healthList = managerMapper.queryHealth(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"fees");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Fees> feesList = null;
         feesList = managerMapper.queryFees(manager.getDormId(), map);
        if(feesList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            feesList = managerMapper.queryFees(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"punish");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Punish> punishList = null;
        punishList = managerMapper.queryPunish(manager.getDormId(), map);
        if(punishList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            punishList = managerMapper.queryPunish(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"stay");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Stay> stayList = null;
        stayList = managerMapper.queryStay(manager.getDormId(), map);
        if(stayList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            stayList = managerMapper.queryStay(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"repair");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Repair> repairList = null;
        repairList = managerMapper.queryRepair(manager.getDormId(), map);
        if(repairList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            repairList = managerMapper.queryRepair(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"visitor");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Visitor> visitorList = null;
        visitorList = managerMapper.queryVisitor(manager.getDormId(), map);
        if(visitorList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            visitorList = managerMapper.queryVisitor(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
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
        delSelect(map.get("uids"),"notice");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<Notice> noticeList = null;
        noticeList = managerMapper.queryNotice(manager.getDormId(), map);
        if(noticeList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            noticeList = managerMapper.queryNotice(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
        Page<Notice> pageInfo = (Page<Notice>)noticeList;
        return new PageResult<>(pageInfo.getTotal(),Long.parseLong(String.valueOf(pageInfo.getPages())),pageInfo.getResult(),currentPage,rows);
    }
        private void delSelect(String[] uids,String name) {
        if(uids==null||uids.equals(""))
        {
            return;
        }
        for(int i=0;i<uids.length;i++)
        {
            int uid = Integer.parseInt(uids[i]);
            switch (name)
            {
                case "notice":delNotice(uid);break;
                case "punish":delStuPunish(uid);break;
                case "roomPunish":delRoomPunish(uid);break;
                case "health":delHealth(uid);break;
                case "fees":delStuFees(uid);break;
                case "water":delWater(uid);break;
                case "repair":delRepair(uid);break;
                case "visitor":delVisitor(uid);break;
                case "stay":delStay(uid);break;
                default:
                    System.out.println("这是default");
                    break;
            }
        }
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
        delSelect(map.get("uids"),"roomPunish");
        PageHelper.startPage(currentPage, Math.min(rows, 100));
        List<RoomPunish> roomPunishList = null;
        roomPunishList = managerMapper.queryRoomPunish(manager.getDormId(), map);
        if(roomPunishList.size()<=0)
        {
            PageHelper.startPage(currentPage-1, Math.min(rows, 100));
            roomPunishList = managerMapper.queryRoomPunish(manager.getDormId(), map);
            currentPage = currentPage-1;
        }
        Page<RoomPunish> pageInfo = (Page<RoomPunish>)roomPunishList;
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

    public Boolean delRepair(Integer id) {
        int i = repairMapper.deleteByPrimaryKey(id);
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
        try {
            int i = punishMapper.updateByPrimaryKeySelective(punish);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
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
        try {
            int i = waterMapper.updateByPrimaryKeySelective(water);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean updRoomPunish(RoomPunish roomPunish) {
        try {
            int i = roomPunishMapper.updateByPrimaryKeySelective(roomPunish);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean updHealth(Health health) {
        try {
            int i = healthMapper.updateByPrimaryKeySelective(health);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean updVisitor(Visitor visitor) {
        try {
            int i = visitorMapper.updateByPrimaryKeySelective(visitor);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean updRepair(Repair repair) {
        try {
            int i = repairMapper.updateByPrimaryKeySelective(repair);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean updNotice(Notice notice) {
        try {
            int i = noticeMapper.updateByPrimaryKeySelective(notice);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean addNotice(Notice notice,Integer dormId) {
        notice.setDormId(dormId);
        try {
            int i = noticeMapper.insert(notice);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    @Override
    public Boolean addFees(Fees fees) {
        try {
            int i = feesMapper.insert(fees);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
    @Override
    public Boolean addHealth(Health health) {
        try {
            int i = healthMapper.insert(health);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean addVisitor(Visitor visitor,Integer dormId) {
        visitor.setDormId(dormId);
        try {
            int i = visitorMapper.insert(visitor);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public Boolean addWater(Water water) {
        try {
            int i = waterMapper.insert(water);
            if(i>0)
                return true;
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
