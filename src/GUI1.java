import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.util.Timer;
import java.util.TimerTask;

public class GUI1 extends JFrame {
	private Image screenImage;
	private Graphics screenGraphic;
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/exitButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/play.jpg"));
	private ImageIcon startButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/playbasic.jpg"));
	private ImageIcon quitButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/exit.jpg"));
	private ImageIcon quitButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/exitbasic.jpg"));
	private ImageIcon helpButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/help.jpg"));
	private ImageIcon helpButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/helpbasic.jpg"));
	private ImageIcon leftButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/leftButtonEntered.png"));
	private ImageIcon leftButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/leftButtonBasic.png"));
	private ImageIcon rightButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/rightButtonEntered.png"));
	private ImageIcon rightButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/rightButtonBasic.png"));
	private ImageIcon easyButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/easyButtonEntered.png"));
	private ImageIcon easyButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/easyButtonBasic.png"));
	private ImageIcon hardButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/hardButtonEntered.png"));
	private ImageIcon hardButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/hardButtonBasic.png"));
	private ImageIcon backButtonEnteredImage = new ImageIcon(GameGUI.class.getResource("./images/backButtonEntered.png"));
	private ImageIcon backButtonBasicImage = new ImageIcon(GameGUI.class.getResource("./images/backButtonBasic.png"));

	private Image background = new ImageIcon(GameGUI.class.getResource("./images/mainpage.jpg"))
			.getImage();
	private JLabel menuBar = new JLabel(new ImageIcon(GameGUI.class.getResource("./images/menubar.png")));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private JButton helpButton = new JButton(helpButtonBasicImage);
	private JButton leftButton = new JButton(leftButtonBasicImage);
	private JButton rightButton = new JButton(rightButtonBasicImage);
	private JButton easyButton = new JButton(easyButtonBasicImage);
	private JButton hardButton = new JButton(hardButtonBasicImage);
	private JButton backButton = new JButton(backButtonBasicImage);
	
	//game screen
	private JTextField textField = new JTextField(50);
	private JPanel panel = new JPanel();
	private JTextArea messageAreaQuiz = new JTextArea(16, 50);
	private JTextArea messageAreaGuess = new JTextArea(16, 50);
	private JTextArea messageAreaHint = new JTextArea(16, 50);
	
	private int life = 5;
	private int score = 0;
	private int time;
	
	private JPanel panelLife = new JPanel();
	private JPanel panelScore = new JPanel();
	private JPanel panelTime = new JPanel();
	
	private JLabel labelLife = new JLabel("LIFE");
	private JLabel labelScore = new JLabel("SCORE");
	private JLabel labelTime = new JLabel("TIME");

	
	private JLabel labelLife2 = new JLabel(String.valueOf(life));
	private JLabel labelScore2 = new JLabel(String.valueOf(score));
	private JLabel labelTime2 = new JLabel(String.valueOf(time));

	private int mouseX, mouseY;
	private boolean isMainScreen = false;
	private boolean isGameScreen = false;
	
	static int interval;
	static Timer timer;

	ArrayList<Track> trackList = new ArrayList<Track>();

	private Image titleImage;
	private Image selectedImage;
	private Music selected;
	private Music introMusic = new Music("mainmusic.mp3", true);
	private int nowSelected = 0;
	
	
	QuestionGenerator qg;
	String answer;
	String question;
	String hint;
	int level;
	String X,Y;
	int m,n;
	private int gameType;
	private int gameLevel;
	private int cnt = 0;
	boolean isCorrect = false;
	
	GUI1() {
		setUndecorated(true);
		setTitle("Game LCS");
		setSize(GameGUI.SCREEN_WIDTH, GameGUI.SCREEN_HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBackground(new Color(0, 0, 0, 0));
		setLayout(null);
		
		introMusic.start();
		
		//track list
		trackList.add(new Track("fruitimg.jpg","fruitBackground.png",  "quizmusic.mp3"));
		trackList.add(new Track("animalimg.jpg","animalBackground.jpg",  "quizmusic.mp3"));
		
		exitButton.setBounds(1245, 0, 30, 30);
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(exitButton);

		startButton.setBounds(40, 200, 400, 100);
		startButton.setBorderPainted(false);
		startButton.setContentAreaFilled(false);
		startButton.setFocusPainted(false);
		startButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				startButton.setIcon(startButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				startButton.setIcon(startButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				enterMain();
			}
		});
		add(startButton);
		
		quitButton.setBounds(40, 330, 400, 100);	
		quitButton.setBorderPainted(false);
		quitButton.setContentAreaFilled(false);
		quitButton.setFocusPainted(false);
		quitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				quitButton.setIcon(quitButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				quitButton.setIcon(quitButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		add(quitButton);
		
		helpButton.setBounds(40, 460, 400, 100);	
		helpButton.setBorderPainted(false);
		helpButton.setContentAreaFilled(false);
		helpButton.setFocusPainted(false);
		helpButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				helpButton.setIcon(helpButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				helpButton.setIcon(helpButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "This game is developed by YoonChan & Sojung", "About this game...", JOptionPane.PLAIN_MESSAGE);
			}
		});
		add(helpButton);
		
		leftButton.setVisible(false);
		leftButton.setBounds(140, 310, 60, 60);
		leftButton.setBorderPainted(false);
		leftButton.setContentAreaFilled(false);
		leftButton.setFocusPainted(false);
		leftButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				leftButton.setIcon(leftButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				leftButton.setIcon(leftButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectLeft();
			}
		});
		add(leftButton);

		rightButton.setVisible(false);
		rightButton.setBounds(1080, 310, 60, 60);
		rightButton.setBorderPainted(false);
		rightButton.setContentAreaFilled(false);
		rightButton.setFocusPainted(false);
		rightButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rightButton.setIcon(rightButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				rightButton.setIcon(rightButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				selectRight();
			}
		});
		add(rightButton);
	
		easyButton.setVisible(false);
		easyButton.setBounds(375, 580, 250, 67);
		easyButton.setBorderPainted(false);
		easyButton.setContentAreaFilled(false);
		easyButton.setFocusPainted(false);
		easyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				easyButton.setIcon(easyButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				easyButton.setIcon(easyButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "easy");
			}
		});
		add(easyButton);

		
		hardButton.setVisible(false);
		hardButton.setBounds(655, 580, 250, 67);
		hardButton.setBorderPainted(false);
		hardButton.setContentAreaFilled(false);
		hardButton.setFocusPainted(false);
		hardButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				hardButton.setIcon(hardButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				hardButton.setIcon(hardButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				gameStart(nowSelected, "hard");
			}
		});
		add(hardButton);
		
		backButton.setVisible(false);
		backButton.setBounds(20, 50, 60, 60);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				backButton.setIcon(backButtonEnteredImage);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				backButton.setIcon(backButtonBasicImage);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				backMain();
			}
		});
		add(backButton);
		
		menuBar.setBounds(0, 0, 1280, 30);
		menuBar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		menuBar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				setLocation(x - mouseX, y - mouseY);
			}
		});
		add(menuBar);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setVisible(false);
		panel.setBounds(300, 200,680, 420);
		
		panel.add(new JScrollPane(messageAreaQuiz));
		panel.add(new JScrollPane(messageAreaGuess));
		panel.add(new JScrollPane(messageAreaHint));
		panel.add(textField);
		
		textField.setVisible(false);
		messageAreaQuiz.setVisible(false);
		messageAreaGuess.setVisible(false);
		messageAreaHint.setVisible(false);

		textField.setEditable(true);//입력 화면
		messageAreaQuiz.setEditable(false);//출력 화면
		messageAreaGuess.setEditable(false);//출력 화면
		messageAreaHint.setEditable(false);//출력 화면

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				isCorrect = false;
				answer = textField.getText();
				qg.setAnswer(answer);
				
				messageAreaGuess.append(qg.getAnswer()+"\n");
				
				X = qg.getQuestionOriginal(cnt);
				Y = qg.getAnswer();
				
				m = X.length();
				n = Y.length();
				
				int[][] T = new int[m + 1][n + 1];

				AlgorithmLCS.LCSLength(X, Y, m, n, T);
				
				hint = AlgorithmLCS.LCS(qg.getQuestionOriginal(cnt),qg.getAnswer(),m,n,T);
				if(hint.equals(qg.getQuestionOriginal(cnt))) {
					isCorrect = true;
					cnt++;
					score +=10;
					labelScore2.setText(String.valueOf(score));
					messageAreaQuiz.append("CORRECT!: " + hint + "\n");
					messageAreaQuiz.append(qg.getQuestion(cnt)+"\n");
				}
				if(isCorrect == false) {
					messageAreaHint.append(hint +"\n");
					life--;
					labelLife2.setText(String.valueOf(life));
				}
				if(life == 0) {
					gameOver(1);
				}
				textField.setText("");
			}
		});
		add(panel);

		panelLife.setLayout(new BoxLayout(panelLife, BoxLayout.Y_AXIS));
		panelLife.setBounds(100, 200, 150, 150);
		
		panelScore.setLayout(new BoxLayout(panelScore, BoxLayout.Y_AXIS));
		panelScore.setBounds(1030, 200, 150, 150);

		panelTime.setLayout(new BoxLayout(panelTime, BoxLayout.Y_AXIS));
		panelTime.setBounds(100, 450, 150, 150);
		
		Font font = new Font("showcard gothic", Font.PLAIN,30);
		labelLife.setFont(font);
		labelLife2.setFont(font);
		labelScore.setFont(font);
		labelScore2.setFont(font);
		labelTime.setFont(font);
		labelTime2.setFont(font);
		
		labelLife.setText("LIFE");
		labelLife2.setText(String.valueOf(life));
		
		labelScore.setText("SCORE");
		labelScore2.setText(String.valueOf(score));
		
		labelTime.setText("TIME");
		labelTime2.setText(String.valueOf(time));

		labelLife.setVisible(true);
		labelLife2.setVisible(true);

		labelScore.setVisible(true);
		labelScore2.setVisible(true);

		labelTime.setVisible(true);
		labelTime2.setVisible(true);


		panelLife.add(labelLife);
		panelScore.add(labelScore);
		panelTime.add(labelTime);
		panelLife.add(labelLife2);
		panelScore.add(labelScore2);
		panelTime.add(labelTime2);

		panelLife.setVisible(false);
		panelScore.setVisible(false);
		panelTime.setVisible(false);

		add(panelLife);
		add(panelScore);
		add(panelTime);

	}
	public void paint(Graphics g) {
		screenImage = createImage(GameGUI.SCREEN_WIDTH, GameGUI.SCREEN_HEIGHT);
		screenGraphic = screenImage.getGraphics();
		screenDraw(screenGraphic);
		g.drawImage(screenImage, 0, 0, null);
	}
	
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, null);
		if(isMainScreen)
		{
			g.drawImage(selectedImage, 340, 100, null);
			g.drawImage(titleImage, 340, 70, null);
		}
		if(isGameScreen)
		{
			
		}
		paintComponents(g);
		this.repaint();
	}
	public void selectTrack(int nowSelected) {
		if(selected != null)
			selected.close();
		selectedImage = new ImageIcon(GameGUI.class.getResource("./images/" + trackList.get(nowSelected).getStartImage())).getImage();
	}
	

	public void selectLeft() {
		if(nowSelected == 0)
			nowSelected = trackList.size() - 1;
		else
			nowSelected--;
		selectTrack(nowSelected);
	}
	
	public void selectRight() {
		if(nowSelected == trackList.size() - 1)
			nowSelected = 0;
		else
			nowSelected++;
		selectTrack(nowSelected);
	}

	public void backMain() {
		messageAreaQuiz.setText("");
		messageAreaGuess.setText("");
		messageAreaHint.setText("");
		timer.cancel();
		life = 5;
		score = 0;
		labelLife2.setText(String.valueOf(life));
		labelScore2.setText(String.valueOf(score));

		selected.close();
		introMusic = new Music("mainmusic.mp3", true);
		introMusic.start();
		
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		background = new ImageIcon(GameGUI.class.getResource("./images/mainbackground.png"))
				.getImage();
		panel.setVisible(false);
		backButton.setVisible(false);
		
		panelLife.setVisible(false);
		panelScore.setVisible(false);
		panelTime.setVisible(false);
		
		labelLife.setVisible(false);
		labelLife2.setVisible(false);
		
		labelScore.setVisible(false);
		labelScore2.setVisible(false);
		
		labelTime.setVisible(false);
		labelTime2.setVisible(false);

		selectTrack(nowSelected);
	}
	public void enterMain() {
		startButton.setVisible(false);
		quitButton.setVisible(false);
		helpButton.setVisible(false);
		background = new ImageIcon(GameGUI.class.getResource("./images/mainbackground.png"))
				.getImage();
		isMainScreen = true;
		leftButton.setVisible(true);
		rightButton.setVisible(true);
		easyButton.setVisible(true);
		hardButton.setVisible(true);
		selectTrack(0);
	}

	public void gameStart(int nowSelected, String difficulty) {
		if(nowSelected == 0)
			this.gameType = 2;
		else if(nowSelected == 1)
			this.gameType = 1;
		
		if(difficulty.equals("easy")) {
			this.gameLevel = 1;
			this.level = 1;
		}
		else if(difficulty.equals("hard")) {
			this.gameLevel = 2;
			this.level = 2;
		}

		this.qg =  new QuestionGenerator(level, gameType);
		
		messageAreaQuiz.append(qg.getQuestion(cnt)+"\n");
		
		if(selected != null)
			selected.close();
		isMainScreen = false;
		isGameScreen = true;
		leftButton.setVisible(false);
		rightButton.setVisible(false);
		easyButton.setVisible(false);
		hardButton.setVisible(false);
		background = new ImageIcon(GameGUI.class.getResource("./images/" + trackList.get(nowSelected).getGameImage()))
				.getImage();
		backButton.setVisible(true);
		
		introMusic.close();
		selected = new Music(trackList.get(nowSelected).getGameMusic(), true);
		selected.start();
		
		panel.setVisible(true);
		textField.setVisible(true);
		messageAreaQuiz.setVisible(true);
		messageAreaGuess.setVisible(true);
		messageAreaHint.setVisible(true);
		
		panelLife.setVisible(true);
		panelScore.setVisible(true);
		panelTime.setVisible(true);

		labelLife.setVisible(true);
		labelLife2.setVisible(true);
		
		labelScore.setVisible(true);
		labelScore2.setVisible(true);
		
		labelTime.setVisible(true);
		labelTime2.setVisible(true);
		
		time();
	}
	public  void time() {
	    String secs = "300"; //시간 초단위
	    int delay = 1000;
	    int period = 1000;
	    timer = new Timer();
	    interval = Integer.parseInt(secs);
	    time = Integer.parseInt(secs);
	    labelTime2.setText(String.valueOf(time));
	    timer.scheduleAtFixedRate(new TimerTask() {

	        public void run() {
	        	time = setInterval();
	        	if(time == 0) {
	        		gameOver(2);
	        		timer.cancel();
	        	}
	            labelTime2.setText(String.valueOf(time));
	        }
	    }, delay, period);
	}

	private static final int setInterval() {
	    if (interval == 1)
	        timer.cancel();
	    return --interval;
	}
	public void gameOver(int option){
		if(option ==1) {
			JOptionPane.showMessageDialog(null, "No More Chance!\nYour Score: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
			backMain();
		}
		else if(option ==2) {
			JOptionPane.showMessageDialog(null, "No More Time!\nYour Score:" + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
			backMain();
		}
	}
}