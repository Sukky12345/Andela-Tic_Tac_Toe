package sunkanmi.com.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class boardthreebythree extends AppCompatActivity implements View.OnClickListener {
    private final Button[][] button = new Button[3][3];
    private int player1Score = 0;
    private int player2Score = 0;
    private int Count;
    private final int[] ResourceID = new int[9];
    private int cou = 0;
    private boolean isPlayer1Turn = true;
    private String player1card;
    private String player2card;
    private TextView dispalyForPlayer1;
    private TextView dispalyForPlayer2;
    private String whoIsPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threebythree);
        player1card = getIntent().getExtras().getString("symbol");
        whoIsPlaying = getIntent().getExtras().getString("Player");
        switch (player1card) {
            case "X":
                player2card = "O";
                break;
            case "O":
                player2card = "X";
                break;
        }
        dispalyForPlayer1 = findViewById(R.id.score1);
        dispalyForPlayer2 = findViewById(R.id.score2);
        Button reset = findViewById(R.id.reset);


        for (int i = 0; i < 9; i++) {
            String buttonID = "button" + (i + 1);
            ResourceID[i] = getResources().getIdentifier(buttonID, "id", getPackageName());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j] = findViewById(ResourceID[cou]);
                button[i][j].setOnClickListener(this);
                cou++;
            }
        }
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!(((Button) v).getText().toString().equals(""))) {
        } else {
            if (whoIsPlaying == "1 Player") {
                if (isPlayer1Turn == true) {
                    ((Button) v).setText(player1card);
                    AI();
                }
                Count++;
                if (doYouWIn(button) == true) {
                    if (isPlayer1Turn == true) {
                        Player1Wins();
                        Count = 0;
                    } else {
                        Player2Wins();
                        Count = 0;
                    }
                } else if (Count == 9) {
                    Count = 0;
                    Draw();
                }
            } else {
                    if (isPlayer1Turn == true) {
                        ((Button) v).setText(player1card);
                    } else {
                        ((Button) v).setText(player2card);
                    }
                Count++;
                if (doYouWIn(button) == true) {
                    if (isPlayer1Turn == true) {
                        Player1Wins();
                        Count = 0;
                    } else {
                        Player2Wins();
                        Count = 0;
                    }
                } else if (Count == 9) {
                    Count = 0;
                    Draw();
                } else {
                    isPlayer1Turn = !isPlayer1Turn;
                }
            }
        }



    }

    private boolean doYouWIn(Button[][] button) {
        /// Checking Horizontal
        for (int i = 0; i < 3; i++) {
            if (button[0][i].getText().toString().equals(button[1][i].getText().toString()) && button[0][i].getText().toString().equals(button[2][i].getText().toString()) && !(button[0][i].getText().toString()).equals("") && !(button[1][i].getText().toString()).equals("") && !(button[2][i].getText().toString()).equals("")) {
                return true;
            }
        }
        /// Checking Vertical
        for (int i = 0; i < 3; i++) {
            if (button[i][0].getText().toString().equals(button[i][1].getText().toString()) && button[i][0].getText().toString().equals(button[i][2].getText().toString()) && !(button[i][0].getText().toString()).equals("") && !(button[i][1].getText().toString()).equals("") && !(button[i][2].getText().toString()).equals("")) {
                return true;
            }
        }
        if (button[0][0].getText().toString().equals(button[1][1].getText().toString()) && button[0][0].getText().toString().equals(button[2][2].getText().toString()) && !(button[1][1].getText().toString()).equals("") && !(button[0][0].getText().toString()).equals("") && !(button[2][2].getText().toString()).equals("")) {
            return true;
        }
        if (button[0][2].getText().toString().equals(button[1][1].getText().toString()) && button[0][2].getText().toString().equals(button[2][0].getText().toString()) && !(button[2][0].getText().toString()).equals("") && !(button[1][1].getText().toString()).equals("") && !(button[0][2].getText().toString()).equals("")) {
            return true;
        }
        return false;
    }

    private void Player1Wins() {
        player1Score++;
        Toast.makeText(this, "Player1Wins", Toast.LENGTH_SHORT).show();
        dispalyForPlayer1.setText("" + player1Score);
        clearBoardOnly();
    }

    private void Player2Wins() {
        player2Score++;
        Toast.makeText(this, "Player2Wins", Toast.LENGTH_SHORT).show();
        dispalyForPlayer2.setText("" + player2Score);
        clearBoardOnly();
    }

    private void Draw() {
        Toast.makeText(this, "Nobody Wins Lol!!!!, Keep trying", Toast.LENGTH_SHORT).show();
        clearBoardOnly();
    }

    private void resetBoard() {
        clearBoardOnly();
        cou = 0;
        Count = 0;
        player1Score = 0;
        player2Score = 0;
        dispalyForPlayer1.setText("0");
        dispalyForPlayer2.setText("0");
    }

    private void clearBoardOnly() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                button[i][j].setText("");
            }
        }
    }

    //AI always plays second
    /// THE AI PART RiGHT AKA COMPUTER PLAYING
    private int[][] available_spaces() {
        int[][] b = new int[button.length][button.length];
        for (int i = 0; i < button.length; i++) {
            for (int j = 0; i < button.length; i++) {
                if (button[i][j].getText().equals("")) {
                    b[i][j] = 0;
                } else {
                    b[i][j] = 1;
                }
            }
        }
        return b;
    }
    private void AI(){
        int space[][]= available_spaces();
        Random a = new Random();
        boolean stop= false;
        while (stop =false){
            int num = a.nextInt(9);
            Toast.makeText(this,"num",Toast.LENGTH_SHORT );
            int k= (int)Math.floor(num/2);
            if(space[k][3-k]==0){
                button[k][3-k].setText(player2card);
                stop=true;
            }
        }
    }

}


