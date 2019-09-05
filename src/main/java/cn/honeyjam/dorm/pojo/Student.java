package cn.honeyjam.dorm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Table(name = "tb_stu")
public class Student {


    private String stuName;
    private Integer age;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Transient
    private String className;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    @Transient
    private String collegeName;

    @Override
    public String toString() {
        return "Student{" +
                "stuName='" + stuName + '\'' +
                ", age=" + age +
                ", id=" + id +
                ", className='" + className + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", stayList=" + stayList +
                ", sex='" + sex + '\'' +
                ", classId=" + classId +
                ", collegeId=" + collegeId +
                ", stuPhone='" + stuPhone + '\'' +
                ", address='" + address + '\'' +
                ", roomId='" + roomId + '\'' +
                ", bed=" + bed +
                ", checkin=" + checkin +
                ", identity='" + identity + '\'' +
                ", password='" + password + '\'' +
                ", punishes=" + punishes +
                ", feesList=" + feesList +
                ", sign='" + sign + '\'' +
                '}';
    }

    public List<Stay> getStayList() {
        return stayList;
    }

    public void setStayList(List<Stay> stayList) {
        this.stayList = stayList;
    }

    @Transient
    private List<Stay> stayList;

    private String sex;

    private Integer classId;

    private Integer collegeId;

    private String stuPhone;

    private String address;

    private String roomId;

    private Integer bed;

    private Date checkin;

    private String identity;
    @JsonIgnore
    private String password;

    @Transient
    private List<Punish> punishes;

    @Transient
    private List<Fees> feesList;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Transient
    private String sign;
    public List<Punish> getPunishes() {
        return punishes;
    }

    public void setPunishes(List<Punish> punishes) {
        this.punishes = punishes;
    }

    public List<Fees> getFeesList() {
        return feesList;
    }

    public void setFeesList(List<Fees> feesList) {
        this.feesList = feesList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName == null ? null : stuName.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone == null ? null : stuPhone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}