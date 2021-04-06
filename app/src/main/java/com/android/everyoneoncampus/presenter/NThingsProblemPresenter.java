package com.android.everyoneoncampus.presenter;

import com.android.everyoneoncampus.allinterface.DataListener;
import com.android.everyoneoncampus.model.api.MySQLModel;
import com.android.everyoneoncampus.model.entity.Things;
import com.android.everyoneoncampus.view.talk.NThingsFragment;
import com.android.everyoneoncampus.view.talk.ProblemFragment;

import java.util.List;

public class NThingsProblemPresenter {

    private MySQLModel mMySQLModel = new MySQLModel();
    private NThingsFragment mNThingsTalkFragment;
    private ProblemFragment mProblemFragment;


    public NThingsProblemPresenter(NThingsFragment nThingsFragment){
        mNThingsTalkFragment = nThingsFragment;
    }

    public NThingsProblemPresenter(ProblemFragment problemFragment){
        mProblemFragment = problemFragment;
    }

    public void getNewThings(String event){
        mMySQLModel.getNewProblemThingsApi(event, new DataListener<List<Things>>() {
            @Override
            public void onComplete(List<Things> result) {
                mNThingsTalkFragment.setNewThingsData(result);
            }
        });
    }
    public void getProblemThings(String event){
        mMySQLModel.getNewProblemThingsApi(event, new DataListener<List<Things>>() {
            @Override
            public void onComplete(List<Things> result) {
                mProblemFragment.setProblemData(result);
            }
        });
    }

}
