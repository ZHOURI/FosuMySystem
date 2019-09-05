package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_fees")
public class Fees {
    @Id
    private Integer id;

    private Long stuId;

    @Override
    public String toString() {
        return "Fees{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", years=" + years +
                ", fee=" + fee +
                ", paystatus='" + paystatus + '\'' +
                ", paytime=" + paytime +
                '}';
    }

    private Integer years;

    private Float fee;

    private String paystatus;

    private Date paytime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Float getFee() {
        return fee;
    }

    public void setFee(Float fee) {
        this.fee = fee;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }
}