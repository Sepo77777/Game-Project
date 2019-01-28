package project_engelhardt;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class World extends JFrame
{
    private int[][] grid;
    private List<Bullet> bullets;
    private Player player;
    private int x;
    private int y;
    private int difficulty;
    private static final int EMPTY = 0;
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
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                grid[i][j] = (int) (Math.random() * 100);
                grid[i][j] = (int) (Math.random() * 100);
            }
        }
        bullets = new ArrayList<>();
        this.setSize(x * 50, y * 50);
        this.setLayout(null);
        player = new Player(this, x / 2, y / 2);
        this.add(player);
        this.addKeyListener(new MultiKeyListener(this));
        this.addMouseListener(player);
        this.updatePlayerPosition();
        this.setVisible(true);
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

    public void checkBulletCollision(int xPosition, int yPosition)
    {
       if (xPosition >= 0 && xPosition < x && yPosition >= 0 && yPosition < y)
       {
           if (grid[xPosition][yPosition] == STRONGENEMY)
           {
               grid[xPosition][yPosition] = ENEMY;
           }
           else if (grid[xPosition][yPosition] == ENEMY)
           {
               grid[xPosition][yPosition] = EMPTY;
           }
       }
       
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
}
