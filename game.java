import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.util.Iterator;


// Main Game class
public class game extends JPanel {
    private JFrame frame;
    private Gardener gardener;
    private List<Level> levels;
    private int selectedLevelIndex = -1;
    private Image startBackground;
    private Image[] backgrounds = new Image[3];
    private Image gardenerImg;
    private Image potatoImg;
    private Image moleRightImg, moleLeftImg;
    private boolean onStartScreen = true;
    private JButton map1Button, map2Button, map3Button;
    private Image heartImg;

    public game() {
        gardener = new Gardener("Player1", 1, 100, 230);  // Adjusted starting position for Gardener
        loadImages();
        frame = new JFrame("Potato Protector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 420);
        frame.add(this);
        frame.setVisible(true);

        map1Button = createMapButton("Level 1", 200, 300, 0);
        map2Button = createMapButton("Level 2", 350, 300, 1);
        map3Button = createMapButton("Level 3", 500, 300, 2);
        map1Button.setBackground(Color.GREEN);
        map2Button.setBackground(Color.YELLOW);
        map3Button.setBackground(Color.RED);

        this.setLayout(null);
        this.add(map1Button);
        this.add(map2Button);
        this.add(map3Button);

        initializeLevels();

        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!onStartScreen) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_A:
                            gardener.move(-10);
                            break;
                        case KeyEvent.VK_D:
                            gardener.move(10);
                            break;
                        case KeyEvent.VK_SPACE:
                            gardener.attack(levels.get(selectedLevelIndex).getMoles());
                            break;
                        default:
                            break;
                    }
                    repaint();
                }
            }
        });
    }

    private JButton createMapButton(String label, int x, int y, int levelIndex) {
        JButton button = new JButton(label);
        button.setBounds(x, y, 100, 50);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedLevelIndex = levelIndex;
                startGame();
                hideMapButtons();
            }
        });
        return button;
    }

    private void hideMapButtons() {
        map1Button.setVisible(false);
        map2Button.setVisible(false);
        map3Button.setVisible(false);
    }

    private void loadImages() {
        try {
            startBackground = new ImageIcon("picture/backgroundStart.jpg").getImage();
            backgrounds[0] = new ImageIcon("picture/level1.jpg").getImage();
            backgrounds[1] = new ImageIcon("picture/level2.jpg").getImage();
            backgrounds[2] = new ImageIcon("picture/level3.jpg").getImage();

            gardenerImg = new ImageIcon("picture/gardener2.png").getImage();
            potatoImg = new ImageIcon("picture/potato2.png").getImage();
            moleRightImg = new ImageIcon("picture/moleRight.png").getImage();
            moleLeftImg = new ImageIcon("picture/moleLeft.png").getImage();

            
            heartImg = new ImageIcon("picture/heart.png").getImage();
            heartImg = heartImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);


            startBackground = startBackground.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            for (int i = 0; i < 3; i++) {
                backgrounds[i] = backgrounds[i].getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            }
            gardenerImg = gardenerImg.getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            potatoImg = potatoImg.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            moleRightImg = moleRightImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            moleLeftImg = moleLeftImg.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        } catch (Exception e) {
            System.out.println("Error loading images: " + e.getMessage());
        }
    }

    public void initializeLevels() {
        levels = new ArrayList<>();
    
        // Level 1
        Potato potato1 = new Potato(3, 360, 260);
        List<Mole> moles1 = new ArrayList<>();
        // Moles from the right
        moles1.add(new Mole(3, 1000, 270, 2));
        moles1.add(new Mole(3, 1200, 270, 2));
        moles1.add(new Mole(3, 1400, 270, 2));
        moles1.add(new Mole(3, 1800, 270, 2));
        // Moles from the left
        moles1.add(new Mole(3, -400, 270, 2));
        moles1.add(new Mole(3, -600, 270, 2));
        moles1.add(new Mole(3, -1000, 270, 2));
        moles1.add(new Mole(3, -1200, 270, 2));
        levels.add(new Level(potato1, moles1));
    
        // Level 2
        Potato potato2 = new Potato(3, 360, 260);
        List<Mole> moles2 = new ArrayList<>();
        moles2.add(new Mole(3, 1000, 270, 2));
        moles2.add(new Mole(3, 1400, 270, 2));
        moles2.add(new Mole(3, 1600, 270, 2));
        moles2.add(new FastMole(2, 2000, 270, 2));
        moles2.add(new FastMole(2, 2800, 270, 2));
        // Moles from the left
        moles2.add(new Mole(3, -400, 270, 2));
        moles2.add(new Mole(3, -600, 270, 2));;
        moles2.add(new Mole(3, -1000, 270, 2));
        moles2.add(new Mole(3, -1200, 270, 2));
        moles2.add(new FastMole(2, -1800, 270, 2));
        levels.add(new Level(potato2, moles2));
    
        // Level 3
        Potato potato3 = new Potato(3, 360, 260);
        List<Mole> moles3 = new ArrayList<>();
        // Moles from the right
        moles3.add(new Mole(3, 1000, 270, 2));
        moles3.add(new Mole(3, 1400, 270, 2));
        moles3.add(new Mole(3, 1600, 270, 2));
        moles3.add(new Mole(3, 1900, 270, 2));
        moles3.add(new FastMole(2, 2400, 270, 2));
        moles3.add(new FastMole(2, 2800, 270, 2));
        moles3.add(new FastMole(2, 3000, 270, 2));
        moles3.add(new FastMole(2, 3400, 270, 2));
        // Moles from the left
        moles3.add(new Mole(3, -600, 270, 2));;
        moles3.add(new Mole(3, -1000, 270, 2));
        moles3.add(new Mole(3, -1200, 270, 2));
        moles3.add(new Mole(3, -1500, 270, 2));
        moles3.add(new Mole(3, -1800, 270, 2));
        moles3.add(new FastMole(2, -1800, 270, 2));
        moles3.add(new FastMole(2, -2400, 270, 2));
        moles3.add(new FastMole(2, -2800, 270, 2));
        levels.add(new Level(potato3, moles3));
    }
    

    private Timer gameTimer;

    public void startGame() {
        onStartScreen = false;
        Level currentLevel = levels.get(selectedLevelIndex);

        gameTimer = new Timer(30, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!currentLevel.getPotato().isAlive()) {
                    showGameOverScreen();
                    gameTimer.stop();
                } else if (currentLevel.isComplete()) {
                    showVictoryScreen();
                    gameTimer.stop();
                } else {
                    currentLevel.start();
                    repaint();
                }
            }
        });

        gameTimer.start();
    }

    private void showGameOverScreen() {
        JOptionPane.showMessageDialog(frame, "Game Over!");
        resetGame();
    }

    private void showVictoryScreen() {
        JOptionPane.showMessageDialog(frame, "Victory! You completed the level.");
        resetGame();
    }

    private void resetGame() {
        onStartScreen = true;
        selectedLevelIndex = -1;
        initializeLevels();  
        showMapButtons();
        repaint();
    }

    private void showMapButtons() {
        map1Button.setVisible(true);
        map2Button.setVisible(true);
        map3Button.setVisible(true);
    }

    @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    //Graphics Rendering
    Graphics2D g2d = (Graphics2D) g;
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (onStartScreen) {
        g.drawImage(startBackground, 0, 0, this);
    } else {
        g.drawImage(backgrounds[selectedLevelIndex], 0, 0, this);
        g.drawImage(gardenerImg, gardener.getPositionX(), gardener.getPositionY(), this);
        g.drawImage(potatoImg, levels.get(selectedLevelIndex).getPotato().getPositionX(),
                levels.get(selectedLevelIndex).getPotato().getPositionY(), this);

        
        for (Mole mole : levels.get(selectedLevelIndex).getMoles()) {
            Image moleImg = mole.getPositionX() > levels.get(selectedLevelIndex).getPotato().getPositionX()
                    ? moleLeftImg : moleRightImg;
            g.drawImage(moleImg, mole.getPositionX(), 270, this);
        }

        // Draw hearts
        int heartX = 10; 
        int heartY = 10; 

        Potato currentPotato = levels.get(selectedLevelIndex).getPotato();
        for (int i = 0; i < currentPotato.getHealth(); i++) {
            g.drawImage(heartImg, heartX + i * 35, heartY, this); 
        }
    }
}

    public static void main(String[] args) {
        new game();
    }
}

// Gardener 
class Gardener {
    private String name;
    private int health;
    private int positionX;
    private int positionY;

    public Gardener(String name, int health, int positionX, int positionY) {
        this.name = name;
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() { return positionX; }
    public int getPositionY() { return positionY; }

    public void move(int dx) {
        positionX +=  dx ;
    }

    public void attack(List<Mole> moles) {
        moles.removeIf(mole -> Math.abs(mole.getPositionX() - positionX) < 50);
    }
}

// Potato 
class Potato {
    private int health;
    private int positionX;
    private int positionY;

    public Potato(int health, int positionX, int positionY) {
        this.health = health;
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() { return positionX; }
    public int getPositionY() { return positionY; }

    public void reduceHealth() { health--; }
    public boolean isAlive() { return health > 0; }

    public int getHealth() { return health; }
}

// Mole 
class Mole {
    private int speed;
    private int positionX;
    private int positionY;
    private int health;

    public Mole(int speed, int positionX, int positionY, int health) {
        this.speed = speed;
        this.positionX = positionX;
        this.positionY = positionY;
        this.health = health;
    }

    public int getPositionX() { return positionX; }

    // Move left
    public void moveLeft() {
        positionX -= speed;
    }

    // Move right
    public void moveRight() {
        positionX += speed;
    }

    public int getHealth() { return health; }
    public void reduceHealth() { health--; }
}

// FastMole 
class FastMole extends Mole {
    public FastMole(int speed, int positionX, int positionY, int health) {
        super(speed + 3, positionX, positionY, health);
    }
}

// Level class
class Level {
    private Potato potato;
    private List<Mole> moles;

    public Level(Potato potato, List<Mole> moles) {
        this.potato = potato;
        this.moles = moles;
    }

    public Potato getPotato() { return potato; }
    public List<Mole> getMoles() { return moles; }

    public void start() {
        Iterator<Mole> moleIterator = moles.iterator();
        while (moleIterator.hasNext()) {
            Mole mole = moleIterator.next();

            // Move mole  starting position
            if (mole.getPositionX() > potato.getPositionX()) {
                mole.moveLeft(); // Moves mole from right to left
            } else {
                mole.moveRight(); // Moves mole from left to right
            }

            // Check if mole is close to potato for "attack"
            if (Math.abs(mole.getPositionX() - potato.getPositionX()) < 50) {
                mole.reduceHealth();
                if (mole.getHealth() <= 0) {
                    moleIterator.remove(); // Remove mole if its health reaches zero
                } else {
                    potato.reduceHealth(); // Reduce potato's health if mole is still alive
                }
            }
        }
    }

    public boolean isComplete() {
        return moles.isEmpty();
    }
}