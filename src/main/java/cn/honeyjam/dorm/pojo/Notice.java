package cn.honeyjam.dorm.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Table(name = "tb_notice")
public class Notice {
    @Id
    private Integer id;

    private Date noticeTime;

    private Integer dormId;

    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", noticeTime=" + noticeTime +
                ", dormId=" + dormId +
                ", noticePeoper='" + noticePeoper + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public Integer getDormId() {
        return dormId;
    }

    public void setDormId(Integer dormId) {
        this.dormId = dormId;
    }

    private String noticePeoper;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getNoticeTime() {
        return noticeTime;
    }

    public void setNoticeTime(Date noticeTime) {
        this.noticeTime = noticeTime;
    }

    public String getNoticePeoper() {
        return noticePeoper;
    }

    public void setNoticePeoper(String noticePeoper) {
        this.noticePeoper = noticePeoper == null ? null : noticePeoper.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}