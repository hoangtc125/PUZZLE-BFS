import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GenMatrix {
	private ArrayList<String> path = new ArrayList<String>();
	private Queue<String> queue = new LinkedList<String>(), close = new LinkedList<String>();
	private String des = "12345678 ";

	public GenMatrix() {
		super();
	}

	private String getState() {
		String s = "123456789";
		return s;
	}

	public Matrix Solve(int num) {
		queue.clear();
		close.clear();
		queue.offer(getState());
		while (!queue.isEmpty() && num > 0) {
			String string = queue.poll();
			num--;
			int mark = 0;
			while (mark == 0) {
				int res = (int) (Math.random() * 4);
				String newPos = "";
				int pos = string.indexOf("9");
				if (pos / 3 > 0 && res == 0) { // Up
					newPos = string.substring(0, pos - 3) + string.charAt(pos) + string.substring(pos - 3 + 1, pos)
							+ string.charAt(pos - 3) + string.substring(pos + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos / 3 < 2 && res == 1) { // Down
					newPos = string.substring(0, pos) + string.charAt(pos + 3) + string.substring(pos + 1, pos + 3)
							+ string.charAt(pos) + string.substring(pos + 3 + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos % 3 > 0 && res == 2) { // Left
					newPos = string.substring(0, pos - 1) + string.charAt(pos) + string.charAt(pos - 1)
							+ string.substring(pos + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				if (pos % 3 < 2 && res == 3) { // Right
					newPos = string.substring(0, pos) + string.charAt(pos + 1) + string.charAt(pos)
							+ string.substring(pos + 1 + 1);
					if (!close.contains(newPos)) {
						queue.offer(newPos);
						mark = 1;
					}
				}
				close.offer(string);
			}

		}
		String ans = queue.poll();
//		System.out.println(ans);
		int[][] aa = { { 0, 2, 4, 1 }, { 0, 5, 3, 8 }, { 0, 6, 9, 7 }, { 0, 0, 0, 0 } };
		aa[0][1] = Integer.parseInt(ans.charAt(0) + "");
		aa[0][2] = Integer.parseInt(ans.charAt(1) + "");
		aa[0][3] = Integer.parseInt(ans.charAt(2) + "");
		aa[1][1] = Integer.parseInt(ans.charAt(3) + "");
		aa[1][2] = Integer.parseInt(ans.charAt(4) + "");
		aa[1][3] = Integer.parseInt(ans.charAt(5) + "");
		aa[2][1] = Integer.parseInt(ans.charAt(6) + "");
		aa[2][2] = Integer.parseInt(ans.charAt(7) + "");
		aa[2][3] = Integer.parseInt(ans.charAt(8) + "");
		int size = 3;
		Matrix a = new Matrix(aa, size);
		return a;
	}

	public ArrayList<String> getPath() {
		ArrayList<String> pathRev = new ArrayList<String>();
		for (int i = path.size() - 1; i >= 0; i--) {
			pathRev.add(path.get(i));
		}
		return pathRev;
	}

	public Queue<String> getClose() {
		return close;
	}

}
