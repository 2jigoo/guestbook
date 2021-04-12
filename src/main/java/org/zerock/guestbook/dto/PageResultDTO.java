package org.zerock.guestbook.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

@Data
public class PageResultDTO<DTO, ENTITY> {
    
    private List<DTO> dtoList;

    private int totalPage; // 총 페이지 번호
    private int page; // 현재 페이지 번호
    private int size; // 목록 사이즈
    private int start, end; // 시작,끝 페이지 번호
    private boolean prev, next; // 이전, 다음 존재 여부
    private List<Integer> pageList; // 페이지 번호 목록


    public PageResultDTO(Page<ENTITY> result, Function<ENTITY, DTO> fn) {
        // Page<ENTITY> 객체를 DTO로 변환하는 기능 필요 -> Function<ENTITY, DTO>.
        // 서비스 계층의 entityToDTO와 다른 메서드.
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }


    private void makePageList(Pageable pageable) {
        this.page = pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize();

        //temp end page
        int tempEnd = (int)(Math.ceil(page/10.0)) * 10;

        start = tempEnd - 9;
        prev = start > 1;
        end = totalPage > tempEnd ? tempEnd : totalPage;
        next = totalPage > tempEnd;
        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
