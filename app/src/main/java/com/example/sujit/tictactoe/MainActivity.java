package com.example.sujit.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

   int activePlayer = 0;
   //0  for small o & 1 for cross
    int gameState[] ={2,2,2,2,2,2,2,2,2};

    int winningPositions[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{2,4,6},{8,4,0}};

    boolean isGameActive = true;

    public void dropIn(View view) {

        String whoWon;

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if(isGameActive) {
            if (gameState[tappedCounter - 1] == 2) {
                counter.setTranslationY(-1000f);


                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.smallo);
                    gameState[tappedCounter - 1] = activePlayer;

                    activePlayer = 1;
                } else {
                    counter.setImageResource(R.drawable.cross);
                    gameState[tappedCounter - 1] = activePlayer;
                    activePlayer = 0;
                }
                counter.animate().translationYBy(1000f).rotation(360).setDuration(500);
            }
            for (int[] winningPosition : winningPositions) {
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) &&( gameState[winningPosition[0]] != 2 ))
                {
                    isGameActive = false;
                    int win = gameState[winningPosition[0]];
                    if (win == 0)
                        whoWon = "O";
                    else
                        whoWon = "Cross";

                    TextView text = (TextView) findViewById(R.id.winningTextField);
                    text.setText(whoWon + " Wins !!!");

                    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                    layout.setVisibility(View.VISIBLE);

                }
                else{
                    int c= 0;
                    for(int i=0;i<gameState.length;i++)
                    {
                        if(gameState[i]!=2)
                                  c++;
                    }
                    if(c==9) {
                        TextView text = (TextView) findViewById(R.id.winningTextField);
                        text.setText(" Draw !!!");

                        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
                        layout.setVisibility(View.VISIBLE);
                    }

                }
            }
        }

    }
    public void playAgainClicked(View view){
        LinearLayout layout =(LinearLayout) findViewById(R.id.linearLayout);
        layout.setVisibility(View.INVISIBLE);
      activePlayer = 0;
   isGameActive =true;
      for(int i= 0; i<gameState.length;i++)
      {
          gameState[i] = 2;
      }

        GridLayout gridLayout =(GridLayout) findViewById(R.id.gridLayout);
      for(int i =0; i<gridLayout.getChildCount();i++)
      {
          ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);

      }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
