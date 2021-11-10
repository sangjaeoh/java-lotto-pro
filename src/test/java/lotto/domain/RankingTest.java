package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class RankingTest {

    @DisplayName("6일경우 FIRST 반환")
    @Test
    void first() {
        Ranking ranking = Ranking.find(6, false);
        assertThat(ranking).isEqualTo(Ranking.FIRST);
    }

    @DisplayName("5이면서 보너스볼을 가지면 SECOND_BONUS 반환")
    @Test
    void secondBonus() {
        Ranking ranking = Ranking.find(5, true);
        assertThat(ranking).isEqualTo(Ranking.SECOND_BONUS);
    }

    @DisplayName("5일경우 SECOND 반환")
    @Test
    void second() {
        Ranking ranking = Ranking.find(5, false);
        assertThat(ranking).isEqualTo(Ranking.SECOND);
    }

    @DisplayName("4일경우 THIRD 반환")
    @Test
    void third() {
        Ranking ranking = Ranking.find(4, false);
        assertThat(ranking).isEqualTo(Ranking.THIRD);
    }

    @DisplayName("3일경우 FOURTH 반환")
    @Test
    void fourth() {
        Ranking ranking = Ranking.find(3, false);
        assertThat(ranking).isEqualTo(Ranking.FOURTH);
    }

    @DisplayName("0~2 일경우 DROP 반환")
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void etcCount(int count) {
        Ranking ranking = Ranking.find(count, false);
        assertThat(ranking).isEqualTo(Ranking.DROP);
    }

}