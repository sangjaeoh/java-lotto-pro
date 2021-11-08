package lotto.domain;

import lotto.exception.InputDataErrorCode;
import lotto.exception.InputDataException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @Test
    @DisplayName("6개가 일치하면 1등이 나오는지 확인 ")
    void rank_First() {
        assertThat(Rank.rank(6, false)).isEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개가 일치하면 1등이 아닌걸로 나오는지 확인 ")
    void rank_First_Fail() {
        assertThat(Rank.rank(5, true)).isNotEqualTo(Rank.FIRST);
    }

    @Test
    @DisplayName("5개가 일치하고 보너스 볼이 일치하면  2등이 나오는지 확인 ")
    void rank_SECOND() {
        assertThat(Rank.rank(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개가 일치하면 3등이 나오는지 확인 ")
    void rank_THIRD() {
        assertThat(Rank.rank(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개가 일치하면 4등이 나오는지 확인 ")
    void rank_Fourth() {
        assertThat(Rank.rank(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개가 일치하면 5등이 나오는지 확인 ")
    void rank_Fifth() {
        assertThat(Rank.rank(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("꽝이 나왔을 경우 확인하기")
    void rank_Bomb() {
        assertAll(
                () -> assertThat(Rank.rank(2, false)).isEqualTo(Rank.NOT_WIN_MONEY_MATCH_COUNT_TWO),
                () -> assertThat(Rank.rank(1, false)).isEqualTo(Rank.NOT_WIN_MONEY_MATCH_COUNT_ONE),
                () -> assertThat(Rank.rank(0, false)).isEqualTo(Rank.NOT_MATCH)
        );
    }

    @Test
    @DisplayName("유효한 로또 순위가 나오는지 확인 1~5등까지")
    void outputValidRankTest(){
        List<Rank> ranks = Rank.getLottoResultRank();
        assertThat(ranks).contains(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST);
    }

    @Test
    @DisplayName("잘못된 로또 당첨 개수가 입력이 되었을 경우 확인하기")
    void rank_wrong_mach_count() {
        assertThatThrownBy(
                () -> Rank.rank(-1, false)
        ).isInstanceOf(InputDataException.class)
                .hasMessageContaining(InputDataErrorCode.INVALID_LOTTO_WIN_COUNT.errorMessage());
    }


}