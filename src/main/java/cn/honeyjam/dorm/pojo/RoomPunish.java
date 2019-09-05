package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Table(name = "tb_room_punish")
public class RoomPunish {
    @Id
    private Integer id;
    private String roomId;
    private String type;
    private String comments;
    private Date createtime;

    @Temporal(TemporalType.TIME)
    private Date punishtime;

    @Override
    public String toString() {
        return "RoomPunish{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", type='" + type + '\'' +
                ", comments='" + comments + '\'' +
                ", createtime=" + createtime +
                ", punishtime=" + punishtime +
                '}';
    }

    public Date getPunishtime() {
        return punishtime;
    }

    public void setPunishtime(Date punishtime) {
        this.punishtime = punishtime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }





    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


}
