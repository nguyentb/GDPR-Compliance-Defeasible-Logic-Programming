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
 * Enum constants for the two classical inference models of skeptical inference (assess
 * a formula as true iff it is contained in every model) and credulous inference
 * (assess a formula as true iff it is contained in some model).
 * 
 * @author Matthias Thimm
 */
public enum InferenceMode {
	/**
	 * Skeptical inference assesses a formula as true iff it is contained in <strong>every</strong> model
	 */
	SKEPTICAL,
	/**
	 * Credulous inference assesses a formula as true iff it is contained in <strong>some</strong> model
	 */
	CREDULOUS
}
