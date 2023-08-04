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


import java.util.*;

/**
 * Instances of this class represent a functional term on some inner term.
 * 
 * @author Matthias Thimm
 */
public abstract class FunctionalTerm extends Term {

	/**
	 * The inner term of this operation.
	 */
	private Term term;
	
	/**
	 * Creates a new functional term with the given inner term.
	 * @param term a term
	 */
	public FunctionalTerm(Term term){
		this.term = term;
	}
	
	/**
	 * Returns the inner term.
	 * @return the inner term.
	 */
	public Term getTerm(){
		return this.term;
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#collapseAssociativeOperations()
	 */
	@Override
	public void collapseAssociativeOperations() {
		this.term.collapseAssociativeOperations();
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#expandAssociativeOperations()
	 */
	@Override
	public void expandAssociativeOperations(){
		this.term.expandAssociativeOperations();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getMinimums()
	 */
	@Override
	public Set<Minimum> getMinimums() {
		return this.term.getMinimums();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getMaximums()
	 */
	@Override
	public Set<Maximum> getMaximums() {
		return this.term.getMaximums();
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getProducts()
	 */
	@Override
	public Set<Product> getProducts() {
		return this.term.getProducts();
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getSum()
	 */
	@Override
	public Set<Sum> getSums() {
		return this.term.getSums();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getVariables()
	 */
	@Override
	public Set<Variable> getVariables() {
		return this.term.getVariables();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getAbsoluteValues()
	 */
	@Override
	public Set<AbsoluteValue> getAbsoluteValues(){
		return this.term.getAbsoluteValues();		
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#isInteger()
	 */
	@Override
	public boolean isInteger() {
		return this.term.isInteger();
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toLinearForm()
	 */
	@Override
	public Sum toLinearForm() throws IllegalArgumentException{
		throw new IllegalArgumentException("The term '" + this + "' cannot be brought into linear form because it is non-linear.");
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toQuadraticForm()
	 */
	@Override
	public Sum toQuadraticForm() throws IllegalArgumentException{
		throw new IllegalArgumentException("The term '" + this + "' cannot be brought into quadratic form because it is non-linear.");
	}
		
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#replaceTerm(org.tweetyproject.math.term.Term, org.tweetyproject.math.term.Term)
	 */
	@Override
	public abstract Term replaceTerm(Term toSubstitute, Term substitution);
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toString()
	 */
	@Override
	public abstract String toString();
	
	@Override
	public boolean isLinear() {
		return false;
	}


	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#value()
	 */
	@Override
	public abstract Constant value() throws IllegalArgumentException;

}
