package org.tweetyproject.math.term;

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


import java.util.ArrayList;
import java.util.List;

import org.tweetyproject.math.*;

/**
 * This class represents a the nth root function
 * 
 * @author Matthias Thimm
 */
public class Root extends FunctionalTerm {

	/** The base of the root */
	private Term base;
	
	/**
	 * Creates a new square root.
	 * @param term the term inside the square root
	 */
	public Root(Term term) {
		this(term,new IntegerConstant(2));
	}
	
	/**
	 * Creates a new root for the given base.
	 * @param term the term inside the square root
	 * @param base the base of the root
	 */
	public Root(Term term, Term base) {
		super(term);
		this.base = base;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.FunctionalTerm#replaceTerm(org.tweetyproject.math.term.Term, org.tweetyproject.math.term.Term)
	 */
	@Override
	public Term replaceTerm(Term toSubstitute, Term substitution) {
		return new Root(this.getTerm().replaceTerm(toSubstitute, substitution),this.base);
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.FunctionalTerm#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.getTerm().toString() + ")**(1.0/" + this.base + ")";
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.FunctionalTerm#value()
	 */
	@Override
	public Constant value() throws IllegalArgumentException {
		return new FloatConstant(Math.pow(this.getTerm().value().doubleValue(), 1.0/this.base.doubleValue()));
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#isContinuous(org.tweetyproject.math.term.Variable)
	 */
	@Override
	public boolean isContinuous(Variable v) {
		return this.getTerm().isContinuous(v);
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#simplify()
	 */
	@Override
	public Term simplify() {
		return new Root(this.getTerm().simplify(),this.base);
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#derive(org.tweetyproject.math.term.Variable)
	 */
	@Override
	public Term derive(Variable v) throws NonDifferentiableException {
		throw new RuntimeException("Implement me");
	}

	@Override
	public List<Term> getTerms() {
		ArrayList<Term> result = new ArrayList<Term>();
		result.add(this.base);
		return result;
	}

}
