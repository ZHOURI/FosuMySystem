package cn.honeyjam.dorm.service;

import cn.honeyjam.dorm.pojo.*;

import java.util.List;
import java.util.Map;

public interface ManageService {
    Manager queryOwnInfo(Manager manager);

    Boolean updatePassword(String password, Manager manager);

    PageResult<Student> queryStu(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    Student queryStuInfo(Long id);

    PageResult<Room> queryRoom(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    Room queryRoomInfo(String id);

    PageResult<Water> queryWater(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Health> queryHealth(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<RoomPunish> queryRoomPunish(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    Boolean editStay(Integer id);

    Boolean addStuPunish(Punish punish);

    Boolean editFees(Integer id);

    Boolean delStuPunish(Integer id);

    Boolean addRoomPunish(RoomPunish roomPunish);

    Boolean editWater(Integer id);

    Boolean addRoomHealth(Health health);

    Boolean addRoomWater(Water water);

    Punish queryStuPunishOne(Integer id);

    Boolean updStuPunish(Punish punish);

    PageResult<Fees> queryFees(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Punish> queryPunish(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Stay> queryStay(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    Boolean editRepair(Integer id);

    PageResult<Repair> queryRepair(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Visitor> queryVisitor(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    PageResult<Notice> queryNotice(Manager manager, Integer currentPage, Integer rows, Map<String, String[]> map);

    Boolean delStay(Integer id);

    Boolean delWater(Integer id);

    Boolean delRoomPunish(Integer id);

    Boolean delStuFees(Integer id);

    Boolean delVisitor(Integer id);

    Boolean delHealth(Integer id);

    Fees queryFeesOne(Integer id);

    Water queryWaterOne(Integer id);

    RoomPunish queryRoomPunishOne(Integer id);

    Health queryHealthOne(Integer id);

    Visitor queryVisitorOne(Integer id);

    Boolean updFees(Fees fees);

    Boolean updWater(Water water);

    Boolean updRoomPunish(RoomPunish roomPunish);

    Boolean updHealth(Health health);

    Boolean updVisitor(Visitor visitor);

    Boolean updRepair(Repair repair);

    Boolean updNotice(Notice notice);

    Notice queryNoticeOne(Integer id);

    Boolean delNotice(Integer id);
}
