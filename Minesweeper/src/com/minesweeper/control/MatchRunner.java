package com.minesweeper.control;

import java.util.Scanner;
import com.minesweeper.model.Board;

public class MatchRunner {

	public static final String WELCOME = "Welcome to Minesweeper! Please enter the board dimensions and number of mines you want for this match.";;
	private static Board board;

	public static void MatchRunner() {

		Scanner input = new Scanner(System.in);
		String sentence = input.nextLine();
		String[] data = sentence.split(" ");

		int i = Integer.parseInt(data[0]);
		int j = Integer.parseInt(data[1]);
		int mines = Integer.parseInt(data[2]);
		//input.close();

		board = new Board(i, j, mines);

		System.out.println(board.getBoard());
		
		while (board.getFinished()==false && board.getWin()==false) {
			System.out.println("Indicate the x,y coordinates and type for your move");
			
			Scanner move = new Scanner(System.in);
			String moveString = input.nextLine();
			String[] moveData = moveString.split(" ");
			
			int x = Integer.parseInt(moveData[0]);
			int y = Integer.parseInt(moveData[1]);
			String type = moveData[2];
			
			
			//move.close();
			
			if(x<=board.getHeight() && y <= board.getWidth()) {
			
				if (type.equalsIgnoreCase("P")) {
					board.gameMoveP(x, y);
					System.out.print(board.getBoard() + "\n");
					if (board.getWin()==true) {
						System.out.print("You've won!! :)");
					}
				}
				
				if(type.equalsIgnoreCase("U")) {
					
					if(board.verifyContent(x, y).equals("*")) {
						System.out.print(board.gameMoveUWithMine(x, y));
						System.out.print(board.getBoard() + "\n");
						input.close();
						move.close();
					}
					
					else if(!board.verifyContent(x,y).equalsIgnoreCase("*")) {
						board.gameMoveUNoMine(x, y);
						System.out.print(board.getBoard() + "\n");
					}
					
				}
			
			}
		}
		
		input.close();
	}

	public static void main(String[] args) {
		System.out.println(WELCOME);
		MatchRunner();

	}

}
