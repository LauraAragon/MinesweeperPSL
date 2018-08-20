package com.minesweeper.model;

public class Cell {
	//Constant that represents the content of a cell that is unselected.
	public final static String UNSELECTED = ".";
	//Constant that represents the content of a cell that is disable.
	public final static String DISABLE = "-";
	//Constant that represents the content of a cell that has a mine.
	public final static String MINE = "*";
	//Constant that represents the content of a mine that is mark with a flag.
	public final static String MARKED = "P";
	//Constant that represents the content of a cell that is uncover.
	public final static String UNCOVER = "u";
	//Boolean atribute that shows if a cell contains or not a mine.
	private boolean hasMine;
	//Boolean atribute that shows if a cell has been played or not.
	private boolean playedCell;
	//Boolean atribute that shows if a cell has been mark with a flag or not.
	private boolean markedCell;
	//String atribute that represents the content of the cell, which is displayed in the console.
	private String content;
	
	
	/*
	 * Constructor that initializes the atributes of a cell.
	 */
	public Cell() {
		
		hasMine = false;
		playedCell = false;
		markedCell = false;
		content = ".";
	}
	
	
	/*
	 * Getters and setters for all the atributes.
	 */
	
	
	
	public void setContent(String arg) {
		content = arg;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setHasMine(boolean b) {
		hasMine = b;
	}
	
	public boolean getHasMine() {
		return hasMine;
	}
	
	public void setPlayedCell(boolean b) {
		playedCell = b;
	}
	
	public boolean getPlayedCell() {
		return playedCell;
	}
	
	public void setMarkedCell (boolean b) {
		markedCell = b;
	}
	
	public boolean getMarkedCell() {
		return markedCell;
	}
}
