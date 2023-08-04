package org.tweetyproject.graphs;

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
 * 
 * @author Lars Bengel, Sebastian Franke
 *
 * @param <S> a Node
 * @param <T> a number
 */
public class WeightedDirectedEdge<S extends Node,T extends Number> extends DirectedEdge<S> implements WeightedEdge<S,T>{

	/** The weight of this edge. */
	private T weight;
	
	/**
	 * Creates a new weighted direct edge.
	 * @param nodeA some node
	 * @param nodeB some node
	 * @param weight some weight
	 */
	public WeightedDirectedEdge(S nodeA, S nodeB, T weight) {
		super(nodeA, nodeB);
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see org.tweetyproject.graphs.WeightedEdge#getWeight()
	 */
	@Override
	public T getWeight() {
		return this.weight;
	}
	
	/**
	 * 
	 * @param number a number for the weight
	 */
	public void setWeight(T number) {
		this.weight = number;
	}
}
