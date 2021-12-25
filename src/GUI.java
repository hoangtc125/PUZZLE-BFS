import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Flow.Subscriber;
import java.awt.Color;
import java.awt.Font;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.sql.Date;

import javax.swing.Action;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class GUI {

	private JFrame frmPuzzle;
	private JPanel Controller = new JPanel(new GridLayout(4, 3, 0, 0));
	private ArrayList<Item> items = new ArrayList<Item>();
	JPanel View = new JPanel();
	JButton btnBFS = new JButton("GO BFS");
	private BFS bfs;
	private Solution solution = new Solution();
	private GenMatrix genMatrix = new GenMatrix();
	private Heuristic heuristic = new Heuristic();
	private final Action action = new SwingAction();
	private int delay = 300;
	private int period = 300;
	private int index = 0;
	private Matrix a;
	private ArrayList<String> path = new ArrayList<String>();
	private final Action action_1 = new SwingAction_1();
	private final JLabel lblNewLabel = new JLabel("Số đỉnh đã xét: 0");
	private final JButton btnGoA = new JButton("GO A*");
	private final JButton btnAgainA = new JButton("Again A*");
	private final JLabel lblSnh = new JLabel("Số đỉnh đã xét: 0");
	private final JLabel lblSngi = new JLabel("Số đường đi: 0");
	private final JLabel lblSngi_1 = new JLabel("Số đường đi: 0");
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();
	private final JButton btnGoHeu = new JButton("GO HEURISTIC");
	private final JButton btnAgainHeu = new JButton("Again Heu");
	private final JLabel lbHeuSoDuong = new JLabel("Số đường đi: 0");
	private final JLabel lbHeuDaxet = new JLabel("Số đỉnh đã xét: 0");
	private final Action action_4 = new SwingAction_4();
	private final Action action_5 = new SwingAction_5();
	private final JTextField tfNum = new JTextField();
	private ArrayList<Integer> genArrayList = new ArrayList<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmPuzzle.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		tfNum.setHorizontalAlignment(SwingConstants.CENTER);
		tfNum.setBackground(Color.LIGHT_GRAY);
		tfNum.setColumns(10);
		frmPuzzle = new JFrame();
		frmPuzzle.setTitle("PUZZLE");
		frmPuzzle.setBounds(10, 10, 1211, 707);
		frmPuzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmPuzzle.getContentPane().add(View, BorderLayout.CENTER);
		View.setLayout(new GridLayout(3, 3, 0, 0));
		Controller.setBackground(Color.LIGHT_GRAY);

		frmPuzzle.getContentPane().add(Controller, BorderLayout.EAST);

		setItems();
		for (Item item : items) {
			View.add(item.getButton());
		}
		btnBFS.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBFS.setBackground(Color.LIGHT_GRAY);
		btnBFS.setEnabled(false);
		btnBFS.setEnabled(true);
		Controller.add(btnBFS);
		btnGoA.setBackground(Color.LIGHT_GRAY);
		btnGoA.setFont(new Font("Tahoma", Font.BOLD, 20));

		Controller.add(btnGoA);
		btnGoHeu.setBackground(Color.LIGHT_GRAY);
		btnGoHeu.setAction(action_4);
		btnGoHeu.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnGoHeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		Controller.add(btnGoHeu);

		Controller.add(tfNum);
		btnAgainA.setBackground(Color.LIGHT_GRAY);
		btnAgainA.setFont(new Font("Tahoma", Font.BOLD, 20));

		Controller.add(btnAgainA);
		btnAgainHeu.setBackground(Color.LIGHT_GRAY);
		btnAgainHeu.setAction(action_5);
		btnAgainHeu.setFont(new Font("Tahoma", Font.BOLD, 20));

		Controller.add(btnAgainHeu);

		Controller.add(lblNewLabel);

		Controller.add(lblSnh);

		Controller.add(lbHeuDaxet);

		Controller.add(lblSngi);

		Controller.add(lblSngi_1);

		Controller.add(lbHeuSoDuong);
	}

	private void setItems() {
		items.add(new Item(2 + "", 0 + ""));
		items.add(new Item(4 + "", 1 + ""));
		items.add(new Item(1 + "", 2 + ""));
		items.add(new Item(5 + "", 3 + ""));
		items.add(new Item(3 + "", 4 + ""));
		items.add(new Item(8 + "", 5 + ""));
		items.add(new Item(6 + "", 6 + ""));
		items.add(new Item(" " + "", 7 + ""));
		items.add(new Item(7 + "", 8 + ""));
		bfs = new BFS(items);
		int[][] aa = { { 0, 2, 4, 1 }, { 0, 5, 3, 8 }, { 0, 6, 9, 7 }, { 0, 0, 0, 0 } };
		int size = 3;
		a = new Matrix(aa, size);
		btnBFS.setAction(action);
		btnGoA.setAction(action_2);
		btnAgainA.setAction(action_3);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "GO BFS");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			bfs.Solve();

			lblNewLabel.setText("Số đỉnh đã xét: " + bfs.getClose().size());
			lblSngi.setText("Đường đi: " + bfs.getPath().size());
			btnBFS.setEnabled(false);
			btnGoA.setEnabled(false);
			path = bfs.getPath();
			final Timer timer = new Timer();
			index = 0;
			timer.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					if (index == path.size() - 1) {
						timer.cancel();
					}
					for (int j = 0; j < items.size(); j++) {
						items.get(j).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\"
								+ (path.get(index).charAt(j) == ' ' ? "9" : path.get(index).charAt(j)) + ".png"));

					}
					index++;
				}

			}, delay, period);
		}

	}

	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Again");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
//			items.get(0).getButton().setText(2 + "");
//			items.get(1).getButton().setText(4 + "");
//			items.get(2).getButton().setText(1 + "");
//			items.get(3).getButton().setText(5 + "");
//			items.get(4).getButton().setText(3 + "");
//			items.get(5).getButton().setText(8 + "");
//			items.get(6).getButton().setText(6 + "");
//			items.get(7).getButton().setText(" " + "");
//			items.get(8).getButton().setText(7 + "");
			items.get(0).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 2 + ".png"));
			items.get(1).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 4 + ".png"));
			items.get(2).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 1 + ".png"));
			items.get(3).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 5 + ".png"));
			items.get(4).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 3 + ".png"));
			items.get(5).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 8 + ".png"));
			items.get(6).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 6 + ".png"));
			items.get(7).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 9 + ".png"));
			items.get(8).getButton().setIcon(new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + 7 + ".png"));
			index = 0;
			btnBFS.setEnabled(true);
			btnGoA.setEnabled(true);
		}
	}

	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "GO A*");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			solution.Solve(a);
			btnBFS.setEnabled(false);
			btnGoA.setEnabled(false);
			lblSnh.setText("Số đỉnh đã xét: " + solution.getClosematrixs().size());
			lblSngi_1.setText("Đường đi: " + solution.getPath().size());
			path = solution.getPath();
//			System.out.println(path.size());
			final Timer timer = new Timer();
			index = 0;
			timer.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					if (index == path.size() - 1) {
						timer.cancel();
					}
					for (int j = 0; j < items.size(); j++) {
						items.get(j).getButton().setIcon(new ImageIcon(
								"C:\\Users\\ADMIN\\Pictures\\puzzle\\" + (path.get(index).charAt(j)) + ".png"));
					}
					index++;
				}

			}, delay, period);
		}
	}

	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Generate");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			int num = Integer.parseInt(tfNum.getText());
			a = genMatrix.Solve(num);
			for (int i = 0; i < a.getClosedMatrix().length(); i++) {
				items.get(i).getButton().setIcon(
						new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + a.getClosedMatrix().charAt(i) + ".png"));
			}
			index = 0;
			btnBFS.setEnabled(true);
			btnGoA.setEnabled(true);
		}
	}

	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "GO HEURISTIC");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			heuristic.Solve(a);
			btnBFS.setEnabled(false);
			btnGoA.setEnabled(false);
			lbHeuDaxet.setText("Số đỉnh đã xét: " + heuristic.getClosematrixs().size());
			lbHeuSoDuong.setText("Đường đi: " + heuristic.getPath().size());
			path = heuristic.getPath();
//			System.out.println(path.size());
			final Timer timer = new Timer();
			index = 0;
			timer.scheduleAtFixedRate(new TimerTask() {

				public void run() {
					if (index == path.size() - 1) {
						timer.cancel();
					}
					for (int j = 0; j < items.size(); j++) {
						items.get(j).getButton().setIcon(new ImageIcon(
								"C:\\Users\\ADMIN\\Pictures\\puzzle\\" + (path.get(index).charAt(j)) + ".png"));
					}
					index++;
				}

			}, delay, period);
		}
	}

	private class SwingAction_5 extends AbstractAction {
		public SwingAction_5() {
			putValue(NAME, "Again Heu");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}

		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < a.getClosedMatrix().length(); i++) {
				items.get(i).getButton().setIcon(
						new ImageIcon("C:\\Users\\ADMIN\\Pictures\\puzzle\\" + a.getClosedMatrix().charAt(i) + ".png"));
			}
			index = 0;
			btnBFS.setEnabled(true);
			btnGoA.setEnabled(true);
		}
	}
}
