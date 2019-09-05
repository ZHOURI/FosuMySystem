package cn.honeyjam.dorm.mapper;

import cn.honeyjam.dorm.pojo.*;

import java.util.List;
import java.util.Map;

import cn.honeyjam.dorm.provide.ManagerProvide;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface ManagerMapper extends Mapper<Manager>
{
    @SelectProvider(type = ManagerProvide.class,method = "queryStudents")
    List<Student> queryStudents(Integer dorm_ID, Map<String,String[]> map);
    @Select("select * from tb_room where dorm_ID=#{dorm_ID}")
    List<Room> queryRoom(Integer dorm_ID, Map<String,String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryWater")
    List<Water> queryWater(Integer dorm_ID, Map<String,String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryHealth")
    List<Health> queryHealth(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryRoomPunish")
    List<RoomPunish> queryRoomPunish(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryFees")
    List<Fees> queryFees(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryPunish")
    List<Punish> queryPunish(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryStay")
    List<Stay> queryStay(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryRepair")
    List<Repair> queryRepair(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryVisitor")
    List<Visitor> queryVisitor(Integer dormId, Map<String, String[]> map);
    @SelectProvider(type = ManagerProvide.class,method = "queryNotice")
    List<Notice> queryNotice(Integer dormId, Map<String, String[]> map);
}