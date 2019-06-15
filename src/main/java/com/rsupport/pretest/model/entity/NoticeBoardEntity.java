package com.rsupport.pretest.model.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.Valid;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rsupport.pretest.model.dto.Notice;

@Entity(name = "NOTICE_BOARD")
public class NoticeBoardEntity {
    @Id
    @Column(name = "NOTICE_ID")
    @GeneratedValue(strategy  = GenerationType.IDENTITY)
    private Integer noticeId;
    
    @Column(name="TITLE")
    private String title;
    
    @Column(name = "CONTENT")
    private String content;
    
    @Column(name = "CREATED_BY")
    private String createdBy;
    
    @Column(name = "created")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime created;
    
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    
    @Column(name = "updated")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime updated;

    public NoticeBoardEntity() {
    }

    public NoticeBoardEntity(Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.createdBy = notice.getCreatedBy();
        this.updatedBy = notice.getCreatedBy();
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

	public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
    
    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.created = now;
        this.updated = now;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.updated = LocalDateTime.now();
    }

    public void updateNotice(@Valid Notice notice) {
        this.title = notice.getTitle();
        this.content = notice.getContent();
        this.updatedBy = notice.getUpdatedBy();
    }

}
