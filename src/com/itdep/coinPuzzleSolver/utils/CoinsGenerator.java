package com.itdep.coinPuzzleSolver.utils;

import java.util.Calendar;
import java.util.Locale;

import com.itdep.coinPuzzleSolver.vo.Coin;
import com.itdep.coinPuzzleSolver.vo.CoinsPuzzleDataArrayList;

public class CoinsGenerator {

	public static CoinsPuzzleDataArrayList generateTwelweCoinsWithOneFake()
	{
		CoinsPuzzleDataArrayList toRet = new CoinsPuzzleDataArrayList(12);
		
		long fakeCoinIndex = Calendar.getInstance().getTimeInMillis() / ((long)(Math.random() * 100 + 1)) % 12;
		
		System.out.println(String.format(Locale.getDefault(), "Fake coin index = %d", fakeCoinIndex));
		
		for(int i = 0; i < 12; i++)
		{
			if(i == fakeCoinIndex)
			{
				toRet.getPuzzleData().add(new Coin(true));
			}
			else
			{
				toRet.getPuzzleData().add(new Coin(false));
			}
		}
		
		return toRet;
	}
}
