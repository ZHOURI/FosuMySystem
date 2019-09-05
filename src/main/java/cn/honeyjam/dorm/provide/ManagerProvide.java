package cn.honeyjam.dorm.provide;

import java.util.Map;

public class ManagerProvide {
    public String queryStudents(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select s.*,c.class_name,c1.college_name from tb_stu s,tb_class c,tb_college c1 where s.class_ID=c.class_ID and s.college_ID = c1.college_ID and room_ID in (select room_ID from tb_room where dorm_ID ="+dorm_ID+" )");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryWater(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_water_elec where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" )");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryHealth(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_health where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" )");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryRoomPunish(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_room_punish where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" )");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryFees(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_fees where stu_ID in (select stu_ID from tb_stu where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" ))");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryPunish(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_punish where stu_ID in (select stu_ID from tb_stu where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" ))");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryRepair(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select r.*,w.worker_name from tb_repair r left join tb_worker w on r.worker_ID=w.id where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" )");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryVisitor(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_visitor where dorm_ID="+dorm_ID);
        String sql = common(buffer, map);
        return sql;
    }
    public String queryStay(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_stay where stu_ID in (select stu_ID from tb_stu where room_ID in (select room_ID from tb_room where dorm_ID="+dorm_ID+" ))");
        String sql = common(buffer, map);
        return sql;
    }
    public String queryNotice(Integer dorm_ID, Map<String,String[]> map)
    {
        StringBuffer buffer = new StringBuffer("select * from tb_notice where dorm_ID="+dorm_ID);
        String sql = common(buffer, map);
        return sql;
    }
    public String common(StringBuffer buffer,Map<String,String[]> map)
    {
        for (String key:map.keySet())
        {
            if(key.equals("rows")||key.equals("currentPage")||key.equals("cids"))
            {
                continue;
            }
            String s = map.get(key)[0];
            if (s != null && s!="") {
                buffer.append(" and " + key + " like '%"+s+"%'");
            }
        }
        return buffer.toString();
    }
}
