package com.ohgiraffers.section01.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestAnnotationTests {

//    Junit Annotation  사용하기

    public TestAnnotationTests() {}
//    public  TestAnnotationTests(int value) {}

    @Test
    @DisplayName("테스트 메소드 1")
    public void testMethod1() {

    }

    @Test
//    @DisplayName("displayName 우선순위 테스트")
    public void 테스트_메소드2(){

    }

}
