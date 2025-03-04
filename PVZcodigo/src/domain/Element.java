
/**
 * This is abstract class with behaviours that share all the ele.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public interface Element {

    String getName();
    int getXPosition();
    int getYPosition();
    int getRow();
    int getCol();
    int getWidth();
    int getHeight();
    String getExtension();
    int getLife();
    void takeDamage(int damage);
}
