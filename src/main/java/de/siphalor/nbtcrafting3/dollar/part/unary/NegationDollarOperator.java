/*
 * Copyright 2020-2022 Siphalor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied.
 * See the License for the specific language governing
 * permissions and limitations under the License.
 */

package de.siphalor.nbtcrafting3.dollar.part.unary;

import de.siphalor.nbtcrafting3.dollar.DollarUtil;
import de.siphalor.nbtcrafting3.dollar.exception.DollarDeserializationException;
import de.siphalor.nbtcrafting3.dollar.exception.DollarEvaluationException;
import de.siphalor.nbtcrafting3.dollar.exception.DollarException;
import de.siphalor.nbtcrafting3.dollar.part.DollarPart;
import de.siphalor.nbtcrafting3.util.NumberUtil;

public class NegationDollarOperator extends UnaryDollarOperator {
	private NegationDollarOperator(DollarPart dollarPart) {
		super(dollarPart);
	}

	public static DollarPart of(DollarPart dollarPart) throws DollarDeserializationException {
		return shortCircuitConstant(new NegationDollarOperator(dollarPart));
	}

	@Override
	public Object apply(Object value) throws DollarEvaluationException {
		try {
			return NumberUtil.negate(DollarUtil.expectNumber(value));
		} catch (DollarException e) {
			throw new DollarEvaluationException(e);
		}
	}
}
