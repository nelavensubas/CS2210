/*
 * Assignment 2: This is the file creates the board configuration
 * with its associated score.
 */

public class Record {
	/*
	 * The string representation of the Tic-Tac-Toe board.
	 */
	private String config;
	/*
	 * The score assigned to the configuration.
	 */
	private int score;

	/*
	 * Constructor will create a new configuration with its score.
	 * 
	 * @param config the specified configuration
	 * 
	 * @param score the score
	 */
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}

	/*
	 * getConfig method to get the string representation of configuration
	 * 
	 * @return the configuration
	 */
	public String getConfig() {
		return config;
	}

	/*
	 * getScore method to get the score of the configuration
	 * 
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
}
