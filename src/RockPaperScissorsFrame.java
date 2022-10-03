import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Random;

public class RockPaperScissorsFrame extends JFrame {
    JPanel mainPnl, titlePnl, gameCmdPnl, cmdPnl, resultsPnl, statsPnl;
    JLabel titleLbl, playerWinLbl, cpuWinLbl, tieLbl, gamesLbl;
    JTextField playerWins, cpuWins, tieGames, games;
    ImageIcon rockIcon, paperIcon, scissorsIcon;
    JScrollPane scroller;
    JTextArea resultsTA;
    JButton quitBtn, rockBtn, paperBtn, scissorsBtn;
    Random rnd = new Random();
    int rps, pws=0, cws=0, ties=0, rock=0, paper=0, scissors=0, strategy, turn=0;
    String player, lastUsed;

    public RockPaperScissorsFrame() throws HeadlessException
    {
        setTitle("Rock Paper Scissors");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int scrnHeight = screenSize.height;
        int scrnWidth = screenSize.width;
        setSize(scrnWidth*3/4, scrnHeight*3/4);
        setLocation(scrnWidth/8, scrnHeight/8);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout(5,5));
        add(mainPnl);

        createTitlePanel();
        createGameCmdPanel();
        createCommandPanel();
        createResultsPanel();
        createStatsPanel();

        setVisible(true);
    }
    private void createCommandPanel()
    {
        cmdPnl = new JPanel();
        cmdPnl.setLayout(new GridLayout(1,1));

        quitBtn = new JButton("Quit");
        quitBtn.setFont(new Font("Bold", Font.BOLD, 18));

        quitBtn.addActionListener((ActionEvent ae) -> System.exit(0));

        cmdPnl.add(quitBtn);

        mainPnl.add(cmdPnl, BorderLayout.SOUTH);
    }
    private void createGameCmdPanel()
    {
        gameCmdPnl = new JPanel();
        gameCmdPnl.setLayout(new GridLayout(1,3));
        gameCmdPnl.setBorder(new LineBorder(Color.BLACK,5,true));

        rockIcon = new ImageIcon(System.getProperty("user.dir") + "/src/rock.png");
        paperIcon = new ImageIcon(System.getProperty("user.dir") + "/src/paper.png");
        scissorsIcon = new ImageIcon(System.getProperty("user.dir") + "/src/scissors.png");

        rockBtn = new JButton("Rock", rockIcon);
        rockBtn.setFont(new Font("Bold", Font.BOLD, 18));
        rockBtn.setVerticalTextPosition(JButton.BOTTOM);
        rockBtn.setHorizontalTextPosition(JButton.CENTER);
        paperBtn = new JButton("Paper", paperIcon);
        paperBtn.setFont(new Font("Bold", Font.BOLD, 18));
        paperBtn.setVerticalTextPosition(JButton.BOTTOM);
        paperBtn.setHorizontalTextPosition(JButton.CENTER);
        scissorsBtn = new JButton("Scissors", scissorsIcon);
        scissorsBtn.setFont(new Font("Bold", Font.BOLD, 18));
        scissorsBtn.setVerticalTextPosition(JButton.BOTTOM);
        scissorsBtn.setHorizontalTextPosition(JButton.CENTER);

        rockBtn.addActionListener((ActionEvent ae) ->
        {
            turn++;
            player = "R";
            strategy = rnd.nextInt(10);
            if (strategy==0||strategy==1)
            {
                leastUsed();
            } else if (strategy==2||strategy==3) {
                mostUsed();
            } else if (turn!=1&&(strategy==4||strategy==5)) {
                lastUsed();
            } else if (strategy==6||strategy==7||strategy==8) {
                random();
            } else if (strategy==9) {
                cheat();
            } else if (turn==1&&(strategy==4||strategy==5)) {
                random();
            }
            rock++;
            lastUsed = "R";
            games.setText(String.valueOf(turn));
        });
        paperBtn.addActionListener((ActionEvent ae) ->
        {
            turn++;
            player = "P";
            strategy = rnd.nextInt(10);
            if (strategy==0||strategy==1)
            {
                leastUsed();
            } else if (strategy==2||strategy==3) {
                mostUsed();
            } else if (turn!=1&&(strategy==4||strategy==5)) {
                lastUsed();
            } else if (strategy==6||strategy==7||strategy==8) {
                random();
            } else if (strategy==9) {
                cheat();
            } else if (turn==1&&(strategy==4||strategy==5)) {
                random();
            }
            paper++;
            lastUsed = "P";
            games.setText(String.valueOf(turn));
        });
        scissorsBtn.addActionListener((ActionEvent ae) ->
        {
            turn++;
            player = "S";
            strategy = rnd.nextInt(10);
            if (strategy==0||strategy==1)
            {
                leastUsed();
            } else if (strategy==2||strategy==3) {
                mostUsed();
            } else if (turn!=1&&(strategy==4||strategy==5)) {
                lastUsed();
            } else if (strategy==6||strategy==7||strategy==8) {
                random();
            } else if (strategy==9) {
                cheat();
            } else if (turn==1&&(strategy==4||strategy==5)) {
                random();
            }
            scissors++;
            lastUsed = "S";
            games.setText(String.valueOf(turn));
        });

        gameCmdPnl.add(rockBtn);
        gameCmdPnl.add(paperBtn);
        gameCmdPnl.add(scissorsBtn);

        mainPnl.add(gameCmdPnl, BorderLayout.CENTER);
    }
    private void createTitlePanel()
    {
        titlePnl = new JPanel();

        titleLbl = new JLabel("Rock Paper Scissors", JLabel.CENTER);
        titleLbl.setFont(new Font("Bold Italic", Font.BOLD | Font.ITALIC, 36));

        titlePnl.add(titleLbl);

        mainPnl.add(titlePnl, BorderLayout.NORTH);
    }
    private void createResultsPanel()
    {
        resultsPnl = new JPanel();

        resultsTA = new JTextArea(41,30);
        resultsTA.setFont(new Font("Italic", Font.ITALIC, 12));
        resultsTA.setEditable(false);
        scroller = new JScrollPane(resultsTA);

        resultsPnl.add(scroller);

        mainPnl.add(resultsPnl, BorderLayout.EAST);
    }
    private void createStatsPanel()
    {
        statsPnl = new JPanel();
        statsPnl.setLayout(new BoxLayout(statsPnl, BoxLayout.Y_AXIS));

        playerWinLbl = new JLabel("Player Wins:");
        playerWins = new JTextField(20);
        playerWins.setEditable(false);
        playerWins.setHorizontalAlignment(JTextField.CENTER);
        playerWins.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        playerWins.setText(String.valueOf(pws));

        cpuWinLbl = new JLabel("Computer Wins:");
        cpuWins = new JTextField(20);
        cpuWins.setEditable(false);
        cpuWins.setHorizontalAlignment(JTextField.CENTER);
        cpuWins.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        cpuWins.setText(String.valueOf(cws));

        tieLbl = new JLabel("Ties:");
        tieGames = new JTextField(20);
        tieGames.setEditable(false);
        tieGames.setHorizontalAlignment(JTextField.CENTER);
        tieGames.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        tieGames.setText(String.valueOf(ties));

        gamesLbl = new JLabel("Total Games:");
        games = new JTextField(20);
        games.setEditable(false);
        games.setHorizontalAlignment(JTextField.CENTER);
        games.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        games.setText(String.valueOf(turn));

        statsPnl.add(playerWinLbl);
        statsPnl.add(playerWins);
        statsPnl.add(cpuWinLbl);
        statsPnl.add(cpuWins);
        statsPnl.add(tieLbl);
        statsPnl.add(tieGames);
        statsPnl.add(gamesLbl);
        statsPnl.add(games);

        mainPnl.add(statsPnl, BorderLayout.WEST);
    }
    private void leastUsed()
    {
        if (scissors<rock&&scissors<paper){
            if (player.equals("R")) {
                ties++;
                resultsTA.append("Both chose rock (Tie using Least Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Least Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Least Used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (rock<paper&&rock<scissors){
            if (player.equals("R")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Least Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                ties++;
                resultsTA.append("Both chose paper (Tie using Least Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Scissors cut paper (Player Wins using Least used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (paper<rock&&paper<scissors) {
            if (player.equals("R")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Least Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Scissors cut paper (Computer Wins using Least used)\n");
                playerWins.setText(String.valueOf(pws));
            } else if (player.equals("S")) {
                ties++;
                resultsTA.append("Both chose scissors (Tie using Least Used)\n");
                tieGames.setText(String.valueOf(ties));
            }
        } else random();
    }
    private void mostUsed()
    {
        if (scissors>rock&&scissors>paper){
            if (player.equals("R")) {
                ties++;
                resultsTA.append("Both chose rock (Tie using Most Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Most Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Most Used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (rock>paper&&rock>scissors){
            if (player.equals("R")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Most Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                ties++;
                resultsTA.append("Both chose paper (Tie using Most Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Scissors cut paper (Player Wins using Most used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (paper>rock&&paper>scissors) {
            if (player.equals("R")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Most Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Scissors cut paper (Computer Wins using Most used)\n");
                playerWins.setText(String.valueOf(pws));
            } else if (player.equals("S")) {
                ties++;
                resultsTA.append("Both chose scissors (Tie using Most Used)\n");
                tieGames.setText(String.valueOf(ties));
            }
        } else random();
    }
    private void lastUsed()
    {
        if (lastUsed.equals("R")){
            if (player.equals("R")) {
                ties++;
                resultsTA.append("Both chose rock (Tie using Last Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Last Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Last Used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (lastUsed.equals("P")){
            if (player.equals("R")) {
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins using Last Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                ties++;
                resultsTA.append("Both chose paper (Tie using Last Used)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (player.equals("S")) {
                pws++;
                resultsTA.append("Scissors cut paper (Player Wins using Last used)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (lastUsed.equals("S")) {
            if (player.equals("R")) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins using Last Used)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (player.equals("P")) {
                cws++;
                resultsTA.append("Scissors cut paper (Computer Wins using Last used)\n");
                playerWins.setText(String.valueOf(pws));
            } else if (player.equals("S")) {
                ties++;
                resultsTA.append("Both chose scissors (Tie using Last Used)\n");
                tieGames.setText(String.valueOf(ties));
            }
        }
    }
    private void random()
    {
        if (player.equals("R"))
        {
            rps = rnd.nextInt(3);
            if (rps == 0) {
                ties++;
                resultsTA.append("Both chose rock (Tie)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (rps == 1){
                cws++;
                resultsTA.append("Paper covers rock (Computer Wins)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (rps == 2) {
                pws++;
                resultsTA.append("Rock breaks scissors (Player Wins)\n");
                playerWins.setText(String.valueOf(pws));
            }
        } else if (player.equals("P")) {
            rps = rnd.nextInt(3);
            if (rps == 0) {
                pws++;
                resultsTA.append("Paper covers rock (Player Wins)\n");
                playerWins.setText(String.valueOf(pws));
            } else if (rps == 1){
                ties++;
                resultsTA.append("Both chose paper (Tie)\n");
                tieGames.setText(String.valueOf(ties));
            } else if (rps == 2) {
                cws++;
                resultsTA.append("Scissors cut paper (Computer Wins)\n");
                cpuWins.setText(String.valueOf(cws));
            }
        } else if (player.equals("S")) {
            rps = rnd.nextInt(3);
            if (rps == 0) {
                cws++;
                resultsTA.append("Rock breaks scissors (Computer Wins)\n");
                cpuWins.setText(String.valueOf(cws));
            } else if (rps == 1){
                pws++;
                resultsTA.append("Scissors cut paper (Player Wins)\n");
                playerWins.setText(String.valueOf(pws));
            } else if (rps == 2) {
                ties++;
                resultsTA.append("Both chose scissors (Tie)\n");
                tieGames.setText(String.valueOf(ties));
            }
        }
    }
    private void cheat()
    {
        if (player.equals("R")) {
            cws++;
            resultsTA.append("Paper covers rock (Computer Wins by Cheating)\n");
            cpuWins.setText(String.valueOf(cws));
        } else if (player.equals("P")) {
            cws++;
            resultsTA.append("Scissors cut paper (Computer Wins by Cheating)\n");
            cpuWins.setText(String.valueOf(cws));
        } else if (player.equals("S")) {
            cws++;
            resultsTA.append("Rock breaks scissors (Computer Wins by Cheating)\n");
            cpuWins.setText(String.valueOf(cws));
        }
    }
}