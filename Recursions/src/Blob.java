/**
 * Name(s): Viet Nguyen
 * Date: 28th April 2021
 * CSC 202
 * Lab10--Blob.java
 * 
 * Class that solves problem of counting abnormal cells.
 **/
public class Blob {

    /** The grid */
    private TwoDimGrid grid;

    /** Constructors */
    public Blob(TwoDimGrid grid) {
        this.grid = grid;
    }

    /**
     * Finds the number of cells in the blob at (x,y).
     * Abnormal cells are in GridColors.ABNORMAL color;
     * Other cells are in GridColors.BACKGROUND color.
     * After the method executes, all cells in the blob
     * are in the GridColors.TEMPORARY color.
     * @param x The x-coordinate of a blob cell
     * @param y The y-coordinate of a blob cell
     * @return The number of cells in the blob that contains (x, y)
     */
    public int countCells(int x, int y) {
       if (x < 0 || y < 0 || y >= grid.getNRows() || x >= grid.getNCols()) {
    	   return 0;
       }
       if (grid.getColor(x, y).equals(GridColors.TEMPORARY)) {
    	   return 0;
       }
       if (grid.getColor(x, y).equals(GridColors.ABNORMAL)) {
    	   grid.recolor(x, y, GridColors.TEMPORARY);
    	   return 1 + countCells(x, y-1) + countCells(x, y+1) 
    	   + countCells(x+1, y) + countCells(x-1, y) 
    	   + countCells(x+1, y+1) + countCells(x+1,y-1)
    	   + countCells(x-1, y+1) + countCells(x-1, y-1);
       }
       return 0; 
    }

    
    /** Restore the grid by returning all TEMPORARY cells to ABNORMAL */
    public void restore() {
        grid.recolor(GridColors.TEMPORARY, GridColors.ABNORMAL);
    }
    
}
