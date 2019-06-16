package com.rsupport.pretest.controller;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rsupport.pretest.model.dto.Notice;
import com.rsupport.pretest.model.dto.NoticeList;
import com.rsupport.pretest.model.entity.NoticeBoardEntity;
import com.rsupport.pretest.service.impl.NoticeBoardServiceImpl;

@RestController
@RequestMapping("/notice")
public class NoticeBoardApiController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired private NoticeBoardServiceImpl noticeBoardService;

    @GetMapping("/all/{pageIndex}")
    public Page<NoticeList> getNotices(@PathVariable Integer pageIndex, @RequestParam String createdBy) {
        logger.info("getNotices is called. : {}", pageIndex);
        return this.noticeBoardService.getNotices(pageIndex, createdBy);
    }

    @GetMapping("/{noticeId}")
    public NoticeBoardEntity getNotice(@PathVariable Integer noticeId) {
        logger.info("getNotice is called. : {}", noticeId);
        return this.noticeBoardService.getNotice(noticeId);
    }

    @PostMapping("")
    public NoticeBoardEntity postNotices(@Valid @RequestBody Notice notice, Authentication authentication) {
        logger.info("postNotices is called.");
        if((notice.getTitle() == null) || (notice.getTitle().trim().isEmpty())) {
            throw new RuntimeException("제목은 공백이나 Null이 허용되지 않습니다.");
        }
        notice.setCreatedBy(authentication.getName());
        return this.noticeBoardService.postNotices(notice);
    }

    @PutMapping("")
    public NoticeBoardEntity putNotice(@Valid @RequestBody Notice notice, Authentication authentication) {
        logger.info("putNotice is called.");
        if((notice.getTitle() == null) || (notice.getTitle().trim().isEmpty())) {
            throw new RuntimeException("제목은 공백이나 Null이 허용되지 않습니다.");
        }
        notice.setUpdatedBy(authentication.getName());
        return this.noticeBoardService.putNotice(notice);
    }

    @DeleteMapping("/{noticeId}")
    public Integer deleteNotice(@PathVariable Integer noticeId) {
        logger.info("deleteNotice is called. : {}", noticeId);
        return this.noticeBoardService.deleteNotices(noticeId);
    }
}
