

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * This is the window of the gameBoard.
 *
 * @author Miguel Angel Vanegas y Julian Castiblanco.
 * @version 1.0
 */
public class PVZInGame extends JFrame implements GeneralInterface {
    private JPanel gamePanel;
    private JMenuItem open, save, newItem, exit;
    private String gameMode, selectedPlant, plantPlayer,zombiePlayer,plantsType,zombieType;
    private String[] plantsToPlay;
    private String[] zombiesToPlay;
    private JButton[] plantsButtons;
    private JButton[] zombiesButtons;
    private JButton[][] cells = new JButton[5][11];
    private PVZ pvz;
    private int sunCount,brainCount,gameTime,hordesTime,hordesNumber;


    /**
     * Constructor for PVZInGame PvsM.
     *
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param plantPlayer    The plant player name.
     * @param zombieType     The zombie type.
     * @param startingBrains The start number of brains.
     * @param startingSuns   The start number of suns.
     * @param gameTime       The time in game.
     * @param hordesTime     The hordes duration time.
     * @param hordesNumber   The number of hordes for game.
     *
     */
    public PVZInGame(String gameMode, HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay,String plantPlayer,String zombieType, int startingBrains, int startingSuns, int gameTime, int hordesTime,int hordesNumber) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, true);
        this.plantPlayer = plantPlayer;
        this.zombieType = zombieType;
        this.sunCount = startingSuns;
        this.brainCount = startingBrains;
        this.gameTime = gameTime;
        this.hordesTime = hordesTime;
        this.hordesNumber = hordesNumber;
        prepareElements();
        prepareActions();
        refresh();

    }

    /**
     * Constructor for PVZInGame PvsP.
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param plantPlayer    The plant player name.
     * @param zombiePlayer   The zombie player name.
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param startingBrains The start number of brains.
     * @param startingSuns   The start number of suns.
     */
    public PVZInGame( HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay,String plantPlayer,String zombiePlayer,String gameMode,int startingBrains, int startingSuns, int gameTime) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, true);
        this.plantPlayer = plantPlayer;
        this.zombiePlayer = zombiePlayer;
        this.sunCount = startingSuns;
        this.brainCount = startingBrains;
        this.gameTime = gameTime;
        prepareElements();
        prepareActions();
        refresh();
        System.out.println(sunCount);

    }

    /**
     * Constructor for PVZInGame MvsM.
     * @param gameMode       The game mode ("PvsP", "PvsM", or "MvsM").
     * @param zombieType     The zombie type.
     * @param plantType      The plant type.
     * @param plantasToPlay  The set of plants available to play.
     * @param zombiesToPlay  The set of zombies available to play.
     * @param gameTime       The time in game.
     * @param hordesTime     The hordes duration time.
     * @param hordesNumber   The number of hordes for game.
     */
    public PVZInGame(String plantType,String zombieType,String gameMode,HashSet<String> plantasToPlay,HashSet<String> zombiesToPlay, int startingBrains, int startingSuns, int gameTime, int hordesTime,int hordesNumber) {
        this.gameMode = gameMode;
        this.plantsToPlay = plantasToPlay.toArray(new String[0]);
        plantsButtons = new JButton[this.plantsToPlay.length];
        this.zombiesToPlay = zombiesToPlay.toArray(new String[0]);
        pvz = new PVZ(this.plantsToPlay,this.zombiesToPlay, true);
        this.plantsType = plantType;
        this.zombieType = zombieType;
        this.sunCount = startingSuns;
        this.brainCount = startingBrains;
        this.gameTime = gameTime;
        this.hordesTime = hordesTime;
        this.hordesNumber = hordesNumber;
        prepareElements();
        prepareActions();
        refresh();

    }

    /*
     * Prepares all the graphical and logical components of the game.
     */
    private void prepareElements() {
        prepareElementsMenu();
        createPanel();
        if(gameMode.equals("PvsP")) {
            prepareButtonsZombies();
            prepareButtonsPlants();
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantPlayer+"</span></html>");
            label.setBounds(20,0,plantPlayer.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombiePlayer+"</span></html>");
            label2.setBounds(920,0,zombiePlayer.length()*20,50);
            gamePanel.add(label2);
        }
        if(gameMode.equals("PvsM")){
            prepareButtonsPlants();
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantPlayer+"</span></html>");
            label.setBounds(20,0,plantPlayer.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombieType+"</span></html>");
            label2.setBounds(920,0,zombieType.length()*20,50);
            gamePanel.add(label2);
        }
        if(gameMode.equals("MvsM")){
            JLabel label = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+plantsType+"</span></html>");
            label.setBounds(20,0,plantsType.length()*20,50);
            gamePanel.add(label);
            JLabel label2 = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+zombieType+"</span></html>");
            label2.setBounds(920,0,zombieType.length()*20,50);
            gamePanel.add(label2);
        }
        changeSizeToImage("fondoTablero.png");
        if(!gameMode.equals("MvsM"))prepareButtonsTablero();


    }
    /*
     * Creates and configures the game panel where the game board and elements are drawn.
     */
    private void createPanel(){
        // Crear panel con las plantas que se van a jugar
        ImageIcon icon = getImageIcon("fondoTablero.png");
        Image originalImage = icon.getImage();

        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);
                //zone of plants anzombies select.
                g.drawImage(originalImage, 0, 0, getWidth(), getHeight(), null);
                int count = 50;
                for (String planta : plantsToPlay) {
                    ImageIcon iconPlant = getImageIcon(planta+".png");
                    Image originalImagePlant = iconPlant.getImage();
                    g.drawImage(originalImagePlant, 20, count, 40, 60, null);
                    count += 70;
                }
                count = 50;
                for (String zombie : zombiesToPlay) {
                    ImageIcon iconZombie = getImageIcon(zombie + ".png");
                    Image originalImageZombie = iconZombie.getImage();
                    g.drawImage(originalImageZombie, 930, count, 60, 60, null);
                    count += 70;
                }
                for(List<Element>[] matrizElements: pvz.getBoard()) {
                    for (List<Element> elementList : matrizElements) {
                        for (Element element : elementList) {
                            if (element != null) {
                                ImageIcon iconZombie = getImageIcon(element.getName()+"G.png");
                                Image originalImageZombie = iconZombie.getImage();
                                int width =(element instanceof Plant || element instanceof Coin)? 50:70;
                                int height = (element instanceof Plant || element instanceof Coin)? 50:70;;

                                g.drawImage(originalImageZombie, element.getXPosition(), element.getYPosition(),width , height, null);

                            }
                        }
                    }
                }
                //name or type zone
                g.setColor(new Color(101, 67, 33));
                g.fillRect(10, 5,250, 40);
                g.setColor(new Color(100, 100, 100));
                g.fillRect(910, 5, 250, 40);
                //sun counter
                g.setColor(new Color(139, 69, 19));
                g.fillRect(280, 5, 120, 40);
                ImageIcon iconSun = getImageIcon( "sun.png");
                Image originalImageSun = iconSun.getImage();
                g.drawImage(originalImageSun, 285, 10, 30, 30, null);
                JLabel sunLabel = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+sunCount+"</span></html>");
                sunLabel.setBounds(320,5,80,40);
                gamePanel.add(sunLabel);
                //brain counter
                g.setColor(new Color(150, 150, 150));
                g.fillRect(760, 5, 120, 40);
                ImageIcon iconBrain = getImageIcon( "brain.png");
                Image originalImageBrain = iconBrain.getImage();
                g.drawImage(originalImageBrain, 765, 10, 30, 30, null);
                JLabel brainLabel = new JLabel("<html><span style='font-size:20px; letter-spacing:5px;'>"+brainCount+"</span></html>");
                brainLabel.setBounds(800 ,5,80,40);
                gamePanel.add(brainLabel);




            }
        };

        gamePanel.setLayout(null); // Usar layout absoluto para colocar botones
        setContentPane(gamePanel);
    }
    /*
     * Adjusts the JFrame size to match the dimensions of an image.
     *
     * @param imageName The name of the image file.
     */
    private void changeSizeToImage(String imageName) {
        ImageIcon icon = getImageIcon(imageName);

        int imageWidth = icon.getIconWidth();
        int imageHeight = icon.getIconHeight();

        setSize(imageWidth, imageHeight);

        setLocationRelativeTo(null);
        setResizable(false);
    }

    /*
     * Prepares the menu bar for the game.
     */
    private void prepareElementsMenu(){
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        open = new JMenu("Open");
        save = new JMenuItem("Save");
        newItem = new JMenuItem("New");
        exit = new JMenuItem("Exit");

        menu.add(open);
        menu.add(save);
        menu.add(newItem);
        menu.add(exit);

        menuBar.add(menu);
        setJMenuBar(menuBar);

    }
    /*
     * Prepares the game board (grid of cells) for the game.
     * Each cell is represented by a transparent button.
     */
    private void prepareButtonsTablero() {
        int startCol = 140;
        int startRow =55;
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 10; col++) {
                cells[row][col] = new TransparentButton("");
                cells[row][col].setBounds(startCol,startRow,70,75);
                gamePanel.add(cells[row][col]);
                startCol +=70 ;
            }
            startCol = 140;
            startRow += 75 ;
        }
    }

    /*
     * Prepares the buttons representing plants available for the player.
     * Buttons are aligned vertically on the left side of the game panel.
     */
    private void prepareButtonsPlants() {
        int row = 50;
        int len;
        len = plantsToPlay.length;
        plantsButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            plantsButtons[i] = new BorderButton(" ");
            plantsButtons[i].setBounds(20,row,40,60);
            row += 70;
            gamePanel.add(plantsButtons[i]);
        }
    }

    /*
     * Prepares the buttons representing zombies available for the player.
     * Buttons are aligned vertically on the left side of the game panel.
     */
    private void prepareButtonsZombies() {
        int row = 50;
        int len;
        len = zombiesToPlay.length;
        zombiesButtons = new JButton[len];
        for (int i = 0; i < len; i++) {
            zombiesButtons[i] = new BorderButton(" ");
            zombiesButtons[i].setBounds(930,row,60,60);
            row += 70;
            gamePanel.add(zombiesButtons[i]);
        }
    }

    /*
     * Prepares all actions and listeners for game interactions.
     */
    public void prepareActions(){
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                closeWindowAction();
            }
        });
        exit.addActionListener(e -> closeWindowAction());
        newItem.addActionListener(e -> openPrincipalWindow());
        int plantsLen = plantsButtons.length;
        for(int i = 0; i < plantsLen; i++) {
            String plant = plantsToPlay[i];
            if (plantsButtons[i] != null) plantsButtons[i].addActionListener(e ->selectPlant(plant));
        }
        for(int row = 0; row < 5; row++) {
            for(int col = 0; col < 10; col++) {
                int i = row;
                int j = col;
                if (cells[i][j] != null) cells[row][col].addActionListener(e -> addPlant(i,j));
            }
        }

        Timer repaintTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        repaintTimer.start();

    }
    /*
     * Adds a plant to the game board at the specified row and column.
     *
     * @param row The row index where the plant will be added.
     * @param col The column index where the plant will be added.
     */
    private void addPlant(int row, int col) {
        try {
            pvz.addPlant(row, col, selectedPlant);
            gamePanel.repaint();
            selectedPlant = null;
        } catch (PVZException e) {
            JDialog dialog = new JDialog();
            JLabel label = new JLabel(e.getMessage());
            label.setBounds(20, 20, 70, 70);
            label.setFont(new Font("DialogInput", Font.BOLD, 10));
            dialog.setTitle("ERROR");
            dialog.add(label);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null); // Centrar en la pantalla
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
            new Timer(1000, s -> dialog.dispose()).start();
        }
    }

    /*
     * Selects a plant to be placed on the game board.
     *
     * @param planta The name of the plant to be selected.
     */
    private void selectPlant(String planta){
        selectedPlant = planta;
    }

    /*
     * Opens the main menu window and closes the current game window.
     */
    private void openPrincipalWindow() {
        int opcion = JOptionPane.showConfirmDialog(this, "Do you want to come to the start screen?", "Confirm back", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            PVZGUI pvzguiwindow = new PVZGUI();
            pvzguiwindow.setVisible(true);

            dispose();
        }
    }

    /*
     * Handles the action for closing the window. Prompts the user for confirmation.
     */
    private void closeWindowAction() {
        int opcion = JOptionPane.showConfirmDialog(null, "Are you sure that tou want to exit?", "Confirm exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_NO_OPTION) {
            System.exit(0);
        }
    }

    /*
     * Refreshes the game panel by repainting it.
     */
    private void refresh(){
        gamePanel.repaint();
    }

}
