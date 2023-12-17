package com.example.xo_ilyas;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button[][] click ;
    int[][] whoclick ;
    private static int comptClicks=0 ;
    String XorO ="X";
    TextView TVwinner ;
    Button Breplay  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whoclick= new int[3][3] ;
        click= new Button[3][3];
        TVwinner=findViewById(R.id.TVwinner);
        click[0][0] = findViewById(R.id.XO11);
        click[0][1] = findViewById(R.id.XO12);
        click[0][2]= findViewById(R.id.XO13);
        click[1][0] = findViewById(R.id.XO21);
        click[1][1] = findViewById(R.id.XO22);
        click[1][2]= findViewById(R.id.XO23);
        click[2][0] = findViewById(R.id.XO31);
        click[2][1] = findViewById(R.id.XO32);
        click[2][2] = findViewById(R.id.XO33);
        Breplay = findViewById(R.id.Breplay);
        Initialiser() ;
        //--------------------------
      for(int i =0 ; i<3 ; i++){
          for (int j=0 ; j<3 ;j++){
              final int ligne = i;
              final int colonne = j;
              click[i][j].setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      int col ;
                      String win  ;
                      Button clickedButton = (Button) view ;
                      comptClicks++ ;
                      if(comptClicks%2==1)  {
                          XorO="X";
                          whoclick[ligne][colonne] =1  ;
                          win=getResources().getString(R.string.winnerMessage)+" X";
                          clickedButton.setTextColor(Color.RED);
                          col=Color.RED;
                          }
                      else  {
                          XorO="O";
                          whoclick[ligne][colonne] =0  ;
                          win=getResources().getString(R.string.winnerMessage)+" O";;
                          clickedButton.setTextColor(Color.BLUE);
                          col=Color.BLUE;
                                 }  ;
                      clickedButton.setText(XorO);
                      clickedButton.setEnabled(false);
                      //System.out.println(whoclick[ligne][colonne]);
                      System.out.println(correction());
                      if(correction()){
                          TVwinner.setTextColor(col);
                          TVwinner.setText(win);
                          for (int i = 0 ; i<3 ;i++){
                              for(int j =0 ; j<3 ; j++) {
                                  click[i][j].setEnabled(false);
                              }
                          }
                          TVwinner.setVisibility(View.VISIBLE);
                      }
                      //----------
                  }
              });
          }
      }

      Breplay.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Initialiser();
          }
      });

    }
    public boolean correction(){

        for(int i = 0;i<2 ; i++){
            if(whoclick[0][i]==whoclick[1][i] && whoclick[0][i]==whoclick[2][i] ) return true ;
            if(whoclick[i][0]==whoclick[i][1] && whoclick[i][0]==whoclick[i][2]) return true ;
        }
        if(whoclick[0][0]==whoclick[1][1] && whoclick[0][0]==whoclick[2][2]) return true ;
        if(whoclick[0][2]==whoclick[1][1] && whoclick[0][2]==whoclick[2][0]) return true ;

        return false ;
    }

    public void Initialiser(){
        comptClicks=0 ;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                whoclick[i][j] = -(i+1+3*j);
                click[i][j].setEnabled(true);
                click[i][j].setText("");
            }
        }

    }
}