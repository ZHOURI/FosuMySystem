package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_health")
public class Health {
    @Id
    private Integer id;

    private String roomId;

    @Override
    public String toString() {
        return "Health{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", checktime=" + checktime +
                ", checkresult=" + checkresult +
                ", remark='" + remark + '\'' +
                '}';
    }

    private Date checktime;

    private String checkresult;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public Date getChecktime() {
        return checktime;
    }

    public void setChecktime(Date checktime) {
        this.checktime = checktime;
    }


    public String getCheckresult() {
        return checkresult;
    }

    public void setCheckresult(String checkresult) {
        this.checkresult = checkresult;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}