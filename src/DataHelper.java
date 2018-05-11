public class DataHelper {

    private static Vaus vaus;

    public static Vaus getUsageVaus(){
        if(vaus == null)
            throw new IllegalArgumentException("객체가 초기화 되지 않았습니다.");
        return vaus;
    }

    public static void setUsageVaus(Vaus v){
        vaus = v;
    }

}
