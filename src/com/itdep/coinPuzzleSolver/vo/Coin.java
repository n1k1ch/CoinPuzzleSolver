package com.itdep.coinPuzzleSolver.vo;

import java.util.Calendar;

import com.itdep.coinPuzzleSolver.interfaces.IPuzzleAnswer;
import com.itdep.coinPuzzleSolver.interfaces.IPuzzleData;
import com.itdep.coinPuzzleSolver.interfaces.IWeightable;

public class Coin implements IWeightable, IPuzzleAnswer, IPuzzleData {
	
	private final double mWeight;
	private final boolean mIsFake;
	
	
	
	public Coin(Boolean cIsFake) {
		
		mIsFake = cIsFake;
		
		if(mIsFake)
		{
			if(Calendar.getInstance().getTimeInMillis() / ((long)(Math.random() * 100 + 1)) % 2 == 0)
			{
				mWeight = WEIGHT_FAKE_HEAVY;
			}
			else
			{
				mWeight = WEIGHT_FAKE_LIGHT;
			}
		}
		else
		{
			mWeight = WEIGHT_NORMAL;
		}
	}
	

	
	public static final double WEIGHT_NORMAL = 2.0;
	public static final double WEIGHT_FAKE_LIGHT = 1.0;
	public static final double WEIGHT_FAKE_HEAVY = 3.0;

	
	
	@Override
	public double getWeight() {
		return mWeight;
	}
	
	@Override
	public String toString() {
		return (mIsFake ? "Fake" : "Normal") + " Coin with weigth " + mWeight;
	}
	
}
