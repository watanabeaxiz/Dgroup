package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class pokerTest {

	@Test
	public void ポーカーテスト() {

		//ブタチェック
		int result1=Poker.Test(1,2,3,30,25);
		assertEquals(result1,0);

		//1ペアチェック
		int result2=Poker.Test(1,14,2,3,4);
		assertEquals(result2,1);

		//2ペアチェック
		int result3=Poker.Test(1,2,14,15,52);
		assertEquals(result3,2);

		//3カードチェック
		int result4=Poker.Test(1,14,27,2,52);
		assertEquals(result4,3);

		//ストレートチェック
		int result5=Poker.Test(1,15,3,17,5);
		assertEquals(result5,4);

		//フラッシュチェック
		int result6=Poker.Test(1,3,6,8,12);
		assertEquals(result6,5);

		//フルハウスチェック
		int result7=Poker.Test(1,14,15,28,41);
		assertEquals(result7,6);

		//4カードチェック
		int result8=Poker.Test(1,14,27,40,8);
		assertEquals(result8,7);

		//ストレートフラッシュチェック
		int result9=Poker.Test(1,2,3,4,5);
		assertEquals(result9,8);

		//ロイヤルストレートフラッシュチェック
		int result10=Poker.Test(1,10,11,12,13);
		assertEquals(result10,9);

	}

}
