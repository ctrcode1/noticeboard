package com.rsupport.pretest.model.dto;

import java.time.format.DateTimeFormatter;

import com.rsupport.pretest.model.entity.NoticeBoardEntity;

public class NoticeList {
    private Integer noticeId;
    private String title;
    private String updatedBy;
    private String updated;
    
    public NoticeList() {
    }
    public NoticeList(NoticeBoardEntity noticeBoardEntity) {
        this.noticeId = noticeBoardEntity.getNoticeId();
        this.title = noticeBoardEntity.getTitle();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.updated = noticeBoardEntity.getUpdated().format(formatter);;
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
