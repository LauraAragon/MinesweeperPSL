package com.minesweeper.model;

import java.util.Random;

/**
 * This class creates the game board and match according to the height, width
 * and number of mines the player inputs.
 * 
 * @author Laura Aragón
 *
 */

public class Board {

	// A matrix made of cells, which represents the match board
	private Cell[][] board;
	// The number of mines in the match board
	private int minesNumber;
	// The cells correctly marked with a flag by the user
	private int correctlyMarkedCells;
	// Height of the board
	private int height;
	// Width of the board
	private int width;
	// Boolean atribute which identifies if the match has been finished.
	private boolean finished;
	// Boolean atribute which identifies if the player has won the match.
	private boolean win;

	/*
	 * The constructor assigns each atribute it's value. The height and width of the
	 * board, along with the number of mines during the match, it's given by the
	 * player before the match begins.
	 */
	public Board(int i, int j, int mines) {

		height = i;
		width = j;
		board = new Cell[height][width];

		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {

				Cell cell = new Cell();
				board[h][w] = cell;
			}
		}

		minesNumber = mines;
		correctlyMarkedCells = 0;
		finished = false;
		win = false;

		distributeMines();
	}

	/*
	 * This method distributes randomly along the board the number of mines input by
	 * the player at the beginning of the match
	 */
	public void distributeMines() {

		int locatedMines = 0;

		while (locatedMines < minesNumber) {

			Random ri = new Random();
			Random rj = new Random();

			int positioni = ri.nextInt(height);
			int positionj = rj.nextInt(width);

			if (!board[positioni][positionj].getHasMine()) {
				board[positioni][positionj].setHasMine(true);
				locatedMines++;
			}
		}
	}

	/*
	 * This method returns a string with the content of each cell, in order for the
	 * game to display it in the console.
	 */
	public String getBoard() {

		String boardString = "";

		for (int i = 0; i < height; i++) {
			boardString = boardString + "\n";
			for (int j = 0; j < width; j++) {
				Cell cell = board[i][j];
				boardString = boardString + cell.getContent() + " ";
			}
		}

		return boardString;
	}

	public boolean getFinished() {
		return finished;
	}

	public void setFinished(boolean b) {
		finished = b;
	}

	public boolean getWin() {
		return win;
	}

	public void setWin(boolean b) {
		win = b;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	/*
	 * This methods returns the content of a given cell in the board matrix.
	 */
	public String verifyContent(int i, int j) {
		String content = board[i][j].getContent();
		return content;
	}

	/*
	 * This method Marks a cell with a flag. If the cell do have a mine, it adds one
	 * to the correctlyMarkedCell variable. If the correctlyMarkedCell reaches the
	 * number of mines in the board, the game is win and finished.
	 */
	public void gameMoveP(int i, int j) {

		if (board[i][j].getPlayedCell() == true) {
			System.out.print("You've already played this cell, pick another one.");
		}

		if (board[i][j].getPlayedCell() == false) {

			board[i][j].setContent(board[i][j].MARKED);

			if (board[i][j].getHasMine() == true) {
				correctlyMarkedCells++;
			}

			if (board[i][j].getHasMine() == true && correctlyMarkedCells == minesNumber) {
				setFinished(true);
				setWin(true);
			}

		}

	}

	/*
	 * This method describes the logic of a move if it is of the type "Uncover" and
	 * the cell does not have a mine. It checks if the cell selected has a mine, if
	 * it does, the match is over and all the mines on the board are shown.
	 */
	public String gameMoveUWithMine(int i, int j) {

		if (board[i][j].getPlayedCell() == true) {
			System.out.print("You've already played this cell, pick another one.");
		}

		while (board[i][j].getPlayedCell() == false) {

			board[i][j].setPlayedCell(true);
			board[i][j].setContent("*");

			for (int h = 0; h < board.length; h++) {
				for (int w = 0; w < board[h].length; w++) {

					if (board[h][w].getHasMine() == true) {
						board[h][w].setContent("*");
					}
				}
			}
		}
		setFinished(true);
		String message = "You've uncover a mine, match is over :(";

		return message;
	}

	/*
	 * This method counts the number of mines around a given cell.
	 */

	public int minesAround(int i, int j) {
		int minesAroundCell = 0;

		// check up
		if (i - 1 >= 0) {
			if (board[i - 1][j].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check up-right
		if (i - 1 >= 0 && j + 1 < width) {
			if (board[i - 1][j + 1].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check right
		if (j + 1 < width) {
			if (board[i][j + 1].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check down-right
		if (i + 1 < height && j + 1 < width) {
			if (board[i + 1][j + 1].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check down
		if (i + 1 < height) {
			if (board[i + 1][j].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check down-left
		if (i + 1 < height && j - 1 >= 0) {
			if (board[i + 1][j - 1].getHasMine()) {
				minesAroundCell++;
			}
		}

		// check left
		if (j - 1 >= 0) {
			if (board[i][j - 1].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		// check up-left
		if (i - 1 >= 0 && j - 1 >= 0) {
			if (board[i - 1][j - 1].getHasMine() == true) {
				minesAroundCell++;
			}
		}

		return minesAroundCell;
	}

	/*
	 * This method checks if a given cell has mines around. If it doesn't, it checks
	 * the cells around it to see if they have neighboring mines.
	 */
	public void gameMoveUNoMine(int i, int j) {

		while (i >= 0 && j >= 0 && i < height && j < width && board[i][j].getPlayedCell() == false) {
			board[i][j].setPlayedCell(true);

			if (!(minesAround(i, j) == 0)) {
				board[i][j].setContent(Integer.toString(minesAround(i, j)));
			}

			if (minesAround(i, j) == 0) {
				board[i][j].setContent(board[i][j].DISABLE);
				gameMoveUNoMine(i - 1, j);
				gameMoveUNoMine(i - 1, j + 1);
				gameMoveUNoMine(i, j + 1);
				gameMoveUNoMine(i + 1, j + 1);
				gameMoveUNoMine(i + 1, j);
				gameMoveUNoMine(i + 1, j - 1);
				gameMoveUNoMine(i, j - 1);
				gameMoveUNoMine(i - 1, j - 1);
			}
		}
	}

}
