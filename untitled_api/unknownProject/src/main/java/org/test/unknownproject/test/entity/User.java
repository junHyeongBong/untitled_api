package org.test.unknownproject.test.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder // 선언한 클래스의 빌더 패턴을 가지는 클래스를 생성합니다.
@Entity // User라는 객체와 DB 테이블을 매핑합니다. JPA가 관리합니다.
@Table(name = "user") // 테이블 이름을 지정합니다. 만약에 선언을 안 할 경우 클래스이름으로 설정합니다.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String name;

    private String password;

    private String phoneNumber;


}
