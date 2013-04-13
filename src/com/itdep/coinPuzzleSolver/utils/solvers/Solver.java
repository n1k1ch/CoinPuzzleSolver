package com.itdep.coinPuzzleSolver.utils.solvers;

import com.itdep.coinPuzzleSolver.interfaces.IPuzzleAnswer;
import com.itdep.coinPuzzleSolver.interfaces.IPuzzleData;

public abstract class Solver {
	abstract public IPuzzleAnswer solve(IPuzzleData puzzleData);
}
