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

package de.siphalor.nbtcrafting.dollar.part.binary;

import java.util.function.BiFunction;

import de.siphalor.nbtcrafting.dollar.DollarUtil;
import de.siphalor.nbtcrafting.dollar.exception.DollarDeserializationException;
import de.siphalor.nbtcrafting.dollar.exception.DollarEvaluationException;
import de.siphalor.nbtcrafting.dollar.part.DollarPart;

public class LogicalBinaryDollarOperator extends BinaryDollarOperator {
	private final BiFunction<Boolean, Boolean, Boolean> function;

	private LogicalBinaryDollarOperator(BiFunction<Boolean, Boolean, Boolean> function, DollarPart first, DollarPart second) {
		super(first, second);
		this.function = function;
	}

	public static DollarPart of(BiFunction<Boolean, Boolean, Boolean> function, DollarPart first, DollarPart second) throws DollarDeserializationException {
		return shortCircuitConstant(new LogicalBinaryDollarOperator(function, first, second));
	}

	public static DollarPart andOf(DollarPart first, DollarPart second) throws DollarDeserializationException {
		return of(Boolean::logicalAnd, first, second);
	}

	public static DollarPart orOf(DollarPart first, DollarPart second) throws DollarDeserializationException {
		return of(Boolean::logicalOr, first, second);
	}

	@Override
	public Object apply(Object first, Object second) throws DollarEvaluationException {
		return function.apply(DollarUtil.asBoolean(first), DollarUtil.asBoolean(second));
	}
}
