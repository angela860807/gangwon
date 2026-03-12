package com.tour.gangwon.service;

import com.tour.gangwon.dto.MemberFormDto;
import com.tour.gangwon.entity.Member;
import com.tour.gangwon.entity.MemberRole;
import com.tour.gangwon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    public Long save(MemberFormDto dto) {
        memberRepository.findByUserId(dto.getUserId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 아이디입니다.");
                });

        Member member = Member.builder()
                .userId(dto.getUserId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .role(MemberRole.USER)
                .build();

        return memberRepository.save(member).getId();
    }

    // 로그인 검증
    @Transactional(readOnly = true)
    public Member login(String userId, String password) {
        Member member = memberRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("아이디가 없습니다."));

        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    // 아이디 중복 체크
    @Transactional(readOnly = true)
    public boolean existsByUserId(String userId) {
        return memberRepository.existsByUserId(userId);
    }

    // 이메일 중복 체크
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
