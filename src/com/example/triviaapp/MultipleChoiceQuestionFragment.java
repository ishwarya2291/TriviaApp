package com.example.triviaapp;

import android.app.Fragment;
import android.graphics.Typeface;
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

public class MultipleChoiceQuestionFragment extends Fragment {

	private Button nextButton, finalizeButton;
	private RadioGroup mcqOptions;
	private RadioButton option1Button, option2Button, option3Button, option4Button;
	private TextView questionTextView;
	private SmartImageView questionImage;
	private static String mQuestion, mQuesImageUrl, mOption1, mOption2, mOption3, mOption4, mCorrectAnswer;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.multiple_choice_question_fragment,
                container, false);
		nextButton = (Button) mLinearLayout.findViewById(R.id.next_mcq_button);
		finalizeButton = (Button) mLinearLayout.findViewById(R.id.mcq_finalize_button);
		mcqOptions = (RadioGroup) mLinearLayout.findViewById(R.id.mcq_Options);
		questionTextView = (TextView) mLinearLayout.findViewById(R.id.mcq_question);
		questionImage = (SmartImageView) mLinearLayout.findViewById(R.id.mcq_ques_image);
		option1Button = (RadioButton) mLinearLayout.findViewById(R.id.mcq_option1);
		option2Button = (RadioButton) mLinearLayout.findViewById(R.id.mcq_option2);
		option3Button = (RadioButton) mLinearLayout.findViewById(R.id.mcq_option3);
		option4Button = (RadioButton) mLinearLayout.findViewById(R.id.mcq_option4);
		
		questionTextView.setText(mQuestion);
		option1Button.setText(mOption1);
		option2Button.setText(mOption2);
		option3Button.setText(mOption3);
		option4Button.setText(mOption4);		
		
		if(!mQuesImageUrl.equals("null")){
			questionImage.setImageUrl(mQuesImageUrl);
		}
		
		finalizeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(option1Button.isEnabled() && mOption1.equals(mCorrectAnswer)){
					Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();

					option1Button.setTypeface(null, Typeface.BOLD);
					option1Button.setTextColor(0xff00ff00);
				}else if(option2Button.isEnabled() && mOption2.equals(mCorrectAnswer)){
					Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();

					option2Button.setTypeface(null, Typeface.BOLD);
					option2Button.setTextColor(0xff00ff00);
				}else if(option3Button.isEnabled() && mOption3.equals(mCorrectAnswer)){
					Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();

					option3Button.setTypeface(null, Typeface.BOLD);
					option3Button.setTextColor(0xff00ff00);
				}else if(option4Button.isEnabled() && mOption4.equals(mCorrectAnswer)){
					Toast.makeText(getActivity(), "4", Toast.LENGTH_SHORT).show();
					option4Button.setTypeface(null, Typeface.BOLD);
					option4Button.setTextColor(0xff00ff00);
				}
				
			}
		});
		
		nextButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((TriviaActivity)getActivity()).displayQuestion();
			}
		});
	
		return mLinearLayout;
	}
	
	public void questionDetails(String question, String imageUrl, String option1, String option2, String option3, String option4, String correctAns){
		mQuestion = question;
		mQuesImageUrl = imageUrl;
		mOption1 = option1;
		mOption2 = option2;
		mOption3 = option3;
		mOption4 = option4;
		mCorrectAnswer = correctAns;
	}
}
