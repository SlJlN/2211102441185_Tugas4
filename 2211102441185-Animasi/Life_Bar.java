import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The player's visual representation of their current life left
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Life_Bar extends Actor
{
    private GreenfootImage life_Bar3;
    private GreenfootImage life_Bar2;
    private GreenfootImage life_Bar1;
    private GreenfootImage life_Bar0;
    private int lifeLeft = 3;
    
    public Life_Bar()
    {
        life_Bar3 = new GreenfootImage("Life_Bar 3.png");
        life_Bar2 = new GreenfootImage("Life_Bar 2.png");
        life_Bar1 = new GreenfootImage("Life_Bar 1.png");
        life_Bar0 = new GreenfootImage("Life_Bar 0.png");
    }

    public void subtractLife()
    {
        lifeLeft --;
        updateLifeBar();
    }
    
    public void recoverLife()
    {
        if(lifeLeft <3)
        {
            lifeLeft ++;
            updateLifeBar();
        }
    }
    
    public void restart()
    {
        lifeLeft = 3;
        updateLifeBar();
    }
    
    public void pitFall()
    {
        lifeLeft = 0;
        updateLifeBar();
    }

    public void updateLifeBar()
    {
        if(lifeLeft == 3)
        {
            setImage(life_Bar3);
        }
        else if(lifeLeft == 2)
        {
            setImage(life_Bar2);
        }
        else if(lifeLeft == 1)
        {
            setImage(life_Bar1);
        }
        else if(lifeLeft == 0)
        {
            MyWorld myworld = (MyWorld) getWorld();
            setImage(life_Bar0);
            myworld.gameOver();
        }
    }
}