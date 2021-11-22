import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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

public class GUI {

	private JFrame frmPuzzle;
	private final JPanel Controller = new JPanel();
	private ArrayList<Item> items = new ArrayList<Item>();
	JPanel View = new JPanel();
	JButton btnBFS = new JButton("GO BFS");
	private BFS bfs;
	private final Action action = new SwingAction();
	private int delay = 300;
    private int period = 300;
    private int index = 0;
	Timer timer = new Timer();
    private ArrayList<String> path = new ArrayList<String>();
    private final JButton btnNewButton = new JButton("Again");
    private final Action action_1 = new SwingAction_1();

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
		frmPuzzle = new JFrame();
		frmPuzzle.setTitle("PUZZLE");
		frmPuzzle.setBounds(100, 100, 581, 300);
		frmPuzzle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmPuzzle.getContentPane().add(View, BorderLayout.CENTER);
		View.setLayout(new GridLayout(3, 3, 0, 0));
		Controller.setBackground(Color.LIGHT_GRAY);
		
		frmPuzzle.getContentPane().add(Controller, BorderLayout.EAST);
		
		setItems();
		for (Item item : items) {
			View.add(item.getButton());
		}
		Controller.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnBFS.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnBFS.setBackground(Color.LIGHT_GRAY);
		btnBFS.setEnabled(false);
		btnBFS.setEnabled(true);
		Controller.add(btnBFS);
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setAction(action_1);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Controller.add(btnNewButton);
	}
	
	private void setItems() {
		items.add(new Item(2 + "", 0 + ""));
		items.add(new Item(4 + "", 1 + ""));
		items.add(new Item(3 + "", 2 + ""));
		items.add(new Item(1 + "", 3 + ""));
		items.add(new Item(6 + "", 4 + ""));
		items.add(new Item(" " + "", 5 + ""));
		items.add(new Item(7 + "", 6 + ""));
		items.add(new Item(5 + "", 7 + ""));
		items.add(new Item(8 + "", 8 + ""));
		bfs = new BFS(items);
		bfs.Solve();
		btnBFS.setAction(action);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "GO BFS");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		
		public void actionPerformed(ActionEvent e) {
			btnBFS.setEnabled(false);
			path = bfs.getPath();
			timer = new Timer();
		    timer.scheduleAtFixedRate(new TimerTask() {
		 
		        public void run() {
	        		if(index == path.size() - 1) {
	        			timer.cancel();
	        		}
	        		for(int j = 0; j < items.size(); j++) {
			    		items.get(j).getButton().setText(path.get(index).charAt(j) + "");
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
			items.get(0).getButton().setText(2 + "");
			items.get(1).getButton().setText(4 + "");
			items.get(2).getButton().setText(3 + "");
			items.get(3).getButton().setText(1 + "");
			items.get(4).getButton().setText(6 + "");
			items.get(5).getButton().setText(" " + "");
			items.get(6).getButton().setText(7 + "");
			items.get(7).getButton().setText(5 + "");
			items.get(8).getButton().setText(8 + "");
			index = 0;
			btnBFS.setEnabled(true);
		}
	}
}
