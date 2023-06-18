public class Dialog {
    public static String talk(char c){
       if(c=='K')
           return KingTalk();
       else if(c=='C')
           return QueenTalk();
       else //(c=='M')
           return MountainTalk();

    }
    private static String KingTalk(){
        return "$Night's King: The day will never prevail!!!$\n\n";
    }
    private static String QueenTalk(){
        return "$Queen Cersei: you will never see the day light again!$\n\n";
    }
    private static String MountainTalk(){
        return "$The Mountain: I will end you!$\n\n";
    }

}
