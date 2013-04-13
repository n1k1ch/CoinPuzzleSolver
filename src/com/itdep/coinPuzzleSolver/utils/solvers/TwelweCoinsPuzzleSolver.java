package com.itdep.coinPuzzleSolver.utils.solvers;

import java.util.List;

import com.itdep.coinPuzzleSolver.exceptions.ScalerException;
import com.itdep.coinPuzzleSolver.interfaces.IPuzzleAnswer;
import com.itdep.coinPuzzleSolver.interfaces.IPuzzleData;
import com.itdep.coinPuzzleSolver.utils.scalers.LimitedAttemptsScaler;
import com.itdep.coinPuzzleSolver.utils.scalers.Scaler;
import com.itdep.coinPuzzleSolver.utils.scalers.Scaler.SCALEPAN_SIDE;
import com.itdep.coinPuzzleSolver.utils.scalers.Scaler.SCALER_STATE;
import com.itdep.coinPuzzleSolver.vo.Coin;
import com.itdep.coinPuzzleSolver.vo.CoinsPuzzleDataArrayList;

public class TwelweCoinsPuzzleSolver extends Solver {

	@Override
	public IPuzzleAnswer solve(IPuzzleData puzzleData) {
		
		CoinsPuzzleDataArrayList coins = ((CoinsPuzzleDataArrayList)puzzleData);
		
		if(coins.getPuzzleData().size() != 12)
		{
			System.out.println("Cannot solve \"Twelwe Coins puzzle\" using not twelve coins");
		}
		
		Coin toRet = null;

		LimitedAttemptsScaler scaler = new LimitedAttemptsScaler(3);

		//1) divide in 3 groups of 4
		List<Coin> firstGroup = coins.getPuzzleData().subList(0, 4);
		List<Coin> secondGroup = coins.getPuzzleData().subList(4, 8);
		List<Coin> thirdGroup = coins.getPuzzleData().subList(8, 12);

		//2) scale 1st and 2nd groups
		scaler.clearScalepans();
		scaler.putContentOnScalepan(firstGroup, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(secondGroup, SCALEPAN_SIDE.RIGHT);

		try
		{
			SCALER_STATE scaleState = scaler.getScaleState();

			//if they are equal - all coins in firstGroup and secondGroup are normal
			if(scaleState == SCALER_STATE.EQUAL)
			{
				toRet = getCoinFromThirdGroupUsingTwoNormalCoins(firstGroup.get(0), firstGroup.get(1), thirdGroup, scaler);
			}
			//firstGroup is heavier
			else if(scaleState == SCALER_STATE.LEFT_HEAVIER)
			{
				toRet = getCoinIfFirstWeighingIsNotEqual(thirdGroup.get(0), thirdGroup.get(1), thirdGroup.get(2), firstGroup, secondGroup, scaler);
			}
			//secondGroup is heavier
			else if(scaleState == SCALER_STATE.RIGHT_HEAVIER)
			{
				toRet = getCoinIfFirstWeighingIsNotEqual(thirdGroup.get(0), thirdGroup.get(1), thirdGroup.get(2), secondGroup, firstGroup, scaler);
			}
		}
		catch(ScalerException e)
		{
			System.err.println(e.getMessage());
		}

		return toRet;
	}
	
	
	private static Coin getCoinFromThirdGroupUsingTwoNormalCoins(Coin firstNormalCoin, Coin secondNormalCoin, List<Coin> groupWithFakeCoin, Scaler scaler) throws ScalerException
	{
		Coin toRet;

		//divide in two groups with two coins
		List<Coin> firstSubgroup = groupWithFakeCoin.subList(0, 2);
		List<Coin> secondSubgroup = groupWithFakeCoin.subList(2, 4);

		scaler.clearScalepans();

		//compare two normal coins and two coins from the first group
		scaler.putContentOnScalepan(firstNormalCoin, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(secondNormalCoin, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(firstSubgroup, SCALEPAN_SIDE.RIGHT);

		SCALER_STATE scaleState = scaler.getScaleState();

		//if equal - fake coin is in secondSubGroup
		if(scaleState == SCALER_STATE.EQUAL)
		{
			Coin firstFromFakeGroup = secondSubgroup.get(0);
			Coin secondFromFakeGroup = secondSubgroup.get(1);

			scaler.clearScalepans();

			scaler.putContentOnScalepan(firstFromFakeGroup, SCALEPAN_SIDE.LEFT);
			scaler.putContentOnScalepan(firstNormalCoin, SCALEPAN_SIDE.RIGHT);

			scaleState = scaler.getScaleState();

			if(scaleState == SCALER_STATE.EQUAL)
			{
				toRet = secondFromFakeGroup;
			}
			else
			{
				toRet = firstFromFakeGroup;
			}
		}
		//fake coin is in firstSubgroup
		else
		{
			Coin firstFromFakeGroup = firstSubgroup.get(0);
			Coin secondFromFakeGroup = firstSubgroup.get(1);
			
			scaler.clearScalepans();
			
			scaler.putContentOnScalepan(firstNormalCoin, SCALEPAN_SIDE.LEFT);
			scaler.putContentOnScalepan(firstFromFakeGroup, SCALEPAN_SIDE.RIGHT);
			
			scaleState = scaler.getScaleState();
			
			if(scaleState == SCALER_STATE.EQUAL)
			{
				toRet = secondFromFakeGroup;
			}
			else
			{
				toRet = firstFromFakeGroup;
			}
		}
		
		return toRet;
	}
	
	private static Coin getCoinIfFirstWeighingIsNotEqual(Coin firstNormalCoin, Coin secondNormalCoin, Coin thirdNormalCoin, List<Coin> heavierGroup, List<Coin> lightWeightGroup, Scaler scaler) throws ScalerException
	{
		Coin toRet = new Coin(true);
		
		//get three from heavier
		Coin firstHeavier = heavierGroup.get(0);
		Coin secondHeavier = heavierGroup.get(1);
		Coin thirdHeavier = heavierGroup.get(2);
		Coin fourthHeavier = heavierGroup.get(3);
		
		//get three from light weight group
		Coin firstLight = lightWeightGroup.get(0);
		Coin secondLight = lightWeightGroup.get(1);
		Coin thirdLight = lightWeightGroup.get(2);
		Coin fourthLight = lightWeightGroup.get(3);
		
		//weigh...  
		scaler.clearScalepans();
		
		// first three heavy plus first light
		scaler.putContentOnScalepan(firstHeavier, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(secondHeavier, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(thirdHeavier, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(firstLight, SCALEPAN_SIDE.LEFT);
		
		//...VS
		
		//...fourth light plus three normal
		scaler.putContentOnScalepan(fourthHeavier, SCALEPAN_SIDE.RIGHT);
		scaler.putContentOnScalepan(firstNormalCoin, SCALEPAN_SIDE.RIGHT);
		scaler.putContentOnScalepan(secondNormalCoin, SCALEPAN_SIDE.RIGHT);
		scaler.putContentOnScalepan(thirdNormalCoin, SCALEPAN_SIDE.RIGHT);
		
		SCALER_STATE scaleState = scaler.getScaleState();
		
		//needed coin is one of light but not first
		if(scaleState == SCALER_STATE.EQUAL)
		{
			toRet = getCoinFromThreeLight(secondLight, thirdLight, fourthLight, scaler);
		}
		//needed coin is one of heavier but not last
		else if(scaleState == SCALER_STATE.LEFT_HEAVIER)
		{
			toRet = getCoinFromThreeHeavy(firstHeavier, secondHeavier, thirdHeavier, scaler);
		}
		else
		{
			toRet = getCoinFromHeavyAndLight(fourthHeavier, firstLight, firstNormalCoin, scaler);
		}
		
		return toRet;
	}
	
	private static Coin getCoinFromThreeLight(Coin firstLightCoin, Coin secondLightCoin, Coin thirdLightCoin, Scaler scaler) throws ScalerException
	{
		Coin toRet;
		
		scaler.clearScalepans();
		
		//compare first and second coins
		scaler.putContentOnScalepan(firstLightCoin, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(secondLightCoin, SCALEPAN_SIDE.RIGHT);
		
		SCALER_STATE scaleState = scaler.getScaleState();
		
		if(scaleState == SCALER_STATE.EQUAL)
		{
			toRet = thirdLightCoin;
		}
		else if(scaleState == SCALER_STATE.LEFT_HEAVIER)
		{
			toRet = secondLightCoin;
		}
		else
		{
			toRet = firstLightCoin;
		}
		
		return toRet;
	}
	
	private static Coin getCoinFromThreeHeavy(Coin firstHeavyCoin, Coin secondHeavyCoin, Coin thirdHeavyCoin, Scaler scaler) throws ScalerException
	{
		Coin toRet;
		
		scaler.clearScalepans();
		
		//compare first and second heavy coins
		scaler.putContentOnScalepan(firstHeavyCoin, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(secondHeavyCoin, SCALEPAN_SIDE.RIGHT);
		
		SCALER_STATE scaleState = scaler.getScaleState();
		
		if(scaleState == SCALER_STATE.EQUAL)
		{
			toRet = thirdHeavyCoin;
		}
		else if(scaleState == SCALER_STATE.LEFT_HEAVIER)
		{
			toRet = firstHeavyCoin;
		}
		else
		{
			toRet = secondHeavyCoin;
		}
		
		return toRet;
	}
	
	private static Coin getCoinFromHeavyAndLight(Coin heavyCoin, Coin lightCoin, Coin normalCoin, Scaler scaler) throws ScalerException
	{
		Coin toRet;
		
		scaler.clearScalepans();
		
		//compare heavy and normal coins
		scaler.putContentOnScalepan(heavyCoin, SCALEPAN_SIDE.LEFT);
		scaler.putContentOnScalepan(normalCoin, SCALEPAN_SIDE.RIGHT);
		
		SCALER_STATE scaleState = scaler.getScaleState();
		
		if(scaleState == SCALER_STATE.EQUAL)
		{
			toRet = lightCoin;
		}
		else
		{
			toRet = heavyCoin;
		}
		
		return toRet;
	}

}
