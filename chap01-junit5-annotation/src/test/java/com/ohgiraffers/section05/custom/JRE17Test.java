package com.ohgiraffers.section05.custom;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.condition.EnabledOnJre;
import org.junit.jupiter.api.condition.JRE;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@EnabledOnJre(value = JRE.JAVA_17, disabledReason = "JRE 17 환경에서만 테스트합니다.")
@Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
@Test
public @interface JRE17Test {
}
