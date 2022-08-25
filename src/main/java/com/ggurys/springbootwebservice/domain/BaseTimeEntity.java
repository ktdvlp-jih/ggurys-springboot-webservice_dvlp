package com.ggurys.springbootwebservice.domain;

/*
 * JPA Auditing으로 생성/수정시간 자동화
 *  BaseTimeEntity는 모든 Entity의 상위 클래스가 되서 createdDate,modifiedDate를 자동관리하는 역할
 * */

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // 1. Entity클래스들이 BaseTimeEntity를 상속할 경우 필드에 있는 createdDate,modifiedDate도 칼럼으로 본인의 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class) // 2. BaseTimeEntity클래스에 Auditing기능을 포함시킴
public class BaseTimeEntity {

    @CreatedDate // 3. Entitiy가 생성되어 저장될때 시간이 나중 저장
    private LocalDateTime createdDate; 

    @LastModifiedDate // 4. 조회한 Entitiy의 값이 변경할때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
