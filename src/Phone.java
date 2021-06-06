//手机类

public class Phone implements RandomN{

    private final int phoneID;
    private int cphoneID;
    private final String number;
    private int position_x;
    private int position_y;
    private int state;
    private final int width;
    private final int height;
    private int in_stationID;

    Phone(int phoneID, int width, int height){

        int num = randomNumber(100000000,999999999);
        this.phoneID = phoneID;
        this.number = "18" + num;
        this.state = 0;
        this.position_x = randomNumber(0, width);
        this.position_y = randomNumber(0, height);
        this.width = width;
        this.height = height;
        this.in_stationID = -1;

    }

    public int getPhoneID() {
        return phoneID;
    }
    public void setCphoneID(int cphoneID) {
        this.cphoneID = cphoneID;
    }
    public int getCphoneID() {
        return cphoneID;
    }
    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }
    public int getPosition_x() {
        return position_x;
    }
    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }
    public int getPosition_y() {
        return position_y;
    }
    public String getNumber() {
        return number;
    }
    public void setState(int state) {
        this.state = state;
    }
    public void setIn_stationID(int in_stationID) {
        this.in_stationID = in_stationID;
    }
    public int getIn_stationID() {
        return in_stationID;
    }
    public int getState() {
        return state;
    }

    public void move_x(boolean mo) {
        int newx;
        if (mo) {
            newx = getPosition_x() + 1;
            if (newx < width)   setPosition_x(getPosition_x() + 1);
            else                setPosition_x(getPosition_x() - 1);
        } else {
            newx = getPosition_x() - 1;
            if (newx >= 0)      setPosition_x(getPosition_x() - 1);
            else                setPosition_x(getPosition_x() + 1);
        }
    }
    public void move_y(boolean mo){
        int newy;
        if (mo) {
            newy = getPosition_y() + 1;
            if (newy < height)   setPosition_y(getPosition_y() + 1);
            else                setPosition_y(getPosition_y() - 1);
        } else {
            newy = getPosition_y() - 1;
            if (newy >= 0)      setPosition_y(getPosition_y() - 1);
            else                setPosition_y(getPosition_y() + 1);
        }
    }

    @Override
    public int randomNumber(int l, int r) {
        return (int)(Math.random() * (r + 1 - l) + l);
    }

    @Override
    public boolean randomChoice() {
        double r = Math.random();
        return r < 0.5;
    }

}
