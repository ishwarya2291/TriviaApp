package com.example.triviaapp;

import org.json.JSONArray;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class TitleFragment extends Fragment{
	
	private Button playButton;
	private JSONArray jArrayQuestions1;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		LinearLayout mLinearLayout = (LinearLayout) inflater.inflate(R.layout.title_fragment,
                container, false);
		playButton = (Button) mLinearLayout.findViewById(R.id.play_button);
		playButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				((TriviaActivity)getActivity()).displayQuestion();

			}
		});
		return mLinearLayout;
	}
}
