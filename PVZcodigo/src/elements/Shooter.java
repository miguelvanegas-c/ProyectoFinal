import java.io.Serializable;

public interface Shooter  {
    Missile shoot(boolean bandera);
    int getRow();
    int getCol();
}
