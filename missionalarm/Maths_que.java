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

public class Maths_que extends Fragment {

    private TextView questionTv,questionnumbertv;
   private  Button  option1,option2,option3,option4;
private ArrayList<QuizModel> quizModelArrayList;
Random random;
int currentScore=0,questionattempted=1,currentPos;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_maths_que, container, false);
        questionTv = v.findViewById(R.id.idTvquestion);
        questionnumbertv=v.findViewById(R.id.idTvQuestionAttempted);
        option1 = v.findViewById(R.id.optn1);
        option2 = v.findViewById(R.id.optn2);
        option3 = v.findViewById(R.id.optn3);
        option4 = v.findViewById(R.id.optn4);
        quizModelArrayList=new ArrayList<>();
        random=new Random();
        getQuizQuestion(quizModelArrayList);

    currentPos=random.nextInt(quizModelArrayList.size());

    setDataToViews( currentPos);
        final String answerText = "all of above";


        option1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(quizModelArrayList.get(currentPos).getAnswer().equals(option1.getText().equals(answerText)))
            {
                currentScore++;
                option1.setBackgroundColor(Color.GREEN);

            }
            questionattempted++;
            currentPos=random.nextInt(quizModelArrayList.size());
            setDataToViews(currentPos);
        }

    });

option2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase()))
        {
            currentScore++;
        }
        questionattempted++;
        currentPos=random.nextInt(quizModelArrayList.size());
        setDataToViews(currentPos);

    }
});
option3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase()))
        {
            currentScore++;
        }
        questionattempted++;
        currentPos=random.nextInt(quizModelArrayList.size());
        setDataToViews(currentPos);

    }
});
option4.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(quizModelArrayList.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase()))
        {
            currentScore++;
        }
        questionattempted++;
        currentPos=random.nextInt(quizModelArrayList.size());
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
    View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.score_bottom_sheet,(LinearLayout)getView());
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
            questionTv.setText(quizModelArrayList.get(currentPos).getQuestion());
            option1.setText(quizModelArrayList.get(currentPos).getOption1());
            option2.setText(quizModelArrayList.get(currentPos).getOption2());
            option3.setText(quizModelArrayList.get(currentPos).getOption3());
            option4.setText(quizModelArrayList.get(currentPos).getOption4());

        }

    }
    private void getQuizQuestion(ArrayList<QuizModel>quizModelArrayList) {
        quizModelArrayList.add(new QuizModel("36","What is the average of first five multiples of 12?","36","38","40","42"));
        quizModelArrayList.add(new QuizModel("11","121 Divided by 11 is " ,"11","10","19","18"));
        quizModelArrayList.add(new QuizModel("11","What is the Next Prime Number after 7 ?","13","12","14","11"));
        quizModelArrayList.add(new QuizModel("14"," Solve 3 + 6 × ( 5 + 4) ÷ 3 - 7","11","16","14","15"));
        quizModelArrayList.add(new QuizModel("24","Solve 23 + 3 ÷ 3","24","25","26","27"));
        quizModelArrayList.add(new QuizModel("0.06"," What is 6% Equals to " ,"0.6","0.06","0.006","0.0006"));
        quizModelArrayList.add(new QuizModel("10 years","How Many Years are there in a Decade?","5 years","10 years","15 years","20 years"));
        quizModelArrayList.add(new QuizModel("10"," How Many Sides are there in a Decagon?","7","8","9","10"));
        quizModelArrayList.add(new QuizModel("4 months","How Many Months Have 30 Days?","2 months","4 months","11 months","12 months"));
        quizModelArrayList.add(new QuizModel("14.02"," Add the Decimals 5.23 + 8.79","14.02","14.19","14.11","14.29"));



    }
        }


