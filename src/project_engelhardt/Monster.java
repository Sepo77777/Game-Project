package project_engelhardt;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel implements Runnable
{
    private int xPosition;
    private int yPosition;
    private World world;
    private boolean strong;
    private boolean killed;
    
    public Monster(Monster another)
    {
        this.xPosition = another.xPosition;
        this.yPosition = another.yPosition;
        this.world = another.world;
        this.strong = another.strong;
        this.killed = another.killed;
    }
    
    public Monster(World world, int xPosition, int yPosition, boolean strong)
    {
        this.world = world;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.strong = strong;
        this.killed = false;
        ImageIcon image;
        if (strong)
        {
            image = new ImageIcon("Images/strongzombie.png");
        }
        else
        {
            image = new ImageIcon("Images/zombie.png");
        }
        this.setIcon(image);
    }

    @Override
    public void run()
    {
        while (!killed)
        {
            if (!strong)
            {
                int direction = (int) (Math.random() * 8) + 1;
                switch (direction)
                {
                    case 1:
                        if (yPosition > 1)
                        {
                            yPosition--;
                            world.updateMonsters(xPosition, yPosition + 1, xPosition, yPosition, strong);
                        }
                        break;
                    case 2:
                        if (xPosition < world.getX() - 1)
                        {
                            xPosition++;
                            world.updateMonsters(xPosition - 1, yPosition, xPosition, yPosition, strong);
                        }
                        if (yPosition > 1)
                        {
                            yPosition--;
                            world.updateMonsters(xPosition, yPosition + 1, xPosition, yPosition, strong);
                        }
                        break;
                    case 3:
                        if (xPosition < world.getX() - 1)
                        {
                            xPosition++;
                            world.updateMonsters(xPosition - 1, yPosition, xPosition, yPosition, strong);
                        }
                        break;
                    case 4:
                        if (xPosition < world.getX() - 1)
                        {
                            xPosition++;
                            world.updateMonsters(xPosition - 1, yPosition, xPosition, yPosition, strong);
                        }
                        if (yPosition < world.getY() - 1)
                        {
                            yPosition++;
                            world.updateMonsters(xPosition, yPosition - 1, xPosition, yPosition, strong);
                        }
                        break;
                    case 5:
                        if (yPosition < world.getY() - 1)
                        {
                            yPosition++;
                            world.updateMonsters(xPosition, yPosition - 1, xPosition, yPosition, strong);
                        }
                        break;
                    case 6:
                        if (xPosition > 1)
                        {
                            xPosition--;
                            world.updateMonsters(xPosition + 1, yPosition, xPosition, yPosition, strong);
                        }
                        if (yPosition < world.getY() - 1)
                        {
                            yPosition++;
                            world.updateMonsters(xPosition, yPosition - 1, xPosition, yPosition, strong);
                        }  
                        break;
                    case 7:
                        if (xPosition > 1)
                        {
                            xPosition--;
                            world.updateMonsters(xPosition + 1, yPosition, xPosition, yPosition, strong);
                        }
                        break;
                    case 8:
                        if (xPosition > 1)
                        {
                            xPosition--;
                            world.updateMonsters(xPosition + 1, yPosition, xPosition, yPosition, strong);
                        }
                        if (yPosition > 1)
                        {
                            yPosition--;
                            world.updateMonsters(xPosition, yPosition + 1, xPosition, yPosition, strong);
                        }
                        break;
                }    
            }
            else
            {
                if (xPosition < world.getPlayer().getXPosition())
                {
                    xPosition++;
                    world.updateMonsters(xPosition - 1, yPosition, xPosition, yPosition, strong);
                }
                else if (xPosition > world.getPlayer().getXPosition())
                {
                    xPosition--;
                    world.updateMonsters(xPosition + 1, yPosition, xPosition, yPosition, strong);
                }
                if (yPosition < world.getPlayer().getYPosition())
                {
                    yPosition++;
                    world.updateMonsters(xPosition, yPosition - 1, xPosition, yPosition, strong);
                }
                else if (yPosition > world.getPlayer().getYPosition())
                {
                    yPosition--;
                    world.updateMonsters(xPosition, yPosition + 1, xPosition, yPosition, strong);
                }
            }
            updateLocation();
            if (world.checkPlayerCollision())
            {
                try
                {
                    Thread.sleep(3000);
                } 
                catch (InterruptedException e) 
                {
                    e.printStackTrace();
                }
            }
            world.repaint();
            try 
            {
                Thread.sleep(500);
            } 
            catch (InterruptedException e) 
            {
                e.printStackTrace();
            }
        }
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

    public void kill()
    {
        killed = true;
        world.remove(this);
    }
    
    public void hit()
    {
        strong = false;
    }
}
