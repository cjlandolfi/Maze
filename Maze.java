/**
 * Class that solves maze problems with backtracking.
 * I pledge my honor that I have abided by the Stevens Honor System
 * @author Chris J Landolfi
 **/

import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }
    
    public boolean findMazePath() {
        return findMazePath(0, 0);
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */

    public boolean findMazePath(int x, int y) {
        if(x >= 0 && y >= 0 && x < maze.getNCols() && y < maze.getNRows() && maze.getColor(x, y) == NON_BACKGROUND){
        	if(x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)){
        		maze.recolor(x, y, PATH);
        		return true;
        	}
        	else{
        		maze.recolor(x, y, PATH);
        		if(findMazePath(x+1, y) || findMazePath(x-1, y) || findMazePath(x, y+1) || findMazePath(x, y-1)){
        			return true;
        		}
        		else{
        			maze.recolor(x, y, TEMPORARY);
        			return false;
        		}
        	}
        }
        else{
        	return false;
        }
    }
    
    
    /**
     * Finds all possible paths to the end of the maze
     * @param x Starting 'X' coordinate 
     * @param y Starting 'Y' coordinate
     * @return an ArrayList containing ArrayLists of all the possible paths through the maze
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y){
    	if(findMazePath()){
    		maze.recolor(PATH, NON_BACKGROUND);
    		maze.recolor(TEMPORARY, NON_BACKGROUND);
	    	return findAllMazePathsHelp(0,0, new ArrayList<PairInt>());
    	}
    	else{
    		maze.recolor(PATH, NON_BACKGROUND); 
    		maze.recolor(TEMPORARY, NON_BACKGROUND);
    		ArrayList<ArrayList<PairInt>> ret = new ArrayList<ArrayList<PairInt>>();
    		ArrayList<PairInt> empty = new ArrayList<PairInt>(); 
    		ret.add(empty);
    		return ret;
    	}
    }
    /**
     * Helper function for findAllMazePaths
     */
    public ArrayList<ArrayList<PairInt>> findAllMazePathsHelp(int x, int y, ArrayList<PairInt> current ){
    	if(x < maze.getNCols() && x >= 0 && y < maze.getNRows() && y >= 0  && maze.getColor(x, y).equals(NON_BACKGROUND)){
    		current.add(new PairInt(x,y));
	    	if(x == (maze.getNCols()-1) && y == (maze.getNRows()-1)){
	    		ArrayList<ArrayList<PairInt>> answer = new ArrayList<ArrayList<PairInt>>();
	    		@SuppressWarnings("unchecked")
				ArrayList<PairInt> currentCopy = (ArrayList<PairInt>) current.clone();
	    		answer.add(currentCopy);
	    		current.remove(new PairInt(x,y));
	    		return answer;
	    	}
	    	else{
	    		maze.recolor(x, y, PATH);
	    		ArrayList<ArrayList<PairInt>> right = findAllMazePathsHelp(x+1, y, current);
	    		ArrayList<ArrayList<PairInt>> left = findAllMazePathsHelp(x-1, y, current);
	    		ArrayList<ArrayList<PairInt>> up = findAllMazePathsHelp(x, y+1, current);
	    		ArrayList<ArrayList<PairInt>> down = findAllMazePathsHelp(x, y-1, current);
	    		ArrayList<ArrayList<PairInt>> answer = new ArrayList<ArrayList<PairInt>>();
	    		answer.addAll(right);
	    		answer.addAll(left);
	    		answer.addAll(up);
	    		answer.addAll(down);
	    		maze.recolor(x, y, NON_BACKGROUND);
	    		current.remove(new PairInt(x,y));
	    		return answer;
	    	}
    	}
    	else{
    		return new ArrayList<ArrayList<PairInt>>();
    	}
    }
    
    /**
     * Finds the shortest path to the end of the maze
     * @param x Starting 'X' coordinate
     * @param y Starting 'Y' coordinate
     * @return ArrayList containing the smallest path to the end of the maze
     */
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	maze.recolor(PATH, NON_BACKGROUND);
    	ArrayList<ArrayList<PairInt>> answer = findAllMazePaths(x,y);
    	if(answer.size() != 0){
	    	ArrayList<PairInt> min = answer.get(0);
	    	int minLength = min.size();
	    	for(int i=1; i<answer.size(); i++){
	    		if(minLength >= answer.get(i).size()){
	    			min = answer.get(i);
	    			minLength = min.size();
	    		}
	    	}
	    	return min;
    	}
    	else{
    		return new ArrayList<PairInt>();
    	}
    }
    

    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }

    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
}
