import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class tictactoe extends JFrame{
	int currentPlayer = 0;
	Square[][]board;
	JButton newgame = new JButton("New Game");
	JLabel player = new JLabel("Player: ");
	JTextField playerlabel = new JTextField(10);
	JPanel gameboard = new JPanel();
	JPanel control = new JPanel();
	JPanel statuspanel = new JPanel();


	public tictactoe (int size) {
		int r,c;
		Square tmp;
		board = new Square[size][size];
		setLayout(new BorderLayout());
		gameboard.setLayout(new GridLayout(size,size));
		for(r=0;r<board.length;r++)
			for(c=0;c<board.length;c++) {
				tmp=new Square(r,c);
				board[r][c] = tmp;
				gameboard.add(tmp);
			}
		add(gameboard, BorderLayout.CENTER);
		newgame.addActionListener(new ClearListener());
		control.add(newgame);
		statuspanel.add(player);
		statuspanel.add(playerlabel);
		add(control, BorderLayout.SOUTH);
		add(statuspanel, BorderLayout.NORTH);
		setSize(size*150,size*150);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Tic Tac Toe");

	}
	class Square extends JButton{
		int row, col; 
		public Square(int r, int c ) {
			row = r; // save location of square
			col = c;
			addActionListener(new SquareListener(r,c));

		}
	}
	class SquareListener implements ActionListener{
		int row, col;
		public SquareListener(int r, int c) {
			row = r;
			col = c;
		}

		public void actionPerformed(ActionEvent e) {
			if (board[row][col].getText().equals("")) {
				if(currentPlayer%2==0) {
					board[row][col].setText("X");
					playerlabel.setText("O is next");
				}
				else {
					board[row][col].setText("O");
					playerlabel.setText("X is next");
				}
				currentPlayer++;
				if(checkWin()) {
					JOptionPane.showMessageDialog(null,"Winner!");
					newgame.doClick();
				}
					
				if(currentPlayer>=9) { // checks if the game is a draw
					JOptionPane.showMessageDialog(null,"Tie Game!");
					newgame.doClick();
				}

			}

		}

		boolean checkWin () {
			int r;
			int c;
			for(r=0;r<board.length;r++) {
				if(board[r][0].getText()=="X"||board[r][0].getText()=="O")
				if (board[r][0].getText()==board[r][1].getText()&&board[r][0].getText()==board[r][2].getText())
				return true;
			}
			 for(c=0;c<board.length;c++) {
				 if(board[0][c].getText()=="X"||board[0][c].getText()=="O")
				if (board[0][c].getText()==board[1][c].getText()&&board[1][c].getText()==board[2][c].getText())
				return true;
			}
			 if(board[0][0].getText()=="X"||board[0][0].getText()=="O")
			 if (board[0][0].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][2].getText())
				 return true;
			 
			 if(board[0][2].getText()=="X"||board[0][2].getText()=="O")
			 if(board[0][2].getText()==board[1][1].getText()&&board[1][1].getText()==board[2][0].getText())
				 return true;
			
			return false;
		}

	}
	public class ClearListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			int r,c;
			for(r=0;r<board.length;r++)
				for(c=0;c<board.length;c++) {
					board[r][c].setText("");
				}
			currentPlayer = 0;
			playerlabel.setText("");
		}

	}
	public static void main(String[] args) {
		new tictactoe(3);

	}
}
