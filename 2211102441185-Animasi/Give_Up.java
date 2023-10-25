import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * You gave up. Such a shame...
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Give_Up extends Actor
{
    /**
     * Act - do whatever the Give_Up wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkForPlayer();
    }    
    
    public void checkForPlayer()
    {
        if(isTouching(Player.class))
        {
            MyWorld myworld = (MyWorld) getWorld();
            myworld.giveUp();
        }
    }
}