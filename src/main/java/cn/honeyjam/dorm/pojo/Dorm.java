package cn.honeyjam.dorm.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_dorm")
public class Dorm {
    @Id
    private Integer dormId;

    private String dormName;

    private Integer schoolId;

    @Override
    public String toString() {
        return "Dorm{" +
                "dormId=" + dormId +
                ", dormName='" + dormName + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    public String getDormName() {
        return dormName;
    }

    public void setDormName(String dormName) {
        this.dormName = dormName == null ? null : dormName.trim();
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }
}