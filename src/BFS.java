import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
	private ArrayList<Item> items = new ArrayList<Item>();
	private ArrayList<String> path = new ArrayList<String>();
	private ArrayList<Map> maps = new ArrayList<Map>();
	private Queue<String> queue = new LinkedList<String>(), close = new LinkedList<String>();
	private String des = "12345678 ";
	
	public BFS(ArrayList<Item> items) {
		super();
		this.items = items;
	}
	
	private String getState() {
		String s = "";
		for (Item item : items) {
			s += item.getValue();
		}
		return s;
	}

	public void Solve() {
		queue.offer(getState());
		while(!queue.isEmpty()) {
			String string = queue.poll();
			String newPos = "";
			if(string.equals(des)) {
				break;
			}
			int pos = string.indexOf(" ");
			if(pos / 3 > 0) { // Up
				newPos = string.substring(0, pos - 3) + string.charAt(pos) + string.substring(pos - 3 + 1, pos)
						+ string.charAt(pos - 3) + string.substring(pos + 1);
				if(!close.contains(newPos)) {
					queue.offer(newPos);
					maps.add(new Map(newPos, string));
				}
			}
			if(pos / 3 < 2) { // Down
				newPos = string.substring(0, pos) + string.charAt(pos + 3) + string.substring(pos + 1, pos + 3)
						+ string.charAt(pos) + string.substring(pos + 3 + 1);
				if(!close.contains(newPos)) {
					queue.offer(newPos);
					maps.add(new Map(newPos, string));
				}
			}
			if(pos % 3 > 0) { // Left
				newPos = string.substring(0, pos - 1) + string.charAt(pos) 
						+ string.charAt(pos - 1) + string.substring(pos + 1);
				if(!close.contains(newPos)) {
					queue.offer(newPos);
					maps.add(new Map(newPos, string));
				}
			}
			if(pos % 3 < 2) { // Right
				newPos = string.substring(0, pos) + string.charAt(pos + 1) 
						+ string.charAt(pos) + string.substring(pos + 1 + 1);
				if(!close.contains(newPos)) {
					queue.offer(newPos);
					maps.add(new Map(newPos, string));
				}
			}
			close.offer(string);
			System.out.println(string);
		}
		path.add(des);
		for(int i = maps.size() - 1; i >= 0; i--) {
			if(path.get(path.size() - 1).equals(getState())) break;
			if(maps.get(i).getKey().equals(path.get(path.size() - 1))) {
				path.add(maps.get(i).getValue());
			}
		}
	}

	public ArrayList<String> getPath() {
		ArrayList<String> pathRev = new ArrayList<String>();
		for(int i = path.size() - 2; i >= 0; i--) {
			pathRev.add(path.get(i));
		}
		return pathRev;
	}
}
