package com.example.triviaapp;

import android.app.Fragment;
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

import com.loopj.android.image.SmartImageView;

public class TrueFalseFragment extends Fragment{

	private Button nextButton;
	private RadioGroup tfOptions;
	private RadioButton trueButton, falseButton;
	private SmartImageView questionImage;
	private TextView questionTextView;

	private static String mQuestion, mQuesImageUrl, mTrueOption, mFalseOption, mCorrectAnswer;

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.true_false_fragment,
                container, false);
		
		nextButton = (Button) mLinearLayout.findViewById(R.id.next_tf_button);
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
		
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((TriviaActivity)getActivity()).displayQuestion();
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
}
