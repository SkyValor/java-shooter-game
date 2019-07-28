package game;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class    Enemy {

    private final int TAG;                                      // Tags belong to a specific Map

    private int x, y;                                           // position variables
    private final int SPEED;                                    // movement speed
    private int SLOW_SPEED;                                     // value is 2 if ENEMY is slowed , otherwise is 1
    private int hp;                                             // life points


    private Image image, imgUp, imgDown, imgRight, imgLeft;     // stored images for each direction he's facing

    private Rectangle rectangle;                                // Rectangle for collision
    private int rectX, rectY;                                   // Rectangle's X and Y

    private ArrayList<EnemyBehaviour> List_behaviour;           // contains the behaviours that run in a cycle
    private int behaviourCount;                                 // number of Behaviours inside the list
    private int indexBehaviour;                                 // index of current Behaviour inside the list

    private boolean isUp, isDown, isRight, isLeft;              // tells the direction the ENEMY is walking
    private String direction;                                   // tells the direction the ENEMY is facing
    private boolean playerLockOn;                               // is true when ENEMY is targeting the PLAYER

    private ArrayList<EnemyBullet> List_bullets;                // contains the access to all bullets still alive
    private boolean bulletInterval;                             // is true when weapon is on cooldown



    public Enemy(String _direction, int _x, int _y, int _tag, int _initialIndex) {

        TAG = _tag;

        this.x = _x;
        this.y = _y;

        this.SPEED = 2;
        this.SLOW_SPEED = 1;
        this.hp = 100;

        this.direction = _direction;

        ImageIcon imgIconUp = new ImageIcon("src//Img//enemy//MinionLovaW.png");
        imgUp = imgIconUp.getImage();

        ImageIcon imgIconDown = new ImageIcon("src//Img//enemy//MinionLovaS.png");
        imgDown = imgIconDown.getImage();

        ImageIcon imgIconRight = new ImageIcon("src//Img//enemy//MinionLovaD.png");
        imgRight = imgIconRight.getImage();

        ImageIcon imgIconLeft = new ImageIcon("src//Img//enemy//MinionLovaA.png");
        imgLeft = imgIconLeft.getImage();


        rectX = x + 15;
        rectY = y + 15;
        rectangle = new Rectangle(rectX, rectY, 40, 40);


        List_behaviour = new ArrayList<>();
        behaviourCount = 0;
        indexBehaviour =_initialIndex;


        isUp = false;
        isDown = false;
        isRight = false;
        isLeft = true;

        switch (_direction) {

            case "Up":
                this.image = imgUp;
                isUp = true;
                break;

            case "Down":
                this.image = imgDown;
                isDown = true;
                break;

            case "Right":
                this.image = imgRight;
                isRight = true;
                break;

            default:
                this.image = imgLeft;
                isLeft = true;
        }

        playerLockOn = false;
        List_bullets = new ArrayList<>();
        bulletInterval = false;
    }


    public int getTag() {return this.TAG;}
    public int getX() {return this.x;}
    public int getY() {return this.y;}
    public Image getImage() {return this.image;}

    public int getRectX() {return this.rectX;}
    public int getRectY() {return this.rectY;}
    public Rectangle getRectangle() {return this.rectangle;}

    public void setPlayerLockOn(boolean onOff) {this.playerLockOn = onOff;}
    public ArrayList<EnemyBullet> getList_bullets() {return this.List_bullets;}

    public void setHP(int sumDif) {this.hp += sumDif;}
    public int getHP() {return this.hp;}

    private String getDirection() {return this.direction;}


    // method for getting the correct direction , making all others FALSE
    private void setDirection(String _direction) {

        isLeft = false;
        isRight = false;
        isDown = false;
        isUp = false;

        switch (_direction) {

            case "Up":
                isUp = true;
                break;

            case "Down":
                isDown = true;
                break;

            case "Right":
                isRight = true;

            default:
                isLeft = true;
        }

        this.direction = _direction;
    }


    // method for adding a new BEHAVIOUR (happens at the Game constructor)
    public void addBehaviour(String _fromTo, int xORy) {

        this.List_behaviour.add(new EnemyBehaviour(_fromTo, xORy));
        this.behaviourCount++;
    }


    // method for updating the indexBehaviour variable , every time the ENEMY completes one BEHAVIOUR
    private void updateIndex() {

        if (indexBehaviour == behaviourCount - 1)
            indexBehaviour = 0;
        else
            ++indexBehaviour;
    }


    // method for checking what is the limit of current BEHAVIOUR
    public void moveAccordingToBehaviour()  {

        // if ENEMY is not targeting PLAYER
        if (!playerLockOn) {

            boolean check;  // to see if ENEMY completed the BEHAVIOUR

            switch (List_behaviour.get(indexBehaviour).getFromTo()) {   // which BEHAVIOUR is being tested

                // from UP

                case "UpDown":
                    check = List_behaviour.get(indexBehaviour).upDown();

                    if (check)
                        updateIndex();
                    break;

                case "UpRight":
                    check = List_behaviour.get(indexBehaviour).upRight();

                    if (check)
                        updateIndex();
                    break;

                case "UpLeft":
                    check = List_behaviour.get(indexBehaviour).upLeft();

                    if (check)
                        updateIndex();
                    break;


                // from RIGHT

                case "RightUp":
                    check = List_behaviour.get(indexBehaviour).rightUp();

                    if (check)
                        updateIndex();
                    break;

                case "RightDown":
                    check = List_behaviour.get(indexBehaviour).rightDown();

                    if (check)
                        updateIndex();
                    break;

                case "RightLeft":
                    check = List_behaviour.get(indexBehaviour).rightLeft();

                    if (check)
                        updateIndex();
                    break;


                // from DOWN

                case "DownUp":
                    check = List_behaviour.get(indexBehaviour).downUp();

                    if (check)
                        updateIndex();

                case "DownRight":
                    check = List_behaviour.get(indexBehaviour).downRight();

                    if (check)
                        updateIndex();
                    break;

                case "DownLeft":
                    check = List_behaviour.get(indexBehaviour).downLeft();

                    if (check)
                        updateIndex();
                    break;


                // from LEFT

                case "LeftUp":
                    check = List_behaviour.get(indexBehaviour).leftUp();

                    if (check)
                        updateIndex();
                    break;

                case "LeftRight":
                    check = List_behaviour.get(indexBehaviour).leftRight();

                    if (check)
                        updateIndex();
                    break;

                case "LeftDown":
                    check = List_behaviour.get(indexBehaviour).leftDown();

                    if (check)
                        updateIndex();
                    break;
            }
        }
    }


    // method that gets the PLAYER position and shoots a BULLET to the said position
    public void attackThePlayer(int playerX, int playerY) {

        if (playerLockOn) {

            rotateToPlayer(playerX, playerY);
            shootAtPlayer();
        }
    }


    // method that executes the ENEMY's change of position
    public void move() {

        if (!playerLockOn) {

            if (isUp) {
                this.y -= SPEED / SLOW_SPEED;
                this.rectangle.translate(0, -SPEED / SLOW_SPEED);
            }

            else if (isDown) {
                this.y += SPEED / SLOW_SPEED;
                this.rectangle.translate(0, SPEED / SLOW_SPEED);
            }

            else if (isRight) {
                this.x += SPEED / SLOW_SPEED;
                this.rectangle.translate(SPEED / SLOW_SPEED, 0);
            }

            else {
                this.x -= SPEED / SLOW_SPEED;
                this.rectangle.translate(-SPEED / SLOW_SPEED, 0);
            }
        }
    }


    // method that calculates the angle between the ENEMY and the PLAYER , based on PLAYER position
    private double getAngle(int playerX, int playerY) {

        double angle = Math.toDegrees(Math.atan2( playerY - (this.y + 35), playerX - (this.x + 35)));

        if (angle < 0)
            angle += 360;

        else if (angle > 360)
            angle -= 360;

        return angle;
    }


    // method that forces the ENEMY to face the PLAYER , based on method getAngle()
    private void rotateToPlayer(int playerX, int playerY) {

        double angle = getAngle(playerX, playerY);

        if (angle >= 45 & angle < 135) {
            setDirection("Down");
            updateImageByDebuff();
        }

        else if (angle >= 135 && angle < 225) {
            setDirection("Left");
            updateImageByDebuff();
        }

        else if (angle >= 225 && angle < 315) {
            setDirection("Up");
            updateImageByDebuff();
        }

        else {
            setDirection("Right");
            updateImageByDebuff();
        }
    }


    // method that instantiates a BULLET to the direction the ENEMY is facing
    // happens according to bulletInterval and isSlow variables
    private void shootAtPlayer() {

        if (playerLockOn) {

            // if the weapon in not in cooldown
            if (!bulletInterval) {


                // actually shoot one bullet
                List_bullets.add(new EnemyBullet(this.x, this.y, getDirection()));
                bulletInterval = !bulletInterval;

                // bulletInterval is false after 2 seconds normally and 4 seconds if slowed
                new java.util.Timer().schedule(
                        new TimerTask() {

                            @Override
                            public void run() { bulletInterval = false; }
                        }, 2000 * SLOW_SPEED);
            }
        }
    }


    // method for updating the SENTRY's image according to its slow-debuff state
    public void updateImageByDebuff() {

        ImageIcon imgIcon;

        if (this.SLOW_SPEED == 1) {     // if speed is normal
            switch (direction) {

                case "Up":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaW.png");
                    break;

                case "Right":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaD.png");
                    break;

                case "Down":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaS.png");
                    break;

                default:
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaA.png");

            }
        }

        else {
            switch (direction) {        // if speed is slow

                case "Up":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaW_slow.png");
                    break;

                case "Right":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaD_slow.png");
                    break;

                case "Down":
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaS_slow.png");
                    break;

                default:
                    imgIcon = new ImageIcon("src//Img//enemy//MinionLovaA_slow.png");
            }
        }

        this.image = imgIcon.getImage();
    }

    // method for applying Slow-debuff effects
    public void setSlowDebuff() {

        SLOW_SPEED = 2;

        // ENEMY's speed is back to normal after 6 seconds
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Override
                    public void run() { SLOW_SPEED = 1; }
                }, 6000);
    }


    // ENEMY AI m
    private class EnemyBehaviour {

        private String fromTo;      // from where the ENEMY is coming from and where he is going -- ex.: UpDown
        private int xORy;           // tells the X or Y that triggers the next Behaviour



        // Constructor
        public EnemyBehaviour(String _fromTo, int _xORy) {

            this.fromTo = _fromTo;
            this.xORy = _xORy;
        }


        public String getFromTo() {return this.fromTo;}



        // methods for the movement AI of the ENEMY


        private boolean upRight() {

            if (y <= this.xORy && isUp) {
                setDirection("Right");
                updateImageByDebuff();
                return true;
            }

            else {
                setDirection("Up");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean upDown() {

            if (y <= this.xORy && isUp) {
                setDirection("Down");
                updateImageByDebuff();
                return true;
            }

            else {
                setDirection("Up");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean upLeft() {

            if (y <= this.xORy && isUp) {
                setDirection("Left");
                updateImageByDebuff();
                return true;
            }

            else {
                setDirection("Up");
                updateImageByDebuff();
                return false;
            }
        }

        private boolean rightUp() {

            if (x >= this.xORy && isRight) {
                setDirection("Up");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Right");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean rightLeft() {

            if (x >= this.xORy && isRight) {
                setDirection("Left");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Right");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean rightDown() {

            if (x >= this.xORy && isRight) {
                setDirection("Down");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Right");
                updateImageByDebuff();
                return false;
            }
        }

        private boolean downUp() {

            if (y >= this.xORy && isDown) {
                setDirection("Up");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Down");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean downRight() {

            if (y >= this.xORy && isDown) {
                setDirection("Right");
                updateImageByDebuff();
                return true;
            }

            else {
                setDirection("Down");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean downLeft() {

            if (y >= this.xORy && isDown) {
                setDirection("Left");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Down");
                updateImageByDebuff();
                return false;
            }
        }

        private boolean leftUp() {

            if (x <= this.xORy && isLeft) {
                setDirection("Up");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Left");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean leftRight() {

            if (x <= this.xORy && isLeft) {
                setDirection("Right");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Left");
                updateImageByDebuff();
                return false;
            }
        }
        private boolean leftDown() {

            if (x <= this.xORy && isLeft) {
                setDirection("Down");
                updateImageByDebuff();
                return true;

            } else {
                setDirection("Left");
                updateImageByDebuff();
                return false;
            }
        }
    }


}