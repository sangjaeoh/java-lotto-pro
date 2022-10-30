## 로또(자동)
### 기능 요구 사항
- 로또 구입 금액을 입력하면 구입 금액에 해당하는 로또를 발급해야 한다.
- 로또 1장의 가격은 1000원이다.

### 입출력 예시
```text
구입금액을 입력해 주세요.
14000
14개를 구매했습니다.
[8, 21, 23, 41, 42, 43]
[3, 5, 11, 16, 32, 38]
[7, 11, 16, 35, 36, 44]
[1, 8, 11, 31, 41, 42]
[13, 14, 16, 38, 42, 45]
[7, 11, 30, 40, 42, 43]
[2, 13, 22, 32, 38, 45]
[23, 25, 33, 36, 39, 41]
[1, 3, 5, 14, 22, 45]
[5, 9, 38, 41, 43, 44]
[2, 8, 9, 18, 19, 21]
[13, 14, 18, 21, 23, 35]
[17, 21, 29, 37, 42, 45]
[3, 8, 27, 30, 35, 44]

지난 주 당첨 번호를 입력해 주세요.
1, 2, 3, 4, 5, 6

당첨 통계
---------
3개 일치 (5000원)- 1개
4개 일치 (50000원)- 0개
5개 일치 (1500000원)- 0개
6개 일치 (2000000000원)- 0개
총 수익률은 0.35입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)
```

### 구현 기능 목록
- 구매 금액 입력 받기
- 구매 금액이 숫자인지 확인
- 로또 1장은 1000원이므로 1000원 이상인지 확인
- 로또 구매 가능 갯수 계산
- 로또 번호는 숫자, 1 ~ 45 사이의 랜덤값, 6개, 중복 X
- 구매한 로또의 결과 출력
- 지난 주 당첨 번호 입력 받기
- 사용자가 입력한 당첨 번호를 쉼표 기준으로 분리
- 지난 주 당첨 번호와 구매한 로또 번호를 비교하여 일치한 숫자 갯수 확인
- 총 수익률 계산
- 당첨 통계 출력