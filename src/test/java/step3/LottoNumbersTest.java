package step3;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

	@Test
	@DisplayName("로또 번호가 중복된 경우 6개만 뽑히는지 확인")
	void createLotto() {
		// given
		Integer[] inputLottoNumbers = {1, 4, 4, 5, 9, 9, 4};
		Integer[] resultLottoNumbers = {1, 4, 4, 5, 9, 4};
		LottoNumbers createLottoNumbers = LottoNumbers.createLottoNumber(resultLottoNumbers);

		//when
		LottoNumbers lottoNumbers = LottoNumbers.createLottoNumber(inputLottoNumbers);

		//then
		Assertions.assertThat(lottoNumbers).isEqualTo(createLottoNumbers);
	}

	@Test
	@DisplayName("로또 번호가 6개 이상 뽑히면 Exception 발생")
	void createOverFlowThrow() {
		// given
		Integer[] inputLottoNumbers = {1, 4, 42, 5, 9, 6, 45, 32};

		assertThatThrownBy(() -> {
			LottoNumbers.createLottoNumber(inputLottoNumbers);
		}).isInstanceOf(ArrayIndexOutOfBoundsException.class);
	}

}