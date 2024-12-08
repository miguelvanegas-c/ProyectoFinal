
/**
 * This is an interfaces to all the plants.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public abstract class Plant implements Element {
    protected String name;
    protected int xPosition = 140;
    protected int yPosition = 55;

    /**
     * Create a plant in the row and col that are given.
     * @param row, row of the plant.
     * @param col, col of the plant.
     */
    public Plant(int row, int col) {
        this.xPosition += (col*70);
        this.yPosition += (row*75);
    }
    public String getName(){return this.name;}
    public int getXPosition() {
        return xPosition;
    }
    public int getYPosition() {
        return yPosition;
    }


}
