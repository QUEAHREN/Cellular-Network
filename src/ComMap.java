import java.util.ArrayList;
import java.util.Iterator;

public class ComMap implements RandomN{

    private int width;
    private int height;
    private int R;
    private int num;
    private int phonenum;
    char map[][] = new char[10000][10000];
    ArrayList<BaseStation> baseStations = new ArrayList<>();
    ArrayList<Phone> phones = new ArrayList<>();

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

        for (Iterator<Phone> it= phones.iterator(); it.hasNext(); ) {
            Phone phone = it.next();
            map[phone.getPosition_x()][phone.getPosition_y()] = (char)(phone.getPhoneID() + '0');
            int id = checkStation(phone);
            if (id >= 0) System.out.println("phone: " + phone.getPhoneID() + " " +  phone.getNumber() +
                    " has entered the " + id +  " base station." + " sysmbol: " +(char)(id + 'a'));
            else         System.out.println("phone: " + phone.getPhoneID() + " " + phone.getNumber() + " is out of service.");
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

    public void refreshMap() {
        int i, j;
        for (i = 0; i < width; i ++) for (j = 0; j < height; j ++)
            map[i][j] = '-';
        for (Iterator<BaseStation> it= baseStations.iterator(); it.hasNext(); ) {
            BaseStation baseStation = it.next();
            map[baseStation.getPosition_x()][baseStation.getPosition_y()] = (char)(baseStation.getStationID() + 'a');
        }
        for (Iterator<Phone> it= phones.iterator(); it.hasNext(); ) {
            Phone phone = it.next();
            phone.move_x(randomChoice());
            phone.move_y(randomChoice());
            map[phone.getPosition_x()][phone.getPosition_y()] = (char)(phone.getPhoneID() + '0');

            int id = checkStation(phone);
            if (id >= 0) System.out.println("phone: " + phone.getPhoneID() + " " +  phone.getNumber() +
                    " has entered the " + id +  " base station." + " sysmbol: " +(char)(id + 'a'));
            else         System.out.println("phone: " + phone.getPhoneID() + " " + phone.getNumber() + " is out of service.");

        }
        System.out.println();
        for (Iterator<Phone> it= phones.iterator(); it.hasNext(); ) {
            Phone phone = it.next();
            checkPhone(phone);
            System.out.println();
        }
        printMap();
        System.out.println();
    }

    public int checkPhone(Phone phone){

        int cphone = randomNumber(0, getPhonenum());
        while (cphone == phone.getPhoneID()){
            cphone = randomNumber(0, getPhonenum());
        }

//        System.out.println(cphone);

        if (phone.getState() == 1) {
            Phone oldcPhone = phones.get(phone.getCphoneID());
            if (phone.getIn_stationID() == -1) {

                System.out.println("Because " + "phone: " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                        " is out of service. The call with phone " + oldcPhone.getPhoneID() + " has been terminated.");
                System.out.println(phone.getPhoneID() + "--" + "NO BASESTATION"
                        + "||" + (char) (oldcPhone.getIn_stationID() + 'a')+ "--" + oldcPhone.getPhoneID());
                phone.setState(0);
                oldcPhone.setState(0);
                return 1;
            }
            else {
                if (oldcPhone.getIn_stationID() == -1){
                    System.out.println("Because " + "phone: " + oldcPhone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            " is out of service. The call with phone " + phone.getPhoneID() + " has been terminated.");
                    System.out.println(oldcPhone.getPhoneID() + "--" + "NO BASESTATION"
                            + "||" + (char) (phone.getIn_stationID() + 'a')+ "--" + oldcPhone.getPhoneID());
                    phone.setState(0);
                    oldcPhone.setState(0);
                    return 1;
                }
                else {
                    if (randomChoice() == true) {
                        System.out.println("Because " + "phone: " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                                " hung up the phone. The call with phone " + oldcPhone.getPhoneID() + " has been terminated.");
                        System.out.println(phone.getPhoneID() + "||" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (oldcPhone.getIn_stationID() + 'a') + "--" + oldcPhone.getPhoneID());
                        phone.setState(0);
                        oldcPhone.setState(0);
                        return 1;
                    } else {
                        System.out.println("phone " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                                " is still calling with phone " + oldcPhone.getPhoneID() + ".");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (oldcPhone.getIn_stationID() + 'a') + "--" + oldcPhone.getPhoneID());
                        return 1;
                    }
                }
            }
        }
        else {
            if (phone.getIn_stationID() != -1) {
                Phone cPhone = phones.get(cphone);
//            System.out.println("!!!!!!!!");
//            System.out.println(randomChoice());
                if (randomChoice() == true) {

                    System.out.println("phone: " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            "is trying to call phone: " + cphone);
                    if (cPhone.getIn_stationID() == -1) {
                        System.out.println("Failed, phone: " + cPhone.getPhoneID() + " is out of sevice!");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "||" + "NO BASESTATION" + "--" + cPhone.getPhoneID());
                        return 1;
                    }
                    if (cPhone.getState() == 1) {
                        System.out.println("Failed, phone: " + cPhone.getPhoneID() + " is on the line!");
                        System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                                + "--" + (char) (cPhone.getIn_stationID() + 'a') + "||" + cPhone.getPhoneID());
                        return 1;
                    }
                    System.out.println("Call successful!");
                    System.out.println(phone.getPhoneID() + "--" + (char) (phone.getIn_stationID() + 'a')
                            + "--" + (char) (cPhone.getIn_stationID() + 'a') + "--" + cPhone.getPhoneID());
                    phone.setState(1);
                    phone.setCphoneID(cPhone.getPhoneID());
                    cPhone.setState(1);
                    cPhone.setCphoneID(phone.getPhoneID());
                    return 1;
                }
                else{
                    System.out.println("phone: " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                            "is not doing anything.");
                }
            }
            else{
                System.out.println("phone: " + phone.getPhoneID() + "(" + phone.getNumber() + ")" +
                        " is out of service.The cell phone is currently unable to dial.");
                System.out.println(phone.getPhoneID() + "||" + "NO BASESTATION");

            }
        }

        return 1;
    }


    public int checkStation(Phone phone) {

        int s;
        int min = 100000;
        int x = phone.getPosition_x();
        int y = phone.getPosition_y();

        for (Iterator<BaseStation> it = baseStations.iterator(); it.hasNext();){
            BaseStation baseStation = it.next();
            s = (x - baseStation.getPosition_x())*(x - baseStation.getPosition_x()) +
                    (y - baseStation.getPosition_y())*(y - baseStation.getPosition_y());
            if (s <= min)   min = s;
        }
        for (Iterator<BaseStation> it = baseStations.iterator(); it.hasNext();){
            BaseStation baseStation = it.next();
            s = (x - baseStation.getPosition_x())*(x - baseStation.getPosition_x()) +
                    (y - baseStation.getPosition_y())*(y - baseStation.getPosition_y());
            if (s == min&&s <= getR()) {
                phone.setIn_stationID(baseStation.getStationID());
                return phone.getIn_stationID();
            }
        }
        phone.setIn_stationID(-1);
        return -1;

    }

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

        if (r < 0.5)    return true;
        else            return false;
    }

}
