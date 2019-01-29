package project_engelhardt;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class World extends JFrame
{
    private int[][] grid;
    private Monster[][] monsters;
    private List<Bullet> bullets;
    private Player player;
    private int x;
    private int y;
    private int difficulty;
    private JLabel healthLabel;
    private JLabel ammoLabel;
    private KeyListener keyListener;
    private static final int ENEMY = 1;
    private static final int STRONGENEMY = 2;
    private static final int BANDAID = 3;
    private static final int AMMO = 4;
    
    public static void main(String[] args)
    {
        new World(20, 20);
    }
    
    public World(int x, int y)
    {
        this.x = x;
        this.y = y;
        grid = new int[x][y];
        monsters = new Monster[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                grid[i][j] = (int) (Math.random() * 100);
                grid[i][j] = (int) (Math.random() * 100);
            }
        }
        bullets = new ArrayList<>();
        this.setSize(x * 50 + 120, y * 50 + 50);
        this.setLayout(null);
        player = new Player(this, x / 2, y / 2);
        this.add(player);
        keyListener = new MultiKeyListener(this);
        this.addKeyListener(keyListener);
        this.addMouseListener(player);
        this.updatePlayerPosition();
        this.setVisible(true);
        spawnMonsters();
        healthLabel = new JLabel("Health: 100");
        this.add(healthLabel);
        healthLabel.setBounds(x * 50 + 20, 0, 100, 50);
        ammoLabel = new JLabel("Ammo: 30");
        this.add(ammoLabel);
        ammoLabel.setBounds(x * 50 + 20, 50, 100, 50);
    }
    
    public void increaseDifficulty()
    {
        difficulty++;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Player getPlayer()
    {
        return player;
    }

    public boolean checkBulletCollision(int xPosition, int yPosition)
    {
       if (xPosition >= 0 && xPosition < x && yPosition >= 0 && yPosition < y)
       {
           if (grid[xPosition][yPosition] == STRONGENEMY)
           {
               grid[xPosition][yPosition] = ENEMY;
               return true;
           }
           else if (grid[xPosition][yPosition] == ENEMY)
           {
               grid[xPosition][yPosition] = 0;
               if (monsters[xPosition][yPosition] != null)
               {
                   this.remove(monsters[xPosition][yPosition]);
                   monsters[xPosition][yPosition].kill();
                   monsters[xPosition][yPosition] = null;
               } 
               return true;
           }
       }
       return false;
       
    }
    
    public void addBullet(Bullet bullet)
    {
        bullets.add(bullet);
    }
    
    public void checkPlayerCollision()
    {
        if (grid[player.getXPosition()][player.getYPosition()] == ENEMY)
        {
            player.reduceHealth(10);
        }
        if (grid[player.getXPosition()][player.getYPosition()] == STRONGENEMY)
        {
            player.reduceHealth(20);
        }
    }
    
    public void updatePlayerPosition()
    {
        player.setBounds(player.getXPosition()*50, player.getYPosition()*50, 50, 50);
    }
    
    public void spawnMonsters()
    {
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                if (grid[i][j] == ENEMY)
                {
                    Monster monster = new Monster(this, i, j, false);
                    this.add(monster);
                    monster.setVisible(true);
                    monster.setBounds(i*50, j*50, 50, 50);
                    monsters[i][j] = monster;
                    Thread t = new Thread(monster);
                    t.start();
                }
                if (grid[i][j] == STRONGENEMY)
                {
                    Monster monster = new Monster(this, i, j, true);
                    this.add(monster);
                    monster.setVisible(true);
                    monster.setBounds(i*50, j*50, 50, 50);
                    monsters[i][j] = monster;
                }
            }
        }ds
    }
    
    public void updateHealthLabel()
    {
        healthLabel.setText("Health: " + player.getHealth());
    }
    
    public void updateAmmoLabel()
    {
        ammoLabel.setText("Ammo: " + player.getAmmo());
    }
    
    public void updateMonsters(int oldXPosition, int oldYPosition, int newXPosition, int newYPosition)
    {
        if (oldXPosition >= 0 && oldXPosition < x && oldYPosition >= 0 && oldYPosition < y)
        {
            grid[oldXPosition][oldYPosition] = 0;
            grid[newXPosition][newYPosition] = ENEMY;
            monsters[newXPosition][newYPosition] = monsters[oldXPosition][oldYPosition];
            monsters[oldXPosition][oldYPosition] = null;
        }
        
    }
    
    public void gameOver()
    {
        JLabel gameOverLabel = new JLabel("GAME OVER");
        this.add(gameOverLabel);
        gameOverLabel.setBounds(x * 25, y * 25, 100, 50);
        this.removeKeyListener(keyListener);
        this.removeMouseListener(player);
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                if (monsters[i][j] != null)
                {
                    monsters[i][j].kill();
                }
            }
        }
    }
}
