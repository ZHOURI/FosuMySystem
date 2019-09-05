package cn.honeyjam.dorm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Table(name = "tb_punish")
@JsonIgnoreProperties(ignoreUnknown = true)//忽略未知属性
public class Punish {
    @Id
    private Integer id;

    private Long stuId;

    private String sign;

    private String type;

    private Date createtime;
    @Temporal(TemporalType.TIME)
    private Date punishtime;

    @Override
    public String toString() {
        return "Punish{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", sign='" + sign + '\'' +
                ", type='" + type + '\'' +
                ", createtime=" + createtime +
                ", punishtime=" + punishtime +
                ", comments='" + comments + '\'' +
                '}';
    }

    public Date getPunishtime() {
        return punishtime;
    }

    public void setPunishtime(Date punishtime) {
        this.punishtime = punishtime;
    }

    private String comments;

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

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign == null ? null : sign.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Punish() {
    }
}