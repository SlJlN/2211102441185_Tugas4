import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;


public class MyWorld extends World
{
    private int currentLevel = 1;
    int score = 0;
    private Player player; 
    private int codeCubes = 0;
    private int gameStart = 0;
    private Life_Bar lifeBar;
    private GreenfootImage gameBG;
    private GreenfootImage gameOverBG;
    private GreenfootImage gameOverBG2;
    private GreenfootImage endingBG;
    GreenfootSound introBGM = new GreenfootSound("His Theme.mp3");
    GreenfootSound gameplayBGM = new GreenfootSound("Its Showtime.mp3");
    GreenfootSound continueBGM = new GreenfootSound("Continue.mp3");
    GreenfootSound gameOverBGM = new GreenfootSound("GameOver.mp3");
    GreenfootSound endingBGM = new GreenfootSound("Ending.mp3");

    public MyWorld() {
        super(832, 728, 1);
        gameOverBG = new GreenfootImage("GameOverBG.png");
        gameOverBG2 = new GreenfootImage("GameOverBG2.png");
        gameBG = new GreenfootImage("gameBG.png");
        endingBG = new GreenfootImage("endingBG.png");
        Greenfoot.start();
    }

    public void act() {
        if(gameStart ==0)
        {
            introBGM.playLoop();
        }
        if(Greenfoot.isKeyDown("enter") && gameStart ==0)
        {
            gameStart ++;
            introBGM.stop();
            startGame();
            prepareNextWorld();
        }
    }

    public void collectCodeCube()
    {
        codeCubes ++;
        prepareNextWorld();
    }

    public void confirmDamage()
    {
        lifeBar.subtractLife();
    }

    public void collectHati()
    {
        lifeBar.recoverLife();
    }

    public void pitFall()
    {
        lifeBar.pitFall();
    }

    public void startGame()
    {
        this.player = new Player();
        addObject(player,49,665);
        this.lifeBar= new Life_Bar();
        addObject(lifeBar,103,28);
    }

    public void prepareNextWorld()
    {
        if(codeCubes == 0)
        {
            prepareWorld1();
        }
        else if (codeCubes == 1)
        {
            prepareWorld2();
        }
        else if (codeCubes == 2)
        {
            prepareWorld3();
        }
        else if (codeCubes == 3)
        {
            endGame();
        }
    }

    private void prepareWorld1()
    {
        gameplayBGM.playLoop();
        clearWorld();
        setBackground(gameBG);
        M_Ground m_Ground = new M_Ground();
        addObject(m_Ground,128,705);
        GE_Right gE_Right = new GE_Right();
        addObject(gE_Right,280,705);
        GE_Left gE_Left = new GE_Left();
        addObject(gE_Left,389,704);
        M_Ground m_Ground2 = new M_Ground();
        addObject(m_Ground2,540,704);
        GE_Right gE_Right2 = new GE_Right();
        addObject(gE_Right2,691,704);
        S_Platform s_Platform1 = new S_Platform();
        addObject(s_Platform1,259,591);
        S_Platform s_Platform2 = new S_Platform();
        addObject(s_Platform2,379,245);
        S_Platform s_Platform3 = new S_Platform();
        addObject(s_Platform3,760,591);
        S_Platform s_Platform4 = new S_Platform();
        addObject(s_Platform4,624,508);
        S_Platform s_Platform5 = new S_Platform();
        addObject(s_Platform5,343,472);
        S_Platform s_Platform6 = new S_Platform();
        addObject(s_Platform6,221,189);
        M_Platform m_Platform1 = new M_Platform();
        addObject(m_Platform1,574,246);
        M_Platform m_Platform2 = new M_Platform();
        addObject(m_Platform2,439,449);
        M_Platform m_Platform3 = new M_Platform();
        addObject(m_Platform3,247,449);
        M_Platform m_Platform4 = new M_Platform();
        addObject(m_Platform4,67,303);
        Spike spike = new Spike();
        addObject(spike,362,444);
        Spike spike2 = new Spike();
        addObject(spike2,323,444);
        Spike spike3 = new Spike();
        addObject(spike3,503,244);
        Spike spike4 = new Spike();
        addObject(spike4,646,244);
        Spring spring = new Spring();
        addObject(spring,199,425);
        Enemy_m enemy_m = new Enemy_m();
        addObject(enemy_m,549,664);
        Hati hati = new Hati();
        addObject(hati,65,270);
        Cube_Fragment cube_Fragment = new Cube_Fragment();
        addObject(cube_Fragment,574,180);
        player.setLocation(49,665);
    }

    private void prepareWorld2()
    {
        clearWorld();
        setBackground(gameBG);
        M_Ground m_Ground = new M_Ground();
        addObject(m_Ground,128,705);
        M_Ground m_Ground2 = new M_Ground();
        addObject(m_Ground2,384,705);
        M_Ground m_Ground3 = new M_Ground();
        addObject(m_Ground3,735,728);
        GE_Right gE_Right1 = new GE_Right();
        addObject(gE_Right1,536,705);
        GE_Right gE_Right2 = new GE_Right();
        addObject(gE_Right2,831,728);
        GE_Left gE_Left1 = new GE_Left();
        addObject(gE_Left1,583,728);
        S_Platform s_Platform1 = new S_Platform();
        addObject(s_Platform1,616,591);
        S_Platform s_Platform2 = new S_Platform();
        addObject(s_Platform2,761,541);
        S_Platform s_Platform3 = new S_Platform();
        addObject(s_Platform3,484,445);
        S_Platform s_Platform4 = new S_Platform();
        addObject(s_Platform4,317,470);
        S_Platform s_Platform5 = new S_Platform();
        addObject(s_Platform5,43,356);
        S_Platform s_Platform6 = new S_Platform();
        addObject(s_Platform6,130,265);
        S_Platform s_Platform7 = new S_Platform();
        addObject(s_Platform7,763,130);
        M_Platform m_Platform1 = new M_Platform();
        addObject(m_Platform1,159,415);
        M_Platform m_Platform2 = new M_Platform();
        addObject(m_Platform2,625,356);
        M_Platform m_Platform3 = new M_Platform();
        addObject(m_Platform3,261,219);
        M_Platform m_Platform4 = new M_Platform();
        addObject(m_Platform4,431,176);
        M_Platform m_Platform5 = new M_Platform();
        addObject(m_Platform5,623,176);
        Spike spike1 = new Spike();
        addObject(spike1,232,662);
        Spike spike2 = new Spike();
        addObject(spike2,401,662);
        Spike spike3 = new Spike();
        addObject(spike3,432,145);
        Spring spring = new Spring();
        addObject(spring,761,518);
        Enemy_m enemy_m1 = new Enemy_m();
        addObject(enemy_m1,729,687);
        Enemy_m enemy_m2 = new Enemy_m();
        addObject(enemy_m2,584,328);
        Enemy_m enemy_m3 = new Enemy_m();
        addObject(enemy_m3,268,191);
        Hati hati = new Hati();
        Cube_Fragment cube_Fragment1 = new Cube_Fragment();
        addObject(cube_Fragment1,762,84);
        addObject(hati,316,414);
        player.setLocation(49,665);
    }

    private void prepareWorld3()
    {
        clearWorld();
        setBackground(gameBG);
        M_Ground m_Ground = new M_Ground();
        addObject(m_Ground,128,705);
        M_Ground m_Ground2 = new M_Ground();
        addObject(m_Ground2,294,705);
        GE_Right gE_Right = new GE_Right();
        addObject(gE_Right,446,705);
        S_Platform s_Platform = new S_Platform();
        addObject(s_Platform,527,637);
        S_Platform s_Platform2 = new S_Platform();
        addObject(s_Platform2,631,596);
        S_Platform s_Platform3 = new S_Platform();
        addObject(s_Platform3,721,541);
        S_Platform s_Platform4 = new S_Platform();
        addObject(s_Platform4,273,555);
        S_Platform s_Platform5 = new S_Platform();
        addObject(s_Platform5,136,553);
        S_Platform s_Platform6 = new S_Platform();
        addObject(s_Platform6,41,412);
        S_Platform s_Platform7 = new S_Platform();
        addObject(s_Platform7,405,197);
        S_Platform s_Platform8 = new S_Platform();
        addObject(s_Platform8,626,217);
        S_Platform s_Platform9 = new S_Platform();
        addObject(s_Platform9,697,721);
        S_Platform s_Platform10 = new S_Platform();
        addObject(s_Platform10,219,388);
        M_Platform m_Platform1 = new M_Platform();
        addObject(m_Platform1,544,720);
        M_Platform m_Platform2 = new M_Platform();
        addObject(m_Platform2,581,406);
        M_Platform m_Platform3 = new M_Platform();
        addObject(m_Platform3,419,493);
        M_Platform m_Platform4 = new M_Platform();
        addObject(m_Platform4,227,241);
        M_Platform m_Platform5 = new M_Platform();
        addObject(m_Platform5,522,252);
        M_Platform m_Platform6 = new M_Platform();
        addObject(m_Platform6,699,152);
        Spike spike1 = new Spike();
        addObject(spike1,226,210);
        Spike spike2 = new Spike();
        addObject(spike2,636,186);
        Spring spring1 = new Spring();
        addObject(spring1,720,517);
        Spring spring2 = new Spring();
        addObject(spring2,135,529);
        Spring spring3 = new Spring();
        addObject(spring3,43,388);
        Enemy_m enemy_m1 = new Enemy_m();
        addObject(enemy_m1,444,665);
        Enemy_m enemy_m2 = new Enemy_m();
        addObject(enemy_m2,601,378);
        Enemy_m enemy_m3 = new Enemy_m();
        addObject(enemy_m3,417,466);
        Enemy_m enemy_m4 = new Enemy_m();
        addObject(enemy_m4,564,225);
        Hati hati = new Hati();
        addObject(hati,219,350);
        Cube_Fragment cube_Fragment = new Cube_Fragment();
        addObject(cube_Fragment,696,96);
        player.setLocation(49,665);
    }

    private void endGame()
    {
        clearWorld();
        gameplayBGM.stop();
        endingBGM.playLoop();
        player.setLocation(398, 399);
        player.ending();
        List lifeBar = getObjects(Life_Bar.class);
        removeObjects(lifeBar);
        setBackground(endingBG);
        Greenfoot.stop();
    }

    public void gameOver()
    {
        clearWorld();
        gameplayBGM.stop();
        continueBGM.playLoop();
        setBackground(gameOverBG);
        M_Ground m_Ground = new M_Ground();
        addObject(m_Ground,128,705);
        M_Ground m_Ground2 = new M_Ground();
        addObject(m_Ground2,384,705);;
        M_Ground m_Ground3 = new M_Ground();
        addObject(m_Ground3,640,705);
        M_Ground m_Ground4 = new M_Ground();
        addObject(m_Ground4,804,705);
        Continue continueText = new Continue();
        addObject(continueText,630,414);
        Give_Up giveUpText = new Give_Up();
        addObject(giveUpText,186,414);
        Spring spring = new Spring();
        addObject(spring,637,670);
        Spring spring2 = new Spring();
        addObject(spring2,186,670);
        player.setLocation(398,544);
    }

    public void continueGame()
    {
        lifeBar.restart();
        clearWorld();
        continueBGM.stop();
        gameplayBGM.playLoop();
        prepareNextWorld();
    }

    public void giveUp()
    {
        clearWorld();
        continueBGM.stop();
        gameOverBGM.play();
        setBackground(gameOverBG2);
        List lifeBar = getObjects(Life_Bar.class);
        removeObjects(lifeBar);
        player.gameOver();
        player.setLocation(410, 390);
        Greenfoot.stop();
    }

    public void clearWorld()
    {
        List platform = getObjects(Platform.class);
        List enemies = getObjects(Enemy.class);
        List spikes = getObjects(Spike.class);
        List hati = getObjects(Hati.class);
        List cubeFragment = getObjects(Cube_Fragment.class);
        List continueButton = getObjects(Continue.class);
        List giveUp = getObjects(Give_Up.class);
        removeObjects(platform);
        removeObjects(enemies);
        removeObjects(spikes);
        removeObjects(hati);
        removeObjects(cubeFragment);
        removeObjects(continueButton);
        removeObjects(giveUp);
    }
}