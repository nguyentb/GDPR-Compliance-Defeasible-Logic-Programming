package org.tweetyproject.commons;

/*
 *  This file is part of "TweetyProject", a collection of Java libraries for
 *  logical aspects of artificial intelligence and knowledge representation.
 *
 *  TweetyProject is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License version 3 as
 *  published by the Free Software Foundation.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 *  Copyright 2018 The TweetyProject Team <http://tweetyproject.org/contact/>
 */


/**
 * The general interface for objects that are able to query a belief base
 * with some formula and return either TRUE or FALSE as answer.
 * 
 * @author Matthias Thimm
 *
 * @param <B> the belief base type that can be queried
 * @param <F> the type of formulas that can be queries
 */
public interface QualitativeReasoner<B extends BeliefBase,F extends Formula> extends Reasoner<Boolean,B,F>{
	/* (non-Javadoc)
	 * @see org.tweetyproject.commons.Reasoner#query(org.tweetyproject.commons.BeliefBase, org.tweetyproject.commons.Formula)
	 */
	public Boolean query(B beliefbase, F formula);
	/**
	 * 
	 * @return if the solver is installed
	 */
	public abstract boolean isInstalled();
}
