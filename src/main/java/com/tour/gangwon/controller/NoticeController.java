package com.tour.gangwon.controller;

import com.tour.gangwon.entity.Member;
import com.tour.gangwon.entity.Notice;
import com.tour.gangwon.service.NoticeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {

    private final NoticeService noticeService;

    //목록
    @GetMapping("/list")
    public String list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "title") String type,
            Model model) {

        Page<Notice> noticePage;
        if (keyword.isBlank()) {
            noticePage = noticeService.findAll(page, 10);
        } else {
            noticePage = noticeService.search(type, keyword, page, 10);
        }

        model.addAttribute("notices", noticePage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("type", type);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", noticePage.getTotalPages());
        return "notice/list";
    }

    //상세보기
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        Notice notice = noticeService.findByIdAndIncreaseView(id);
        model.addAttribute("notice", notice);
        return "notice/detail";
    }

    //글쓰기 폼
    @GetMapping("/new")
    public String writeForm() {
        return "notice/form";
    }

    //글쓰기 처리
    @PostMapping("/new")
    public String write(
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session) {
        Member loginMember = (Member) session.getAttribute("loginMember");
        noticeService.save(title, content, loginMember.getName());
        return "redirect:/notice/list";
    }

    //수정 폼
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Notice notice = noticeService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (!notice.getWriter().equals(loginMember.getName())) {
            return "redirect:/notice/detail/" + id;
        }
        model.addAttribute("notice", notice);
        model.addAttribute("mode", "edit");
        return "notice/form";
    }

    //수정 처리
    @PostMapping("/edit/{id}")
    public String edit(
            @PathVariable("id") Long id,
            @RequestParam String title,
            @RequestParam String content,
            HttpSession session) {
        Notice notice = noticeService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (notice.getWriter().equals(loginMember.getName())) {
            noticeService.update(id, title, content);
        }
        return "redirect:/notice/detail/" + id;
    }

    //삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, HttpSession session) {
        Notice notice = noticeService.findById(id);
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (notice.getWriter().equals(loginMember.getName())) {
            noticeService.delete(id);
        }
        return "redirect:/notice/list";
    }
}
