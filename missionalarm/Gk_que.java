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

public class Gk_que  extends Fragment {

    private TextView questionTv,questionnumbertv;
    private Button option1,option2,option3,option4;
    private ArrayList<QuizModelGk> quizModelGks;
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
        quizModelGks=new ArrayList<>();
        random=new Random();
        getQuizQuestion(quizModelGks);

        currentPos=random.nextInt(quizModelGks.size());

        setDataToViews( currentPos);
        final String answerText = "all of above";


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelGks.get(currentPos).getAnswer().equals(option1.getText().equals(answerText)))
                {
                    currentScore++;
                    option1.setBackgroundColor(Color.GREEN);

                }
                questionattempted++;
                currentPos=random.nextInt(quizModelGks.size());
                setDataToViews(currentPos);
            }

        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelGks.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelGks.size());
                setDataToViews(currentPos);

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelGks.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelGks.size());
                setDataToViews(currentPos);

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelGks.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelGks.size());
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
        View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.score_gk,(LinearLayout)getView());
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
            questionTv.setText(quizModelGks.get(currentPos).getQuestion());
            option1.setText(quizModelGks.get(currentPos).getOption1());
            option2.setText(quizModelGks.get(currentPos).getOption2());
            option3.setText(quizModelGks.get(currentPos).getOption3());
            option4.setText(quizModelGks.get(currentPos).getOption4());

        }

    }
    private void getQuizQuestion(ArrayList<QuizModelGk> quizModelGks) {
        quizModelGks.add(new QuizModelGk("All of the above","Dumping is?","selling of goods abroad at a price well below the production cost at the home market price","the process by which the supply of a manufacture's product remains low in the domestic market, which batches him better price","prohibited by regulations of GATT","All of the above"));
        quizModelGks.add(new QuizModelGk("All of the above", "For which of the following disciplines is Nobel Prize awarded?" ,"Physics and Chemistry","Physiology or Medicine","Literature, Peace and Economics","All of the above"));
        quizModelGks.add(new QuizModelGk("Diphu, Assam","Garampani sanctuary is located at","Junagarh, Gujarat","Diphu, Assam","Kohima, Nagaland","Gangtok, Sikkim"));
        quizModelGks.add(new QuizModelGk("Insects","Entomology is the science that studies","Behavior of human beings","Insects","The origin and history of technical and scientific terms","The formation of rocks"));
        quizModelGks.add(new QuizModelGk("Film Finance Corporation","FFC stands for","Foreign Finance Corporation","Film Finance Corporation","Federation of Football Council","None of the above"));
        quizModelGks.add(new QuizModelGk("Dr. G. D. Bist"," Fastest shorthand writer was " ,"Dr. G. D. Bist","J.R.D. Tata","J.M. Tagore","Khudada Khan"));
        quizModelGks.add(new QuizModelGk("Fiji", "Golf player Vijay Singh belongs to which country?","USA","Fiji","India","UK"));
        quizModelGks.add(new QuizModelGk("China and Britain", "First China War was fought between","China and Britain","China and France","China and Egypt","China and Greek"));
        quizModelGks.add(new QuizModelGk("Volleyball","Federation Cup, World Cup, Allywyn International Trophy and Challenge Cup are awarded to winners of","Tennis","Volleyball","Basketball","Cricket"));
        quizModelGks.add(new QuizModelGk("Ultraviolet radiation","The ozone layer restricts","Visible light","Infrared radiation","X-rays and gamma rays","Ultraviolet radiation"));



    }




}