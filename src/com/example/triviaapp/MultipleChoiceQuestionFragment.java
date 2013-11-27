package com.example.triviaapp;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.res.Configuration;
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

public class MultipleChoiceQuestionFragment extends Fragment {

	public static final String PREFS = "allPrefs";
	private static Button nextButton, finalizeButton;
	private static RadioGroup mcqOptions;
	private static RadioButton option1Button, option2Button, option3Button, option4Button;
	private static TextView questionTextView;
	private static SmartImageView questionImage;
	private static String mQuestion, mQuesImageUrl, mOption1, mOption2, mOption3, mOption4, mCorrectAnswer;
	int next = 0;
	
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
				next = 1;
				if(option1Button.isChecked() || option2Button.isChecked() || option3Button.isChecked() || option4Button.isChecked()){
					finalizeButton.setEnabled(false);
					if(option1Button.isChecked() && mOption1.equals(mCorrectAnswer)){
						option1Button.setTypeface(null, Typeface.BOLD);
						option1Button.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else if(option2Button.isChecked() && mOption2.equals(mCorrectAnswer)){
						option2Button.setTypeface(null, Typeface.BOLD);
						option2Button.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else if(option3Button.isChecked() && mOption3.equals(mCorrectAnswer)){
						option3Button.setTypeface(null, Typeface.BOLD);
						option3Button.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else if(option4Button.isChecked() && mOption4.equals(mCorrectAnswer)){
						option4Button.setTypeface(null, Typeface.BOLD);
						option4Button.setTextColor(0xff00ff00);
						playCorrectSound();
						TriviaActivity.score++;
					}else{
						Toast.makeText(getActivity(), "Ans: "+ mCorrectAnswer, Toast.LENGTH_SHORT).show();
						playWrongSound();
					}
				}else{
					Builder alert = new AlertDialog.Builder(getActivity());
		        	alert.setMessage("Select an answer");
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
	
	public void questionDetails(String question, String imageUrl, String option1, String option2, String option3, String option4, String correctAns){
		mQuestion = question;
		mQuesImageUrl = imageUrl;
		mOption1 = option1;
		mOption2 = option2;
		mOption3 = option3;
		mOption4 = option4;
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
	
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        populateViewForOrientation(inflater, (ViewGroup) getView());
    }
	
	private void populateViewForOrientation(LayoutInflater inflater, ViewGroup viewGroup) {
        viewGroup.removeAllViewsInLayout();
        View subview = inflater.inflate(R.layout.multiple_choice_question_fragment, viewGroup);
 
        // Find your buttons in subview, set up onclicks, set up callbacks to your parent fragment or activity here.
        
        // You can create ViewHolder or separate method for that.
        // example of accessing views: TextView textViewExample = (TextView) view.findViewById(R.id.text_view_example);
        // textViewExample.setText("example");
    }
	
}
