package com.ohgiraffers.section04.environment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

public class EnvAnnotationTests {

    // OS, JRE에 따라 테스트 수행

    // @EnabledOnOs, @DisabledOnOs
    // 특정 OS 환경에서만 테스트를 진행하도록 세팅 (무시되는 사유를 disabledReason에 기술)
    @Test
    @EnabledOnOs(value = OS.MAC, disabledReason = "맥에서만 테스트합니다.")
    public void testMac() {}

    @Test
    @EnabledOnOs(value = {OS.WINDOWS, OS.LINUX}, disabledReason = "윈도우와 리눅스만 테스트합니다.")
    public void testWindowsAndLinux() {}

    @Test
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "윈도우에서는 무시합니다.")
    public void testDisabledOnWindows() {}


    // @EnabledOnJre, @DisabledOnJre, @EnabledForJreRange(min, max 속성)
    // 특정 JRE 버전에서만 테스트
    @Test
    @EnabledOnJre(value = JRE.JAVA_8, disabledReason = "JRE 1.8. 환경에서만 테스트합니다")
    public void testJRE8() {}

    @Test
    @EnabledOnJre(value = {JRE.JAVA_8, JRE.JAVA_17}, disabledReason = "JRE 8, 17 환경에서만 테스트합니다")
    public void testJRE8ANDJRE17() {}

    @Test
    @DisabledOnJre(value = JRE.JAVA_17)
    public void testDisabledJRE17() {}

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_17)
    public void testFromJRE8TOJRE17() {}



}
