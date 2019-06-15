package com.rsupport.pretest.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rsupport.pretest.model.dto.Notice;
import com.rsupport.pretest.model.dto.NoticeList;
import com.rsupport.pretest.model.entity.NoticeBoardEntity;
import com.rsupport.pretest.service.impl.NoticeBoardServiceImpl;

@Controller
@RequestMapping("/noticeboard")
public class NoticeBoardController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired private NoticeBoardServiceImpl noticeBoardService;
    @GetMapping("/all/{pageIndex}")
    public String getNotices(@PathVariable Integer pageIndex, Model model) {
        logger.info("getNotices is called. : {}", pageIndex);
        Page<NoticeList> noticeList = this.noticeBoardService.getNotices(pageIndex);
        model.addAttribute("noticeList", noticeList);
        if (noticeList.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, noticeList.getTotalPages())
                .boxed()
                .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "notice_list";
    }
    
    @GetMapping("/newNoticeForm")
    public String newNoticeForm(@RequestParam Integer prevPageNumber, Model model) {
        logger.info("newNoticeForm is called.");
        model.addAttribute("prevPageNumber", prevPageNumber);
        return "notice_new";
    }

    @GetMapping("/{noticeId}")
    public String getNotice(@PathVariable Integer noticeId, @RequestParam Integer prevPageNumber, Model model) {
        logger.info("getNotice is called. : {}", noticeId);
        NoticeBoardEntity notice = this.noticeBoardService.getNotice(noticeId);
        if(notice == null) {
            notice = new NoticeBoardEntity();
        }
        
        model.addAttribute("notice", notice);
        model.addAttribute("prevPageNumber", prevPageNumber);
        return "notice_detail";
    }
    
    @PostMapping("/createNewNotice")
    public String createNewNotice(@RequestBody Notice notice, Model model) {
        logger.info("createNewNotice is called.");
        return "notice_new";
    }
}
