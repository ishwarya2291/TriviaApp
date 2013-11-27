package com.example.triviaapp;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.AlertDialog.Builder;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.image.SmartImageView;

public class TrueFalseFragment extends Fragment{

	private static Button nextButton, finalizeButton;
	private static RadioGroup tfOptions;
	private static RadioButton trueButton, falseButton;
	private static SmartImageView questionImage;
	private static TextView questionTextView;
	private static String mQuestion, mQuesImageUrl, mTrueOption, mFalseOption, mCorrectAnswer;
	private int next = 0;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.true_false_fragment,
                container, false);
		
		nextButton = (Button) mLinearLayout.findViewById(R.id.next_tf_button);
		finalizeButton = (Button) mLinearLayout.findViewById(R.id.tf_finalize_button);
		tfOptions = (RadioGroup) mLinearLayout.findViewById(R.id.tf_Options);
		questionTextView = (TextView) mLinearLayout.findViewById(R.id.tf_question);
		questionImage = (SmartImageView) mLinearLayout.findViewById(R.id.tf_ques_image);
		trueButton = (RadioButton) mLinearLayout.findViewById(R.id.trueButton);
		falseButton = (RadioButton) mLinearLayout.findViewById(R.id.falseButton);
		
		questionTextView.setText(mQuestion);
		trueButton.setText(mTrueOption);
		falseButton.setText(mFalseOption);
		
		if(!mQuesImageUrl.equals("null")){
			questionImage.setImageUrl(mQuesImageUrl);
		}
		
		finalizeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				next = 1;
				if(trueButton.isChecked() || falseButton.isChecked()){
					finalizeButton.setEnabled(false);
					if(trueButton.isChecked() && mTrueOption.equals(mCorrectAnswer)){
						trueButton.setTypeface(null, Typeface.BOLD);
						trueButton.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else if(falseButton.isChecked() && mFalseOption.equals(mCorrectAnswer)){
						falseButton.setTypeface(null, Typeface.BOLD);
						falseButton.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else{
						Toast.makeText(getActivity(), "Ans: "+ mCorrectAnswer, Toast.LENGTH_SHORT).show();
						playWrongSound();
					}	
				}else{
					Builder alert = new AlertDialog.Builder(getActivity());
		        	alert.setMessage("Select an option");
		        	alert.setPositiveButton("OK",null);
		        	alert.show(); 		
				}
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(next == 1){
					next = 0;
					((TriviaActivity)getActivity()).displayQuestion();
				}else{
					Builder alert = new AlertDialog.Builder(getActivity());
		        	alert.setMessage("Please answer the question!");
		        	alert.setPositiveButton("OK",null);
		        	alert.show(); 				
				}
			}
		});
		return mLinearLayout;
	}
	
	public void questionDetails(String question, String imageUrl, String option1, String option2, String correctAns){
		mQuestion = question;
		mQuesImageUrl = imageUrl;
		mTrueOption = option1;
		mFalseOption = option2;
		mCorrectAnswer = correctAns;
	}
	
	public void playCorrectSound(){
		MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.correct);
		mediaPlayer.start();
	}
	
	public void playWrongSound(){
		MediaPlayer mediaPlayer = MediaPlayer.create(getActivity(), R.raw.wrong);
		mediaPlayer.start();
	}
}
