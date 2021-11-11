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

public class It_que  extends Fragment {

    private TextView questionTv,questionnumbertv;
    private Button option1,option2,option3,option4;
    private ArrayList<QuizModelIt> quizModelIts;
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
        quizModelIts=new ArrayList<>();
        random=new Random();
        getQuizQuestion(quizModelIts);

        currentPos=random.nextInt(quizModelIts.size());

        setDataToViews( currentPos);
        final String answerText = "all of above";


        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelIts.get(currentPos).getAnswer().equals(option1.getText().equals(answerText)))
                {
                    currentScore++;
                    option1.setBackgroundColor(Color.GREEN);

                }
                questionattempted++;
                currentPos=random.nextInt(quizModelIts.size());
                setDataToViews(currentPos);
            }

        });

        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelIts.get(currentPos).getAnswer().trim().toLowerCase().equals(option2.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelIts.size());
                setDataToViews(currentPos);

            }
        });
        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelIts.get(currentPos).getAnswer().trim().toLowerCase().equals(option3.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelIts.size());
                setDataToViews(currentPos);

            }
        });
        option4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quizModelIts.get(currentPos).getAnswer().trim().toLowerCase().equals(option4.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionattempted++;
                currentPos=random.nextInt(quizModelIts.size());
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
        View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.score_it,(LinearLayout)getView());
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
            questionTv.setText(quizModelIts.get(currentPos).getQuestion());
            option1.setText(quizModelIts.get(currentPos).getOption1());
            option2.setText(quizModelIts.get(currentPos).getOption2());
            option3.setText(quizModelIts.get(currentPos).getOption3());
            option4.setText(quizModelIts.get(currentPos).getOption4());

        }

    }
    private void getQuizQuestion(ArrayList<QuizModelIt> quizModelIts) {
        quizModelIts.add(new QuizModelIt("ROM BIOS", "From what location are the 1st computer instructions available on boot up?","ROM BIOS","CPU","boot.ini","CONFIG.SYS"));
        quizModelIts.add(new QuizModelIt("Incorrect CMOS settings","What could cause a fixed disk error " ,"No-CD installed","bad ram","slow processor","Incorrect CMOS settings"));
        quizModelIts.add(new QuizModelIt("over heat","Missing slot covers on a computer can cause?","over heat","power surges","EMI","incomplete path for ESD"));
        quizModelIts.add(new QuizModelIt("motherboard BIOS", "When installing PCI NICS you can check the IRQ availability by looking at","dip switches","CONFIG.SYS","jumper settings","motherboard BIOS"));
        quizModelIts.add(new QuizModelIt("ATX","Which Motherboard form factor uses one 20 pin connector","ATX","AT","BABY AT","All of the above"));
        quizModelIts.add(new QuizModelIt("sectors"," A hard disk is divided into tracks which are further subdivided into: " ,"clusters","sectors","vectors","heads"));
        quizModelIts.add(new QuizModelIt("Resistor","A wrist grounding strap contains which of the following:","Surge protector","Capacitor","Voltmeter","Resistor"));
        quizModelIts.add(new QuizModelIt("Expansion board","ESD would cause the most damage to which component?","Power supply","Expansion board","Monitor","Keyboard"));
        quizModelIts.add(new QuizModelIt("4 months","To view any currently running Terminate Stay Resident (TSR's) programs you could type:","Memory","MEM","SYS /M","Memmaker"));
        quizModelIts.add(new QuizModelIt("in parallel","Voltage is measured:","in parallel","in series","after breaking the circuit","after checking current"));



    }


}