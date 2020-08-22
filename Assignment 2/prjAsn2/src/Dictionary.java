/*
 * Assignment 2: This is the file that stores all the
 * configurations of the Tic-Tac-Toe board.
 */

import java.util.LinkedList;

public class Dictionary implements DictionaryADT {
	/*
	 * Creates a LinkedList that will be used to store all the configurations.
	 */
	private LinkedList<Record>[] ticTacToeDict;
	/*
	 * The size of the dictionary.
	 */
	private int size;
	/*
	 * The number of records in the dictionary.
	 */
	private int numRecords;

	/*
	 * Constructor will be used to create an empty dictionary of the specified size.
	 * 
	 * @param size the size of the dictionary
	 */
	public Dictionary(int size) {
		this.size = size;
		ticTacToeDict = new LinkedList[size];

		// Initialize each index of dictionary with a LinkedList to store configurations
		for (int i = 0; i < ticTacToeDict.length; i++) {
			ticTacToeDict[i] = new LinkedList<Record>();
		}
	}

	/*
	 * insert method to insert a new record in the dictionary
	 * 
	 * @param pair an entry for dictionary of type Record that will store a
	 * configuration and score
	 * 
	 * @return 1 if the dictionary already stores at least one record, otherwise a 0
	 * will be returned if the dictionary was empty before the insertion
	 */
	@Override
	public int insert(Record pair) throws DictionaryException {
		// The position of where the record will be put in the dictionary
		int position = polynomialHash(pair.getConfig(), 7069);

		if (ticTacToeDict[position] != null) {
			/*
			 * Go to the specific position that has a list of configurations in it.
			 */
			for (int i = 0; i < ticTacToeDict[position].size(); i++) {
				// Check to see if the configuration has already been added to the dictionary
				if (ticTacToeDict[position].get(i).getConfig().equals(pair.getConfig())) {
					throw new DictionaryException();
				} else {
					ticTacToeDict[position].add(pair);
					numRecords++;
					return 1;
				}
			}
		}

		// Add the new record to the dictionary
		ticTacToeDict[position].add(pair);
		numRecords++;

		return 0;
	}

	/*
	 * remove method removes the entry with the given configuration from the
	 * dictionary.
	 * 
	 * @param config the configuration that will be removed from the dictionary.
	 */
	@Override
	public void remove(String config) throws DictionaryException {
		if (get(config) != -1) {
			int position = polynomialHash(config, 7069);
			ticTacToeDict[position].remove();
		} else {
			throw new DictionaryException();
		}
	}

	/*
	 * get method the score stored in the dictionary for the given configuration
	 * 
	 * @param config the score for the specific configuration
	 * 
	 * @return the score for the configuration or -1 if the configuration doesn't
	 * exist
	 */
	@Override
	public int get(String config) {
		int position = polynomialHash(config, 7069);

		for (int i = 0; i < ticTacToeDict[position].size(); i++) {
			// Looks for the specific configuration in the dictionary to get the score
			if (ticTacToeDict[position].get(i).getConfig().equals(config)) {
				return ticTacToeDict[position].get(i).getScore();
			}
		}

		return -1;
	}

	/*
	 * numElements method displays the number of Record objects stored in the
	 * dictionary
	 * 
	 * @return the number of Record objects
	 */
	@Override
	public int numElements() {
		return numRecords;
	}

	/*
	 * polynomialHash method finds a position to put the record in the dictionary
	 * 
	 * @param config the board configuration that will be stored in the dictionary
	 * 
	 * @param x the size of the table
	 * 
	 * @return the position to store the configuration
	 */
	private int polynomialHash(String config, int x) {
		int val = config.length() - 1;

		/*
		 * Go through the string and look at each char to determine where to put the
		 * record in the dictionary
		 */
		for (int i = config.length() - 2; i >= 0; i--) {
			val = ((val * x) + config.charAt(i)) % size;
		}

		return val;
	}
}
