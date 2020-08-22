/*
 * Assignment 2: This is the file that will have all methods needed to run
 * the Tic-Tac-Toe game.
 */

public class nk_TicTacToe {
	/*
	 * The size of the Tic-Tac-Toe board.
	 */
	private int board_size;
	/*
	 * The number of symbols in-line needed to win the game.
	 */
	private int inline;
	/*
	 * The number of levels the game tree will explore in the program.
	 */
	private int max_levels;
	/*
	 * The 2D Array that will store the game board.
	 */
	private char[][] gameBoard;

	/*
	 * Constructor will initialize the create the game board used for the game.
	 * 
	 * @param board_size the size of the game board
	 * 
	 * @param inline the number of symbols in-line needed to win
	 * 
	 * @param max_levels the number of levels the game tree will explore
	 */
	public nk_TicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;

		gameBoard = new char[board_size][board_size];

		// Create the game board and initializes it with empty spaces
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				gameBoard[row][col] = ' ';
			}
		}
	}

	/*
	 * createDictionary method will create an empty dictionary of the size of the
	 * game board.
	 * 
	 * @return the empty dictionary of the game board size
	 */
	public Dictionary createDictionary() {
		return new Dictionary(board_size);
	}

	/*
	 * repeatedConfig method checks to see if the string representation of the
	 * gameboard is the in the configurations dictionary.
	 * 
	 * @param configurations dictionary that has a list of configurations
	 * 
	 * @return the score for the associated dictionary if it exists
	 */
	public int repeatedConfig(Dictionary configurations) {
		String config = "";
		// Goes through each character in the game board and stores it in a string
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				config += gameBoard[row][col];
			}
		}
		return configurations.get(config);
	}

	/*
	 * insertConfig method inserts the string representation of the game board and
	 * it's associated score as a Record object.
	 * 
	 * @param configurations a dictionary with a list of configurations
	 * 
	 * @param score the score associated with the configuration
	 */
	public void insertConfig(Dictionary configurations, int score) {
		String config = "";
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				config += gameBoard[row][col];
			}
		}
		configurations.insert(new Record(config, score));
	}

	/*
	 * storePlay method stores the symbol at a specific position in the game board
	 * 
	 * @param row the row that will store the symbol
	 * 
	 * @param col the column that will the store the symbol
	 * 
	 * @param symbol either an 'X' or 'O' that will be stored in the game board
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	}

	/*
	 * squareIsEmpty method checks to see if a specific square in the game board is
	 * empty
	 * 
	 * @param row the row to check if the square is empty
	 * 
	 * @param column the column to check if the square is empty
	 * 
	 * @return true if the square is empty or false if it is not
	 */
	public boolean squareIsEmpty(int row, int col) {
		if (gameBoard[row][col] == ' ') {
			return true;
		}
		return false;
	}

	/*
	 * wins method will check to see if there is a winner
	 * 
	 * @param symbol the symbol to check if there are number of adjacent occurrences
	 * of the symbol in the same row, column, or diagonal of the game board
	 * 
	 * @return true if there is a winner or false if there isn't a winner
	 */
	public boolean wins(char symbol) {
		/*
		 * The counter variable will be used to check if the number of symbols in-line
		 * needed exists to win the game.
		 */
		int counter = 0;

		// Goes through the game board horizontally to find a winner
		for (int i = 0; i < board_size; i++) {
			counter = 0;
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == symbol) {
					counter++;
				} else {
					counter = 0;
				}
				if (counter == inline) {
					return true;
				}
			}
		}

		// Goes through the game board veritcally to find a winner
		for (int i = 0; i < board_size; i++) {
			counter = 0;
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[j][i] == symbol) {
					counter++;
				} else {
					counter = 0;
				}
				if (counter == inline) {
					return true;
				}
			}
		}

		// Goes through the board diagonally from left to right
		int diag = 0;
		for (int k = 0; k < board_size; k++) {
			diag = k;
			counter = 0;
			for (int i = 0; i < board_size; i++) {
				if (gameBoard[i][diag] == symbol) {
					counter++;
				} else {
					counter = 0;
				}

				if (counter == inline) {
					return true;
				}

				if (diag + 1 < board_size) {
					diag++;
				}
			}
		}

		// Goes through the board diagonally from right to left
		for (int i = board_size - 1; i >= 0; i--) {
			diag = i;
			counter = 0;
			for (int k = 0; k < board_size; k++) {
				if (gameBoard[k][diag] == symbol) {
					counter++;
				} else {
					counter = 0;
				}

				if (counter == inline) {
					return true;
				}

				if (diag - 1 >= 0) {
					diag--;
				}
			}
		}

		return false;
	}

	/*
	 * isDraw method checks to see if there are no empty positions left and no
	 * player has won
	 * 
	 * @return true if there are no more available moves for the players to make and
	 * there is no winner, otherwise, false.
	 */
	public boolean isDraw() {
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == ' ') {
					return false;
				}
			}
		}
		return true;
	}

	/*
	 * evalBoard method gives a final score of the game after determining the result
	 * 
	 * @return a score based on which player has won, if the game is tied, or if the
	 * game has been undecided
	 */
	public int evalBoard() {
		if (wins('O')) {
			return 3;
		} else if (wins('X')) {
			return 0;
		} else if (wins('O') == false && wins('X') == false && isDraw()) {
			return 2;
		}
		return 1;
	}

}
