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

import mavlc.syntax.statement.Declaration;
/**
 * EiCB group number: 40
 * Names and matriculation numbers of all group members:
 * Nyami Gochui Steve Jordi (2756527)
 * Julian von Hammel (2972165)
 * Nil Sila Ulucan (2378251)
 */

/**
 * A table for identifiers used inside a function.
 */
public class IdentificationTable {
	private Scope Head; // unser aktuelles Scope, in dem man neue Element nach Rechts einfügt
	/**
	 * Declares the given identifier in the current scope.
	 *
	 * @param name the identifier to declare
	 * @param declaration the reference to the identifier's declaration site
	 */
	public void addIdentifier(String name, Declaration declaration) {
		Head.addIdentifier(name, declaration); // rufe einfach addIdentifier von Scope Klasse mit aktuellen Parametern
	}
	
	/**
	 * Looks up the innermost declaration of the given identifier.
	 *
	 * @param name the identifier to look up
	 * @return the identifier's innermost declaration site
	 */
	public Declaration getDeclaration(String name) {
		return Head.getDeclaration(name); // getDeclaration von Scope Klasse
	}
	
	/**
	 * Opens a new scope.
	 */
	public void openNewScope() {
		if( Head == null  ){ // prüfe, ob es gar nichts gibt
			Head = new Scope(null);
		} else {
			Head = new Scope(Head);
		}
	}
	
	/**
	 * Closes the current scope.
	 */
	public void closeCurrentScope() {
		Head = Head.parentScope; // setze einfach das aktuelle Element auf das vorherige
	}
}
