package Round4.main;
import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.util.GameBlackBoard;
import GameMenu.util.PreLocation;
import GameMenu.util.UndoManager;

import javax.swing.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Round4Panel extends JPanel {
	//实例化背景类
    private GameBlackBoard gameBlackBoard;
    //实例化箱子类
    private GameBox4 gameBox;
    //存放图片
    private final BufferedImage buffimg = new BufferedImage(1030,640,BufferedImage.TYPE_4BYTE_ABGR);
    private final Stack<PreLocation> preLocation_people = new Stack<>();
    private final UndoManager undoManager = new UndoManager() ;
    //实例化人物
    private GamePeople4 gamePeople;
    private final HelpFrame helpFrame;

    //构造器对游戏初始化
    public Round4Panel(MainFrame mainFrame) {
        helpFrame = new HelpFrame(mainFrame);
        setLayout(null);
        setFocusable(true);

        //启动线程
        Instantiation(mainFrame, helpFrame);
        new run().start();

        addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        undoManager.saveHistoryToStorage(gameBox.box);
                        push();
                        gamePeople.walk(1);
                        break;
                    case KeyEvent.VK_DOWN:
                        undoManager.saveHistoryToStorage(gameBox.box);
                        push();
                        gamePeople.walk(2);
                        break;
                    case KeyEvent.VK_LEFT:
                        undoManager.saveHistoryToStorage(gameBox.box);
                        push();
                        gamePeople.walk(3);
                        break;
                    case KeyEvent.VK_RIGHT:
                        undoManager.saveHistoryToStorage(gameBox.box);
                        push();
                        gamePeople.walk(4);
                        break;
                    case KeyEvent.VK_Z:
                        unDo();
                        break;
                    case KeyEvent.VK_ESCAPE:
                        String currentPanel = mainFrame.getCurrentPanelName();
                        helpFrame.showPanel("Pause", currentPanel, 0, 0);
                        break;
                }
                repaint();
        	}
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                Round4Panel.this.requestFocusInWindow();
            }
        });
    }
    //实例化类
    public void Instantiation(MainFrame mainFrame, HelpFrame helpFrame){
        gameBlackBoard = new GameBlackBoard();
        gameBox= new GameBox4(mainFrame, helpFrame);
        gamePeople=new GamePeople4();
    }

    class run extends Thread{
    	long startTime = System.currentTimeMillis();
        @Override
        public void run(){
            while(gameBox.victory){
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        repaint();
                    }
                });
                try {
                    Thread.sleep(33);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            long endTime =  System.currentTimeMillis();
            long usedTime = (endTime-startTime)/1000;
            helpFrame.showPanel("GamePass", null, gamePeople.step, usedTime);
            System.out.println("用时：" + usedTime);
        }
    }
    /*
     * 所有绘制的的内容
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics graphics = buffimg.getGraphics();
        try {
            gameBlackBoard.draw(graphics);
            gameBox.draw(graphics, gamePeople);
            gamePeople.draw(graphics);
            g.drawImage(buffimg, -30, -40, null);
        } finally {
            graphics.dispose();
        }
    }

    public void unDo(){
        //玩家的撤回逻辑
        if (!preLocation_people.isEmpty()) { // 如果栈不为空
            if (preLocation_people.peek().isPeople()) {
                gamePeople.x = preLocation_people.peek().getX();
                gamePeople.y = preLocation_people.peek().getY();
                preLocation_people.pop();
            }else {
                gamePeople.x = preLocation_people.peek().getX();
                gamePeople.y = preLocation_people.peek().getY();
                preLocation_people.pop();
            }
        }else {
            System.out.println("player栈为空");
        }
        //地图的撤回逻辑
        int[][] previousMap = undoManager.getPreviousMap();
        if (previousMap != null) {
            gameBox.box = previousMap;
            undoManager.getMapStored().pop();
        } else {
            System.out.println("没有历史地图状态可用");
        }

    }
    public void push(){
        preLocation_people.push(new PreLocation(gamePeople.x, gamePeople.y,true));
    }

    @Override
    public void addNotify(){
        super.addNotify();
        requestFocusInWindow();
    }
}
