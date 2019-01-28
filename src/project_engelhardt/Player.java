package project_engelhardt;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements MouseListener, Runnable
{
    private int health;
    private int ammo;
    private int xPosition;
    private int yPosition;
    private World world;
    private int direction;
    
    
    public Player(World world, int xPosition, int yPosition)
    {
        this.world = world;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        health = 100;
        ammo = 30;
        direction = 3;
        ImageIcon image = new ImageIcon("Images/soldier_medium.png");
        this.setIcon(image);
        this.setSize(50, 50);
        this.setVisible(true);
    }
    
    public void shoot()
    {
        Bullet bullet = new Bullet(world, xPosition, yPosition, direction);
        world.addBullet(bullet);
        world.add(bullet);
        Thread t = new Thread(bullet);
        t.start();
    }
    
    public int getXPosition()
    {
        return xPosition;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }
        
    public void moveNorth()
    {
        direction = 1;
        if (yPosition > 0)
        {
            yPosition--; 
        }
    }
    
    public void moveWest()
    {
        direction = 7;
        if (xPosition > 0)
        {
            xPosition--;
        }
    }
    
    public void moveSouth()
    {
        direction = 5;
        if (yPosition < world.getY() - 1)
        {
            yPosition++;
        }
    }
    
    public void moveEast()
    {
        direction = 3;
        if (xPosition < world.getX() - 1)
        {
            xPosition++;
        }
    }
    
    public void moveNorthWest()
    {
        moveNorth();
        moveWest();
        direction = 8;
    }
    
    public void moveNorthEast()
    {
        moveNorth();
        moveEast();
        direction = 2;
    }
    
    public void moveSouthWest()
    {
        moveSouth();
        moveWest();
        direction = 6;
    }
    
    public void moveSouthEast()
    {
        moveSouth();
        moveEast();
        direction = 4;
    }

    public void reduceHealth(int damage)
    {
        health -= damage;
        if (health < 0)
        {
            health = 0;
        }
    }
    
    @Override
    public void run()
    {
        world.checkPlayerCollision();
        world.repaint();
        try 
        {
            Thread.sleep(10);
        } 
        catch (InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0)
    {
    }

    @Override
    public void mouseEntered(MouseEvent arg0)
    { 
    }

    @Override
    public void mouseExited(MouseEvent arg0)
    {  
    }

    @Override
    public void mousePressed(MouseEvent arg0)
    {
        shoot();
    }

    @Override
    public void mouseReleased(MouseEvent arg0)
    {
    }
}
