public class BaseStation {

    private int stationID;
    private int position_x;
    private int position_y;
    private int area_R;

    BaseStation(int stationID, int position_x, int position_y, int area_R){
        this.stationID = stationID;
        this.position_x = position_x;
        this.position_y = position_y;
        this.area_R = area_R;
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
    public void setStationID(int stationID) {
        this.stationID = stationID;
    }
    public int getStationID() {
        return stationID;
    }
    public void setArea_R(int area_R) {
        this.area_R = area_R;
    }
    public int getArea_R() {
        return area_R;
    }

}
