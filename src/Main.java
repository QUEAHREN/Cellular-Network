import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        int width = 15;
        int height = 15;
        int R = 5;
        int baseStation_num = 26;
        int phone_num = 5;
        int times = 20;
        ArrayList<Phone> phones = new ArrayList<Phone>();

        int i;
        for (i = 0; i < phone_num; i ++){
            Phone phone = new Phone(i, width, height);
            phones.add(phone);
        }

        ComMap comMap = new ComMap(width, height, R, baseStation_num, phone_num, phones);

        for (i = 0; i < 20; i ++) comMap.refreshMap();

    }


}
