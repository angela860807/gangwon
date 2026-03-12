package com.tour.gangwon.service;

import com.tour.gangwon.entity.Notice;
import com.tour.gangwon.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public Page<Notice> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return noticeRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Notice> search(String type, String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return switch (type) {
            case "content" -> noticeRepository.findByContentContainingIgnoreCase(keyword, pageable);
            case "writer"  -> noticeRepository.findByWriterContainingIgnoreCase(keyword, pageable);
            default        -> noticeRepository.findByTitleContainingIgnoreCase(keyword, pageable);
        };
    }

    @Transactional(readOnly = true)
    public Notice findById(Long id) {
        return noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
    }

    public Notice findByIdAndIncreaseView(Long id) {
        Notice notice = findById(id);
        notice.setViewCount(notice.getViewCount() + 1);
        return noticeRepository.save(notice);
    }

    public Notice save(String title, String content, String writer) {
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
        return noticeRepository.save(notice);
    }

    public void update(Long id, String title, String content) {
        Notice notice = findById(id);
        notice.setTitle(title);
        notice.setContent(content);
        noticeRepository.save(notice);
    }

    public void delete(Long id) {
        noticeRepository.deleteById(id);
    }
}
