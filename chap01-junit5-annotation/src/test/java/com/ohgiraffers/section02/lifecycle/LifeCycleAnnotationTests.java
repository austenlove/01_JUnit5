package com.ohgiraffers.section02.lifecycle;

import org.junit.jupiter.api.*;

public class LifeCycleAnnotationTests {

    //  테스트 메소드의 실행 전 후에 동작하는 어노테이션

    // 테스트 실행 직전 딱 한번만 동작하는 어노테이션
    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }


    // 각 테스트 메소드가 실행되기 전 동작
    // 테스트 하기 전 주로 목업 데이터 세팅용으로 사용A
    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }

    @Test
    public void test1() {
        System.out.println("Test1");
    }

    @Test
    public void test2() {
        System.out.println("Test2");
    }


    // 테스트 메소드 실행 직후 동작하는 어노테이션
    // 모든 테스트 수행 후 사용한 자원 해제 목적으로 사용

    @AfterEach
    public void afterEach () {
        System.out.println("afterEach");
    }

    // 모든 단위테스트가 완전히 끝난 후 딱 한번만 실행
    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }
}
