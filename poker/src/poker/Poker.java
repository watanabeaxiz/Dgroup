package poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Poker extends Hand{

	public static void shuffle(ArrayList<Integer> card) {
		card.clear();
		for(int i=1;i<53;i++) {
			card.add(i);
		}
		Collections.shuffle(card);
	}




	public static void main(String[] args) {

		//山札を格納する配列
		ArrayList<Integer> card = new ArrayList<Integer>();
		Hand hand=new Hand();
		Scanner sc  = new Scanner(System.in);


		while (true) {

			shuffle(card);
			System.out.println("あなたの手札");
			hand.deal(card);
			hand.show();





			for(int n=0;n<5;n++) {
				System.out.println((n+1)+"枚目のカードを入れ替えますか? (1:はい  2:いいえ)");
				int input = sc.nextInt();
				if(input==1) {
					hand.change(card, n);
				}else if(input==2) {

				}else {
					System.out.println((n+1)+"枚目のカードを入れ替えますか? (1:はい  2:いいえ)");
					input = sc.nextInt();
				}
			}

			hand.numhenkan();
			hand.sorthenkan();
			hand.count();
			hand.judge();
			System.out.println("変更後のあなたの手札");
			hand.show();
			hand.printHand();
			System.out.println("このままゲームを続けますか? (1:継続 2:終了)");
			int answer = sc.nextInt();
			if(answer==1) {
				hand.clear();
			}else {
				System.out.print("ゲームを終了します");
				break;
			}

		}
		sc.close();
	}



	//テスト用メソッド
	public static int Test(int a,int b, int c,int d,int e) {
		Hand hand=new Hand();
		hand.playerHand.add(a);
		hand.playerHand.add(b);
		hand.playerHand.add(c);
		hand.playerHand.add(d);
		hand.playerHand.add(e);
		hand.numhenkan();
		hand.sorthenkan();
		hand.count();
		hand.judge();
		hand.show();
		hand.printHand();
		return hand.rolenum;
	}
}