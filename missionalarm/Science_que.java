package com.example.missionalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Random;

public class Science_que extends Fragment {

    private TextView questionTv,questionnumbertv;
    private Button option1,option2,option3,option4;
    private ArrayList<QuizModelScience> quizModelSciences;
    Random random;
    int currentScore=0,questionattempted=1,currentPos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_science_que, container, false);
        questionTv = v.findViewById(R.id.idTvquestion);
        questionnumbertv=v.findViewById(R.id.idTvQuestionAttempted);
        option1 = v.findViewById(R.id.optn1);
        option2 = v.findViewById(R.id.optn2);
        option3 = v.findViewById(R.id.optn3);
        option4 = v.findViewById(R.id.optn4);
        quizModelSciences=new ArrayList<>();
        random=new Random();
        getQuizQuestion(quizModelSciences);

        currentPos=random.nextInt(quizModelSciences.size());

        setDataToViews( currentPos);
        final String answerText = "all of above";


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelSciences.get(currentPos).getAnswer().equals(option1.getText().equals(answerText)))
                {
                    currentScore++;
                    option1.setBackgroundColor(Color.GREEN);

                }
                questionattempted++;
                currentPos=random.nextInt(quizModelSciences.size());
                setDataToViews(currentPos);
            }

        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelSciences.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelSciences.size());
                setDataToViews(currentPos);

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelSciences.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelSciences.size());
                setDataToViews(currentPos);

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelSciences.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelSciences.size());
                setDataToViews(currentPos);

            }
        });



        return v;

    }


    private void showBottomSheet(){

        SharedPreferences sp = getActivity().getPreferences(getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("score",""+currentScore);
        editor.apply();

        final BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(getContext());
        View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.score_science,(LinearLayout)getView());
        TextView scoreTV=bottomSheetView.findViewById(R.id.idtvScore);
        Button AlarmReturn= bottomSheetView.findViewById(R.id.returnalarm);
        scoreTV.setText("your Score is \n "+currentScore+"/10");

        AlarmReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetAlarm setAlarm=new SetAlarm();
                FragmentTransaction transaction=getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container,setAlarm);
                transaction.commit();

                bottomSheetDialog.dismiss();
            }
        });
        bottomSheetDialog.show();
    }
    private void setDataToViews(int currentPos)
    {
        if(questionattempted == 10){
            showBottomSheet();

        }else {
            questionnumbertv.setText("Questions attempted:"+questionattempted +"/10");
            questionTv.setText(quizModelSciences.get(currentPos).getQuestion());
            option1.setText(quizModelSciences.get(currentPos).getOption1());
            option2.setText(quizModelSciences.get(currentPos).getOption2());
            option3.setText(quizModelSciences.get(currentPos).getOption3());
            option4.setText(quizModelSciences.get(currentPos).getOption4());

        }

    }
    private void getQuizQuestion(ArrayList<QuizModelScience> quizModelSciences) {
        quizModelSciences.add(new QuizModelScience("Hydrogen sulphide","Brass gets discoloured in air because of the presence of which of the following gases in air?","Oxygen","Hydrogen sulphide","Carbon dioxide","Nitrogen"));
        quizModelSciences.add(new QuizModelScience("Bromine", "Which of the following is a non metal that remains liquid at room temperature?" ,"Phosphorous","Bromine","Chlorine","Helium"));
        quizModelSciences.add(new QuizModelScience("Graphite","Which of the following is used in pencils?","Graphite","Silicon","Charcoal","Phosphorous0o"));
        quizModelSciences.add(new QuizModelScience("Mercury"," Which of the following metals forms an amalgam with other metals?","Tin","Mercury","Lead","Zinc"));
        quizModelSciences.add(new QuizModelScience("nitrogen","The gas usually filled in the electric bulb is","nitrogen","hydrogen","carbon dioxide","oxygen"));
        quizModelSciences.add(new QuizModelScience("Sodium carbonate"," Washing soda is the common name for " ,"Sodium carbonate","Calcium bicarbonate","Sodium bicarbonate","Calcium carbonate"));
        quizModelSciences.add(new QuizModelScience("Hydrogen","Which of the gas is not known as green house gas?","Methane","Nitrous oxide"," Carbon dioxide","Hydrogen"));
        quizModelSciences.add(new QuizModelScience("Diamond"," The hardest substance available on earth is","Gold","Iron","Diamond","Platinum"));
        quizModelSciences.add(new QuizModelScience("petrol additive","Tetraethyl lead is used as","pain killer","fire extinguisher","mosquito repellent","petrol additive"));
        quizModelSciences.add(new QuizModelScience("Graphite","Which of the following is used as a lubricant?"," Graphite","Silica","Iron Oxide"," Diamond"));



    }


}