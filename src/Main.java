import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //参数设置
        int width = 15;     //地图宽度
        int height = 15;    //地图高度
        int R = 5;          //基站信号半径
        int baseStation_num = 26;   //基站数量
        int phone_num = 5;  //手机数量
        int times = 20;     //地图刷新（手机随机移动、随机拨号、随机挂断）
        ArrayList<Phone> phones = new ArrayList<>();

        int i;
        //生成手机
        for (i = 0; i < phone_num; i ++){
            Phone phone = new Phone(i, width, height);
            phones.add(phone);
        }

        //生成地图(含基站)
        ComMap comMap = new ComMap(width, height, R, baseStation_num, phone_num, phones);

        //刷新
        for (i = 0; i < times; i ++) comMap.refreshMap();

    }


}
