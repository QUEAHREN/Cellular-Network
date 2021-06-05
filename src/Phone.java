public class Phone {

    private int phoneID;
    private int cphoneID;
    private String number;
    private int position_x;
    private int position_y;
    private int state;

    Phone(int phoneID, String number, int state){
        this.phoneID = phoneID;
        this.number = number;
        this.state = state;
    }

    public void setPhoneID(int phoneID) {
        this.phoneID = phoneID;
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

    public String getState() {
        String s_state = "";
        if (state == 1)     s_state = getNumber() + " " + "is busy";
        else                s_state = getNumber() + " " + "is free";
        return s_state;
    }

    public int move_x(int x){
        setPosition_x(getPosition_x() + x);
        return  getPosition_x();
    }
    public int move_y(int y){
        setPosition_y(getPosition_y() + y);
        return  getPosition_y();
    }


}
