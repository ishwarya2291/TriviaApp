package com.example.triviaapp;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;

public class TriviaActivity extends Activity {
	
	public static final String PREFS = "allPrefs";
	private static String questionsData;
	int highScore;
	static int i=0;
	private static JSONObject jObjQuestionsData;
	private static JSONArray jArrayQuestions;
	private static JSONArray allQuestions = allQuestions();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_trivia);
		titleFragment();
		setHighScore();
	}
	
	public void setHighScore(){
		SharedPreferences allPrefs = getSharedPreferences(PREFS,0);
		Editor editor = allPrefs.edit();
		editor.putInt("highScore", highScore);
		editor.commit();
		// To set the action bar title to highscore
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("Trivia High Score:"+highScore);
		
	}
	
	public static JSONArray allQuestions(){
		try {
			questionsData = new Questions().execute("https://dl.dropboxusercontent.com/u/8606210/trivia.json").get();
			try {
				jObjQuestionsData = new JSONObject(questionsData);
				jArrayQuestions = jObjQuestionsData.getJSONArray("questions");
				jArrayQuestions = shuffleJsonArray(jArrayQuestions);

			} catch (JSONException e) {
				e.printStackTrace();
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return jArrayQuestions;
	}
	
	public void displayQuestion(){
		while(i<allQuestions.length()){
			try {
				if(allQuestions.getJSONObject(i).getString("questionType").equals("multipleChoice")){
					MultipleChoiceQuestionFragment mcqFragment = new MultipleChoiceQuestionFragment();
					mcqFragment.questionDetails(allQuestions.getJSONObject(i).getString("question"),
							allQuestions.getJSONObject(i).getString("imageUrl"),
							allQuestions.getJSONObject(i).getJSONArray("incorrectAnswers").getString(0),
							allQuestions.getJSONObject(i).getJSONArray("incorrectAnswers").getString(1),
							allQuestions.getJSONObject(i).getJSONArray("incorrectAnswers").getString(2),
							allQuestions.getJSONObject(i).getString("correctAnswer"),
							allQuestions.getJSONObject(i).getString("correctAnswer"));
					multipleChoiceQuestionsFragment();
					System.out.println(allQuestions.getJSONObject(i).getString("questionType"));
					System.out.println(allQuestions.getJSONObject(i).getString("question"));
					
				} else if(allQuestions.getJSONObject(i).getString("questionType").equals("trueFalse")){
					TrueFalseFragment tfFragment = new TrueFalseFragment();
					tfFragment.questionDetails(allQuestions.getJSONObject(i).getString("question"),
							allQuestions.getJSONObject(i).getString("imageUrl"),
							"True", "False",
							allQuestions.getJSONObject(i).getString("correctAnswer"));
					trueFalseFragment();
					System.out.println(allQuestions.getJSONObject(i).getString("questionType"));
					System.out.println(allQuestions.getJSONObject(i).getString("question"));
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
			break;
		}i++;
		
//		if(i>allQuestions.length()){
//			i=0;
//			allQuestions = allQuestions();
//			titleFragment();
//		}
	}
	
	public static JSONArray shuffleJsonArray (JSONArray array) throws JSONException {
	    // Implementing Fisher–Yates shuffle
	        Random rnd = new Random();
	        for (int i = array.length() - 1; i >= 0; i--)
	        {
		          int j = rnd.nextInt(i + 1);
		          // Simple swap
		          try {
					JSONObject object = array.getJSONObject(j);
					  array.put(j, array.getJSONObject(i));
					  array.put(i, object);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }
	        
	    return array;
	}
	
	public void titleFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		TitleFragment myFragment = new TitleFragment();
		ft.add(R.id.myFragment, myFragment);
		ft.commit();
	}
	
	public void multipleChoiceQuestionsFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		MultipleChoiceQuestionFragment mcqFragment = new MultipleChoiceQuestionFragment();
		ft.replace(R.id.myFragment, mcqFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void trueFalseFragment(){
		FragmentManager fm = getFragmentManager();
		FragmentTransaction ft = fm.beginTransaction();
		TrueFalseFragment tfFragment = new TrueFalseFragment();
		ft.replace(R.id.myFragment, tfFragment);
		ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.trivia, menu);
		return true;
	}

}
