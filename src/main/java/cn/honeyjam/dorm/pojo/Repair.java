package cn.honeyjam.dorm.pojo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
@Table(name = "tb_repair")
public class Repair {
    @Id
    private Integer id;

    private String roomId;

    private Long workerId;

    private String reporter;

    private Date reporttime;

    private String images;

    private Date repairtime;

    private Float pay;

    private String reason;

    private String remarks;

    private String result;

    private String status;

    @Override
    public String toString() {
        return "Repair{" +
                "id=" + id +
                ", roomId='" + roomId + '\'' +
                ", workerId=" + workerId +
                ", reporter='" + reporter + '\'' +
                ", reporttime=" + reporttime +
                ", images='" + images + '\'' +
                ", repairtime=" + repairtime +
                ", pay=" + pay +
                ", reason='" + reason + '\'' +
                ", remarks='" + remarks + '\'' +
                ", result='" + result + '\'' +
                ", status='" + status + '\'' +
                ", workerName='" + workerName + '\'' +
                '}';
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    @Transient
    private String workerName;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

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

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter == null ? null : reporter.trim();
    }

    public Date getReporttime() {
        return reporttime;
    }

    public void setReporttime(Date reporttime) {
        this.reporttime = reporttime;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images == null ? null : images.trim();
    }

    public Date getRepairtime() {
        return repairtime;
    }

    public void setRepairtime(Date repairtime) {
        this.repairtime = repairtime;
    }

    public Float getPay() {
        return pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }
}