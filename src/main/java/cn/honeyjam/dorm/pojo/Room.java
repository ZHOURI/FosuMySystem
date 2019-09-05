package cn.honeyjam.dorm.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Table(name = "tb_room")
public class Room {
    @Id
    private String roomId;

    private String members;

    private Integer count;
    @Transient
    private List<Water> waterList;
    @Transient
    private List<Health> healthList;
    @Transient
    private String dormName;
    @Transient
    private String schoolName;

    @Override
    public String toString() {
        return "Room{" +
                "roomId='" + roomId + '\'' +
                ", members='" + members + '\'' +
                ", count=" + count +
                ", waterList=" + waterList +
                ", healthList=" + healthList +
                ", dormName='" + dormName + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", repairList=" + repairList +
                ", roomPunishList=" + roomPunishList +
                ", dormId=" + dormId +
                ", schoolId=" + schoolId +
                '}';
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public List<Repair> getRepairList() {
        return repairList;
    }

    public void setRepairList(List<Repair> repairList) {
        this.repairList = repairList;
    }

    @Transient
    private List<Repair> repairList;

    public List<RoomPunish> getRoomPunishList() {
        return roomPunishList;
    }

    public void setRoomPunishList(List<RoomPunish> roomPunishList) {
        this.roomPunishList = roomPunishList;
    }

    @Transient
    private List<RoomPunish> roomPunishList;

    public List<Water> getWaterList() {
        return waterList;
    }

    public void setWaterList(List<Water> waterList) {
        this.waterList = waterList;
    }

    public List<Health> getHealthList() {
        return healthList;
    }

    public void setHealthList(List<Health> healthList) {
        this.healthList = healthList;
    }




    private Integer dormId;

    private Integer schoolId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members == null ? null : members.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}