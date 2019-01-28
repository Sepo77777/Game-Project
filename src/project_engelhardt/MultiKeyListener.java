package project_engelhardt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MultiKeyListener implements KeyListener
{
    
    private char pressed = Character.MIN_VALUE;
    private World world;
    
    public MultiKeyListener(World world)
    {
        this.world = world;
    }
    
    @Override
    public synchronized void keyPressed(KeyEvent e)
    {
        if (pressed == Character.MIN_VALUE)
        {
            pressed = e.getKeyChar();
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e)
    {
        pressed = Character.MIN_VALUE;
    }

    @Override
    public synchronized void keyTyped(KeyEvent e)
    {
        if (pressed == 'w' && e.getKeyChar() == 'a' || pressed == 'a' && e.getKeyChar() == 'w')
        {
            world.getPlayer().moveNorthWest();
        }
        else if (pressed == 'w' && e.getKeyChar() == 'd' || pressed == 'd' && e.getKeyChar() == 'w')
        {
            world.getPlayer().moveNorthEast();
        }
        else if (pressed == 's' && e.getKeyChar() == 'a' || pressed == 'a' && e.getKeyChar() == 's')
        {
            world.getPlayer().moveSouthWest();
        }
        else if (pressed == 's' && e.getKeyChar() == 'd' || pressed == 'd' && e.getKeyChar() == 's')
        {
            world.getPlayer().moveSouthEast();
        }
        else
        {
            if (e.getKeyChar() == 'w')
            {
                world.getPlayer().moveNorth();
            }
            if (e.getKeyChar() == 'a')
            {
                world.getPlayer().moveWest();
            }
            if (e.getKeyChar() == 's')
            {
                world.getPlayer().moveSouth();
            }
            if (e.getKeyChar() == 'd')
            {
                world.getPlayer().moveEast();
            }
        }
        world.updatePlayerPosition();
    }
    
}
