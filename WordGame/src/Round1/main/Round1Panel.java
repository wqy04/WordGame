package Round1.main;

import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.menu.Music.AudioController;
import GameMenu.util.GameBlackBoard;
import GameMenu.util.PreLocation;
import GameMenu.util.UndoManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class Round1Panel extends JPanel {
      private GameBlackBoard gameBlackBoard;
      private Stack<PreLocation> preLocation_people = new Stack<>();
      private UndoManager undoManager = new UndoManager() ;
      private GameBox gameBox;

      private GamePeople gamePeople;
      private HelpFrame helpFrame;

      private BufferedImage buffimg = new BufferedImage(1030,640,BufferedImage.TYPE_4BYTE_ABGR);

      public Round1Panel(MainFrame mainFrame){
            helpFrame = new HelpFrame(mainFrame);
            setLayout(null);
            setFocusable(true);

            Instantiation(mainFrame, helpFrame);
            new run().start();
            addKeyListener(new KeyAdapter() {
                  @Override
                  public void keyPressed(KeyEvent e) {
                        int key = e.getKeyCode();
                        switch (key){
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
                                    if (gamePeople.step != 0){
                                          gamePeople.step--;
                                    }
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
                        Round1Panel.this.requestFocusInWindow();
                  }
            });
      }

      //实例化类
      public void Instantiation(MainFrame mainFrame, HelpFrame helpFrame){
            gameBlackBoard = new GameBlackBoard();
            gameBox = new GameBox(mainFrame, helpFrame);
            gamePeople = new GamePeople();
      }

      class run extends Thread{

            long startTime = System.currentTimeMillis();

            @Override
                  public void run(){
                        while(!gameBox.exit){
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

      //绘制图形类
      @Override
      public void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics graphics = buffimg.getGraphics();
            try {
                  gameBlackBoard.draw(graphics);
                  gameBox.draw(graphics, gamePeople);
                  gamePeople.draw(graphics);
                  g.drawImage(buffimg, -30, -20, null);
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
