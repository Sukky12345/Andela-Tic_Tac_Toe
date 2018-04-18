package sunkanmi.com.tic_tac_toe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    private RadioGroup rstyle;
    private RadioGroup rsymbol;
    private RadioButton rSymbolButton;
    private RadioButton rPlayer;
    private RadioGroup rplayer;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rstyle = findViewById(R.id.radioStyleGroup);
        rsymbol = findViewById(R.id.radioSymbol);
        rplayer = findViewById(R.id.whichPlayer);
        start= findViewById(R.id.StartGame);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int firstID = rstyle.getCheckedRadioButtonId();
                int secondID = rsymbol.getCheckedRadioButtonId();
                rSymbolButton = findViewById(secondID);
                int playeriD = rplayer.getCheckedRadioButtonId();
                rPlayer = findViewById(playeriD);
                if (firstID == R.id.threerdbtn) {
                    Intent nextPage = new Intent(MainActivity.this, boardthreebythree.class);
                    nextPage.putExtra("symbol", rSymbolButton.getText().toString());
                    nextPage.putExtra("Player", rPlayer.getText().toString());
                    startActivity(nextPage);
                }
                else{
                    Intent nextPage = new Intent(MainActivity.this, boardfivebyfive.class);
                    nextPage.putExtra("symbol", rSymbolButton.getText().toString());
                    startActivity(nextPage);
                    nextPage.putExtra("Player", rPlayer.getText().toString());
                }

            }});


    }

}

