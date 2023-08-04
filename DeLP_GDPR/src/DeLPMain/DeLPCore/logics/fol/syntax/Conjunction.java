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


import java.util.Collection;
import java.util.HashSet;

import org.tweetyproject.logics.common.LogicalSymbols;
import org.tweetyproject.logics.commons.syntax.RelationalFormula;
import org.tweetyproject.logics.commons.syntax.interfaces.Term;

/**
 * The classical conjunction of first-order logic.
 * @author Matthias Thimm
 */
public class Conjunction extends AssociativeFolFormula {
	
	/**
	 * Creates a new conjunction with the given inner formulas. 
	 * @param formulas a collection of formulas.
	 */
	public Conjunction(Collection<? extends RelationalFormula> formulas){
		super(formulas);
	}
	
	/**
	 * Creates a new (empty) conjunction.
	 */
	public Conjunction(){
		this(new HashSet<RelationalFormula>());
	}
	
	/**
	 * Creates a new conjunction with the two given formulae
	 * @param first a relational formula.
	 * @param second a relational formula.
	 */
	public Conjunction(RelationalFormula first, RelationalFormula second){
		this();
		this.add(first);
		this.add(second);
	}	
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.FolFormula#isDnf()
	 */
	@Override
	public boolean isDnf(){
		return this.getFormulas(Disjunction.class).isEmpty() && 
				this.getFormulas(ForallQuantifiedFormula.class).isEmpty() &&
				this.getFormulas(ExistsQuantifiedFormula.class).isEmpty();
	}
	

	@Override
	public Conjunction substitute(Term<?> v, Term<?> t) throws IllegalArgumentException{
		return (Conjunction)super.substitute(v, t);
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.FolFormula#toNNF()
	 */
	@Override
	public FolFormula toNnf() {
    Conjunction c = new Conjunction();
    for(RelationalFormula p : this) {
      if(p instanceof FolFormula)
        c.add( ((FolFormula) p).toNnf() );
      else
        throw new IllegalStateException("Can not convert conjunctions containing non-first-order formulae to NNF.");
    }
    return c;
	}
		
	/*
	 * (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.FolFormula#collapseAssociativeFormulas()
	 */
	@Override
	public RelationalFormula collapseAssociativeFormulas() {
    if(this.isEmpty())
      return new Tautology();
    if(this.size() == 1)
      return ((FolFormula)this.iterator().next()).collapseAssociativeFormulas();
    Conjunction newMe = new Conjunction();
    for(RelationalFormula f: this){
      if(! (f instanceof FolFormula)) throw new IllegalStateException("Can not collapse conjunctions containing non-first-order formulae.");
      RelationalFormula newF = ((FolFormula)f).collapseAssociativeFormulas();
      if(newF instanceof Conjunction)
        newMe.addAll((Conjunction) newF);
      else newMe.add(newF);
    }
    return newMe;
  }

	@Override
	public Conjunction clone() {
		return new Conjunction(support.copyHelper(this));
	}

	//-------------------------------------------------------------------------
	//	ASSOC SUPPORT BRIDGE METHODS
	//-------------------------------------------------------------------------
	
	@SuppressWarnings("unchecked")
	@Override
	public Conjunction createEmptyFormula() {
		return new Conjunction();
	}

	@Override
	public String getOperatorSymbol() {
		return LogicalSymbols.CONJUNCTION();
	}

	@Override
	public String getEmptySymbol() {
		return LogicalSymbols.TAUTOLOGY();
	}


}