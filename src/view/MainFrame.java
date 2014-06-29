package view;

import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Dictionary;
import model.TxtFileParser;
import controller.Controller;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	/* datas for panels */
	private ChoosePanel choosePanel = new ChoosePanel();
	private WordPanel wordPanel = new WordPanel();
	private NumPanel numPanel = new NumPanel();
	private PlayPanel playPanel = new PlayPanel();
	private CurrentWordListResultPanel currentWordListResultPanel = new CurrentWordListResultPanel();
	private AllResultPanel allResultPanel = new AllResultPanel();
	private QuizResultPanel quizResultPanel = new QuizResultPanel();

	private Controller controller = new Controller();
	private CardLayout cardLayout = new CardLayout();

	// private Dictionary dictionary = new ;

	public MainFrame() {
		 TxtFileParser.getInstance().init("file/txtfile.txt");
		// add Panels to the MainFrame
		getContentPane().setLayout(cardLayout);
		add(choosePanel, "choosePanel");

		initActsForChoosePanel();
		initActsForWordPanel();
		initActsForNumPanel();
		initActsForPlayPanel();
		initActsForCurrentWordListResultPanel();
		initActsForAllResultPanel();
		initActsForQuizResultPanel();

		// set some basic attributes of the frame
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("WordMaster");
		setVisible(true);

	}

	private void initActsForQuizResultPanel() {
		quizResultPanel.getReturnButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				quizResultPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				quizResultPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// switch panel
				choosePanel.setWordListPosition(-1);
				add(choosePanel, "choosePanel");
				remove(quizResultPanel);
				pack();
				setLocationRelativeTo(null);
				cardLayout.show(getContentPane(), "choosePanel");
			}
		});
	}

	private void initActsForChoosePanel() {

		// add listener to getStartButton
		choosePanel.getStartButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// if no word bank is chosen, show a message
				if (choosePanel.getWordListPosition() < 0) {
					JOptionPane.showMessageDialog(null, "请通过鼠标点击字母选择词库！");
				}
				// otherwise switch the panel
				else {
					// Before switching the panel, give the selected letter,
					// which indicates the word bank chose, to the controller
					controller.setLetterPosition(choosePanel
							.getWordListPosition());
					// switch panel
					wordPanel = new WordPanel();
					initActsForWordPanel();
					// if there the user hasn't studied this word list before
					int hasLastTime = controller.setFirstWordIndex(
							WordPanel.STARTFROMLAST,
							controller.getLetterPosition(), "");
					if (hasLastTime < 0) {
						wordPanel.setOption(-1);
					} else {
						wordPanel.setOption(WordPanel.STARTFROMFIRST);
					}
					add(wordPanel, "wordPanel");
					remove(choosePanel);
				}
			}
		});

		// add listeners to getResultButton
		choosePanel.getResultButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// if no word bank is chosen, show a message
				if (choosePanel.getWordListPosition() < 0) {
					System.out.println();
					JOptionPane.showMessageDialog(null, "请通过鼠标点击字母选择词库！");
				}
				// otherwise switch the panel
				else {
					int letterPosition = controller.getLetterPosition();
					char wordBankChar = (char) ('A' + controller
							.getLetterPosition());
					String wordBankName = wordBankChar + "";
					currentWordListResultPanel.setData(
							wordBankName,
							controller.getAllNum(letterPosition),
							controller.getDoneNum(letterPosition),
							controller.getCorrectNum(letterPosition),
							controller.getDoneNum(letterPosition)
									- controller.getCorrectNum(letterPosition),
							controller.getCorrectRate(letterPosition));
					add(currentWordListResultPanel);
					remove(choosePanel);
				}
			}
		});

		// add listeners to getAllResultButton
		choosePanel.getAllResultButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				choosePanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// switch panel
				allResultPanel.setData(controller.getAllNum(26),
						controller.getDoneNum(26),
						controller.getCorrectNum(26), controller.getDoneNum(26)
								- controller.getCorrectNum(26),
						controller.getCorrectRate(26));
				add(allResultPanel, "allResultPanel");
				remove(choosePanel);
				pack();
				setLocationRelativeTo(null);
				cardLayout.show(getContentPane(), "allResultPanel");
			}
		});

		// add listeners to to letter buttons
		int i = 0;
		for (i = 0; i < 10; i++) {
			choosePanel.getLetters().get(i)
					.addMouseListener(new MouseAdapter() {
						// when mouse enter the letter, cursor becomes a hand
						public void mouseEntered(MouseEvent e) {
							choosePanel
									.setCursor(new Cursor(Cursor.HAND_CURSOR));
						}

						public void mouseExited(MouseEvent e) {
							choosePanel.setCursor(new Cursor(
									Cursor.DEFAULT_CURSOR));
						}

						// when mouse clicked, the button changes its bg color
						// and panel records the clicked letter
						public void mouseClicked(MouseEvent e) {
							int letterPos = Integer.parseInt(e.getSource()
									.toString());
							choosePanel.setWordListPosition(letterPos);
							controller.setLetterPosition(letterPos);
						}
					});
		}

	}

	private void initActsForWordPanel() {
		// add listeners to ReturnToCBt
		wordPanel.getReturnToCButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				controller.setLetterPosition(-1);
				wordPanel.setOption(WordPanel.STARTFROMFIRST);
				choosePanel.setWordListPosition(-1);
				add(choosePanel, "choosePanel");
				remove(wordPanel);
				cardLayout.show(getContentPane(), "choosePanel");
			}
		});

		// add listeners to okWBt
		wordPanel.getOkWButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				
//				System.out.println("firstWordIndex from MainFrame:");
				
				int result = controller.setFirstWordIndex(
						wordPanel.getOption(), choosePanel.getWordListPosition(),
						wordPanel.getTextField().getText());

				// System.out.println("用户模式！！！！" + wordPanel.getOption());

				if (result == 0) {
					int numRange = controller.getMaxNum(
							controller.getLetterPosition(),
							controller.getFirstWordIndex());
					numPanel.setInputNote(numRange);
					// switch panel
					add(numPanel, "numPanel");
					remove(wordPanel);
					cardLayout.show(getContentPane(), "numPanel");
				} else if (result == 1) {
					JOptionPane.showMessageDialog(null, "输入信息错误，默认从第一个单词开始");
					wordPanel.setOption(WordPanel.STARTFROMFIRST);
					int numRange = controller.getMaxNum(
							controller.getLetterPosition(),
							controller.getFirstWordIndex());
					numPanel.setInputNote(numRange);
					// switch panel
					add(numPanel, "numPanel");
					remove(wordPanel);
					cardLayout.show(getContentPane(), "numPanel");
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "该词库已经背完，将从第一个单词开始背");
					wordPanel.setOption(WordPanel.STARTFROMFIRST);
					int numRange = controller.getMaxNum(
							controller.getLetterPosition(),
							controller.getFirstWordIndex());
					numPanel.setInputNote(numRange);
					// switch panel
					add(numPanel, "numPanel");
					remove(wordPanel);
					cardLayout.show(getContentPane(), "numPanel");
				}
			}
		});

		// add listeners to input
		wordPanel.getTextField().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if (wordPanel.getTextField().getText().isEmpty())
					wordPanel.hideNotes(3);
				else {
					String[] notes = controller.stringMatching(wordPanel
							.getTextField().getText());
					if (notes[0].equals("")) {
						wordPanel.hideNotes(3);
					} else {
						// this is the note word from the controller
						wordPanel.showNotes(notes[0], notes[1], notes[2]);
					}
				}
			}
		});
		wordPanel.getTextField().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wordPanel.setOption(WordPanel.STARTFROMINPUT);
			}
		});

		// add listeners to radio buttons
		wordPanel.getJrbFirst().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wordPanel.setOption(WordPanel.STARTFROMFIRST);
				wordPanel.hideNotes(3);
				wordPanel.getTextField().setText("");
			}
		});
		wordPanel.getJrbLastTime().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wordPanel.setOption(WordPanel.STARTFROMLAST);
				wordPanel.hideNotes(3);
				wordPanel.getTextField().setText("");
			}
		});
		wordPanel.getJrbInput().addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				wordPanel.setOption(WordPanel.STARTFROMINPUT);
			}
		});

		// add listeners to note labels
		wordPanel.getNoteLabel1().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				wordPanel.setInputString(wordPanel.getNoteLabel1().getText().trim());
				wordPanel.hideNotes(3);
			}
		});

		// add listeners to note labels
		wordPanel.getNoteLabel2().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				wordPanel.setInputString(wordPanel.getNoteLabel2().getText().trim());
				wordPanel.hideNotes(3);
			}
		});

		// add listeners to note labels
		wordPanel.getNoteLabel3().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				wordPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				wordPanel.setInputString(wordPanel.getNoteLabel3().getText().trim());
				wordPanel.hideNotes(3);
			}
		});

	}

	private void initActsForNumPanel() {
		// add listeners to ReturnToWButton
		numPanel.getReturnToWButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				numPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				numPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// switch panel
				numPanel.setInputNote(0);
				wordPanel = new WordPanel();
				initActsForWordPanel();
				// if there the user hasn't studied this word list before
				int hasLastTime = controller.setFirstWordIndex(
						WordPanel.STARTFROMLAST,
						controller.getLetterPosition(), "");
				if (hasLastTime < 0) {
					wordPanel.setOption(-1);
				} else {
					wordPanel.setOption(WordPanel.STARTFROMFIRST);
				}
				add(wordPanel, "wordPanel");
				remove(numPanel);
			}
		});

		// add listeners to OkNButton
		numPanel.getOkNButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				numPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				numPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				int result = controller.setNum(numPanel.getTextField()
						.getText());
				// System.out.println("设置的单词数量！！！！"+controller.getNum());

				if (result == 0) {
					String chinese = controller.normalQuizStart(
							controller.getLetterPosition(),
							controller.getFirstWordIndex(), controller.getNum());
					System.out.println("");
					System.out.println("当前单词" + chinese);
					playPanel.setChinese(chinese);
					// switch panel
					numPanel.getTextField().setText("");
					add(playPanel);
					remove(numPanel);
				} else if (result == 1) {
					JOptionPane.showMessageDialog(null, "剩余单词不足，默认为该词库剩余单词数量");
					String chinese = controller.normalQuizStart(
							controller.getLetterPosition(),
							controller.getFirstWordIndex(), controller.getNum());
					playPanel.setChinese(chinese);
					// switch panel
					add(playPanel);
					remove(numPanel);
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "输入不合法");
					numPanel.getTextField().setText("");
				}
			}
		});

	}

	private void initActsForPlayPanel() {
		// add listeners to NextButton
		playPanel.getNextButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				playPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				playPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				final String nextChinese = controller.normalQuizRun(playPanel
						.getTextField().getText());
				int compareResult = controller.getCompareResult();

				if (compareResult == 1) {
					playPanel.setAnswerNote(
							controller.getPreviousWordEnglish(), compareResult);
					playPanel.getNextButton().removeMouseListener(
							playPanel.getNextButton().getMouseListeners()[0]);
					playPanel.getSaveAndExitButton().removeMouseListener(playPanel.getSaveAndExitButton().getMouseListeners()[0]);
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							if (nextChinese != null) {
								initActsForPlayPanel();
								playPanel.setChinese(nextChinese);
							}
							// switch the panel
							else {
								char wordBankChar = (char) ('A' + controller
										.getLetterPosition());
								String wordBankName = wordBankChar + "";
								quizResultPanel.setData(
										wordBankName,
										controller.getCurrrenQuizAllNum(),
										controller.getCurrentQuizCorrectNum(),
										controller.getCurrrenQuizAllNum()
												- controller
														.getCurrentQuizCorrectNum(),
										controller.getCurrentQuizCorrectRate());
								add(quizResultPanel);
								remove(playPanel);
								pack();
								setLocationRelativeTo(null);
								initActsForPlayPanel();
							}
						}
					}, 1500);
				} else {
					playPanel.setAnswerNote("", compareResult);
					Timer timer = new Timer();
					timer.schedule(new TimerTask() {
						@Override
						public void run() {
							if (nextChinese != null) {
								playPanel.setChinese(nextChinese);
							}
							// switch the panel
							else {
								char wordBankChar = (char) ('A' + controller
										.getLetterPosition());
								String wordBankName = wordBankChar + "";
								quizResultPanel.setData(
										wordBankName,
										controller.getCurrrenQuizAllNum(),
										controller.getCurrentQuizCorrectNum(),
										controller.getCurrrenQuizAllNum()
												- controller
														.getCurrentQuizCorrectNum(),
										controller.getCurrentQuizCorrectRate());
								add(quizResultPanel);
								remove(playPanel);
								pack();
								setLocationRelativeTo(null);
							}
						}
					}, 500);
				}

			}
		});

		// add listeners to NextButton
		playPanel.getSaveAndExitButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				playPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				playPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			public void mouseClicked(MouseEvent e) {
				int n = JOptionPane.showConfirmDialog(null, "确定退出吗？好想陪你一起背！",
						"系统信息", JOptionPane.OK_CANCEL_OPTION);
				if (n == JOptionPane.OK_OPTION) {
					controller.normalQuizStop();
					System.exit(0);
				}
			}
		});
	}

	private void initActsForCurrentWordListResultPanel() {
		currentWordListResultPanel.getReturnButton().addMouseListener(
				new MouseAdapter() {
					// when mouse enter the letter, cursor becomes a hand
					public void mouseEntered(MouseEvent e) {
						currentWordListResultPanel.setCursor(new Cursor(
								Cursor.HAND_CURSOR));
					}

					public void mouseExited(MouseEvent e) {
						currentWordListResultPanel.setCursor(new Cursor(
								Cursor.DEFAULT_CURSOR));
					}

					// when mouse clicked, change the panel
					public void mouseClicked(MouseEvent e) {
						// switch panel
						choosePanel.setWordListPosition(-1);
						add(choosePanel, "choosePanel");
						remove(currentWordListResultPanel);
						cardLayout.show(getContentPane(), "choosePanel");
					}
				});
	}

	private void initActsForAllResultPanel() {
		// add listener to getStartButton
		allResultPanel.getReturnButton().addMouseListener(new MouseAdapter() {
			// when mouse enter the letter, cursor becomes a hand
			public void mouseEntered(MouseEvent e) {
				allResultPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			public void mouseExited(MouseEvent e) {
				allResultPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			// when mouse clicked, change the panel
			public void mouseClicked(MouseEvent e) {
				// switch panel
				choosePanel.setWordListPosition(-1);
				add(choosePanel);
				remove(allResultPanel);
				pack();
				setLocationRelativeTo(null);
			}
		});
	}

}
