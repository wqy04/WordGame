package Round4.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import GameMenu.menu.Frame.HelpFrame;
import GameMenu.menu.Frame.MainFrame;
import GameMenu.menu.Music.AudioController;
import GameMenu.menu.Music.SoundMusicPlayer;

import GameMenu.util.GameUtil;
import Round3.main.GameBox3;

public class GameBox4 {
	public List<Rectangle> rects;
	public Rectangle rect;
	public boolean victory = true;
	private MainFrame mainFrame;
	private HelpFrame helpFrame;
	private AudioController audioController;

	// public boolean exit=true;
	// 0代表空 1代表田，2代表人，3代表戈,4代表男，5代表伐，6代表兵，7代表军,8代表胜，9代表草，10代表树
	int[][] box = {
			{ 9, 0, 9, 0, 9, 10, 9, 10, 9, 10, 9, 10, 9, 10, 9, 0, 9, 0, 9 },
			{ 0, 10, 0, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 0, 10, 0 },
			{ 9, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 10, 0, 0, 0, 0, 0, 9 },
			{ 9, 0, 0, 1, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 9 },
			{ 9, 10, 0, 0, 0, 0, 1, 0, 0, 2, 0, 1, 0, 10, 0, 0, 0, 10, 0 },
			{ 0, 9, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 9, 0 },
			{ 0, 10, 0, 0, 0, 0, 10, 0, 0, 0, 10, 0, 0, 0, 0, 0, 0, 10, 0 },
			{ 0, 10, 0, 0, 10, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 10, 0 },
			{ 9, 0, 0, 0, 0, 3, 0, 2, 3, 0, 0, 3, 0, 2, 0, 0, 0, 0, 9 },
			{ 9, 10, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 10, 9 },
			{ 9, 0, 0, 9, 9, 9, 10, 9, 9, 9, 9, 9, 10, 9, 9, 9, 0, 0, 9 }
	};

	// 定义图片
	private Image tian, ren, ge, nan, fa, bings, jun, sheng, cao, shu;
	// 存入图片
	private static final String NAN = "/Round4/ui/img/nan.png";
	private static final String TIAN = "/Round4/ui/img/tian.png";
	private static final String REN = "/Round4/ui/img/ren.png";
	private static final String GE = "/Round4/ui/img/ge.png";
	private static final String FA = "/Round4/ui/img/fa.png";
	private static final String BINGS = "/Round4/ui/img/bing.png";
	private static final String JUN = "/Round4/ui/img/jun.png";
	private static final String SHENG = "/Round4/ui/img/sheng.png";
	private static final String CAO = "/Round4/ui/img/cao.png";
	private static final String SHU = "/Round4/ui/img/shu.png";

	// 定义音频文件
	private SoundMusicPlayer playerBaJian, playerPengZhuang, playerShengLi, playerChuDi, playerGu;

	// 构造器初始化
	public GameBox4(MainFrame mainFrame, HelpFrame helpFrame) {
		this.mainFrame = mainFrame;
		this.helpFrame = helpFrame;
		this.audioController = mainFrame.getAudioController();

		// 加载图片
		nan = GameUtil.LoadBufferedImages(NAN);
		tian = GameUtil.LoadBufferedImages(TIAN);
		ren = GameUtil.LoadBufferedImages(REN);
		ge = GameUtil.LoadBufferedImages(GE);
		fa = GameUtil.LoadBufferedImages(FA);
		bings = GameUtil.LoadBufferedImages(BINGS);
		jun = GameUtil.LoadBufferedImages(JUN);
		sheng = GameUtil.LoadBufferedImages(SHENG);
		cao = GameUtil.LoadBufferedImages(CAO);
		shu = GameUtil.LoadBufferedImages(SHU);

		// 加载音频文件
		playerBaJian = new SoundMusicPlayer("/Round4/ui/music/拔剑.WAV");
		playerPengZhuang = new SoundMusicPlayer("/Round4/ui/music/碰撞.wav");
		playerShengLi = new SoundMusicPlayer("/Round4/ui/music/胜利.WAV");
		playerChuDi = new SoundMusicPlayer("/Round4/ui/music/锄地.WAV");
		playerGu = new SoundMusicPlayer("/Round4/ui/music/鼓.WAV");
		audioController.addSound(playerBaJian);
		audioController.addSound(playerPengZhuang);
		audioController.addSound(playerShengLi);
		audioController.addSound(playerChuDi);
		audioController.addSound(playerGu);

		rect = new Rectangle();
		rects = new ArrayList<>();
	}

	// 箱子的位置
	int x = 0, y = 0;

	public void draw(Graphics g, GamePeople4 gamePeople) {
		for (int j = 0; j < box.length; j++) {
			for (int i = 0; i < box[0].length; i++) {
				switch (box[j][i]) {
					case 0:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						break;
					case 1:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 2) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {

										box[j - 1][i] = 3;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 1;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 1;
									}
								} else {
									// 2人+1田=4男
									box[j][i] = 0;
									box[j - 1][i] = 4;
									playerChuDi.play();
									playerChuDi.stop();

								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 2) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 1;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 1;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										box[j + 1][i] = 3;
										box[j][i] = 1;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 1;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 1;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 1;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 1;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 1;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 1;
									}
								} else {
									// 人+田=男
									box[j][i] = 0;
									box[j + 1][i] = 4;

									playerChuDi.play();
									playerChuDi.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 2) {

									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {
										box[j][i - 1] = 3;
										box[j][i] = 1;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 1;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 1;
									}
								} else {
									// 2人+1田=4男
									box[j][i] = 0;
									box[j][i - 1] = 4;

									playerChuDi.play();
									playerChuDi.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 2) {

									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 3) {
										box[j][i + 1] = 3;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										box[j][i + 1] = 1;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 1;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 1;
									}
								} else {
									box[j][i] = 0;
									box[j][i + 1] = 4;

									playerChuDi.play();
									playerChuDi.stop();
								}

							}
						}
						g.drawImage(tian, x, y, null);
						save(x, y, g);
						break;
					case 2:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 1) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {
										// 2人+3戈=5伐
										box[j - 1][i] = 5;
										box[j][i] = 0;
										// gamePeople.y+=50;
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 2;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 2;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 2;
									}
								} else {
									// 2人+1田=4男
									box[j][i] = 0;
									box[j - 1][i] = 4;
									playerChuDi.play();
									playerChuDi.stop();

								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 1) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 2;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 2;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										// 2人+3戈=5伐
										box[j + 1][i] = 5;
										box[j][i] = 0;
										// gamePeople.y-=50;
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 2;
										box[j][i] = 2;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 2;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 2;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 2;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 2;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 2;
									}
								} else {
									// 人+田=男
									box[j][i] = 0;
									box[j + 1][i] = 4;

									playerChuDi.play();
									playerChuDi.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 1) {

									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {
										// 2人+3戈=5伐
										box[j][i - 1] = 5;
										box[j][i] = 0;
										// gamePeople.x+=50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 2;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 2;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 2;
									}
								} else {
									// 2人+1田=4男
									box[j][i] = 0;
									box[j][i - 1] = 4;

									playerChuDi.play();
									playerChuDi.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 3) {

									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										// 2人+1天=4男
										box[j][i + 1] = 4;
										box[j][i] = 0;
										// gamePeople.x-=50;

										playerChuDi.play();
										playerChuDi.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 2;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 2;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 2;
									}
								} else {
									// 2人+3戈=5伐
									box[j][i] = 0;
									box[j][i + 1] = 5;

									playerBaJian.play();
									playerBaJian.stop();
								}

							}
						}
						g.drawImage(ren, x, y, null);
						break;
					case 3:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 4) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {
										box[j - 1][i] = 3;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 5;
										box[j][i] = 0;
										// 2人+3戈=5伐
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 3;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 3;
									}
								} else {
									//4男+3戈=6兵
									box[j][i] = 0;
									box[j - 1][i] = 6;
									playerBaJian.play();
									playerBaJian.stop();

								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 4) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 3;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 3;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										box[j + 1][i] = 3;
										box[j][i] = 3;
										gamePeople.y -= 50;
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 3;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 5;
										box[j][i] = 0;
										 //2人+3戈=5伐
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 3;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 3;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 3;
										gamePeople.y -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 3;
									}
								} else {
									//4男+3戈=6兵
									box[j][i] = 0;
									box[j + 1][i] = 6;

									playerBaJian.play();
									playerBaJian.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 4) {

									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {

										box[j][i - 1] = 3;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 5;
										box[j][i] = 0;
										 //2人+3戈=5伐
										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 3;
										gamePeople.x += 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 3;
									}
								} else {
									//4男+3戈=6兵
									box[j][i] = 0;
									box[j][i - 1] = 6;

									playerBaJian.play();
									playerBaJian.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 4) {

									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {

										box[j][i + 1] = 1;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 3) {
										box[j][i + 1] = 3;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 5;
										box[j][i] = 0;
										 //2人+3戈=5伐

										playerBaJian.play();
										playerBaJian.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 3;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 3;
										gamePeople.x -= 50;

										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 3;
									}
								} else {
									//4男+3戈=6兵
									box[j][i] = 0;
									box[j][i + 1] = 6;

									playerBaJian.play();
									playerBaJian.stop();
								}

							}
						}
						g.drawImage(ge, x, y, null);
						save(x, y, g);
						break;
					case 4:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 3) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 2;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 4;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 4;
									}
								} else {
									// 4男+3戈=6兵
									box[j][i] = 0;
									box[j - 1][i] = 6;
									playerBaJian.play();
									playerBaJian.stop();
								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 3) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 2;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 4;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 4;
									}
								} else {
									// 4男+3戈=6兵
									box[j][i] = 0;
									box[j + 1][i] = 6;
									playerBaJian.play();
									playerBaJian.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 3) {
									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 2;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 4;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 4;
									}

								} else {
									// 4男+3戈=6兵
									box[j][i] = 0;
									box[j][i - 1] = 6;
									playerBaJian.play();
									playerBaJian.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 3) {
									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										box[j][i + 1] = 1;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 2;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 4;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 4;
									}
								} else {
									// 4男+3戈=6兵
									box[j][i] = 0;
									box[j][i + 1] = 6;
									playerBaJian.play();
									playerBaJian.stop();
								}
							}
						}
						g.drawImage(nan, x, y, null);
						break;
					case 5:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 7) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] =5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 2;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] =5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {
										box[j - 1][i] =3;
										box[j][i] = 5;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 5;
									}
								} else {
									// 5伐+7军=8胜
									box[j][i] = 0;
									box[j - 1][i] =8;
									playerShengLi.play();
									playerShengLi.stop();
								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 7) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 2;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										box[j + 1][i] = 3;
										box[j][i] = 5;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 5;
									}
								} else {
									// 5伐+7军=8胜
									box[j][i] = 0;
									box[j + 1][i] = 8;
									playerShengLi.play();
									playerShengLi.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 7) {
									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 2;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {
										box[j][i - 1] = 3;
										box[j][i] = 5;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 5;
									}

								} else {
									// 5伐+7军=8胜
									box[j][i] = 0;
									box[j][i - 1] = 8;
									playerShengLi.play();
									playerShengLi.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 7) {
									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										box[j][i + 1] = 1;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 2;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 3) {
										box[j][i + 1] = 3;
										box[j][i] = 5;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 5;
									}
								} else {
									// 5伐+7军=8胜
									box[j][i] = 0;
									box[j][i + 1] = 8;
									playerShengLi.play();
									playerShengLi.stop();
								}
							}
						}
						g.drawImage(fa, x, y, null);
						break;
					case 6:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 6) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {
										box[j - 1][i] = 3;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 2;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 5) {
										box[j - 1][i] = 5;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 6;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {

										box[j][i] = 0;
										box[j - 1][i] = 6;
									}
								} else {
									// 6兵+6兵=7军
									box[j][i] = 0;
									box[j - 1][i] = 7;
									playerGu.play();
									playerGu.stop();
								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 6) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										box[j + 1][i] = 3;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 2;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 5) {
										box[j + 1][i] = 5;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 6;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 6;
									}
								} else {
									// 6兵+6兵=7军
									box[j][i] = 0;
									box[j + 1][i] = 7;
									playerGu.play();
									playerGu.stop();
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 6) {
									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {
										box[j][i - 1] = 3;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 2;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 5) {
										box[j][i - 1] = 5;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 6;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 6;
									}
								} else {
									// 6兵+6兵=7军
									box[j][i] = 0;
									box[j][i - 1] = 7;
									playerPengZhuang.play();
									playerPengZhuang.stop();
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 6) {
									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 3) {
										box[j][i + 1] = 3;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 2;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 5) {
										box[j][i + 1] = 5;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										box[j][i + 1] = 1;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 6;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 6;
									}
								} else {
									// 6兵+6兵=7军
									box[j][i] = 0;
									box[j][i + 1] = 7;
									playerGu.play();
									playerGu.stop();
								}
							}
						}
						g.drawImage(bings, x, y, null);
						break;
					case 7:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								if (box[j - 1][i] != 5) {
									if (box[j - 1][i] == 9) {
										box[j - 1][i] = 9;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 10) {
										box[j - 1][i] = 10;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 3) {
										box[j - 1][i] = 3;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 2) {
										box[j - 1][i] = 2;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 4) {
										box[j - 1][i] = 4;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 1) {
										box[j - 1][i] = 1;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 6) {
										box[j - 1][i] = 6;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j - 1][i] == 7) {
										box[j - 1][i] = 7;
										box[j][i] = 7;
										gamePeople.y += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j - 1][i] = 7;
									}
								} else if (box[j - 1][i] == 5) {
									box[j][i] = 0;
									box[j - 1][i] = 8;
									// victory=false;
									playerShengLi.play();
									playerShengLi.stop();
									g.drawImage(sheng, x, y, null);
								}
							} else if (gamePeople.down) {
								if (box[j + 1][i] != 5) {
									if (box[j + 1][i] == 9) {
										box[j + 1][i] = 9;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 10) {
										box[j + 1][i] = 10;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 3) {
										box[j + 1][i] = 3;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 2) {
										box[j + 1][i] = 2;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 4) {
										box[j + 1][i] = 4;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 1) {
										box[j + 1][i] = 1;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 6) {
										box[j + 1][i] = 6;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j + 1][i] == 7) {
										box[j + 1][i] = 7;
										box[j][i] = 7;
										gamePeople.y -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j + 1][i] = 7;
									}
								} else {
									box[j][i] = 0;
									box[j + 1][i] = 8;
									playerShengLi.play();
									playerShengLi.stop();
									g.drawImage(sheng, x, y, null);
								}

							} else if (gamePeople.left) {
								if (box[j][i - 1] != 5) {
									if (box[j][i - 1] == 9) {
										box[j][i - 1] = 9;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 10) {
										box[j][i - 1] = 10;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 3) {
										box[j][i - 1] = 3;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 2) {
										box[j][i - 1] = 2;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 4) {
										box[j][i - 1] = 4;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 1) {
										box[j][i - 1] = 1;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 6) {
										box[j][i - 1] = 6;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i - 1] == 7) {
										box[j][i - 1] = 7;
										box[j][i] = 7;
										gamePeople.x += 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i - 1] = 7;
									}
								} else {
									box[j][i] = 0;
									box[j][i - 1] = 8;
									playerShengLi.play();
									playerShengLi.stop();
									g.drawImage(sheng, x, y, null);
								}
							} else if (gamePeople.right) {
								if (box[j][i + 1] != 5) {
									if (box[j][i + 1] == 9) {
										box[j][i + 1] = 9;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 10) {
										box[j][i + 1] = 10;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 3) {
										box[j][i + 1] = 3;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 2) {
										box[j][i + 1] = 2;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 4) {
										box[j][i + 1] = 4;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 1) {
										box[j][i + 1] = 1;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 6) {
										box[j][i + 1] = 6;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else if (box[j][i + 1] == 7) {
										box[j][i + 1] = 7;
										box[j][i] = 7;
										gamePeople.x -= 50;
										playerPengZhuang.play();
										playerPengZhuang.stop();
									} else {
										box[j][i] = 0;
										box[j][i + 1] = 7;
									}
								} else {
									box[j][i] = 0;
									box[j][i + 1] = 8;
									playerShengLi.play();
									playerShengLi.stop();
									g.drawImage(sheng, x , y, null);
								}
							}
						}
						g.drawImage(jun, x, y, null);
						break;
					case 8:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						g.drawImage(sheng, x, y, null);
						victory = false;
						break;
					case 9:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								gamePeople.y += 50;
							} else if (gamePeople.down) {
								gamePeople.y -= 50;
							} else if (gamePeople.left) {
								gamePeople.x += 50;
							} else if (gamePeople.right) {
								gamePeople.x -= 50;
							}
						}
						g.drawImage(cao, x, y, null);
						save(x, y, g);
						break;
					case 10:
						x = (i + 1) * 50;
						y = 50 + j * 50;
						if (gamePeople.x == x && gamePeople.y == y) {
							if (gamePeople.up) {
								gamePeople.y += 50;
							} else if (gamePeople.down) {
								gamePeople.y -= 50;
							} else if (gamePeople.left) {
								gamePeople.x += 50;
							} else if (gamePeople.right) {
								gamePeople.x -= 50;
							}
						}
						g.drawImage(shu, x, y, null);
						save(x, y, g);
						break;
				}
			}
		}

		coll(gamePeople);
		colls(gamePeople);
	}

	int filesize = 0;

	public void che() {
		for (int i = 0; i < box.length; i++) {
			for (int j = 0; j < box[0].length; j++) {
				if (box[i][j] == 1) {
					filesize++;
				}
			}
		}
	}

	/*
	 * ǽ
	 */
	public void save(int x, int y, Graphics g) {
		if (filesize > 0) {
			GameBox4 gameBox = new GameBox4(mainFrame, helpFrame);
			gameBox.rect.x = x;
			gameBox.rect.y = y;
			gameBox.rect.width = 50;
			gameBox.rect.height = 50;
			rects.add(gameBox.rect);
			filesize--;
		}

		g.setColor(Color.red);
		g.drawRect(rect.x, rect.y, rect.width, rect.height);
	}

	/*
	 * ǽ ײ
	 */
	public void coll(GamePeople4 gamePeople) {
		for (int i = 0; i < rects.size(); i++) {
			Rectangle rectangle = rects.get(i);
			// жϾ Ƿ ཻ
			if (gamePeople.rect.intersects(rectangle)) {
				System.out.println("ײ    ");
				if (gamePeople.up) {
					gamePeople.y += 50;
				} else if (gamePeople.down) {
					gamePeople.y -= 50;
				} else if (gamePeople.left) {
					gamePeople.x += 50;
				} else if (gamePeople.right) {
					gamePeople.x -= 50;
				}
			}
		}
	}

	public void colls(GamePeople4 gamePeople) {
		if (gamePeople.x <= 0 || gamePeople.x >= 1000 || gamePeople.y <= 0 || gamePeople.y >= 600) {
			if (gamePeople.right) {
				gamePeople.x -= 50;
			} else if (gamePeople.left) {
				gamePeople.x += 50;
			} else if (gamePeople.up) {
				gamePeople.y += 50;
			} else if (gamePeople.down) {
				gamePeople.y -= 50;
			}
		}

	}
}

	
