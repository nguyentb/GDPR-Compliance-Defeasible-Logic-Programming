package org.tweetyproject.commons.util.rules;

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
 *  Copyright 2016 The TweetyProject Team <http://tweetyproject.org/contact/>
 */


import java.util.*;

import org.tweetyproject.commons.*;

/**
 * This interface models a general rule, i.e. a structure with some
 * premise (a set of formulas) and some conclusion (a single formula).
 * 
 * @author Matthias Thimm
 * @author Tim Janus
 * @param <C> the type of formulas for the conclusion
 * @param <P> the type of formulas for the premises
 */
public interface Rule<C extends Formula, P extends Formula> extends Formula {

	/**
	 * isFact
	 * @return  whether the rule is a fact
	 */
	public boolean isFact();
/**
 * isConstraint
 * @return whether the rule is a constraint
 */
	public boolean isConstraint();
	
	/**
	 * Set the conclusion of this rule.
	 * @param conclusion some formula
	 */
	public void setConclusion(C conclusion);
	
	/**
	 * Add the given premise to this rule.
	 * @param premise some formula
	 */
	public void addPremise(P premise);
	
	/**
	 * Add the given premises to this rule.
	 * @param premises some formulas
	 */
	public void addPremises(Collection<? extends P> premises);
	
	@Override
	public Signature getSignature();
	
	/**
	 * Returns the premise of this rule.
	 * @return the premise of this rule.
	 */
	public Collection<? extends P> getPremise();
	
	/**
	 * Returns the conclusion of this rule.
	 * @return the conclusion of this rule.
	 */
	public C getConclusion();
		
}