package sunkanmi.com.tic_tac_toe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class boardfivebyfive extends AppCompatActivity implements View.OnClickListener {
    private final Button[][] button = new Button[5][5];
    private int player1Score = 0;
    private int player2Score = 0;
    private int Count;
    private final int[] ResourceID = new int[25];
    private int cou = 0;
    private boolean isPlayer1Turn = true;
    private String player1card;
    private String player2card;
    private TextView dispalyForPlayer1;
    private TextView dispalyForPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fivebyfive);
        player1card = getIntent().getExtras().getString("symbol");
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

        for (int i = 0; i < 25; i++) {
            String buttonID = "button" + (i + 1);
            ResourceID[i] = getResources().getIdentifier(buttonID, "id", getPackageName());
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
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
            return;
        }
        if (isPlayer1Turn) {
            ((Button) v).setText(player1card);
        } else {
            ((Button) v).setText(player2card);
        }
        Count++;
        if (doYouWIn()) {
            if (isPlayer1Turn) {Player1Wins();}
            else  {Player2Wins();}
        } else if (Count == 25) {
            Count = 0;
            Draw();
        } else {
            isPlayer1Turn = !isPlayer1Turn;
        }
    }

    private boolean doYouWIn() {
        /// Checking Vertical
        for (int i = 0; i < 5; i++) {
            if (button[0][i].getText().toString().equals(button[1][i].getText().toString()) && button[0][i].getText().toString().equals(button[2][i].getText().toString()) && button[0][i].getText().toString().equals(button[3][i].getText().toString()) && !(button[0][i].getText().toString()).equals("") && !(button[1][i].getText().toString()).equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
            if (button[1][i].getText().toString().equals(button[2][i].getText().toString()) && button[1][i].getText().toString().equals(button[3][i].getText().toString()) && button[1][i].getText().toString().equals(button[4][i].getText().toString()) && !(button[1][i].getText().toString()).equals("") && !(button[2][i].getText().toString()).equals("") && !(button[3][i].getText().toString()).equals("") && !(button[4][i].getText().toString()).equals("")) {
                return true;
            }
        }
        /// Checking Horizontal
        for (int i = 0; i < 5; i++) {
            if (button[i][0].getText().toString().equals(button[i][1].getText().toString()) && button[i][0].getText().toString().equals(button[i][3].getText().toString()) && button[i][0].getText().toString().equals(button[i][2].getText().toString()) && !(button[i][3].getText().toString()).equals("") && !(button[i][0].getText().toString()).equals("") && !(button[i][1].getText().toString()).equals("") && !(button[i][2].getText().toString()).equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 5; i++) {
           if(button[i][1].getText().toString().equals(button[i][2].getText().toString()) && button[i][1].getText().toString().equals(button[i][3].getText().toString()) && button[i][1].getText().toString().equals(button[i][4].getText().toString()) &&  !(button[i][1].getText().toString()).equals("") && !(button[i][2].getText().toString()).equals("") && !(button[i][3].getText().toString()).equals("") && !(button[i][4].getText().toString()).equals("")){
               return true;
           }
        }

        if (button[0][0].getText().toString().equals(button[1][1].getText().toString()) && button[0][0].getText().toString().equals(button[2][2].getText().toString()) && button[0][0].getText().toString().equals(button[3][3].getText().toString()) && !(button[1][1].getText().toString()).equals("") && !(button[3][3].getText().toString()).equals("") && !(button[0][0].getText().toString()).equals("") && !(button[2][2].getText().toString()).equals("")) {
            return true;
        }
        if (button[4][4].getText().toString().equals(button[1][1].getText().toString()) && button[1][1].getText().toString().equals(button[2][2].getText().toString()) && button[1][1].getText().toString().equals(button[3][3].getText().toString()) && !(button[1][1].getText().toString()).equals("") && !(button[3][3].getText().toString()).equals("") && !(button[4][4].getText().toString()).equals("") && !(button[2][2].getText().toString()).equals("")) {
            return true;
        }
        if (button[0][4].getText().toString().equals(button[1][3].getText().toString()) && button[0][4].getText().toString().equals(button[2][2].getText().toString()) && button[0][4].getText().toString().equals(button[3][1].getText().toString()) && !(button[0][4].getText().toString()).equals("") && !(button[1][3].getText().toString()).equals("") && !(button[2][2].getText().toString()).equals("") && !(button[3][1].getText().toString()).equals("")) {
            return true;
        }
        if (button[4][0].getText().toString().equals(button[1][3].getText().toString()) && button[4][0].getText().toString().equals(button[2][2].getText().toString()) && button[4][0].getText().toString().equals(button[3][1].getText().toString()) && !(button[4][0].getText().toString()).equals("") && !(button[1][3].getText().toString()).equals("") && !(button[2][2].getText().toString()).equals("") && !(button[3][1].getText().toString()).equals("")) {
            return true;
        }
        if (button[0][3].getText().toString().equals(button[1][2].getText().toString()) && button[0][3].getText().toString().equals(button[2][1].getText().toString()) && button[0][3].getText().toString().equals(button[3][0].getText().toString()) && !(button[0][3].getText().toString()).equals("") && !(button[1][2].getText().toString()).equals("") && !(button[2][1].getText().toString()).equals("") && !(button[3][0].getText().toString()).equals("")) {
            return true;
        }
        if (button[1][4].getText().toString().equals(button[2][3].getText().toString()) && button[1][4].getText().toString().equals(button[3][2].getText().toString()) && button[1][4].getText().toString().equals(button[4][1].getText().toString()) && !(button[1][4].getText().toString()).equals("") && !(button[2][3].getText().toString()).equals("") && !(button[3][2].getText().toString()).equals("") && !(button[4][1].getText().toString()).equals("")) {
            return true;
        }
        if (button[0][1].getText().toString().equals(button[1][2].getText().toString()) && button[0][1].getText().toString().equals(button[2][3].getText().toString()) && button[0][1].getText().toString().equals(button[3][4].getText().toString()) && !(button[0][1].getText().toString()).equals("") && !(button[3][4].getText().toString()).equals("") && !(button[1][2].getText().toString()).equals("") && !(button[2][3].getText().toString()).equals("")) {
            return true;
        }
        if (button[1][0].getText().toString().equals(button[2][1].getText().toString()) && button[1][0].getText().toString().equals(button[3][2].getText().toString()) && button[1][0].getText().toString().equals(button[4][3].getText().toString()) && !(button[1][0].getText().toString()).equals("") && !(button[4][3].getText().toString()).equals("") && !(button[2][1].getText().toString()).equals("") && !(button[3][2].getText().toString()).equals("")) {
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                button[i][j].setText("");
            }
        }
    }
// THE AI PART AKA COMPUTER PLAYING

}
