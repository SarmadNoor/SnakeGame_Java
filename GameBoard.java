import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class GameBoard extends JPanel implements ActionListener {

   Random random;
   static final int gameScreenWidth = 600;
   static final int gameScreenHeight = 600;
   int unitSize = 30;
   int snakeSpeedDelay = 200;
   int foodXCoordinate;
   int foodYCoordinate;
   int foodQuantity;
   Timer timer;
   int snakeLength = 5;
   char snakeDirection = 'R';
   final int singleBlockUnit = (gameScreenWidth*gameScreenHeight)/(unitSize*unitSize);
   final int xBoard[] = new int[singleBlockUnit];
   final int yBoard[] = new int[singleBlockUnit];
   boolean isSnakeRunning = false;
   GameBoard(){
    random = new Random();
    this.setPreferredSize(new Dimension(gameScreenWidth, gameScreenHeight));
    this.setBackground(Color.gray);
    this.setFocusable(true);
    this.addKeyListener(new MyKeyAdapter());
    StartSnakeGame();
   }
   
   public void StartSnakeGame()
   {
    FoodGenerator();
    isSnakeRunning = true;
    timer = new Timer(snakeSpeedDelay,this);
    timer.start();
   }
   public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    DrawGameComponents(graphics);
}

public void DrawGameComponents(Graphics g) {
		
    if(isSnakeRunning) {
        g.setColor(Color.red);
        g.fillRect(foodXCoordinate, foodYCoordinate, unitSize, unitSize);
    
        for(int i = 0; i< snakeLength;i++) {
            if(i == 0 || i==1) {
                g.setColor(Color.BLUE);
                g.fillRect(xBoard[i], yBoard[i], unitSize, unitSize);
            }
            else {
                g.setColor(Color.green);
                g.fillRect(xBoard[i], yBoard[i], unitSize, unitSize);
            }			
        }
        g.setFont( new Font(Font.SANS_SERIF,Font.ITALIC, 40));
        g.drawString("Score: "+foodQuantity, 20, 50);
    }
    else {
        GameOver(g);
    }   
}
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isSnakeRunning) {
            move();
            checkFood();
            CrashCheck();
        }
        repaint();
    }

	public void checkFood() {
		if((xBoard[0] == foodXCoordinate) && (yBoard[0] == foodYCoordinate)) {
			snakeLength++;
			foodQuantity++;
            FoodGenerator();
		}
	}
    public void move() {
        for(int i = snakeLength; i> 0; i--)
        {
            xBoard[i] = xBoard[i-1];
            yBoard[i] = yBoard[i-1];
        }
        if(snakeDirection == 'U'){
            yBoard[0] = yBoard[0] - unitSize;
        }
        else if(snakeDirection == 'D'){
            yBoard[0] = yBoard[0] + unitSize;
        }
        else if(snakeDirection == 'L') {
            xBoard[0] = xBoard[0] - unitSize;
        }
        else if(snakeDirection == 'R') {
            xBoard[0] = xBoard[0] + unitSize;
        }
    }
public void FoodGenerator()
{
foodXCoordinate = random.nextInt((int)gameScreenWidth/unitSize) * unitSize;
foodYCoordinate = random.nextInt((int)gameScreenHeight/unitSize) * unitSize;
}
public void CrashCheck() {

    for(int i = snakeLength;i > 0;i--) {
        if((xBoard[0] == xBoard[i]) && (yBoard[0] == yBoard[i])) {
            isSnakeRunning = false;
        }
    }

    if(xBoard[0] < 0) {
        isSnakeRunning = false;
    }

    if(xBoard[0] > gameScreenWidth) {
        isSnakeRunning = false;
    }

    if(yBoard[0] < 0) {
        isSnakeRunning = false;
    }

    if(yBoard[0] > gameScreenHeight) {
        isSnakeRunning = false;
    }
    
    if(!isSnakeRunning) {
        timer.stop();
    }
}
public void GameOver(Graphics g) {
    g.setColor(Color.red);
    g.setFont( new Font(Font.SANS_SERIF,Font.ITALIC, 40));
    g.drawString("Score: "+foodQuantity, 20,50);
    g.drawString("Game Over", 20, 100);
}
public class MyKeyAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e)
    {
      if(e.getKeyCode() == KeyEvent.VK_LEFT && snakeDirection != 'R')
    {
        snakeDirection = 'L';
    }
    else if(e.getKeyCode() == KeyEvent.VK_RIGHT && snakeDirection != 'L')
    {
        snakeDirection = 'R';
    }
    else if(e.getKeyCode() == KeyEvent.VK_UP && snakeDirection != 'D')
    {
        snakeDirection = 'U';
    }
    else if (e.getKeyCode() == KeyEvent.VK_DOWN && snakeDirection != 'U')
    {
        snakeDirection = 'D';
    }
    
}
}
}