package org.zerock.guestbook.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.guestbook.entity.Guestbook;
import org.zerock.guestbook.entity.QGuestbook;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@SpringBootTest
class GuestbookRepositoryTest {

	@Autowired
	private GuestbookRepository guestbookRepository;
	
//	@Test
	void  insertDummies() {
		IntStream.rangeClosed(1, 300).forEach(i -> {
			
			Guestbook guestbook = Guestbook.builder()
					.title("Title..." + i)
					.content("Content..." + i)
					.writer("user" + (i%10))
					.build();
			System.out.println(guestbookRepository.save(guestbook));
		});
	}

	// @Test
	public void updateTest() {
		Optional<Guestbook> result = guestbookRepository.findById(300L);
		
		result.ifPresent(guestbook -> {
			guestbook.changeTitle("Changed Title!");
			guestbook.changeContent("Changed Content!");
			
			guestbookRepository.save(guestbook);
		});
	}

	@Test
	public void test_페이지_처리와_동시에_검색_처리() {
		Pageable pageable = PageRequest.of(0, 10, Sort.by("gno").descending());
		QGuestbook qGuestbook = QGuestbook.guestbook; // 1. Q도메인 클래스를 이용하면 엔티티 클래스의 필드를 변수로 활용할 수 있다.
		
		String keyword = "1";
		BooleanBuilder builder = new BooleanBuilder();
		BooleanExpression expression = qGuestbook.title.contains(keyword); // com.querydsl.core.types.Predicate 타입이어야 함 (java X)
		builder.and(expression);

		Page<Guestbook> result = guestbookRepository.findAll(builder, pageable); // QuerydslPredicateExcuter의 findAll()

		result.stream().forEach(guestbook -> {
			System.out.println(guestbook);
		});
	}
	
}
