import java.util.IllegalFormatException;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.lang.InterruptedException;
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

    public void go() throws InterruptedException{
        System.out.println("Starting Game Of Life. This is your board: ");

        fillGrid();
        System.out.println(this);
        String output = "Population: " + population() + "\n";
        System.out.println(output);
        TimeUnit.MILLISECONDS.sleep(650);
        clearScreen();

        //System.out.println("Continue? <Y/N> ");
        //String d = Keyboard.readString();
        while(!output.equals("Game Over")) {
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
            output = "Population: " + population() + "\n";
            System.out.println(output);

            if (population()<=0 || population()>=(size()*size())) {
                output = "Game Over";
            }

            TimeUnit.MILLISECONDS.sleep(650);

            //System.out.println("Continue? <Y/N> ");
            //d = Keyboard.readString();
            clearScreen();
        }
        System.out.println(output);
        return;
    }

    public static void clearScreen(){
        System.out.println("\n\n\n\n\n\n\n\n");
    }

    public static void main(String[]args) throws InterruptedException{
        Scanner s = new Scanner(System.in);
        System.out.println("Enter dimension of board (press ENTER to use default): ");
        String in = s.nextLine();
        int size = 20;
        int population = 50;
        while (!in.equals("")) {
            try {
                size = Integer.parseInt(in);
                System.out.println("Entered "+size);
                break;
            } catch (IllegalFormatException ife) {
                System.out.println("Invalid number, try again: ");
                in = s.nextLine();
            }
        }
        System.out.println("Enter population (press ENTER to use default): ");
        in = s.nextLine();
        while (!in.equals("")) {
            try {
                population = Integer.parseInt(in);
                break;
            } catch (IllegalFormatException ife) {
                System.out.println("Invalid number, try again: ");
                in = s.nextLine();
            }
        }

        Life life = new Life(size, population);
        life.go();
    }


}