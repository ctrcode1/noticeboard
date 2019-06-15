package com.rsupport.pretest.model.dto;

import java.time.format.DateTimeFormatter;

import com.rsupport.pretest.model.entity.NoticeBoardEntity;

public class Notice {
    private Integer noticeId;
    private String title;
    private String content;
    private String createdBy;
    private String created;
    private String updatedBy;
    private String updated;
    
    public Notice() {
    }
    public Notice(NoticeBoardEntity noticeBoardEntity) {
        this.noticeId = noticeBoardEntity.getNoticeId();
        this.title = noticeBoardEntity.getTitle();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.created = noticeBoardEntity.getCreated().format(formatter);
        this.updated = noticeBoardEntity.getUpdated().format(formatter);;
        this.createdBy = noticeBoardEntity.getCreatedBy();
        this.updatedBy = noticeBoardEntity.getUpdatedBy();
    }
    public Integer getNoticeId() {
        return noticeId;
    }
    public void setNoticeId(Integer noticeId) {
        this.noticeId = noticeId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreated() {
        return created;
    }
    public void setCreated(String created) {
        this.created = created;
    }
    public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdated() {
        return updated;
    }
    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
