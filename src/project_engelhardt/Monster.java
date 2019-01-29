package project_engelhardt;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel implements Runnable
{
    private int xPosition;
    private int yPosition;
    private World world;
    
    public Monster(World world, int xPosition, int yPosition)
    {
        this.world = world;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        ImageIcon image = new ImageIcon("Images/zombie.png");
        this.setIcon(image);
    }

    @Override
    public void run()
    {
        // TODO Auto-generated method stub
        
    }
}
