package com.itdep.coinPuzzleSolver.vo;

import java.util.ArrayList;
import java.util.List;

public class CoinsPuzzleDataArrayList extends PuzzleDataArrayList {

	public CoinsPuzzleDataArrayList(int initialCapacity) {
		super(initialCapacity);
		
		setPuzzleData(new ArrayList<Coin>(initialCapacity));
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Coin> getPuzzleData() {
		
		return (List<Coin>)mPuzzleData;
	}
}
