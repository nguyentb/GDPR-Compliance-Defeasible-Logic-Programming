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

import org.tweetyproject.math.*;

/**
 * This class models a difference between two terms.
 * @author Matthias Thimm
 */
public class Difference extends Term{
	
	/** The two terms of the difference*/
	private Term first, second;
	
	/**
	 * Creates a new difference with the given terms.
	 * @param first a term.
	 * @param second a term.
	 */
	public Difference(Term first, Term second){
		this.first = first;
		this.second = second;
	}		
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#value()
	 */
	@Override
	public Constant value(){
		return new FloatConstant(this.first.value().doubleValue() - this.second.value().doubleValue());		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#replaceTerm(org.tweetyproject.math.term.Term, org.tweetyproject.math.term.Term)
	 */
	@Override
	public Term replaceTerm(Term toSubstitute, Term substitution){
		return new Difference(this.first.replaceTerm(toSubstitute, substitution),this.second.replaceTerm(toSubstitute, substitution));		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#expandAssociativeOperations()
	 */
	@Override
	public void expandAssociativeOperations(){
		this.first.expandAssociativeOperations();
		this.second.expandAssociativeOperations();
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toQuadraticForm()
	 */
	@Override
	public Sum toQuadraticForm() throws IllegalArgumentException{
		return new Sum(this.first, new FloatConstant(-1).mult(this.second)).toQuadraticForm();		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toLinearForm()
	 */
	@Override
	public Sum toLinearForm() throws IllegalArgumentException{
		return new Sum(this.first, new FloatConstant(-1).mult(this.second)).toLinearForm();		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#derive(org.tweetyproject.math.term.Variable)
	 */
	@Override
	public Term derive(Variable v) throws NonDifferentiableException {
		if(!this.getVariables().contains(v)) return new IntegerConstant(0);
		return new Difference(this.first.derive(v), this.second.derive(v));
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#isContinuous(org.tweetyproject.math.term.Variable)
	 */
	@Override
	public boolean isContinuous(Variable v){
		return this.first.isContinuous(v) && this.second.isContinuous(v);		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#simplify()
	 */
	@Override
	public Term simplify(){
		return new Difference(this.first, this.second);		
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#toString()
	 */
	@Override
	public String toString(){
		return "(" + this.first.toString() + " - " + this.second.toString() + ")";
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getVariables()
	 */
	@Override
	public Set<Variable> getVariables() {
		Set<Variable> r = new HashSet<Variable>();
		r.addAll(this.first.getVariables());
		r.addAll(this.second.getVariables());
		return r;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getProducts()
	 */
	@Override
	public Set<Product> getProducts() {
		Set<Product> r = new HashSet<Product>();
		r.addAll(this.first.getProducts());
		r.addAll(this.second.getProducts());
		return r;
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getSums()
	 */
	@Override
	public Set<Sum> getSums() {
		return this.toLinearForm().getSums();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getMinimums()
	 */
	@Override
	public Set<Minimum> getMinimums() {
		Set<Minimum> r = new HashSet<Minimum>();
		r.addAll(this.first.getMinimums());
		r.addAll(this.second.getMinimums());
		return r;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getMaximums()
	 */
	@Override
	public Set<Maximum> getMaximums() {
		Set<Maximum> r = new HashSet<Maximum>();
		r.addAll(this.first.getMaximums());
		r.addAll(this.second.getMaximums());
		return r;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#getAbsoluteValues()
	 */
	@Override
	public Set<AbsoluteValue> getAbsoluteValues() {
		Set<AbsoluteValue> r = new HashSet<AbsoluteValue>();
		r.addAll(this.first.getAbsoluteValues());
		r.addAll(this.second.getAbsoluteValues());
		return r;
	}

	@Override
	public boolean isInteger() {
		return this.first.isInteger() && this.second.isInteger();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.math.term.Term#collapseAssociativeOperations()
	 */
	@Override
	public void collapseAssociativeOperations() {
		this.first.collapseAssociativeOperations();
		this.second.collapseAssociativeOperations();
	}

	@Override
	public List<Term> getTerms() {
		ArrayList<Term> result = new ArrayList<Term>();
		result.add(this.first);
		result.add(this.second);
		return result;
	}
}
