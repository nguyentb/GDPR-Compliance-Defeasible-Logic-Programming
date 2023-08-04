package org.tweetyproject.arg.delp.syntax;

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


import org.tweetyproject.commons.util.rules.Rule;

import org.tweetyproject.logics.commons.syntax.Constant;
import org.tweetyproject.logics.commons.syntax.Functor;
import org.tweetyproject.logics.commons.syntax.Predicate;
import org.tweetyproject.logics.commons.syntax.RelationalFormula;
import org.tweetyproject.logics.commons.syntax.Variable;
import org.tweetyproject.logics.commons.syntax.interfaces.Conjunctable;
import org.tweetyproject.logics.commons.syntax.interfaces.Disjunctable;
import org.tweetyproject.logics.commons.syntax.interfaces.Term;
import org.tweetyproject.logics.fol.syntax.*;
import org.tweetyproject.math.probability.Probability;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This method is the superclass for both a strict rule and a defeasible rule in defeasible logic programming
 * and captures their common attributes and methods.
 *
 * @author Matthias Thimm
 * @author Tim Janus
 *
 */
public abstract class DelpRule extends RelationalFormula implements Rule<FolFormula, FolFormula>{
	
	/**
	 * The head of the rule (this must be a literal).
	 */
	protected FolFormula head;

	/**
	 * The body of the rule (these must be a literals).
	 */
	protected Set<FolFormula> body;

    abstract String getSymbol();

	/**
	 * Default constructor; initializes head and body of the rule
     * @param head a literal
     * @param body a set of literals
     */
    DelpRule(FolFormula head, Collection<? extends FolFormula> body){
        if(!head.isLiteral())
			throw new IllegalArgumentException("Heads of DeLP rules need to consist of a single literal.");
		for(FolFormula f: body)
			if(!f.isLiteral())
				throw new IllegalArgumentException("Body elements of DeLP rules need to consist of a single literal.");
		this.head = head;
		this.body = new HashSet<>(body);
	}

	/**
	 * Checks whether this rule is applicaple in the given context <code>literals</code>,
	 * @param literals a set of literals
	 * @return <code>true</code> iff this rule is applicaple, i.e., if the body of the rule is a subset
	 * 	of the given set of literals
	 */
	public boolean isApplicable(Collection<? extends FolFormula> literals){
		for(FolFormula f: literals)
			if(!f.isLiteral())
				throw new IllegalArgumentException("Parameter 'literals' is expected to consist of literals.");
		return literals.containsAll(body);
	}

	/**
	 * Checks whether there appear any variables in this rule
	 * @return <code>true</code> iff there appears no variable in this rule
	 */
	public boolean isGround(){
		if(!this.head.isGround()) return false;
		for(FolFormula f: this.body)
			if(!f.isGround())
				return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.util.rules.Rule#getPremise()
	 */
	@Override
	public Collection<? extends FolFormula> getPremise() {
		return this.body;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.util.rules.Rule#getConclusion()
	 */
	@Override
	public FolFormula getConclusion() {
		return this.head;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.ClassicalFormula#combineWithAnd(org.tweetyproject.ClassicalFormula)
	 */
	@Override
	public RelationalFormula combineWithAnd(Conjunctable f) {
	    throw new UnsupportedOperationException("Combination using AND not permitted for rules.");
	}

	@Override
	public RelationalFormula combineWithOr(Disjunctable f) {
	    throw new UnsupportedOperationException("Combination using OR not permitted for rules.");
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.ClassicalFormula#complement()
	 */
	@Override
	public RelationalFormula complement() {
		throw new UnsupportedOperationException("Complement not permitted for rules.");
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#getPredicates()
	 */
	@Override
	public Set<Predicate> getPredicates() {
		Set<Predicate> predicates = new HashSet<>();
		predicates.addAll(this.head.getPredicates());
		for(FolFormula f: this.body)
			predicates.addAll(f.getPredicates());
		return predicates;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#getAtoms()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Set<FolAtom> getAtoms() {
		Set<FolAtom> atoms = new HashSet<>();
		atoms.addAll((Collection<FolAtom>) this.head.getAtoms());
		for(FolFormula f: this.body)
			atoms.addAll((Collection<FolAtom>) f.getAtoms());
		return atoms;
	}

	@Override
	public boolean isLiteral() {
		return false;
	}

	@Override
	public Set<Term<?>> getTerms() {
		Set<Term<?>> reval = new HashSet<>();
		reval.addAll(head.getTerms());
		for(FolFormula b : body) {
			reval.addAll(b.getTerms());
		}
		return reval;
	}

	@Override
	public <C extends Term<?>> Set<C> getTerms(Class<C> cls) {
		Set<C> reval = new HashSet<>();
		reval.addAll(head.getTerms(cls));
		for(FolFormula b : body) {
			reval.addAll(b.getTerms(cls));
		}
		return reval;
	}

	@Override
	public Set<Variable> getQuantifierVariables() {
		Set<Variable> reval = new HashSet<>();
		reval.addAll(head.getQuantifierVariables());
		for(FolFormula b : body) {
			reval.addAll(b.getQuantifierVariables());
		}
		return reval;
	}
	
	@Override
	public boolean isFact() {
		return body.isEmpty();
	}

	@Override
	public boolean isConstraint() {
		return false;
	}

	@Override
	public void setConclusion(FolFormula conclusion) {
		if(!conclusion.isLiteral()) {
			throw new IllegalArgumentException("Heads of DeLP rules need to consist of a single literal.");
		}
		head = conclusion;
	}

	@Override
	public void addPremise(FolFormula premise) {
		if(!premise.isLiteral()) {
			throw new IllegalArgumentException("Body elements of DeLP rules need to consist of a single literal.");
		}
		body.add(premise);
	}

	@Override
	public void addPremises(Collection<? extends FolFormula> premises) {
        premises.forEach(this::addPremise);
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#containsQuantifier()
	 */
	@Override
	public boolean containsQuantifier() {
		return !getQuantifierVariables().isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#substitute(org.tweetyproject.logics.firstorderlogic.syntax.Term, org.tweetyproject.logics.firstorderlogic.syntax.Term)
	 */
	@Override
	public abstract RelationalFormula substitute(Term<?> v, Term<?> t) throws IllegalArgumentException;

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#getUnboundVariables()
	 */
	@Override
	public Set<Variable> getUnboundVariables() {
		Set<Variable> vars = new HashSet<>();
		vars.addAll(this.head.getUnboundVariables());
		for(FolFormula f: this.body)
			vars.addAll(f.getUnboundVariables());
		return vars;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#isClosed()
	 */
	@Override
	public boolean isClosed() {
		if(!this.head.isClosed()) 
			return false;
		for(FolFormula f: this.body)
			if(!f.isClosed())
				return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#isClosed(java.util.Set)
	 */
	@Override
	public boolean isClosed(Set<Variable> boundVariables) {
		if(!this.head.isClosed(boundVariables)) 
			return false;
		for(FolFormula f: this.body)
			if(!f.isClosed(boundVariables))
				return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#isWellBound()
	 */
	@Override
	public boolean isWellBound() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.RelationalFormula#isWellBound(java.util.Set)
	 */
	@Override
	public boolean isWellBound(Set<Variable> boundVariables) {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see org.tweetyproject.logics.firstorderlogic.syntax.LogicStructure#getFunctors()
	 */
	@Override
	public Set<Functor> getFunctors() {
		Set<Functor> functors = new HashSet<>();
		functors.addAll(this.head.getFunctors());
		for(FolFormula f: this.body)
			functors.addAll(f.getFunctors());
		return functors;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.ClassicalFormula#getUniformProbability()
	 */
	@Override
	public Probability getUniformProbability(){
		throw new UnsupportedOperationException("IMPLEMENT ME");
	}

	@Override
	public String toString(){
		StringBuilder str = new StringBuilder(head.toString());
		if (!body.isEmpty())
			str.append(getSymbol());
		str.append(body.stream()
				.map(Object::toString)
				.collect(Collectors.joining(",")));
		str.append(".");
		return str.toString();
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DelpRule delpRule = (DelpRule) o;

        if (!head.equals(delpRule.head)) return false;
        return body.equals(delpRule.body);
    }

    @Override
    public int hashCode() {
        int result = head.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }
    
    @Override
	public FolSignature getSignature() {
		FolSignature sig = new FolSignature();
		sig.addAll(this.getTerms(Constant.class));
		sig.addAll(this.getFunctors());
		sig.addAll(this.getPredicates());
		return sig;
	}
    
}