package com.rsupport.pretest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.rsupport.pretest.model.dto.Notice;
import com.rsupport.pretest.model.dto.NoticeList;
import com.rsupport.pretest.model.entity.NoticeBoardEntity;
import com.rsupport.pretest.repository.NoticeBoardRepository;

@Service("noticeBoardService")
public class NoticeBoardServiceImpl {
    
    @Autowired private NoticeBoardRepository noticeBoardRepository;

    public Page<NoticeList> getNotices(Integer pageIndex) {
        PageRequest noticeBoardPage = PageRequest.of(pageIndex, 10, new Sort(Direction.DESC, "updated"));
        Page<NoticeBoardEntity> noticeBoardEntities = this.noticeBoardRepository.findAll(noticeBoardPage);
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
