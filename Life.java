import java.util.Random;
//import Keyboard;

public class Life{

    private Patch[][] _grid;
    private int _population;
    private int _size;

    public Life(int size, int population){
        _size = size;
        _population = population;
        _grid = new Patch[_size][_size];
        //System.out.println(_grid.length);
        fillGrid();
    }

    public int size(){
	return _size;
    }

    public int population(){
	return _population;
    }

    public int countPop(){
        int ans = 0;
        for(int row = 0; row < size(); row++){
            for(int col = 0; col < size(); col++){
                if(_grid[row][col].isAlive()){
                    ans++;
                }
            }
        }
        _population = ans;
        return ans;
    }

    public void fillGrid(){
        for(int row = 0; row < 50; row++){
            for(int col = 0; col < 50; col++){
                Patch p = new Patch(row, col, _grid);
                //System.out.println(p);
                _grid[row][col] = p;
            }
        }
        Random gen = new Random();
        Random ben = new Random();
        //System.out.println(gen+" "+ben);
        while(countPop() < population()){
            int r = gen.nextInt(size());
            int c = ben.nextInt(size());
            _grid[r][c].turnOn();
        }
    }

    public String toString(){
        String ans = "";
        for(int row = 0; row < size(); row++){
            for(int col = 0; col < size(); col++){
            ans += _grid[row][col];
            }
            ans += "\n";
        }
        return ans;
    }

    public int go(){
        System.out.println("Starting Game Of Life. Press Y To Continue: ");
        while(Keyboard.readString() != "N") {
            for (int row = 0; row < 50; row++) {
                for (int col = 0; col < 50; col++) {
                    if (_grid[row][col].countAlive() == 3) {
                        _grid[row][col].turnOn();
                    }
                }
            }

            fillGrid();
            System.out.println(this);
            System.out.println("population: " + population() + "\n");

            System.out.println("Continue <Y/N>: ");
            String d = Keyboard.readString();
            clearScreen();
        }
        return population();
        /*
        if(d.equals("N") || d.equals("n"))
           return -1;
        else
            return population();
        */
    }

    public static void clearScreen(){
        String ans = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        System.out.println(ans);
    }

    public static void main(String[]args){
        Life life = new Life(50, 4);
        life.go();
    }


}