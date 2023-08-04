package org.tweetyproject.arg.delp.semantics;

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
 * Wrapping a generic answer from a reasoner in order to allow UNDECIDED
 * in addition to the traditional YES and NO.
 *
 * We ensure backward compatibility by mapping those 3 values to Double
 * values as follows:
 * <ul>
 *     <li><pre>true  &lt;=&gt; YES       &lt;=&gt; Double(0)</pre></li>
 *     <li><pre>false &lt;=  NO        &lt;=&gt; negative number</pre></li>
 *     <li><pre>false &lt;=  UNDECIDED &lt;=&gt; positive number</pre></li>
 * </ul>
 * Note that only <code>true</code> can be reliably mapped to YES, whereas
 * <code>false</code> remains ambiguous.
 *
 *
 * @author Linda.Briesemeister
 */
public class DelpAnswer {

    public enum Type {
        YES ("The answer is: YES"),
        NO ("The answer is: NO"),
        UNDECIDED ("The answer is: UNDECIDED"),
        UNKNOWN ("The answer is: UNKNOWN");

        private final String text;

        Type(String text) { this.text = text; }

        static Type typeForBoolean(boolean booleanAnswer) {
            if (booleanAnswer)
                return YES;
            else
                return NO; // ambiguous, so default is NO
        }

        static Type typeForDouble(Double doubleAnswer) {
            if (doubleAnswer == 0d)
                return YES;
            else if (doubleAnswer < 0d)
                return NO;
            else // double is positive
                return UNDECIDED;
        }

        @Override
        public String toString() { return text; }

        public boolean getBooleanAnswer() {
            switch (this) {
                case YES: return true;
                default: return false;
            }
        }

        public Double getDoubleAnswer() {
            switch (this) {
                case YES: return 0d;
                case NO: return -1d;
                case UNDECIDED: return 1d;
                case UNKNOWN: return Double.NaN;
                default:
                    throw new IllegalStateException("Cannot generate Double answer from "+this);
            }
        }
    }

   
}