/**
 * A class that represents a coordinate on a grid
 * I pledge my honor that I have abided by the Stevens Honor System
 * @author Chris J Landolfi
 */
public class PairInt {
	//Data Fields
	
	/**The 'X' coordinate**/
	private int x;
	
	/**The 'Y' coordinate**/
	private int y;
	
	//Constructors
	
	/**
	 * Constructs a pair of coordinates
	 * @param x The 'X' coordinate
	 * @param y The 'Y' coordinate
	 */
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	//Class Methods
	
	/**
	 * Gets the 'X' coordinate
	 * @return The 'X' coordinate of the pair
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Gets the 'Y' coordinate
	 * @return The 'Y' coordinate of the pair
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Changes the 'X' coordinate
	 * @param x The new 'X' coordinate
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * Changes the 'Y' coordinate
	 * @param x The new 'Y' coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Compares the current set of coordinates to the object p
	 * @param p What will be compared to the coordinates
	 */
	public boolean equals(Object p) {
		if(!(p instanceof PairInt)){
			return false;
		}
		else{
			PairInt P = (PairInt)p;
			return P.x == this.x && P.y ==this.y;
		}
	}
	
	public String toString() {
		return "(" + String.valueOf(x) + "," + String.valueOf(y) + ")";
	}
	
	/**
	 * Creates a copy of the current set of coordinates
	 * @return A copy of the current set of coordinates
	 */
	public PairInt copy() {
		PairInt answer = new PairInt(x,y);
		return answer;
	}
}
