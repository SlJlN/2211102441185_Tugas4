import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Continue extends Actor
{
    public void act() 
    {
        checkForPlayer();
    }    
    
    public void checkForPlayer()
    {
        if(isTouching(Player.class))
        {
            MyWorld myworld = (MyWorld) getWorld();
            myworld.continueGame();
        }
    }
}
