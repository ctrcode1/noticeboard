package com.rsupport.pretest.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rsupport.pretest.model.dto.Notice;
import com.rsupport.pretest.model.dto.NoticeList;
import com.rsupport.pretest.model.entity.NoticeBoardEntity;
import com.rsupport.pretest.repository.NoticeBoardRepository;
import com.rsupport.pretest.repository.specs.NoticeBoardSpecs;

@Service("noticeBoardService")
public class NoticeBoardServiceImpl {
    
    @Autowired private NoticeBoardRepository noticeBoardRepository;
    
    @PostConstruct
	public void initNoticeBoardService() throws Exception {
    	for(int i = 1 ; i < 35 ; i++) {
    		Notice notice = new Notice();
    		notice.setTitle("테스트 " + i);
    		notice.setContent("이것은 테스트 " + i + " 공지사항 입니다.");
    		notice.setCreatedBy("user" + ((i % 2) + 1));
    		this.postNotices(notice);
    		Thread.sleep(100);
    	}
	}

    public Page<NoticeList> getNotices(Integer pageIndex, String createdBy) {
        PageRequest noticeBoardPage = PageRequest.of(pageIndex, 10, new Sort(Direction.DESC, "updated"));
        Page<NoticeBoardEntity> noticeBoardEntities = null;
    	if((createdBy != null) && (!createdBy.trim().isEmpty())) {
    		Specification<NoticeBoardEntity> noticeBoardSpec = Specification.where(NoticeBoardSpecs.equalTo("createdBy", createdBy));
            noticeBoardEntities = this.noticeBoardRepository.findAll(noticeBoardSpec, noticeBoardPage);
    	} else {
            noticeBoardEntities = this.noticeBoardRepository.findAll(noticeBoardPage);
    	}
        List<NoticeList> notices = new ArrayList<>();
        noticeBoardEntities.getContent().forEach(x -> notices.add(new NoticeList(x)));

        return new PageImpl<NoticeList>(notices, noticeBoardPage, noticeBoardEntities.getTotalElements());
    }

    public NoticeBoardEntity getNotice(Integer noticeId) {
        return this.noticeBoardRepository.findById(noticeId).orElse(null);
    }

    public Integer deleteNotices(Integer noticeId) {
        NoticeBoardEntity notice = this.noticeBoardRepository.findById(noticeId).orElse(null);
        if(notice != null) {
            this.noticeBoardRepository.delete(notice);
        }
        return noticeId;
    }

    public NoticeBoardEntity putNotice(Notice notice) {
        NoticeBoardEntity oldNotice = this.noticeBoardRepository.findById(notice.getNoticeId()).orElse(null);
        if(oldNotice != null) {
        	oldNotice.updateNotice(notice);
        } else {
        	oldNotice = new NoticeBoardEntity(notice);
        }
        oldNotice = this.noticeBoardRepository.save(oldNotice);
        return oldNotice;
    }

    public NoticeBoardEntity postNotices(Notice notice) {
        NoticeBoardEntity newNotice = new NoticeBoardEntity(notice);
        newNotice = this.noticeBoardRepository.save(newNotice);
        return newNotice;
    }
}
