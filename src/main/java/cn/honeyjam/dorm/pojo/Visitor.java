package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_visitor")
public class Visitor {
    @Id
    private Integer id;

    private String visitName;

    private String visitPhone;

    private Integer dormId;

    private String reason;

    private String remark;

    @Override
    public String toString() {
        return "Visitor{" +
                "id=" + id +
                ", visitName='" + visitName + '\'' +
                ", visitPhone='" + visitPhone + '\'' +
                ", dormId=" + dormId +
                ", reason='" + reason + '\'' +
                ", remark='" + remark + '\'' +
                ", visitTime=" + visitTime +
                ", leaveTime=" + leaveTime +
                ", manageName='" + manageName + '\'' +
                '}';
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    private Date visitTime;

    private Date leaveTime;

    private String manageName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVisitName() {
        return visitName;
    }

    public void setVisitName(String visitName) {
        this.visitName = visitName == null ? null : visitName.trim();
    }

    public String getVisitPhone() {
        return visitPhone;
    }

    public void setVisitPhone(String visitPhone) {
        this.visitPhone = visitPhone == null ? null : visitPhone.trim();
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    public String getManageName() {
        return manageName;
    }

    public void setManageName(String manageName) {
        this.manageName = manageName == null ? null : manageName.trim();
    }
}