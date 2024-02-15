package com.ohgiraffers.parameterized.section01.params;

import org.example.parameterized.section01.params.DateValidator;
import org.example.parameterized.section01.params.NumberValidator;
import org.example.parameterized.section01.params.StringValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Month;
import java.util.stream.Stream;

public class ParameterizedTests {

    // junit-jupiter-params 모듈을 이용하여 매개변수화된 테스트 작성
    // 여러 값들을 이용하여 검증해야 하는 경우, 모든 경우의 수만큼 테스트 메소드를 작성해야 하는데
    // 파라미터로 전달할 값의 개수만큼 반복적으로 테스트 메소드 실행
    // parameterized-test는 @Test -> @ParameterizedTest 대체하여 사용
    // given, when, then 패턴에서 given 부분을 효율화 ***


    // 1. @ValueSource 를 이용한 parameter value 목록 지정
    // 한 개의 파라미터로 전달할 값들의 목록 지정
    // 지원타입: short, byte, int, long, float, double, char, java.lang,String, java.lang.Class
    @DisplayName("홀수 짝수 판별 테스트")
    @ParameterizedTest
    @ValueSource(ints = {1, 3, -1, 15, 123})
    void testIsOdd(int number) {

        // when
        boolean result = NumberValidator.isOdd(number);

        // then
        Assertions.assertTrue(result);
    }


    // 2. @NullSource, @EmptySource, @NullAndEmptySource
    // 문자열이나 클래스 타입의 경우 null이나 빈 값을 파라미터로 전달하기 위해 사용되는 어노테이션 (기본 자료형 타입은 null 값을 가질 수 없음)
    // null 값과 empty 값 모두 전달 시 @NullAndEmptySource 이용
    @DisplayName("null값 테스트")
    @ParameterizedTest
    @NullSource
    void testIsNull(String input) {

        // when
        boolean result = StringValidator.isNull(input);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("empty값 테스트")
    @ParameterizedTest
    @EmptySource
    void testIsEmpty(String input) {

        // when
        boolean result = StringValidator.isEmpty(input);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("blank값 테스트")
    @ParameterizedTest
    @NullAndEmptySource
    void testIsBlank(String input) {

        // when
        boolean result = StringValidator.isBlank(input);

        // then
        Assertions.assertTrue(result);
    }


    // 3. @EnumSource 활용하기
    // 열거형에 작성된 타입들을 파라미터로 전달하여 테스트에 활용
    @DisplayName("Month에 정의된 타입들이 1~12월 사이의 범위인지 테스트")
    @ParameterizedTest
    @EnumSource(Month.class)
    void testMonthValueIsCollect(Month month) {

        // when
        boolean result = DateValidator.isCollect(month);

        // then
        Assertions.assertTrue(result);
    }

    @DisplayName("2,4,6,9,11월을 제외한 달이 31일인지 확인하는 테스트")
    @ParameterizedTest
    @EnumSource(
            value = Month.class,
            names = {"FEBRUARY", "APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"},
            mode = EnumSource.Mode.EXCLUDE
    )
    void testHasThirtyOneDaysLong(Month month) {

        // given
        int verifyValue = 31;

        // when
        int actual = DateValidator.getLastDayOf(month);

        // then
        Assertions.assertEquals(actual, verifyValue);
    }


    // 4. @CsvSource, @CsvFileSource 를 이용한 CSV 리터럴
    // 입력값과 예상값을 테스트 메소드에 동시에 전달
    // 매개변수에 값을 전달할 여러 개의 인자 묶음이 필요한데 이 경우 @CsvSource 사용
    @DisplayName("영문자를 대문자로 변경하는지 확인")
    @ParameterizedTest
//    @CsvSource({"test,TEST", "tEst,TEST", "JavaScript,JAVASCRIPT"})
    @CsvSource(value = {"test:TEST", "tEst:TEST", "JavaScript:JAVASCRIPT"}, delimiter = ':')
    void testToUpperCase(String input, String verifyValue) {

        // when
        String actual = input.toUpperCase();

        // then
        Assertions.assertEquals(verifyValue, actual);
    }

    @DisplayName("CSV 테스트 데이터가 테스트에 활용되는지 확인")
    @ParameterizedTest
    @CsvFileSource(resources = "/parameter-test-data.csv", numLinesToSkip = 1)   // CSV 첫줄 무시
    void testToUpperCaseWithCSVFile(String input, String verifyValue) {

        // when
        String actual = input.toUpperCase();

        // then
        Assertions.assertEquals(actual, verifyValue);
    }


    // 5. @MethodSource를 활용한 메소드 인수 활용
    // Stream을 반환하는 메소드를 만들어서 테스트에 활용
    private static Stream<Arguments> providerStringSource() {

        return Stream.of(
                Arguments.of("hello world", "HELLO WORLD"),
                Arguments.of("JavaScript", "JAVASCRIPT"),
                Arguments.of("tEst", "TEST")
        );
    }

    @DisplayName("메소드 소스를 활용한 대문자 변환 테스트")
    @ParameterizedTest
    @MethodSource("providerStringSource")
    void testToUpperCaseWithMethodSource(String input, String verifyValue) {

        // when
        String actual = input.toUpperCase();

        // then
        Assertions.assertEquals(verifyValue, actual);
    }


    // 6. 인수 변환기(암시적 변환과 명시적 변환)
    // JUnit5는 매개변수로 지정된 String -> Enum으로 변환 (기본적으로 제공하는 변환으로, 암시적 변환이라고 함)
    // UUID, LocalDate, LocalDateTime, Year, Month, 파일 및 경로, URL, 열거형 서브클래스 등을 암시적으로 변환
    @DisplayName("암시적 변환 테스트")
    @ParameterizedTest(name = "[{0}] is 30 days long")   // {0}은 첫번째 파라미터를 가리킴
    @CsvSource({"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
    void testAutoConverting(Month month) {

        // given
        int verifyValue = 30;

        // when
        int actual = DateValidator.getLastDayOf(month);

        // then
        Assertions.assertEquals(actual, verifyValue);
    }


}
