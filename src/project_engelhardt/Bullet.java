package project_engelhardt;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bullet extends JLabel implements Runnable
{
    private int xPosition;
    private int yPosition;
    private World world;
    private int direction;
    
    public Bullet(World world, int xPosition, int yPosition, int direction)
    {
        this.world = world;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.direction = direction;
        ImageIcon image = new ImageIcon("Images/bullet.png");
        this.setIcon(image);
        this.setVisible(true);
    }
    
    public int getXPosition()
    {
        return xPosition;
    }
    
    public int getYPosition()
    {
        return yPosition;
    }
    
    @Override
    public void run()
    {
        switch (direction)
        {
            case 1: 
                while (yPosition >= 0)
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try 
                    {
                        Thread.sleep(20);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    yPosition--;
                    updateLocation();
                }
                break;
            case 2:
                while (yPosition >= 0 || xPosition < world.getX())
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (yPosition >= 0)
                    {
                        yPosition--;
                    }
                    if (xPosition < world.getX())
                    {
                        xPosition++;
                    }
                    updateLocation();
                }
                break;
            case 3: 
                while (xPosition < world.getX())
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try 
                    {
                        Thread.sleep(20);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    xPosition++;
                    updateLocation();
                }
                break;
            case 4:
                while (yPosition < world.getY() || xPosition < world.getX())
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (yPosition < world.getY())
                    {
                        yPosition++;
                    }
                    if (xPosition < world.getX())
                    {
                        xPosition++;
                    }
                    updateLocation();
                }
                break;
            case 5: 
                while (yPosition < world.getY())
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try 
                    {
                        Thread.sleep(20);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    yPosition++;
                    updateLocation();
                }
                break;
            case 6:
                while (yPosition < world.getY() || xPosition >= 0)
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (yPosition < world.getY())
                    {
                        yPosition++;
                    }
                    if (xPosition >= 0)
                    {
                        xPosition--;
                    }
                    updateLocation();
                }
                break;
            case 7: 
                while (xPosition >= 0)
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try 
                    {
                        Thread.sleep(20);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    xPosition--;
                    updateLocation();
                }
                break;
            case 8:
                while (yPosition >= 0 || xPosition >= 0)
                {
                    if (world.checkBulletCollision(xPosition, yPosition))
                    {
                        world.remove(this);
                        break;
                    }
                    updateLocation();
                    world.repaint();
                    try
                    {
                        Thread.sleep(20);
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    if (yPosition >= 0)
                    {
                        yPosition--;
                    }
                    if (xPosition >= 0)
                    {
                        xPosition--;
                    }
                    updateLocation();
                }
        }
        world.remove(this);
        world.repaint();
    }
    
    public void updateLocation()
    {
        if (xPosition >= 0 && xPosition < world.getX() && yPosition >= 0 && yPosition < world.getY())
        {
            this.setBounds(xPosition * 50, yPosition * 50, 50, 50);
        }
        else
        {
            world.remove(this);
        }
        
    }
}
