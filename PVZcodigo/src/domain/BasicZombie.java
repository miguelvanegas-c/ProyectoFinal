/**
 * The basicZombies is a zombie that have a basic life and just walks to the plants and eat them.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */

public class BasicZombie extends Zombie{


    /**
     * Constructor of the basic zombie
     * @param row where the zombie is going to appear.
     */
    public BasicZombie(int row) {
        super(row,9);
        value = 100;
    }


    @Override
    public int attack() {
        return 0;
    }
}