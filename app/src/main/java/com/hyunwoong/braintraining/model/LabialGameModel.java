package com.hyunwoong.braintraining.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LabialGameModel {

    private Map<Integer, String> NUM_SENTENCE;
    private Map<Integer, Integer> NUM_LABIAL;
    private Map<Integer, String> STRING_LABIAL;

    private ArrayList Sentencelist;
    private int TextNum;
    private int SenNum;
    private int[] arr;
    private String strText;

    private int Qnumber;
    private int RightAnswerNumber;


    public void Init() {
        NUM_SENTENCE = new HashMap<>();
        NUM_LABIAL = new HashMap<>();
        STRING_LABIAL = new HashMap<>();
        Sentencelist = new ArrayList<>(getNUM_SENTENCE().keySet());
        SenNum = 0;
        Qnumber = 0;


        NUM_SENTENCE.put(1, "나보기가 , 역겨워 , 가실때에는 , 말없이 고이보내 ,  드리오리다.");
        NUM_LABIAL.put(1,4);
        STRING_LABIAL.put(1,"보 , 말 , 없 , 보");


        NUM_SENTENCE.put(2, "별을 노래하는 마음으로, 모든 , 죽어가는 것을 사랑해야지.");
        NUM_LABIAL.put(2,4);
        STRING_LABIAL.put(2,"별 , 마 , 음 , 모");


        NUM_SENTENCE.put(3, "별하나에 추억과, 별하나에 사랑과, 별하나에 시와, 별하나에 어머니");
        NUM_LABIAL.put(3,5);
        STRING_LABIAL.put(3,"별 , 별 , 별 , 별 , 머");


        NUM_SENTENCE.put(4, "어서 따라오라고 , 따라 가자고,  흘러도 , 연달아 , 흐릅디다려.");
        NUM_LABIAL.put(4,1);
        STRING_LABIAL.put(4,"릅");


        NUM_SENTENCE.put(5, "내 신발은, 십 구문 반, 눈과 얼음 길을 걸어, 그들 옆에 벗으면");
        NUM_LABIAL.put(5,8);
        STRING_LABIAL.put(5,"발 , 십 , 문 , 반 , 음 , 옆 , 벗 , 면");


        NUM_SENTENCE.put(6, "불 도적한 죄로, 목에 맷돌을 달고, 끝없이 침전하는, 프로메테우스");
        NUM_LABIAL.put(6,7);
        STRING_LABIAL.put(6,"불 , 목 , 맷 , 없 , 침 , 프 , 메");


        NUM_SENTENCE.put(7, "산모퉁이를 돌아, 논가 외딴 우물을, 홀로 찾아가선, 가만히 ,들여다 봅니다.");
        NUM_LABIAL.put(7,4);
        STRING_LABIAL.put(7,"모 , 물 , 만 , 봅");


        NUM_SENTENCE.put(8, "파란 녹이 낀 , 구리거울 속에, 내얼굴이, 남아 있는 것은");
        NUM_LABIAL.put(8,2);
        STRING_LABIAL.put(8,"파 , 남");


        NUM_SENTENCE.put(9, "산에서 우는, 작은 새여, 꽃이 좋아, 산에서, 사노라네");
        NUM_LABIAL.put(9,0);
        STRING_LABIAL.put(9,"");


        NUM_SENTENCE.put(10, "아이야, 우리 식탁엔, 은쟁반에, 하이얀 모시 수건을, 마련해 드렴");
        NUM_LABIAL.put(10,4);
        STRING_LABIAL.put(10,"반 , 모 , 마 , 렴");


        NUM_SENTENCE.put(11, "죽음을 잊어버린 영혼과, 육체를 위하여 ,눈은, 새벽이 지나도록, 살아있다");
        NUM_LABIAL.put(11,3);
        STRING_LABIAL.put(11,"음 , 버 , 벽");


        NUM_SENTENCE.put(12, "나의 사랑 ,나의 결별 ,샘터에 물고이듯 성숙하는, 내영혼의 슬픈, 눈");
        NUM_LABIAL.put(12,4);
        STRING_LABIAL.put(12,"별 , 샘 , 물 , 픈");


        NUM_SENTENCE.put(13, "모란이 피기까지는 ,나는 ,아직 ,기다리고 있을테요. 찬란한 슬픔의 봄을");
        NUM_LABIAL.put(13,6);
        STRING_LABIAL.put(13,"모 , 피 , 픔 , 의 , 봄 , 을");


        NUM_SENTENCE.put(14, "낙엽이 ,우수수 떨어질 때, 겨울의 ,기나긴밤, 어머니하고 ,둘이 앉아");
        NUM_LABIAL.put(14,3);
        STRING_LABIAL.put(14,"엽 , 밤 , 버");


        NUM_SENTENCE.put(15, "이 조용한 마을과 ,이 마을의 으젓한 사람들과, 살틀하니 ,친한 것은, 무엇인가");
        NUM_LABIAL.put(15,4);
        STRING_LABIAL.put(15,"마 , 마 , 람 , 무");


        NUM_SENTENCE.put(16, "백마를 타고 오는, 초인이 있어, 이 광야에서, 목놓아 부르게 하리라.");
        NUM_LABIAL.put(16,4);
        STRING_LABIAL.put(16,"백 , 마 , 목 , 부");


        NUM_SENTENCE.put(17, "껍데기는 가라. 사월도 알맹이만 남고 껍데기는 가라.");
        NUM_LABIAL.put(17,5);
        STRING_LABIAL.put(17,"껍 , 맹 , 만 , 남 , 껍");


        NUM_SENTENCE.put(18, "북쪽 툰드라에도 , 찬 새벽은 , 눈속 깊히 , 꽃 맹아리가, 옴작거려");
        NUM_LABIAL.put(18,5);
        STRING_LABIAL.put(18,"북 , 벽 , 깊 , 맹 , 옴");


        NUM_SENTENCE.put(19, "나는 ,당신을 기다리면서, 날마다, 날마다 ,낡아갑니다.");
        NUM_LABIAL.put(19,4);
        STRING_LABIAL.put(19,"면 , 마 , 마 , 갑");


        NUM_SENTENCE.put(20, "누가 ,구름 한자락 없이, 맑은 하늘을 ,보았다 하는가.");
        NUM_LABIAL.put(20,4);
        STRING_LABIAL.put(20,"름 , 없 , 맑 , 보");


        NUM_SENTENCE.put(21, "그 물결 위에, 양귀비 꽃보다도 더 붉은, 그 마음 흘러라.");
        NUM_LABIAL.put(21,5);
        STRING_LABIAL.put(21,"물 , 비 , 보 , 밝 , 마");


        NUM_SENTENCE.put(22, "쌓이는 ,눈 더미 앞에, 나의 마음은, 어둠이노라");
        NUM_LABIAL.put(22,4);
        STRING_LABIAL.put(22,"미 , 앞 , 마 , 둠");


        NUM_SENTENCE.put(23, "님은 갔습니다. 아아, 사랑하는 나의 님은 갔습니다.");
        NUM_LABIAL.put(23,4);
        STRING_LABIAL.put(23,"님 , 습 , 님 , 습");


        NUM_SENTENCE.put(24, "우리는 ,버지니아 울프의 생애와, 목마를 타고 떠난, 숙녀의, 옷자락을 ,이야기한다.");
        NUM_LABIAL.put(24,4);
        STRING_LABIAL.put(24,"모 , 물 , 만 , 봅");


        NUM_SENTENCE.put(25, "차라리 ,나는 ,어느 사구에, 회한없는, 백골을 ,쪼이리라");
        NUM_LABIAL.put(25,2);
        STRING_LABIAL.put(25,"없 , 백");


        NUM_SENTENCE.put(26, "성북동 산에, 번지가 새로 생기면서, 본래 살던, 성북동 비둘기만이, 번지가 없어졌다.");
        NUM_LABIAL.put(26,9);
        STRING_LABIAL.put(26,"북 , 번 , 면 , 본 , 북 , 비 , 먼 , 번 , 없");


        NUM_SENTENCE.put(27, "눈속에 따 오신, 산수유, 붉은 알알이 ,아직도 ,내 혈액 속에, 녹아 흐르는 ,까닭일까.");
        NUM_LABIAL.put(27,4);
        STRING_LABIAL.put(27,"모 , 물 , 만 , 봅");


        NUM_SENTENCE.put(28, "꽃처럼 ,피어나는 피를, 어두워가는 하늘 밑에서, 조용히 흘리겠습니다.");
        NUM_LABIAL.put(28,4);
        STRING_LABIAL.put(28,"피 , 피 , 밑 , 습");


        NUM_SENTENCE.put(29, "이러매, 눈 감아 , 생각해 볼밖에, 겨울은, 강철로 된, 무지갠가 보다.");
        NUM_LABIAL.put(29,6);
        STRING_LABIAL.put(29,"매 , 감 , 볼 , 밖 , 무 , 보");


        NUM_SENTENCE.put(30, "타는 가슴 속, 목마름의 기억이, 네 이름을, 남 몰래 쓴다");
        NUM_LABIAL.put(30,7);
        STRING_LABIAL.put(30,"슴 , 목 , 마 , 름 , 름 , 몰");


        NUM_SENTENCE.put(31, "나타와 안정을, 뒤집어 놓은 듯이, 높이도, 폭도 없이 떨어진다.");
        NUM_LABIAL.put(31,4);
        STRING_LABIAL.put(31,"집 , 놉 , 폭 , 없");


        NUM_SENTENCE.put(32, "꿈을 아느냐 네게 물으면, 플라타너스 너의 머리는 어느덧 파아란 하늘에 젖어있다.");
        NUM_LABIAL.put(32,5);
        STRING_LABIAL.put(32,"꿈 , 물 , 플 , 머 , 파");


        NUM_SENTENCE.put(33, "조국을 언제 떠났노. 파초의 꿈은 가련하다.");
        NUM_LABIAL.put(33,2);
        STRING_LABIAL.put(33,"파 , 꿈");


        NUM_SENTENCE.put(34, "선 채로 이자리에 돌이 되어도 부르다가 내가 죽을 이름이여!");
        NUM_LABIAL.put(34,2);
        STRING_LABIAL.put(34,"부 , 름");


        NUM_SENTENCE.put(35, "파란 하늘에, 백로가 노래하고, 이른 봄, 잔디밭에 스며드는, 햇볓처럼");
        NUM_LABIAL.put(35,6);
        STRING_LABIAL.put(35,"파 , 백 , 봄 , 밤 , 며 , 볒");


        NUM_SENTENCE.put(36, "아, 얼마나한 이로이랴 그대 맑은 눈을 들어 나를 보느니");
        NUM_LABIAL.put(36,3);
        STRING_LABIAL.put(36,"마 , 맑 , 보");


        NUM_SENTENCE.put(37, "인간 역사의 첫 페이지에 잉크칠을 할까 술을 마실까 망설일 때에");
        NUM_LABIAL.put(37,3);
        STRING_LABIAL.put(37,"페 , 마 , 망");


        NUM_SENTENCE.put(38, "나직하고 그윽하게 부르는 소리 있어 나아가보니, 아, 나아가 보니...");
        NUM_LABIAL.put(38,3);
        STRING_LABIAL.put(38,"부 , 보 , 보");


        NUM_SENTENCE.put(39, "지금은 남의 땅, 빼앗긴 들에도 봄은 오는가?");
        NUM_LABIAL.put(39,4);
        STRING_LABIAL.put(39,"금 , 남 , 빼 , 봄");


        NUM_SENTENCE.put(40, "행여 백조가 오는 날 이 물가 어지러울까 나는 밤마다 꿈을 덮노라.");
        NUM_LABIAL.put(40,5);
        STRING_LABIAL.put(40,"백 , 물 , 밤 , 꿈 , 덮");


        NUM_SENTENCE.put(41, "나는 나에게 손을 내밀어 눈물과 위안으로 잡는 최초의 악수");
        NUM_LABIAL.put(41,3);
        STRING_LABIAL.put(41,"밀 , 물 , 잡");


        NUM_SENTENCE.put(42, "이 세상 소풍 끝내는 날, 가서, 아름다웠더라고 말하리라...");
        NUM_LABIAL.put(42,6);
        STRING_LABIAL.put(42,"모 , 물 , 만 , 봅");


        NUM_SENTENCE.put(43, "삼월달 ,바다가 꽃이 피지 않아서, 서글픈 나비 허리에 ,새파란 초생달이 시리다");
        NUM_LABIAL.put(43,6);
        STRING_LABIAL.put(43,"삼 , 바 , 피 , 픈 , 비 , 파");


        NUM_SENTENCE.put(44, "이 밤사, 귀또리도 지새우는 삼경인데, 얇은 사 ,하이얀 고깔은,고이 접어서 ,나빌레라");
        NUM_LABIAL.put(44,5);
        STRING_LABIAL.put(44,"밤 , 심 , 얇 , 접 , 빌");


        NUM_SENTENCE.put(45, "오늘도 ,그대를 사랑하는 일보다, 기다리는 일이 ,더, 행복하였습니다.");
        NUM_LABIAL.put(45,3);
        STRING_LABIAL.put(45,"보 , 복 , 습");


        NUM_SENTENCE.put(46, "검은 그림자 쓸쓸하면 , 마침내 호수 속 깊이 거꾸러져, 차마 바람도 흔들진 못해라.");
        NUM_LABIAL.put(46,10);
        STRING_LABIAL.put(46,"검 , 림 , 면 , 마 , 침 ,깊 , 마 , 바 , 람 , 못");


        NUM_SENTENCE.put(47, "그 날이 오면, 그 날이 오면은 ,삼각산이 일어나 더덩실 춤이라도 추고,");
        NUM_LABIAL.put(47,4);
        STRING_LABIAL.put(47,"면 , 면 , 삼 , 춤");


        NUM_SENTENCE.put(48, "그리운 그의 모습 ,다시 찾을 수 없어도, 울고 간 ,그의 영혼들에, 언덕에 피어날지어이.");
        NUM_LABIAL.put(48,4);
        STRING_LABIAL.put(48,"모 , 습 , 없 , 피");


        NUM_SENTENCE.put(49, "술 익은 마을마다 타는 저녁노을 구름에 달 가듯이 가는 나그네.");
        NUM_LABIAL.put(49,3);
        STRING_LABIAL.put(49,"마 , 마 , 름");


        NUM_SENTENCE.put(50, "어느 머언 곳의 그리운 소식이기에, 이 한밤 , 소리 없이 흩날리느뇨.");
        NUM_LABIAL.put(50,3);
        STRING_LABIAL.put(50,"머 , 밤 , 없");

    }

    public Map<Integer, String> getNUM_SENTENCE() {
        return NUM_SENTENCE;
    }

    public Map<Integer, Integer> getNUM_LABIAL() {
        return NUM_LABIAL;
    }

    public Map<Integer, String> getSTRING_LABIAL() {
        return STRING_LABIAL;
    }

    public void setNUM_SENTENCE(Map<Integer, String> NUM_SENTENCE) {
        this.NUM_SENTENCE = NUM_SENTENCE;
    }

    public void setNUM_LABIAL(Map<Integer, Integer> NUM_LABIAL) {
        this.NUM_LABIAL = NUM_LABIAL;
    }

    public void setSTRING_LABIAL(Map<Integer, String> STRING_LABIAL) {
        this.STRING_LABIAL = STRING_LABIAL;
    }

    public ArrayList getSentencelist() {
        return Sentencelist;
    }

    public int[] getArr() {
        return arr;
    }

    public int getSenNum() {
        return SenNum;
    }

    public int getTextNum() {
        return TextNum;
    }

    public String getStrText() {
        return strText;
    }

    public int getQnumber() {
        return Qnumber;
    }

    public int getRightAnswerNumber() {
        return RightAnswerNumber;
    }

    public void setArr(int[] arr) {
        this.arr = arr;
    }

    public void setSenNum(int senNum) {
        SenNum = senNum;
    }

    public void setSentencelist(ArrayList sentencelist) {
        Sentencelist = sentencelist;
    }

    public void setStrText(String strText) {
        this.strText = strText;
    }

    public void setTextNum(int textNum) {
        TextNum = textNum;
    }

    public void setQnumber(int qnumber) {
        Qnumber = qnumber;
    }

    public void setRightAnswerNumber(int rightAnswerNumber) {
        RightAnswerNumber = rightAnswerNumber;
    }
}


