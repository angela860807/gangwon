package com.tour.gangwon.controller;

import com.tour.gangwon.dto.LoginDto;
import com.tour.gangwon.dto.MemberFormDto;
import com.tour.gangwon.entity.Member;
import com.tour.gangwon.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto dto,
                        HttpSession session,
                        Model model) {
        try {
            Member member = memberService.login(dto.getUserId(), dto.getPassword());
            session.setAttribute("loginMember", member);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("errorMsg", e.getMessage());
            return "member/login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    // 약관 페이지
    @GetMapping("/terms")
    public String termsForm() {
        return "member/terms";
    }

    // 회원가입 폼
    @GetMapping("/register")
    public String registerForm() {
        return "member/register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@Valid MemberFormDto dto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            return "member/register";
        }
        if (memberService.existsByUserId(dto.getUserId())) {
            model.addAttribute("errorMsg", "이미 사용 중인 아이디입니다.");
            return "member/register";
        }
        if (memberService.existsByEmail(dto.getEmail())) {
            model.addAttribute("errorMsg", "이미 사용 중인 이메일입니다.");
            return "member/register";
        }

        memberService.save(dto);
        return "redirect:/member/login?registered";
    }

    // Ajax 아이디 중복 체크
    @GetMapping("/check-userid")
    @ResponseBody
    public String checkUserId(@RequestParam String userId) {
        return memberService.existsByUserId(userId) ? "duplicated" : "available";
    }
}
