package com.tour.gangwon.repository;

import com.tour.gangwon.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Page<Notice> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Notice> findByContentContainingIgnoreCase(String keyword, Pageable pageable);
    Page<Notice> findByWriterContainingIgnoreCase(String keyword, Pageable pageable);
}
