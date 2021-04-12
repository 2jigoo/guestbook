package org.zerock.guestbook.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@MappedSuperclass // 테이블 생성X (이 클래스를 상속받은 엔티티가 테이블이 생성이 되도록)
@EntityListeners(value = {AuditingEntityListener.class}) // 엔티티 객체가 생성/변경되는 것을 감지 -> regdate, moddate
@Getter
public abstract class BaseEntity {

	
	@CreatedDate
	@Column(name = "regdate", updatable = false) // 객체를 db에 반영할 때 regdate 컬럼 값은 변경되지 않음. (등록일시는 최초 이후로는 바뀌지 않으니까)
	private LocalDateTime regDate;
	
	@LastModifiedDate // 최종수정시간 자동처리
	@Column(name = "moddate")
	private LocalDateTime modDate;
}
