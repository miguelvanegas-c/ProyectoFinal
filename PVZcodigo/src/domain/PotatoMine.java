public class PotatoMine extends Plant implements Attacker{

    public PotatoMine(int row, int col) {
        super(row, col);
        name = "potatoMine";
        value = 25;
        life = 100;
    }
}
