package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;

public class Player {

    private boolean isLeft, isRight, isUp, isDown;          // tells the direction the PLAYER is facing
    private String direction;                               //
    private int x, y;                                       // PLAYER's position variables
    private int distanceX, distanceY;                       // variables for moving the PLAYER
    private static int hp;                                  // PLAYER's life points

    private Rectangle rectangle;                            // Rectangle for collision
    private int rectX, rectY;                               // Player.Rectangle's position variables

    //    Normal      , Slow-debuffer , Big damage
    private String weaponType; // { Machine Gun } , { Megaphone } , { Bazooka }
    private boolean weaponSwapInterval;

    // Megaphone variables
    private boolean isMegaphone;
    private int megaAmmo;

    // Bazooka variables
    private boolean isBazooka;
    private int bazookaAmmo;

    // bullet cooldowns
    private boolean mGBulletInterval;
    private boolean megaphoneBulletInterval;
    private boolean bazookaInterval;

    private boolean playerController;

    // stores all bullets shot
    private ArrayList<MachineGunBeam> List_mg;
    private ArrayList<MegaphoneBeam> List_megaphone;
    private ArrayList<BazookaBeam> List_bazooka;

    // Image for rotation
    private Image image;



    public Player(int _x, int _y) {

        // PLAYER is instantiated at X and Y
        this.x = _x;
        this.y = _y;




        // small 15x15 Rectangle in center of Player , for collision
        rectX = x + 15;
        rectY = y + 15;
        rectangle = new Rectangle(rectX, rectY, 40, 40);

        // Player's initial HP = 100
        hp = 100;


        // initial direction is 'Up'
        ImageIcon imgIcon = new ImageIcon("src//Img//player//priestMgW.png");
        this.image = imgIcon.getImage();


        // initial weapon
        weaponType = "Machine Gun";
        weaponSwapInterval = false;
        isMegaphone = false;
        megaAmmo = 3;
        isBazooka = false;
        bazookaAmmo = 2;


        // all Weapon cooldowns are FALSE
        mGBulletInterval = false;
        List_bazooka = new ArrayList<>();

        megaphoneBulletInterval = false;
        List_megaphone = new ArrayList<>();

        bazookaInterval = false;
        List_mg = new ArrayList<>();


        // all FALSE , but will be updated at first movement
        isLeft = false;
        isRight = false;
        isUp = false;
        isDown = false;


        direction = "Up";
    }


    public ArrayList<MachineGunBeam> getList_MG() {return this.List_mg;}
    public ArrayList<MegaphoneBeam> getList_Beams() {return this.List_megaphone;}
    public ArrayList<BazookaBeam> getList_Missiles() {return this.List_bazooka;}

    public Image getImage() {return this.image;}
    public int getX()       {return this.x;}
    public int getY()       {return this.y;}
    public int getIntentX() {return (rectX + distanceX);}
    public int getIntentY() {return (rectY + distanceY);}
    public int getRectX()   {return this.rectX;}
    public int getRectY()   {return this.rectY;}

    public String getWeaponType()       {return this.weaponType;}

    public Rectangle getRectangle() {return this.rectangle;}

    public boolean getMegaphone() {return isMegaphone;}
    public boolean getBazooka() {return isBazooka;}

    public int getHp() {return hp;}
    public void setHP(int sumDif) {hp += sumDif;}
    public void maxHP() {

        if (hp != 100)
            hp = 100;
    }

    public void setPlayerController(boolean value) {this.playerController = value;}

    public void activateMegaphone() {this.isMegaphone = true;}

    public void setMegaAmmo(int sum) {this.megaAmmo += sum;}

    public void activateBazooka() {this.isBazooka = true;}

    public void setBazookaAmmo(int sum) {this.bazookaAmmo += sum;}

    private void swapWeapon() {

        switch (weaponType) {

            // Machine Gun --> Megaphone --> Bazooka --> Machine Gun

            case "Machine Gun":
                if (isMegaphone)
                    weaponType = "Megaphone";
                else if (isBazooka)
                    weaponType = "Bazooka";
                break;

            case "Megaphone":
                if (isBazooka)
                    weaponType = "Bazooka";
                else
                    weaponType = "Machine Gun";
                break;

            default:
                weaponType = "Machine Gun";
        }
    }


    // method for creating a temporary Rectangle that checks if movement is possible
    // called from Game
    public Rectangle getTranslatedRectangle() {

        Rectangle translatedRectangle
                = new Rectangle(this.rectX, this.rectY, (int)this.rectangle.getWidth(), (int)this.rectangle.getHeight());

        translatedRectangle.translate(distanceX, distanceY);

        return translatedRectangle;
    }

    // method for pushing the PLAYER when map transition happens
    public void push(int _x, int _y) {

        this.x += _x;
        this.y += _y;
        this.rectX += _x;
        this.rectY += _y;
        this.rectangle.translate(_x, _y);
    }


    // returns the direction the PLAYER is facing
    private String getDirection() {

        if (isUp)
            return "Up";

        if (isDown)
            return "Down";

        if (isLeft)
            return "Left";

        return "Right";
    }


    // method that makes the PLAYER movement happen
    public void move() {

        x += distanceX;
        rectX += distanceX;


        y += distanceY;
        rectY += distanceY;


        rectangle.translate(distanceX, distanceY);
    }


    // receives 'direction' and updates the correct image , based on weaponType
    private void setImageByWeapon() {

        ImageIcon imgIcon;

        switch (direction) {


            case "Up":
                switch (weaponType) {

                    case "Machine Gun":
                        imgIcon = new ImageIcon("src//Img//player//priestMgW.png");
                        break;

                    case "Megaphone":
                        imgIcon = new ImageIcon("src//Img//player//priestMegaW.png");
                        break;

                    default:
                        imgIcon = new ImageIcon("src//Img//player//priestBazookaW.png");
                }
                break;


            case "Right":
                switch (weaponType) {

                    case "Machine Gun":
                        imgIcon = new ImageIcon("src//Img//player//priestMgD.png");
                        break;

                    case "Megaphone":
                        imgIcon = new ImageIcon("src//Img//player//priestMegaD.png");
                        break;


                    default:
                        imgIcon = new ImageIcon("src//Img//player//priestBazookaD.png");
                }
                break;


            case "Down":
                switch (weaponType) {

                    case "Machine Gun":
                        imgIcon = new ImageIcon("src//Img//player//priestMgS.png");
                        break;

                    case "Megaphone":
                        imgIcon = new ImageIcon("src//Img//player//priestMegaS.png");
                        break;

                    default:
                        imgIcon = new ImageIcon("src//Img//player//priestBazookaS.png");
                }
                break;


            default:
                switch (weaponType) {

                    case "Machine Gun":
                        imgIcon = new ImageIcon("src//Img//player//priestMgA.png");
                        break;

                    case "Megaphone":
                        imgIcon = new ImageIcon("src//Img//player//priestMegaA.png");
                        break;

                    default:
                        imgIcon = new ImageIcon("src//Img//player//priestBazookaA.png");
                }
        }

        this.image = imgIcon.getImage();
    }


    //  Control keys | { Ctrl needs to be implemented }
    public void keyPressed(KeyEvent key) throws InterruptedException {

        if (playerController) {

            int code = key.getKeyCode();

            if (!isDown) {
                if (code == KeyEvent.VK_W) {
                    distanceY = -2;
                    this.direction = "Up";

                    setImageByWeapon();

                    isUp = true;
                } else {
                    distanceY = 0;
                    isUp = false;
                }
            }

            if (!isUp) {
                if (code == KeyEvent.VK_S) {
                    distanceY = 2;
                    this.direction = "Down";

                    setImageByWeapon();

                    isDown = true;
                } else {
                    distanceY = 0;
                    isDown = false;
                }
            }

            if (!isLeft) {
                if (code == KeyEvent.VK_D) {
                    distanceX = 2;
                    this.direction = "Right";

                    setImageByWeapon();

                    isRight = true;
                } else {
                    distanceX = 0;
                    isRight = false;
                }
            }

            if (!isRight) {
                if (code == KeyEvent.VK_A) {
                    distanceX = -2;
                    this.direction = "Left";

                    setImageByWeapon();

                    isLeft = true;
                } else {
                    distanceX = 0;
                    isLeft = false;
                }
            }

            if (code == KeyEvent.VK_SPACE) {

                switch (weaponType) {

                    case "Megaphone":
                        if (megaAmmo != 0) {
                            if (!megaphoneBulletInterval) {
                                MegaphoneBeam beam = new MegaphoneBeam(this.direction, this.x, this.y);
                                beam.start();
                                List_megaphone.add(beam);
                                megaphoneBulletInterval = !megaphoneBulletInterval;
                                megaAmmo--;

                                // bulletInterval is false after 1 second
                                new java.util.Timer().schedule(
                                        new TimerTask() {

                                            @Override
                                            public void run() {
                                                megaphoneBulletInterval = false;
                                            }
                                        }, 1000);
                            }
                        }
                        break;

                    case "Machine Gun":
                        if (!mGBulletInterval) {
                            MachineGunBeam mg_beam = new MachineGunBeam(this.direction, this.x, this.y);
                            mg_beam.start();
                            List_mg.add(mg_beam);
                            mGBulletInterval = !mGBulletInterval;

                            new java.util.Timer().schedule(
                                    new TimerTask() {

                                        @Override
                                        public void run() {
                                            mGBulletInterval = false;
                                        }
                                    }, 100);


                        }
                        break;

                    default:
                        if (bazookaAmmo != 0) {
                            if (!bazookaInterval) {
                                BazookaBeam missile = new BazookaBeam(this.direction, this.x, this.y);
                                missile.start();
                                List_bazooka.add(missile);
                                bazookaInterval = !bazookaInterval;
                                bazookaAmmo--;


                                // bulletInterval is false after 2 seconds
                                new java.util.Timer().schedule(
                                        new TimerTask() {

                                            @Override
                                            public void run() {
                                                bazookaInterval = false;
                                            }
                                        }, 2000);
                            }
                        }
                }
            }

            if (!weaponSwapInterval) {
                if (code == KeyEvent.VK_CONTROL) {
                    swapWeapon();
                    setImageByWeapon();

                    System.out.println("Weapon: " + weaponType);

                    weaponSwapInterval = true;

                    // weaponSwapInteral is true after 2 seconds
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {

                                @Override
                                public void run() {
                                    weaponSwapInterval = false;
                                }
                            }, 2000);
                }
            }
        }
    }

    public void keyReleased(KeyEvent key) {

        int code = key.getKeyCode();

        if (code == KeyEvent.VK_W)
            distanceY = 0;

        if (code == KeyEvent.VK_S)
            distanceY = 0;

        if (code == KeyEvent.VK_A)
            distanceX = 0;

        if (code == KeyEvent.VK_D)
            distanceX = 0;
    }
}