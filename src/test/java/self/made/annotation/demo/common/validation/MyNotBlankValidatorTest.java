package self.made.annotation.demo.common.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import self.made.annotation.demo.common.anntotation.MyNotBlank;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MyNotBlankValidatorTest {
    private Validator validator;

    private static class TestBean {
        @MyNotBlank
        private String targetStr;

        TestBean(String targetStr) {
            this.targetStr = targetStr;
        }
    }

    @BeforeEach
    void setUp() {
        // テストケースごとにバリデータを初期化する
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @DisplayName("@MyNotBlankのバリデーションのテスト")
    @ParameterizedTest(name = "文字列が「{0}」の時、isValid()が{1}となること")
    @MethodSource("provideArguments")
    void アノテーションMyNotBlankのBeanバリデーションのテスト(String string, boolean expected) {
        TestBean testBean = new TestBean(string);

        // NOTE: マニュアルでバリデーションをかけるときはこのように行う
        Set<ConstraintViolation<TestBean>> violations = validator.validate(testBean);

        // NOTE: violationsにバリデーションの結果が格納されている
        // NOTE: Setのサイズが空の時はisValidがtrueの時、空でないときはisValidがfalse
        // NOTE: Setのサイズが空かどうかの値とTargetのフィールドのexpectedで比較を行い、テストを実行する
        assertEquals(violations.isEmpty(), expected);
    }

    static Stream<Arguments> provideArguments() {
        return Stream.of(
                arguments(null, false),
                arguments("", false),
                arguments(" ", false),// 半角スペース1文字
                arguments("　", false),// 全角スペース1文字
                arguments("  ", false),// 半角スペース2文字
                arguments("　　", false),// 全角スペース2文字
                arguments(" 　", false),// 半角スペース1文字、全角スペース1文字
                arguments("a", true),
                arguments(" a", true),// 半角スペース + 文字
                arguments("　a", true)// 全角スペース + 文字
        );
    }
}