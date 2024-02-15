package com.ohgiraffers.section05.custom;

import org.junit.jupiter.api.Test;

public class CustomAnnotationTests {

    // 원하는 어노테이션을 조합하여 커스텀 어노테이션을 만들 수 있다.
    @WindowsTest
    public void testWindowsOS () {}

    @DevelopmentTest
    public void testDevelopmentCustomTag () {}

    // JRE17 버전에서 1000 밀리초 안에 테스트가 완료되는지 확인하는 커스텀 어노테이션
    @JRE17Test
    public void testJRE17 () throws InterruptedException {
        Thread.sleep(800);
    }

    // Mac 환경에서 development 태그를 가진 메소드만 테스트
    @OSDevelopmentTest
    public void testOSDevelopment () {}



}
