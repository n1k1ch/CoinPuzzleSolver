package com.itdep.coinPuzzleSolver;

import java.util.Locale;

import com.itdep.coinPuzzleSolver.utils.CoinsGenerator;
import com.itdep.coinPuzzleSolver.utils.solvers.TwelweCoinsPuzzleSolver;
import com.itdep.coinPuzzleSolver.vo.Coin;
import com.itdep.coinPuzzleSolver.vo.CoinsPuzzleDataArrayList;


public class Main {

	public static void main(String[] args) {
		
		System.out.println("Hi");
		
		for(int i = 0; i < 100; i++)
		{
			solveTwelveCoinsPuzzle();
		}
	}
	
	private static void solveTwelveCoinsPuzzle()
	{
		CoinsPuzzleDataArrayList twelweCoins = CoinsGenerator.generateTwelweCoinsWithOneFake();
		
		Coin fakeCoin = (Coin) new TwelweCoinsPuzzleSolver().solve(twelweCoins);
		
		System.out.println(String.format(Locale.getDefault(), "Found %s", fakeCoin.toString()));
	}
}
