import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Standard Enemy is a walking hazard for the Player to avoid
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy_m extends Enemy
{
    private int dX = -1;
    private int bottomWidth = 16;
    private GreenfootImage[] walkRight = new GreenfootImage[3];
    private GreenfootImage[] walkLeft = new GreenfootImage[3];
    private int animationCount = 0;
    private int frameCooldown = 7;
    public Enemy_m()
    {
        initializeAnimationSprites();
    }
    
    /**
     * Act - do whatever the Standard_Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkFrameCooldown();
        checkForWall();
        checkForEdge();
        move();
    }    
    
    public void checkFrameCooldown()
    {
        if(frameCooldown > 0)
        {
            frameCooldown --;
        }
    }
    
    public boolean atRightEdge()
    {
        Actor over = getOneObjectAtOffset (bottomWidth, getImage().getHeight()/2, Platform.class);
        if(over !=null)
        {
            return false;
        }
        return true;
    }
    
    public boolean atLeftEdge()
    {
        Actor over = getOneObjectAtOffset (-bottomWidth, getImage().getHeight()/2, Platform.class);
        if(over !=null)
        {
            return false;
        }
        return true;
    }

    public void checkForWall()
    {
        if(touchingRightWall() || touchingLeftWall())
        {
            changeDirection();
        }
    }
    
    public boolean touchingRightWall()
    {
        Actor right = getOneObjectAtOffset (getImage().getHeight()/2, 0, Platform.class);
        return right !=null;
    }

    public boolean touchingLeftWall()
    {
        Actor left = getOneObjectAtOffset (getImage().getHeight()/-2, 0, Platform.class);
        return left !=null;
    }
    
    public void checkForEdge()
    {
        if(getX() <=0 || getX() >= getWorld().getWidth()-1 || atRightEdge() || atLeftEdge())
        {
            changeDirection();
        }
    }
  
    public void move()
    {
        setLocation(getX()+dX, getY());
        if(dX > 0)
        {
            animateRight();
        }
        if(dX < 0)
        {
            animateLeft();
        }
    }
    
    /**
     * changeDirection - changes the direction of the enemy
     */
    public void changeDirection()
    {
        dX = -dX;
    }
    
    /**
     * initializeAnimationSprites - loads in animation sprites for the Enemy
     */
    public void initializeAnimationSprites()
    {
        for( int i = 0; i <3; i++)
        {
            String filename = "Rabbit" + i + ".png";
            walkRight[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <3; i++)
        {
            String filename = "Rabbit" + i + ".png";
            walkLeft[i] = new GreenfootImage(filename);
            walkLeft[i].mirrorHorizontally();
        }
    }
    
    /**
     * animateRight - sets the image to the corresponding frame for walking Right and
     * divides the animationCount by the amount of frames so it uses the remainder
     */
    public void animateRight()
    {
        if(frameCooldown == 0)
        {
            setImage(walkRight[animationCount++ % 3]);
            frameCooldown = 7;
        }
    }

    /**
     * animateLeft - sets the image to the corresponding frame for walking left and
     * divides the animationCount by the amount of frames so it uses the remainder
     */
    public void animateLeft()
    {
        if(frameCooldown == 0)
        {
            setImage(walkLeft[animationCount++ % 3]);
            frameCooldown = 7;
        }
    }
}
