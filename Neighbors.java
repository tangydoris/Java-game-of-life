import java.util.ArrayList;

public class Neighbors{

    private ArrayList<Patch> _list;

    public Neighbors(){
	    _list = new ArrayList<Patch>(8);
    }

    public int getAliveNumber(){
        int ans = 0;
        int pass = 0;
        int index = 0;
        while(pass < _list.size()){
            if(_list.get(index).isAlive()){
                ans++;
            }
        }
        return ans;
    }

    public void add(Patch p){
        _list.add(p);
    }

    public Patch get(int index) {
        return (Patch)(_list.get(index));
    }

    public ArrayList<Patch> getList(){
	    return _list;
    }

}