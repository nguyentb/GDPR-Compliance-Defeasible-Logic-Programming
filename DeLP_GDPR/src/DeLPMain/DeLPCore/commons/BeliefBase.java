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
 *  Copyright 2016 The TweetyProject Team <http://tweetyproject.org/contact/>
 */


/**
 * This class captures an abstract knowledge base, i.e. some set of
 * formulas in a given knowledge representation language, that can be asked
 * queries.
 * @author Matthias Thimm
 * @author Tim Janus
 */
public interface BeliefBase {
	/**
	 * Returns the signature of the language of this knowledge base.
	 * @return the signature of the language of this knowledge base.
	 */
	public Signature getMinimalSignature();
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString();

}
