package com.ohgiraffers.assertions;

import com.ohgiraffers.assertions.section01.jupiter.Calculator;
import com.ohgiraffers.assertions.section01.jupiter.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JupiterAssertionsTests {

    // Junit5(Jupiter) Assertions 메소드 이해
    // 모든 Jupiter Assertions의 메소드는 static 메소드 형태로 제공

    // give : 필요한 파라미터 준비
    // when : 테스트할 메소드 호출
    // then : 실행결과 검증

    // 하나의 테스트 메소드는 한 가지를 검증
    // 테스트할 메소드와 검증 동시 진행 가능 (when & then)


    // 1. assertEquals(expected, actual)
    @Test
    @DisplayName("더하기 기능 테스트")
    void testAssertEqual() {

        // given
        int firstNum = 10;
        int secondNum = 19;
        int expected = 30;

        // when
        Calculator calculator = new Calculator();
        int result = calculator.plusTwoNumbers(firstNum, secondNum);

        // then
//        Assertions.assertEquals(expected, result);

        // delta: 오차 허용 범위
//        Assertions.assertEquals(expected, result, 1);

        // 3번째 인자가 문자열: 테스트 실패 시 보여줄 메시지 작성
        Assertions.assertEquals(expected, result, "실패할 때 보여줄 메시지");

        // 람다를 이용해도 결과는 동일하지만 불필요한 경우 메시지를 만들지 않도록 지연로딩
        Assertions.assertEquals(expected, result, () -> "실패할 때 보여줄 메시지");
    }


    // 2. assertNotEquals(expected, actual)
    @Test
    @DisplayName("인스턴스 동일성 비교 테스트")
    void testAssertNotEqualsWithInstances() {

        // given
        Object obj1 = new Object();

        // when
        Object obj2 = new Object();

        // then
        // equals와 hashcode가 오버라이딩 된 경우는 같다고 판단
        Assertions.assertNotEquals(obj1, obj2);
    }


    // 3. assertNull(actual)
    @Test
    @DisplayName("null 인지 테스트")
    void testAssertNull() {
        // give
        String str;

        // when
        str = "null";

        // then
        Assertions.assertNull(str);
    }


    // 4. assertNotNull(actual)
    @Test
    @DisplayName("null이 아닌지 테스트")
    void testAssertNotNull() {
        // give
        String str;

        // when
        str = "java";

        // then
        Assertions.assertNotNull(str);
    }


    // 5. assertTrue(actual)
    @Test
    @DisplayName("두 값이 같은지 테스트")
    void testAssertTrue() {
        // give
        int num1 = 10;
        int num2 = 10;

        // when
        boolean result = num1 == num2;

        // then
//        Assertions.assertTrue(result);

        Assertions.assertEquals(true, result);   // result가 true 인지 확인
    }


    // 6. assertFalse(actual)
    @Test
    @DisplayName("두 값이 다른지 테스트")
    void testAssertFalse() {
        // give
        int num1 = 10;
        int num2 = 20;

        // when
        boolean result = num1 == num2;

        // then
        Assertions.assertFalse(result);
    }


    // 7. assertAll(Executable)
    // 모든 Assertion이 실행되고 실패가 함께 보고되는 그룹화된 Assertion
    @Test
    @DisplayName("두 값이 다른지 테스트")
    void testAssertAll() {
        // give
        String firstName = "길동";
        String lastName = "홍";

        // when
        Person person = new Person(firstName, lastName);

        // then
        Assertions.assertAll("그룹화된 테스트의 이름(테스트 실패 시 보여짐)",
                () -> Assertions.assertEquals(firstName, person.getFirstName(), () -> "firstName이 잘못됨"),
                () -> Assertions.assertEquals(lastName, person.getLastName(),  () -> "lastName이 잘못됨"));
    }

}
