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
        fillGrid();
    }

    public int size(){
	return _size;
    }

    public int population(){
	return _population;
    }

    public void setPopulation(int newPopulation) {
        _population = newPopulation;
    }

    public int countTotalAlive(){
        int ans = 0;
        for(int row = 0; row < size(); row++){
            for(int col = 0; col < size(); col++){
                if(_grid[row][col].isAlive()){
                    ans++;
                }
            }
        }
        return ans;
    }

    public void fillGrid(){
        for(int row = 0; row < size(); row++){
            for(int col = 0; col < size(); col++){
                Patch p = new Patch(row, col, _grid);
                _grid[row][col] = p;
            }
        }

        Random gen = new Random();
        Random ben = new Random();
        int count = 0;

        while(count < population()){
            int r = gen.nextInt(size());
            int c = ben.nextInt(size());
            _grid[r][c].turnOn();
            count++;
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

    public void go(){
        System.out.println("Starting Game Of Life. This is your board: ");

        fillGrid();
        System.out.println(this);
        System.out.println("population: " + population() + "\n");

        System.out.println("Continue? <Y/N> ");
        String d = Keyboard.readString();
        while(!d.equals("N")) {
            for (int row = 0; row < size(); row++) {
                for (int col = 0; col < size(); col++) {
                    if (_grid[row][col].countAlive() >= 3) {
                        _grid[row][col].turnOn();
                    }
                    else {
                        _grid[row][col].turnOff();
                    }
                }
            }

            System.out.println(this);
            setPopulation(countTotalAlive());
            System.out.println("population: " + population() + "\n");

            if (population()<=0 || population()>=(size()*size())) {
                break;
            }

            System.out.println("Continue? <Y/N> ");
            d = Keyboard.readString();
            clearScreen();
        }
        System.out.println("Game Over");
        return;
    }

    public static void clearScreen(){
        System.out.println("\n\n\n\n\n\n\n\n\n\n");
    }

    public static void main(String[]args){
        Life life = new Life(15, 20);
        life.go();
    }


}