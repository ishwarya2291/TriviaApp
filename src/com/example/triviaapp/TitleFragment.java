package com.example.triviaapp;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleFragment extends Fragment{
	
	public static final String PREFS = "allPrefs";
	private Button playButton;
	private TextView highScoreTextView;
	static int highScore;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.title_fragment,
                container, false);
		playButton = (Button) mLinearLayout.findViewById(R.id.play_button);
		highScoreTextView = (TextView) mLinearLayout.findViewById(R.id.highScore);
		
		SharedPreferences allPrefs = getActivity().getSharedPreferences(PREFS, 0);
		highScore = allPrefs.getInt("highScore", 0);
		
		highScoreTextView.setText(Integer.toString(highScore));
		
		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				((TriviaActivity)getActivity()).displayQuestion();
			}
		});
		return mLinearLayout;
	}
}
