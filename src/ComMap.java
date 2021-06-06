import java.util.ArrayList;

public class ComMap implements RandomN{

    //地图的基本参数
    final private int width;
    final private int height;
    final private int R;
    final private int phonenum;
    final char[][] map = new char[10000][10000];
    ArrayList<BaseStation> baseStations;
    ArrayList<Phone> phones;

    //初始化
    ComMap(int width, int height, int R, int num, int phonenum, ArrayList<Phone> phones){

        this.width = width;
        this.height = height;
        this.R = R;
        this.phonenum = phonenum;
        this.phones = phones;

        baseStations = new ArrayList<>();

        int i, j;
        for (i = 0; i < width; i ++) for (j = 0; j < height; j ++)
            map[i][j] = '-';
        for (i = 0; i < num; i ++){
            int x = randomNumber(0, getWidth());
            int y = randomNumber(0, getHeight());
            BaseStation baseStation = new BaseStation(i+1, x, y, R);
            map[x][y] = (char)(baseStation.getStationID() + 'a');
            baseStations.add(baseStation);
        }

        for (Phone phone : phones) {
            map[phone.getPosition_x()][phone.getPosition_y()] = (char) (phone.getPhoneID() + '0');
            int id = checkStation(phone);
            if (id >= 0) System.out.println("phone_" + phone.getPhoneID() + " " + phone.getNumber() +
                    " has entered the " + id + " base station." + " Station Sysmbol in map: " + (char) (id + 'a'));
            else System.out.println("phone_" + phone.getPhoneID() + " " + phone.getNumber() + " is out of service.");
        }
        System.out.println();
        printMap();
        System.out.println();

    }

    public int getR() {
        return R;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public char[][] getMap() {
        return map;
    }
    public int getPhonenum() {
        return phonenum;
    }

    //刷新地图(随机移动，并让手机进行随机动作)
    public void refreshMap() {
        //重载地图，并让手机随机移动
        int i, j;
        for (i = 0; i < width; i ++) for (j = 0; j < height; j ++)
            map[i][j] = '-';
        for (BaseStation baseStation : baseStations) {
            map[baseStation.getPosition_x()][baseStation.getPosition_y()] = (char) (baseStation.getStationID() + 'a');
        }
        for (Phone phone : phones) {
            phone.move_x(randomChoice());
            phone.move_y(randomChoice());
            map[phone.getPosition_x()][phone.getPosition_y()] = (char) (phone.getPhoneID() + '0');

            int id = checkStation(phone);
            if (id >= 0) System.out.println("phone_" + phone.getPhoneID() + " " + phone.getNumber() +
                    " has entered the " + id + " base station." + " Station Sysmbol in map: " + (char) (id + 'a'));
            else System.out.println("phone_" + phone.getPhoneID() + " " + phone.getNumber() + " is out of service.");

        }

        //随机拨号、挂断等
        System.out.println();
        for (Phone phone : phones) {
            checkPhone(phone);
            System.out.println();
        }
        printMap();
        System.out.println();
    }

    //手机进行随机动作
    public void checkPhone(Phone phone){

        //生成随机操作对象cPhone，可能会向他拨号
        int cphone = randomNumber(0, getPhonenum());
        while (cphone == phone.getPhoneID()){
            cphone = randomNumber(0, getPhonenum());
        }

//        System.out.println(cphone);
        //正在通话中的话
        if (phone.getState() == 1) {
            //phone通话中的对象oldcPhone
            Phone oldcPhone = phones.get(phone.getCphoneID());
            //如果手机1不在服务区
            if (phone.getIn_stationID() == -1) {

                System.out.println("Because " + "phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                        " is out of service. The call with phone_" + oldcPhone.getPhoneID() + " has been terminated.");
                System.out.println(phone.getPhoneID() + "--" + "NO BASESTATION"
                        + "||" + (char) (oldcPhone.getIn_stationID() + 'a')+ "--" + oldcPhone.getPhoneID());
                phone.setState(0);
                oldcPhone.setState(0);
            }
            else {
                //在服务区但正在通话的对象不在服务区
                if (oldcPhone.getIn_stationID() == -1){
                    System.out.println("Because " + "phone_" + oldcPhone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            " is out of service. The call with phone_" + phone.getPhoneID() + " has been terminated.");
                    System.out.println(oldcPhone.getPhoneID() + "--" + "NO BASESTATION"
                            + "||" + (char) (phone.getIn_stationID() + 'a')+ "--" + oldcPhone.getPhoneID());
                    phone.setState(0);
                    oldcPhone.setState(0);
                }
                else {
                    //都在服务区，但是本机可能随机挂断或者持续通话
                    if (randomChoice()) {
                        System.out.println("Because " + "phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                                " hung up the phone. The call with phone_" + oldcPhone.getPhoneID() + " has been terminated.");
                        System.out.println(phone.getPhoneID() + "||" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (oldcPhone.getIn_stationID() + 'a') + "--" + oldcPhone.getPhoneID());
                        phone.setState(0);
                        oldcPhone.setState(0);
                    } else {
                        System.out.println("phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                                " is still calling with phone_" + oldcPhone.getPhoneID() + "(" + oldcPhone.getNumber() + ")" +".");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (oldcPhone.getIn_stationID() + 'a') + "--" + oldcPhone.getPhoneID());
                    }
                }
            }
        }
        //处于空闲状态的话
        else {
            //如果在服务区
            if (phone.getIn_stationID() != -1) {
                Phone cPhone = phones.get(cphone);
//            System.out.println("!!!!!!!!");
//            System.out.println(randomChoice());

                if (randomChoice()) {
                    //50%的概率随机尝试拨打电话
                    System.out.println("phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            "is trying to call phone_" + cphone);
                    //拨打对象不在服务区
                    if (cPhone.getIn_stationID() == -1) {
                        System.out.println("Failed, phone_" + cPhone.getPhoneID() + " is out of sevice!");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "||" + "NO BASESTATION" + "--" + cPhone.getPhoneID());
                        return;
                    }
                    //拨打对象处于通话中
                    if (cPhone.getState() == 1) {
                        System.out.println("Failed, phone_" + cPhone.getPhoneID() + " is on the line!");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (cPhone.getIn_stationID() + 'a') + "||" + cPhone.getPhoneID());
                        return;
                    }
                    //否则通话成功
                    System.out.println("Call successful!");
                    System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                            + "--" + (char) (cPhone.getIn_stationID() + 'a') + "--" + cPhone.getPhoneID());
                    phone.setState(1);
                    phone.setCphoneID(cPhone.getPhoneID());
                    cPhone.setState(1);
                    cPhone.setCphoneID(phone.getPhoneID());
                }
                //50%的概率并不拨打电话
                else{
                    System.out.println("phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            "is not doing anything.");
                }
            }
            //如果本机没在服务区，失败
            else{
                System.out.println("phone_" + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                        " is out of service.The cell phone is currently unable to dial.");
                System.out.println(phone.getPhoneID() + "||" + "NO BASESTATION");

            }
        }

    }

    //检查离手机最近的基站，返回id，失败返回-1
    public int checkStation(Phone phone) {

        int s;
        int min = 100000;
        int x = phone.getPosition_x();
        int y = phone.getPosition_y();

        for (BaseStation baseStation : baseStations) {
            s = (x - baseStation.getPosition_x()) * (x - baseStation.getPosition_x()) +
                    (y - baseStation.getPosition_y()) * (y - baseStation.getPosition_y());
            if (s <= min) min = s;
        }
        for (BaseStation baseStation : baseStations) {
            s = (x - baseStation.getPosition_x()) * (x - baseStation.getPosition_x()) +
                    (y - baseStation.getPosition_y()) * (y - baseStation.getPosition_y());
            if (s == min && s <= getR()) {
                phone.setIn_stationID(baseStation.getStationID());
                return phone.getIn_stationID();
            }
        }
        phone.setIn_stationID(-1);
        return -1;

    }

    //打印地图
    public void printMap(){
        int i, j;
        for (i = 0; i < width; i ++){
            for (j = 0; j < height; j ++){
                System.out.print(getMap()[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    @Override
    public int randomNumber(int l, int r) {
        return (int)(Math.random() * (r  - l) + l);
    }

    @Override
    public boolean randomChoice() {
        double r = Math.random();
        return r < 0.5;
    }

}
