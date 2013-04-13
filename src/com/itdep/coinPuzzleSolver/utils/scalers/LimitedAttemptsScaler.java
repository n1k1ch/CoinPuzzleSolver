package com.itdep.coinPuzzleSolver.utils.scalers;

import java.util.concurrent.atomic.AtomicInteger;

import com.itdep.coinPuzzleSolver.exceptions.ScalerException;

/**
 * 
 * @author n1k1ch
 *
 */
public class LimitedAttemptsScaler extends Scaler {
	
	private final int mNumberOfAttempts;

	private final AtomicInteger mScaleAttemptsCount = new AtomicInteger(0);
	
	
	
	public LimitedAttemptsScaler(int numberOfAttempts) {
		
		super();

		mNumberOfAttempts = numberOfAttempts;
		
		mScaleAttemptsCount.set(0);
	}

	

	@Override
	public SCALER_STATE getScaleState() throws ScalerException {

		if(mScaleAttemptsCount.get() == mNumberOfAttempts)
		{
			throw new ScalerException();
		}

		SCALER_STATE toRet = super.getScaleState();
		
		mScaleAttemptsCount.incrementAndGet();
		
		return toRet;
	}
}
