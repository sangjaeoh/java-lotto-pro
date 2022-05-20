import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StringAddCalculatorTest {
    @Test
    public void splitAndSum_null_또는_빈문자() {
        int result = StringAddCalculator.splitAndSum(null);
        assertThat(result).isEqualTo(0);

        result = StringAddCalculator.splitAndSum("");
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void splitAndSum_숫자하나() throws Exception {
        int result = StringAddCalculator.splitAndSum("1");
        assertThat(result).isEqualTo(1);
    }

    @Test
    public void splitAndSum_쉼표구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("1,2");
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void splitAndSum_쉼표_또는_콜론_구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("1,2:3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    public void splitAndSum_custom_구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("//;\n1;2;3");
        assertThat(result).isEqualTo(6);
    }

    @Test
    @DisplayName("기본 구분자 + Custom 구분자 같이 입력되는 경우")
    public void splitAndSum_custom_구분자_쉼표구분자() throws Exception {
        int result = StringAddCalculator.splitAndSum("//;\n1;2;3,4:5");
        assertThat(result).isEqualTo(15);
    }

    @Test
    @DisplayName("음수가 입력되었을 때 Runtime Exception 발생")
    public void splitAndSum_negative() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("-1,2,3"))
            .isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("숫자가 아닌 값이 입력되었을 때 Runtime Exception 발생")
    public void splitAndSum_notNumber() throws Exception {
        assertThatThrownBy(() -> StringAddCalculator.splitAndSum("1,2,a"))
            .isInstanceOf(RuntimeException.class);
    }
}