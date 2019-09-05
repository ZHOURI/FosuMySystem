package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_water_elec")
public class Water {
    @Id
    private Integer id;
    private String roomId;
    private Date paytime;

    @Override
    public String toString() {
        return "Water{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", paytime=" + paytime +
                ", lastTime=" + lastTime +
                ", nowTime=" + nowTime +
                ", water=" + water +
                ", electric=" + electric +
                ", pay=" + pay +
                ", paystatus='" + paystatus + '\'' +
                '}';
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    private Date lastTime;

    private Date nowTime;

    private Float water;

    private Float electric;

    private Float pay;

    private String paystatus;

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

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getNowTime() {
        return nowTime;
    }

    public void setNowTime(Date nowTime) {
        this.nowTime = nowTime;
    }

    public Float getWater() {
        return water;
    }

    public void setWater(Float water) {
        this.water = water;
    }

    public Float getElectric() {
        return electric;
    }

    public void setElectric(Float electric) {
        this.electric = electric;
    }

    public Float getPay() {
        return pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }
}