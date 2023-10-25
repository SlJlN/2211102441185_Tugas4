import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Actor
{
    private int score = 0;
    private int groundSpeed;
    private int speedCap = 5;
    private int jumpStrength = 15;
    private int fallSpeed = 1;
    private int maxFallSpeed = 16;
    private int acceleration = 1;
    private boolean facingRight = true;
    private boolean facingLeft = false;
    private boolean crouching = false;
    private int invincibility = 0;
    private int animationCount = 0;
    private int frameCooldown = 7;
    private GreenfootImage[] walkRight = new GreenfootImage[8];
    private GreenfootImage[] walkLeft = new GreenfootImage[8];
    private GreenfootImage[] idleRight = new GreenfootImage[5];
    private GreenfootImage[] idleLeft = new GreenfootImage[5];
    private GreenfootImage[] crouchRight = new GreenfootImage[2];
    private GreenfootImage[] crouchLeft = new GreenfootImage[2];
    private GreenfootImage[] fallRight = new GreenfootImage[8];
    private GreenfootImage[] fallLeft = new GreenfootImage[8];
    private GreenfootImage gameOverText;
    private GreenfootImage star;
    GreenfootSound jumpSFX = new GreenfootSound("Jump.mp3");
    GreenfootSound damageSFX = new GreenfootSound("Damage.mp3");
    GreenfootSound hatiSFX = new GreenfootSound("Hati.mp3");
    GreenfootSound codeCubeSFX = new GreenfootSound("CodeCube.mp3");
    GreenfootSound springSFX = new GreenfootSound("Spring.mp3");
    public Player()
    {
        initializeAnimationSprites();
        gameOverText = new GreenfootImage("GameOver.png");
        star = new GreenfootImage("Star.png");
    }

    public void act() 
    {
        checkInvincibilityFrames();
        checkFrameCooldown();
        checkForKeys();
        checkForMove();
        checkForDeceleration();
        checkForFall();
        checkForHeadBump();
        checkForEnemy();
        checkForSpike();
        checkForPit();
        checkForSpring();
        checkForHati();
        checkForGoal();
    }    

    public void checkInvincibilityFrames()
    {
        if(invincibility > 0)
        {
            invincibility --;
        }
    }

    public void checkFrameCooldown()
    {
        if(frameCooldown > 0)
        {
            frameCooldown --;
        }
    }

    public void checkForDamage()
    {
        if(invincibility == 0)
        {
            MyWorld myworld = (MyWorld) getWorld();
            damagePlayer(); 
            damageSFX.play();
            myworld.confirmDamage();
        }
    }

    public void damagePlayer()
    {
        if(facingRight)
        {
            setLocation(getX() -50, getY());
        }
        if(facingLeft)
        {
            setLocation(getX() +50, getY());
        }
        invincibility = 100;
        groundSpeed = 0;
    }

    public void checkForKeys()
    {
        if(Greenfoot.isKeyDown("right") && !crouching)
        {
            moveRight();
        }

        if(Greenfoot.isKeyDown("left") && !crouching)
        {
            moveLeft();
        }

        if(Greenfoot.isKeyDown("space") && onGround() && fallSpeed >0 && !crouching)
        {
            jump();
        }

        if(Greenfoot.isKeyDown("down") && onGround() && fallSpeed >0 && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("right"))
        {
            crouch();
            crouching = true;
        }

        if(!Greenfoot.isKeyDown("down"))
        {
            crouching = false;
        }

        if(!Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("down") && !Greenfoot.isKeyDown("space"))
        {
            checkForIdle();
        }
    }

    public void moveRight()
    {
        if(!touchingRightWall() && groundSpeed < speedCap && !crouching)
        {
            groundSpeed+=1;
        }
        animateRight();
        facingRight = true;
        facingLeft = false;
    }

    public void moveLeft()
    {
        if(!touchingLeftWall() && groundSpeed > -speedCap && !crouching)
        {
            groundSpeed-=1;
        }
        animateLeft();
        facingLeft = true;
        facingRight = false;
    }

    public void checkForMove()
    {
        if(groundSpeed > 0 || groundSpeed <0)
        {
            move();
        }
    }

    public void move()
    {
        setLocation(getX()+groundSpeed,getY());
        while(touchingRightWall())
        {
            setLocation(getX()-1, getY());
        }
        while(touchingLeftWall())
        {
            setLocation(getX()+1, getY());
        }
    }

    public void checkForDeceleration()
    {
        if(!Greenfoot.isKeyDown("right") && !Greenfoot.isKeyDown("left") ||Greenfoot.isKeyDown("down"))
        {
            decelerate();
        }
    }

    public void decelerate()
    {
        if(groundSpeed >0 && !Greenfoot.isKeyDown("right"))
        {
            groundSpeed --;
        }
        if(groundSpeed < 0 && !Greenfoot.isKeyDown("left"))
        {
            groundSpeed ++;
        }
    }

    public void jump()
    {
        fallSpeed -= jumpStrength;
        jumpSFX.play();
        fall();
    }

    public void crouch()
    {
        if(facingRight)
        {
            animateCrouchR();
        }
        if(facingLeft)
        {
            animateCrouchL();
        }
        if(Greenfoot.isKeyDown("right"))
        {
            decelerate();
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

    public void checkForFall()
    {
        if(onGround() && fallSpeed >0)
        {
            fallSpeed=0;
            while(onGround() && !bumpHead())
            {
                setLocation(getX(), getY()-1);
            }
            setLocation(getX(), getY()+1);
        }
        else
        {
            fall();
        }
    }

    public boolean onGround()
    {
        for(int bottomWidth = getImage().getWidth()/-2; bottomWidth <=16; bottomWidth ++)
        {
            Actor over = getOneObjectAtOffset (bottomWidth, getImage().getHeight()/2, Platform.class);
            if(over !=null)
            {
                return true;
            }
        }
        return false;
    }

    public void fall()
    {
        setLocation(getX(), getY()+fallSpeed);
        fallSpeed += acceleration;
        checkForMaxFallSpeed();
        if(!Greenfoot.isKeyDown("down"))
        {
            if(facingRight && !onGround())
            {
                animateFallR();
            }
            if(facingLeft && !onGround())
            {
                animateFallL();
            }
        }
    }

    public void checkForMaxFallSpeed()
    {    if(fallSpeed > maxFallSpeed)
        {
            fallSpeed = maxFallSpeed;
        }
    }

    public void checkForHeadBump()
    {   if(bumpHead() && fallSpeed <=0)
        {
            fallSpeed=0;
            while(bumpHead())
            {
                setLocation(getX(), getY()+1);
            }
        }
    }

    public boolean bumpHead()
    {
        for(int topWidth = getImage().getWidth()/-2; topWidth <=16; topWidth ++)
        {
            Actor under = getOneObjectAtOffset (topWidth, getImage().getHeight()/-2, Platform.class);
            if(under !=null)
            {
                return true;
            }
        }
        return false;
    }

    public void checkForIdle()
    {
        if(facingRight = true && !facingLeft)
        {
            animateIdleR();
        }
        if(facingLeft = true && !facingRight)
        {
            animateIdleL();
        }
    }

    public void checkForEnemy()
    {
        if(touchingEnemy())
        {
            checkForDamage();
        }
    }

    public boolean touchingEnemy()
    {
        if(isTouching(Enemy.class))
        {
            return true;
        }
        return false;
    }

    public void checkForSpike()
    {
        if(touchingSpike())
        {
            checkForDamage();
        }
    }

    public boolean touchingSpike()
    {
        if(isTouching(Spike.class))
        {
            return true;
        }
        return false;
    }

    public void checkForPit()
    {
        if(getY() >= getWorld().getHeight()-1)
        {
            MyWorld myworld = (MyWorld) getWorld();
            myworld.pitFall();
        }
    }

    public void checkForSpring()
    {
        if(touchingSpring())
        {
            springSFX.play();
            fallSpeed = -20;
        }
    }

    public boolean touchingSpring()
    {
        for(int bottomWidth = getImage().getWidth()/-2; bottomWidth <=16; bottomWidth ++)
        {
            Actor over = getOneObjectAtOffset (bottomWidth, getImage().getHeight()/2, Spring.class);
            if(over !=null)
            {
                return true;
            }
        }
        return false;
    }

    public void checkForHati()
    {
        if(isTouching(Hati.class))
        {
            removeTouching(Hati.class);
            hatiSFX.play();
            MyWorld myworld = (MyWorld) getWorld();
            myworld.collectHati();
        }
    }

    public void checkForGoal()
    {
        if(isTouching(Cube_Fragment.class))
        {
            MyWorld myworld = (MyWorld) getWorld();
            myworld.collectCodeCube();
            removeTouching(Cube_Fragment.class);
            codeCubeSFX.play();
        }
    }

    public void gameOver()
    {
        setImage(gameOverText);
    }

    public void ending()
    {
        setImage(star);
    }

    public void initializeAnimationSprites()
    {
        for( int i = 0; i <8; i++)
        {
            String filename = "Girl_Right" + i + ".png";
            walkRight[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <8; i++)
        {
            String filename = "Girl_Left" + i + ".png";
            walkLeft[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <5; i++)
        {
            String filename = "Girl_Idle R" + i + ".png";
            idleRight[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <5; i++)
        {
            String filename = "Girl_Idle L" + i + ".png";
            idleLeft[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <2; i++)
        {
            String filename = "Girl_Crouch R" + i + ".png";
            crouchRight[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <2; i++)
        {
            String filename = "Girl_Crouch L" + i + ".png";
            crouchLeft[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <8; i++)
        {
            String filename = "Girl_Jump R" + i + ".png";
            fallRight[i] = new GreenfootImage(filename);
        }

        for( int i = 0; i <8; i++)
        {
            String filename = "Girl_Jump L" + i + ".png";
            fallLeft[i] = new GreenfootImage(filename);
        }
    }

    public void animateRight()
    {
        if(frameCooldown == 0)
        {
            setImage(walkRight[animationCount++ % 8]);
            frameCooldown = 7;
        }
    }

    public void animateLeft()
    {
        if(frameCooldown == 0)
        {
            setImage(walkLeft[animationCount++ % 8]);
            frameCooldown = 7;
        }
    }

    public void animateIdleR()
    {
        setImage(idleRight[animationCount++ % 5]);
        frameCooldown = 4;
    }

    public void animateIdleL()
    {
        setImage(idleLeft[animationCount++ % 5]);
        frameCooldown = 4;
    }

    public void animateCrouchR()
    {
        setImage(crouchRight[animationCount++ % 2]);
        frameCooldown = 1;
    }

    public void animateCrouchL()
    {
        setImage(crouchLeft[animationCount++ % 2]);
        frameCooldown = 1;
    }

    public void animateFallR()
    {
        if(!onGround())
        {
            setImage(fallRight[animationCount++ % 8]);
            frameCooldown = 7;
        }
    }

    public void animateFallL()
    {
        if(!onGround())
        {
            setImage(fallLeft[animationCount++ % 8]);
            frameCooldown = 7;
        }
    }
    
    public void increaseScore() {
        score += 10;
        getWorld().showText("Score: " + score, 50, 25);
    }
}