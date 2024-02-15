package com.ohgiraffers.section03.additional;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdditionalAnnotationTests {

    // 해당 테스트를 무시할 때 사용하는 어노테이션
    @Disabled
    @Test
    public void testIgnore() {}

    // 주어진 시간 안에 테스트가 끝나지 않으면 실패 처리
    // value: 시간(정수형), unit: 시간 단위
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(900);
    }

    // @Tag 어노테이션으로 테스트 필터링
    // 공백이나 null이 있으면 안되며, 특수문자 : , ( ) & ! | 포함 불가능
    @Test
    @Tag("development")
    public void testTag1() {}

    @Test
    @Tag("production")
    public void testTag2() {}

    @Test
    @Tags(value = {@Tag("development"), @Tag("performance")})
    public void testTag3() {}



    // 순서 지정 어노테이션
    // 클래스 레벨에 @TestMethodOrder(MethodOrderer.OrderAnnotation.class) 어노테이션 추가 후 각 테스트 메소드에 @Order 로 순서 지정
    // 테스트 메소드 순서는 DisplayName, MethodName, OrderAnnotation, Random
    @Test
    @Order(1)
    public void testFirst() {}

    @Test
    @Order(2)
    public void testSecond() {}

    @Test
    @Order(3)
    public void testThird() {}


}
