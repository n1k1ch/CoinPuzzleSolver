package com.itdep.coinPuzzleSolver.vo;

import java.util.ArrayList;
import java.util.List;

import com.itdep.coinPuzzleSolver.interfaces.IPuzzleData;

public class PuzzleDataArrayList implements IPuzzleData {
	
	protected List<? extends IPuzzleData> mPuzzleData;
	public List<? extends IPuzzleData> getPuzzleData()
	{
		return mPuzzleData;
	}
	
	public PuzzleDataArrayList(int initialCapacity) {
		setPuzzleData(new ArrayList<IPuzzleData>(initialCapacity));
	}

	public void setPuzzleData(List<? extends IPuzzleData> mPuzzleData) {
		this.mPuzzleData = mPuzzleData;
	}
}
