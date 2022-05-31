import javax.swing.*;
import java.text.MessageFormat;
import java.util.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.TimerTask;

public class Board extends JPanel implements ActionListener {
    Random random = new Random();
    private int countClick = 1;

    JMenuBar menuBar = new JMenuBar();
    JPanel buttonsPanel = new JPanel();
    JPanel labelAndReset = new JPanel();
    JPanel topButtonConfiguration = new JPanel();
    JPanel menuAndTopConfiguration = new JPanel();
    NameButton player1 = new NameButton("Player1");
    NameButton startReset = new NameButton("StartReset");
    NameButton player2 = new NameButton("Player2");
    NameButton[] buttons = new NameButton[9];
    JLabel label = new JLabel("LabelStatus");


    public Board() {
        setLayout(new BorderLayout());
        this.buttonsPanel.setLayout(new GridLayout(3, 3));

        buttons[0] = new NameButton("A3");
        buttons[1] = new NameButton("B3");
        buttons[2] = new NameButton("C3");
        buttons[3] = new NameButton("A2");
        buttons[4] = new NameButton("B2");
        buttons[5] = new NameButton("C2");
        buttons[6] = new NameButton("A1");
        buttons[7] = new NameButton("B1");
        buttons[8] = new NameButton("C1");
        for (NameButton button : buttons) {
            button.addActionListener(this);
            this.buttonsPanel.add(button);
        }
        add(buttonsPanel, BorderLayout.CENTER);

        this.labelAndReset.setLayout(new BorderLayout());
        label.setName("LabelStatus");
        labelAndReset.add(label, BorderLayout.WEST);
        add(labelAndReset, BorderLayout.PAGE_END);

        this.topButtonConfiguration.setLayout(new GridLayout(0, 3));
        topButtonConfiguration.add(player1);
        topButtonConfiguration.add(startReset);
        topButtonConfiguration.add(player2);

        player1.addActionListener(this);
        player1.setFocusPainted(false);

        startReset.addActionListener(this);
        startReset.setFocusPainted(false);

        player2.addActionListener(this);
        player2.setFocusPainted(false);

        player1.setFont(new Font("Courier", Font.BOLD, 12));
        player2.setFont(new Font("Courier", Font.BOLD, 12));

        startReset.setText("Start");
        startReset.setFont(new Font("Courier", Font.BOLD, 12));
        label.setText("Game is not started");

        Menu gameMenu = new Menu("Game");
        MenuItems humanHuman = new MenuItems("HumanHuman");
        MenuItems humanRobot = new MenuItems("HumanRobot");
        MenuItems robotHuman = new MenuItems("RobotHuman");
        MenuItems robotRobot = new MenuItems("RobotRobot");
        MenuItems exit = new MenuItems("Exit");
        humanHuman.addActionListener(event -> {
            player1.setText("Human");
            player2.setText("Human");
            startReset.setText("Reset");
            startClicked();
        });
        humanRobot.addActionListener(event -> {
            player1.setText("Human");
            player2.setText("Robot");
            startReset.setText("Reset");
            startClicked();
        });
        robotHuman.addActionListener(event -> {
            player1.setText("Robot");
            player2.setText("Human");
            startReset.setText("Reset");
            startClicked();
        });
        robotRobot.addActionListener(event -> {
            player1.setText("Robot");
            player2.setText("Robot");
            startReset.setText("Reset");
            startClicked();
        });
        exit.addActionListener(event -> System.exit(0));
        gameMenu.add(humanHuman);
        gameMenu.add(humanRobot);
        gameMenu.add(robotHuman);
        gameMenu.add(robotRobot);
        gameMenu.addSeparator();
        gameMenu.add(exit);

        menuBar.add(gameMenu);

        this.menuAndTopConfiguration.setLayout(new BorderLayout());
        this.menuAndTopConfiguration.add(topButtonConfiguration, BorderLayout.PAGE_END);
        this.menuAndTopConfiguration.add(menuBar, BorderLayout.PAGE_START);

        add(menuAndTopConfiguration, BorderLayout.PAGE_START);

        matchStart();

    }


    private void matchCheck() {
        humanOrRobotPlaying();

        if (isXEqual(0, 1, 2)) {
            xWins();
        } else if (isXEqual(3, 4, 5)) {
            xWins();
        } else if (isXEqual(6, 7, 8)) {
            xWins();
        } else if (isXEqual(0, 3, 6)) {
            xWins();
        } else if (isXEqual(1, 4, 7)) {
            xWins();
        } else if (isXEqual(2, 5, 8)) {
            xWins();
        } else if (isXEqual(0, 4, 8)) {
            xWins();
        } else if (isXEqual(2, 4, 6)) {
            xWins();
        } else if (isOEqual(0, 1, 2)) {
            oWins();
        } else if (isOEqual(3, 4, 5)) {
            oWins();
        } else if (isOEqual(6, 7, 8)) {
            oWins();
        } else if (isOEqual(0, 3, 6)) {
            oWins();
        } else if (isOEqual(1, 4, 7)) {
            oWins();
        } else if (isOEqual(2, 5, 8)) {
            oWins();
        } else if (isOEqual(0, 4, 8)) {
            oWins();
        } else if (isOEqual(2, 4, 6)) {
            oWins();
        } else if (countClick == 10) {
            draw();
        }

    }

    private void humanOrRobotPlaying() {
        String message;
        if (countClick % 2 != 0) {
            message = MessageFormat.format("The turn of {0} Player ({1})", player1.getText(), "X");
        } else {
            message = MessageFormat.format("The turn of {0} Player ({1})", player2.getText(), "O");
        }
        label.setText(message);

    }


    private boolean isXEqual(int firstNum, int secondNum, int thirdNum) {
        return buttons[firstNum].getText().equals("X") && buttons[secondNum].getText().equals("X") && buttons[thirdNum].getText().equals("X");
    }

    private boolean isOEqual(int firstNum, int secondNum, int thirdNum) {
        return buttons[firstNum].getText().equals("O") && buttons[secondNum].getText().equals("O") && buttons[thirdNum].getText().equals("O");
    }

    private void draw() {
        for (NameButton button : buttons) {
            button.setEnabled(false);
        }
        label.setText("Draw");
    }

    private void xWins() {
        for (NameButton button : buttons) {
            button.setEnabled(false);
        }
        String message = MessageFormat.format("The {0} Player ({1}) wins", player1.getText(), "X");
        label.setText(message);
    }

    private void oWins() {
        for (NameButton button : buttons) {
            button.setEnabled(false);
        }
        String message = MessageFormat.format("The {0} Player ({1}) wins", player2.getText(), "O");
        label.setText(message);
    }

    private void matchStart() {
        startReset.setText("Start");
        player1.setEnabled(true);
        player2.setEnabled(true);
        player1.setText("Human");
        player2.setText("Human");
        for (NameButton button : buttons) {
            button.setText(" ");
            button.setEnabled(false);
            this.label.setText("Game is not started");
        }

    }


    private void startClicked() {
        for (NameButton button : buttons) {
            button.setText(" ");
            button.setEnabled(true);
        }
        player1.setEnabled(false);
        player2.setEnabled(false);
        countClick = 1;
        humanOrRobotPlaying();

        computerPlaying();
    }

    private void setXOrO(String xOrO) {
        boolean isTrue = true;
        while (isTrue) {
            int randomButton = random.nextInt(9);
            if (buttons[randomButton].getText().equals(" ")) {
                buttons[randomButton].setText(xOrO);
                isTrue = false;
            } else if (countClick > 9) {
                isTrue = false;
            }
        }
        countClick++;
        matchCheck();
    }

    private void runTask() {
        if (countClick % 2 != 0) {
            setXOrO("X");
        } else {
            setXOrO("O");
        }
    }


    private void computerPlaying() {
        if (player1.getText().equals("Robot") && player2.getText().equals("Human")) {
            if (countClick % 2 != 0) {
                setXOrO("X");
            }
        } else if (player1.getText().equals("Human") && player2.getText().equals("Robot")) {
            if (countClick % 2 == 0) {
                setXOrO("O");
            }
        } else if (player1.getText().equals("Robot") && player2.getText().equals("Robot")) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    runTask();
                    if (countClick > 10 || label.getText().equals("The Human Player (X) wins")
                            || label.getText().equals("The Human Player (O) wins")) {
                        timer.cancel();
                    }
                }
            };
            timer.schedule(task, 1000, 1000);   

        }
    }

    private void changePlayerToRobot(JButton player) {
        if (player.getText().equals("Human")) {
            player.setText("Robot");
        } else {
            player.setText("Human");
        }
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (((JButton) actionEvent.getSource()).getText().equals("Start")) {
            startReset.setText("Reset");
            startClicked();
        } else if ((((JButton) actionEvent.getSource()).getText().equals("Reset"))) {
            matchStart();
        } else if (((JButton) actionEvent.getSource()).getName().equals("ButtonPlayer1")) {
            changePlayerToRobot(player1);
        } else if (((JButton) actionEvent.getSource()).getName().equals("ButtonPlayer2")) {
            changePlayerToRobot(player2);
        } else if (countClick % 2 != 0 && ((JButton) actionEvent.getSource()).getText().equals(" ")) {
            ((JButton) actionEvent.getSource()).setText("X");
            countClick++;
            matchCheck();
            computerPlaying();
        } else if (((JButton) actionEvent.getSource()).getText().equals(" ")) {
            ((JButton) actionEvent.getSource()).setText("O");
            countClick++;
            matchCheck();
            computerPlaying();
        }
    }
}