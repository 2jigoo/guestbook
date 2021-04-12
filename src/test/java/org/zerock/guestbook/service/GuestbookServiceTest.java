package org.zerock.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.guestbook.dto.GuestbookDTO;
import org.zerock.guestbook.dto.PageRequestDTO;
import org.zerock.guestbook.dto.PageResultDTO;
import org.zerock.guestbook.entity.Guestbook;

@SpringBootTest
public class GuestbookServiceTest {
    
    @Autowired
    private GuestbookService service;

    @Test
    public void testRegister() {
        GuestbookDTO guestbookDTO = GuestbookDTO.builder()
                .title("Sample Title")
                .content("Sample content")
                .writer("user0")
                .build();
        
        System.out.println(service.register(guestbookDTO));
    }

    @Test
    public void testList() {
        PageRequestDTO requestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(requestDTO);

        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL PAGE: " + resultDTO.getTotalPage());

        System.out.println("----------------------------------------------------------------------------------");
        resultDTO.getDtoList().forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------");
        resultDTO.getPageList().forEach(System.out::println);
    }
    
    @Test
    public void test_검색() {
        PageRequestDTO requestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .type("tc")
                .keyword("Sample")
                .build();
        
        PageResultDTO<GuestbookDTO, Guestbook> resultDTO = service.getList(requestDTO);
        
        System.out.println("PREV: " + resultDTO.isPrev());
        System.out.println("NEXT: " + resultDTO.isNext());
        System.out.println("TOTAL PAGE: " + resultDTO.getTotalPage());
    
        System.out.println("----------------------------------------------------------------------------------");
        resultDTO.getDtoList().forEach(System.out::println);
        System.out.println("----------------------------------------------------------------------------------");
        resultDTO.getPageList().forEach(System.out::println);
    }
}
