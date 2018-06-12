package poker;
import java.util.ArrayList;
import java.util.Random;

public class Hand implements PorkerInterface{


	//手札を格納する配列
	protected ArrayList<Integer> playerHand = new ArrayList<Integer>();
	//役番号を格納する変数
	int rolenum=50;
	//手札の数値を格納する配列
	int[] numshand=new int[5];
	int Hcount=0;
	int Dcount=0;
	int Kcount=0;
	int Scount=0;
	int[] num=new int[13];
	int[] count=new int[13];
	//コンストラクタ 引数なし、フィールドの初期化 山札には1～52の整数を置く
	//protected Hand() {
	//	this.rolenum=0;
	//	for(int i=1;i<53;i++) {
	//		this.card.add(i);
	//	}}

	//乱数作成のためのインスタンス
	Random rnd = new Random();
	//めそっど
	public void deal(ArrayList<Integer> card) {
		for(int i=0;i<5;i++) {
			//1~52までの乱数作成(1づつ減らす)
			int ran = rnd.nextInt(52-i)+1;
			playerHand.add(card.get(ran));
			//被りをなくすために山札から減らす
			card.remove(ran);
		}
	}



	public void change(ArrayList<Integer> card, int num) {
		int tmp=card.get(num);
		card.set(num,playerHand.get(num));
		playerHand.set(num,tmp);
	}

	//value:カード番号(1~52)
	public String disp(int value) {
		String sort;
		String number;
		if(value<14) {
			sort="♥";
		}else if(value<27){
			sort="♠";
			value=value-13;
		}else if(value<40){
			sort="♦";
			value=value-26;
		}else {
			sort="♣";
			value=value-39;
		}
		if(value==1) {
			number="A";
		}else if(value==11) {
			number="J";
		}else if(value==12) {
			number="Q";
		}else if(value==13) {
			number="K";
		}else {
			number=Integer.toString(value);
		}
		return sort+number;
	}

	//手札表示　上記のdispめそっど利用
	public void show() {
		for(int i=0;i<playerHand.size();i++) {
			//手札のカードを表示
			System.out.print(disp(playerHand.get(i))+" ");
		}

	}

	public void printHand() {
		if(rolenum==0) {
			System.out.println(NONE);
		}else if(rolenum==1){
			System.out.println(ONEPAIR);
		}else if(rolenum==2){
			System.out.println(TWOPAIR);
		}else if(rolenum==3){
			System.out.println(THREECARD);
		}else if(rolenum==4){
			System.out.println(STRAIGHT);
		}else if(rolenum==5){
			System.out.println(FLUSH);
		}else if(rolenum==6){
			System.out.println(FULLHOUSE);
		}else if(rolenum==7){
			System.out.println(FOURCARD);
		}else if(rolenum==8){
			System.out.println(STRAIGHT_FLUSH);
		}else if(rolenum==9){
			System.out.println(ROYAL_STRAIGHT_FLUSH);
		}else {


		}
	}

	public void clear() {
		rolenum=0;
		playerHand.clear();
	}


	//1~52の数値をまず数値だけ変換してnums配列に置く
	public void numhenkan() {
		for(int i=0;i<5;i++) {
			int value=playerHand.get(i);
			if(value<14) {
			}else if(value<27){

				value=value-13;
			}else if(value<40){

				value=value-26;
			}else {

				value=value-39;
			}
			numshand[i]=value;
		}}

	public void sorthenkan() {
		for(int i=0;i<5;i++) {
			int value=playerHand.get(i);
			if(value<14) {
				Hcount+=1;
			}else if(value<27){
				Scount+=1;

			}else if(value<40){
				Dcount+=1;

			}else {
				Kcount+=1;
			}
		}
	}

	/*
     * それぞれの番号が何枚入っているかを保持する要素数13の配列を返す．
     * スーツの違いは無視する．
     * nums[0] ... 1の枚数
     * nums[1] ... 2の枚数
     * ...
     * nums[12] ... 13の枚数
     * になる．例えば，手札が 1, 3, 3, 4, 4 ならば，
     * nums[0] = 1, nums[2] = 2, nums[3] = 2, その他の要素は 0
     * という配列を返す．
     */
    public int[] getNumList() {
        int[] nums = new int[13];
        for (int i = 0; i < 5; i++) {
            // カードの番号(1-13)を配列のインデックス(0-12)で扱うため -1 している
            nums[this.numshand[i]-1]++;
        }
        return nums;
    }

    //同じカードが何枚あるか、またそれが何ペアあるかを数える
    public void count() {
    	this.num= getNumList();
    	for (int i = 0; i < 13; i++) {
            count[num[i]]++;
        }
    }




	//以下、judgeめそっどで使うための役判定メソッド
	public boolean isRSF() {
		if(!isSF()) {
			return false;
		}
		return numshand[0]==1 && numshand[4]==13;
	}

	public boolean isSF() {
		return isF() && isS();
	}

	public boolean isF() {
		return Hcount==5||Dcount==5||Kcount==5||Scount==5;
	}

	public boolean isS() {
		if (numshand[0] == 1 && numshand[1] == 10 && numshand[2] == 11 && numshand[3] == 12 && numshand[4] == 13) {
            return true;
        }for (int i = 0; i < 4; i++) {
            if (numshand[i] + 1 != numshand[i + 1]) {
                return false;
            }
        }
        return true;
	}

	public boolean isFourcard() {
		return (count[2] == 0 && count[3] == 0 && count[4] == 1);
	}

	public boolean isFullHouse() {
		return (count[2] == 1 && count[3] == 1 && count[4] == 0);
	}

	public boolean isThreecard() {
		return (count[2] == 0 && count[3] == 1 && count[4] == 0);
	}

	public boolean isTwoPair() {
		return (count[2] == 2 && count[3] == 0 && count[4] == 0);
	}

	public boolean isOnePair() {
		return  (count[2] == 1 && count[3] == 0 && count[4] == 0);
	}




	public void judge() {

		//手札をソート 昇順
		playerHand.sort(null);

		if(isRSF()) {
			rolenum=9;
		}else if(isSF()) {
			rolenum=8;
		}else if(isF()) {
			rolenum=5;
		}else if(isS()) {
			rolenum=4;
		}else if(isFourcard()) {
			rolenum=7;
		}else if(isFullHouse()) {
			rolenum=6;
		}else if(isThreecard()) {
			rolenum=3;
		}else if(isTwoPair()) {
			rolenum=2;
		}else if(isOnePair()) {
			rolenum=1;
		}else {
			rolenum=0;
		}
	}

}
