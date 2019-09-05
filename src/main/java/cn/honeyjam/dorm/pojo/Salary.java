package cn.honeyjam.dorm.pojo;

import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_salary")
public class Salary {
    private Integer id;

    private Long workId;

    private String sign;

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", workId=" + workId +
                ", sign='" + sign + '\'' +
                ", years=" + years +
                ", months=" + months +
                ", basepay=" + basepay +
                ", workdays=" + workdays +
                ", absencedays=" + absencedays +
                ", allowance=" + allowance +
                ", deserved=" + deserved +
                ", paytime=" + paytime +
                ", paystatus='" + paystatus + '\'' +
                '}';
    }

    private Integer years;

    private Integer months;

    private Float basepay;

    private Integer workdays;

    private Integer absencedays;

    private Float allowance;

    private Float deserved;

    private Date paytime;

    private String paystatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getWorkId() {
        return workId;
    }

    public void setWorkId(Long workId) {
        this.workId = workId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Float getBasepay() {
        return basepay;
    }

    public void setBasepay(Float basepay) {
        this.basepay = basepay;
    }

    public Integer getWorkdays() {
        return workdays;
    }

    public void setWorkdays(Integer workdays) {
        this.workdays = workdays;
    }

    public Integer getAbsencedays() {
        return absencedays;
    }

    public void setAbsencedays(Integer absencedays) {
        this.absencedays = absencedays;
    }

    public Float getAllowance() {
        return allowance;
    }

    public void setAllowance(Float allowance) {
        this.allowance = allowance;
    }

    public Float getDeserved() {
        return deserved;
    }

    public void setDeserved(Float deserved) {
        this.deserved = deserved;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public String getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(String paystatus) {
        this.paystatus = paystatus == null ? null : paystatus.trim();
    }
}