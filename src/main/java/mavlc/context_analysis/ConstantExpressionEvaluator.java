/*******************************************************************************
 * Copyright (c) 2016-2019 Embedded Systems and Applications Group
 * Department of Computer Science, Technische Universitaet Darmstadt,
 * Hochschulstr. 10, 64289 Darmstadt, Germany.
 *
 * All rights reserved.
 *
 * This software is provided free for educational use only.
 * It may not be used for commercial purposes without the
 * prior written permission of the authors.
 ******************************************************************************/
package mavlc.context_analysis;

import mavlc.errors.NonConstantExpressionError;
import mavlc.syntax.AstNode;
import mavlc.syntax.AstNodeBaseVisitor;
import mavlc.syntax.expression.*;
import mavlc.type.IntType;


/**
 * EiCB group number: 40
 * Names and matriculation numbers of all group members:
 * Nyami Gochui Steve Jordi (2756527)
 * Julian von Hammel (2972165)
 * Nil Sila Ulucan (2378251)
 */

public class ConstantExpressionEvaluator extends AstNodeBaseVisitor<Integer, Void> {
	@Override
	protected Integer defaultOperation(AstNode node, Void obj) {
		if(node instanceof Expression) {
			throw new NonConstantExpressionError((Expression) node);
		} else {
			throw new RuntimeException("Internal compiler error: should not try to constant-evaluate non-expressions");
		}
	}
	
	@Override
	public Integer visitIntValue(IntValue intValue, Void __) {
		intValue.setType(IntType.instance);
		return intValue.value;
	}
	 public Integer visitAddition(Addition addition, Void __){
		 addition.setType(IntType.instance);
		return addition.leftOperand.accept(this)  + addition.rightOperand.accept(this);
	}
	public Integer visitMultiplication(Multiplication multiplication, Void __){
		multiplication.setType(IntType.instance);
		return multiplication.leftOperand.accept(this) * multiplication.rightOperand.accept(this);
	}
	public Integer visitDivision(Division division, Void __){
		division.setType(IntType.instance);
		return division.leftOperand.accept(this) / division.rightOperand.accept(this);
	}
	public Integer visitSubtraction(Subtraction subtraction, Void __){
		subtraction.setType(IntType.instance);
		return subtraction.leftOperand.accept(this) - subtraction.rightOperand.accept(this);
	}
	public Integer visitUnaryMinus(UnaryMinus unaryMinus, Void __){
		unaryMinus.setType(IntType.instance);
		return -unaryMinus.operand.accept(this);
	}

	public Integer visitExponentiation(Exponentiation exponentiation, Void __){
		exponentiation.setType(IntType.instance);
		return (int) Math.pow(exponentiation.leftOperand.accept(this), exponentiation.rightOperand.accept(this));
	}
}
