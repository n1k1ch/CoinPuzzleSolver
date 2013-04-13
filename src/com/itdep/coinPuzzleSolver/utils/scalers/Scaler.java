package com.itdep.coinPuzzleSolver.utils.scalers;

import java.util.ArrayList;
import java.util.List;

import com.itdep.coinPuzzleSolver.interfaces.IWeightable;

/**
 * 
 * Class, who can scale IScaleables
 * 
 * @author n1k1ch
 *
 */
public class Scaler {

	public enum SCALEPAN_SIDE
	{
		LEFT,
		RIGHT
	}

	public enum SCALER_STATE
	{
		LEFT_HEAVIER,
		RIGHT_HEAVIER,
		EQUAL
	}



	private final ArrayList<IWeightable> leftPanContent;

	private final ArrayList<IWeightable> rightPanContent;


	public Scaler() {
		leftPanContent = new ArrayList<IWeightable>();
		rightPanContent = new ArrayList<IWeightable>();
	}



	public void clearScalepans()
	{
		leftPanContent.clear();
		rightPanContent.clear();
	}

	public void putContentOnScalepan(IWeightable content, SCALEPAN_SIDE scalepan)
	{
		if(scalepan == SCALEPAN_SIDE.LEFT)
		{
			leftPanContent.add(content);
		}
		else if(scalepan == SCALEPAN_SIDE.RIGHT)
		{
			rightPanContent.add(content);
		}
	}

	public void putContentOnScalepan(List<? extends IWeightable> content, SCALEPAN_SIDE scalepan)
	{
		if(scalepan == SCALEPAN_SIDE.LEFT)
		{
			leftPanContent.addAll(content);
		}
		else if(scalepan == SCALEPAN_SIDE.RIGHT)
		{
			rightPanContent.addAll(content);
		}
	}

	public SCALER_STATE getScaleState()
	{
		double weightOnLeft = 0.0;

		for(IWeightable scaleable : leftPanContent)
		{
			weightOnLeft += scaleable.getWeight();
		}

		double weightOnRight = 0.0;

		for(IWeightable scaleable : rightPanContent)
		{
			weightOnRight += scaleable.getWeight();
		}

		return weightOnLeft == weightOnRight ? SCALER_STATE.EQUAL : weightOnLeft > weightOnRight ? SCALER_STATE.LEFT_HEAVIER : SCALER_STATE.RIGHT_HEAVIER;

	}
}
