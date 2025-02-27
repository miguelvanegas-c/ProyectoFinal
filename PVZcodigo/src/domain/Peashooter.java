
/**
 * This is a peashooter, a plant that shot to the zombies on his row.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */


public class Peashooter extends Plant implements Shooter{
    private int index;

    /**
     * Creator to peashooter
     * @param row, row of the peashooter.
     * @param col, col of the peashooter.
     */
    public Peashooter(int row,int col) {
        super(row,col);
        name = "peashooter";
        life = 300;
        value = 100;
    }

    public Missile shoot(){
        if( index == 6) {
            index++;
            return new Pea(xPosition, yPosition, row, col);
        }
        index++;
        if(index >= 7) index = 0;
        return null;
    }

}
