package org.zerock.guestbook.service;

import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

public interface GuestbookService {
    
    // Dto를 Entity로 변환
    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                                .gno(dto.getGno())
                                .title(dto.getTitle())
                                .content(dto.getContent())
                                .writer(dto.getWriter())
                                .build();
        
        return entity;
    }
    
    // Entity를 DTO로 변환
    default GuestbookDTO entityToDTO(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                                    .gno(entity.getGno())
                                    .title(entity.getTitle())
                                    .content(entity.getContent())
                                    .writer(entity.getWriter())
                                    .regDate(entity.getRegDate())
                                    .modDate(entity.getModDate())
                                    .build();
        return dto;
    }
    
    // 페이징된 목록을 얻는 메서드
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    Long register(GuestbookDTO dto);

    GuestbookDTO read(Long gno);
    void modify(GuestbookDTO dto);
    void remove(Long gno);

    
}
