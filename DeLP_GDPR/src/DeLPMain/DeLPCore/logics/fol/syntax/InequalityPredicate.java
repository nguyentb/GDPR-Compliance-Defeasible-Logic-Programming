package org.tweetyproject.logics.fol.syntax;

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


import java.util.Arrays;

import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.commons.syntax.Sort;

/**
 * This class models an inequality predicate, meaning 
 * a predicate of arity 2 that maps to the complement of the identity relation.
 * Terms of the inequality predicate are of the sort "_Any" 
 * which is considered equal to all other sorts.
 * 
 * @author Anna Gessler
 *
 */
public class InequalityPredicate extends Predicate {
	/**
	 * constructor
	 */
	public InequalityPredicate() {
		super("/==",Arrays.asList(Sort.ANY,Sort.ANY));	
	}

}