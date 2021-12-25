
import java.util.ArrayList;
 
public class Solution {
    private ArrayList<Matrix> open = new ArrayList<Matrix>();
    private ArrayList<String> closematrixs = new ArrayList<String>();
    private ArrayList<String> path = new ArrayList<String>();
    
    public Solution() {
		super();
	}
	public void Solve(Matrix a){
		open.clear();
		closematrixs.clear();
		path.clear();
        open.add(a);
        closematrixs.add(a.getClosedMatrix());
        System.out.println(a.getClosedMatrix());
        while (open.size() > 0){
            Matrix aa = open.get(0);
            System.out.println(aa.getClosedMatrix());
            open.remove(0);
            if(aa.getMtdistance() == 0){
//                System.out.println(aa.getMoveDirection());
//                System.out.println(aa.getParentString());
                String [] arraysolution = aa.getParentString().split("[-]");
                for(int i = 0; i < arraysolution.length; i++){
//                    System.out.println(arraysolution[i]);
                	path.add(arraysolution[i]);
                }
                break;
            }
            if(aa.moveBot() == true){
                Matrix bot = new Matrix();
                bot.isParentMoveBot(aa);
                if(checkclosed(bot.getClosedMatrix()) == true){
                    insertMatrix(bot);
                    closematrixs.add(bot.getClosedMatrix());
                }
            }
            if(aa.moveTop() == true){
                Matrix top = new Matrix();
                top.isParentMoveTop(aa);
                if(checkclosed(top.getClosedMatrix()) == true){
                    insertMatrix(top);
                    closematrixs.add(top.getClosedMatrix());
                    //System.out.println(top.getClosedMatrix());
                }
            }
            if(aa.moveLeft() == true){
                Matrix left = new Matrix();
                left.isParentMoveLeft(aa);
                if(checkclosed(left.getClosedMatrix()) == true){
                    insertMatrix(left);
                    closematrixs.add(left.getClosedMatrix());
                    //System.out.println(left.getClosedMatrix());
                }
            }
            if(aa.moveRight() == true){
                Matrix right = new Matrix();
                right.isParentMoveRight(aa);
                if(checkclosed(right.getClosedMatrix()) == true){
                    insertMatrix(right);
                    closematrixs.add(right.getClosedMatrix());
                    //System.out.println(right.getClosedMatrix());
                }
            }
        }
    }
    public void insertMatrix(Matrix b){
        int index = 0;
        for(int i = 0; i < open.size(); i++){
            if(b.getAstar() > open.get(i).getAstar()){
                index++;
            } else {
                break;
            }
        }
        open.add(index, b);
    }
    public boolean checkclosed(String closedmatrix){
        for(int i = 0; i < closematrixs.size(); i++){
            if(closedmatrix.equals(closematrixs.get(i))){
                return false;
            }
        }
        return true;
    }
	public ArrayList<String> getPath() {
		return path;
	}
	public ArrayList<String> getClosematrixs() {
		return closematrixs;
	}
    
}
