import java.util.ArrayList;

public class ComMap implements RandomN{

    private int width;
    private int height;
    private char map[][];
    ArrayList<BaseStation> baseStations = new ArrayList<>();

    ComMap(int width, int height, int num){
        this.width = width;
        this.height = height;
        baseStations = new ArrayList<>();

        int i, j;
        for (i = 0; i < width; i ++) for (j = 0; j < height; j ++)
            map[i][j] = '0';

        for (i = 0; i < num; i ++){
            int x = randomNumber(0, width);
            int y = randomNumber(0, height);
            BaseStation baseStation = new BaseStation(i+1, x, y, 5);
            map[x][y] = '*';
            baseStations.add(baseStation);
        }
    }

    public char[][] getMap() {
        return map;
    }

    public void printMap(){
        for (char[] arr : getMap()){
            for (char n : arr){
                System.out.print(n);
                System.out.print(' ');
            }
            System.out.println();
        }


    }

    @Override
    public int randomNumber(int l, int r) {
        return (int)Math.random() * (r + 1 - l) + l;
    }

    @Override
    public boolean randomChoice() {
        double r = Math.random();
        if (r < 0.5)    return false;
        else            return false;
    }

}
