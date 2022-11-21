import java.util.ArrayList;
import java.util.Hashtable;

/**
 * @author Brian Song
 * @description provides services for building, populating, and using symbol
 * tables that keep track of the symbol properties name, type, kind, and a
 * running index for each kind.
 */

public class SymbolTable {
	private int staticCounter;
	private int fieldCounter;
	private int argCounter;
	private int varCounter;
	private Hashtable<String, Symbol> classScope;
	private Hashtable<String, Symbol> subroutScope;
	private ArrayList<String> voids;

	public SymbolTable() {
		classScope = new Hashtable<String, Symbol>();
		subroutScope = new Hashtable<String, Symbol>();
		staticCounter = 0;
		fieldCounter = 0;
		startSubroutine();
	}

	/**
	 * Starts Subroutine
	 */
	public void startSubroutine() {
		subroutScope.clear();
		argCounter = 0;
		varCounter = 0;
	}

	/**
	 * Increments argument counter
	 */
	public void addImplicitArg() {
		argCounter++;
	}

	/**
	 * Defines/Adds to table a new variable of given name, type, and kind.
	 * Assigns to index value of that kind and adds one to index
	 * @param name name of variable
	 * @param type type of variable
	 * @param kind kind of variable
	 */
	public void define(String name, String type, String kind) {
		switch(kind) {
			case "static":
				classScope.put(name, new Symbol(type, kind, staticCounter));
				staticCounter++;
				break;
			case "field":
				classScope.put(name, new Symbol(type, "this", fieldCounter));
				fieldCounter++;
				break;
			case "arg":
				subroutScope.put(name, new Symbol(type, "argument", argCounter));
				argCounter++;
				break;
			case "var":
				subroutScope.put(name, new Symbol(type, "local", varCounter));
				varCounter++;
				break;
		}
	}

	/**
	 * @param kind key to search in table
	 * @return number of variables of given kind in table
	 */
	public int varCount(String kind) {
		switch(kind) {
			case "static":
				return staticCounter;
			case "field":
				return fieldCounter;
			case "arg":
				return argCounter;
			case "var":
				return varCounter;
		}
		return -1;
	}

	/**
	 * @param name
	 * @return kind of named identifier. If not found, returns null
	 */
	public String kindOf(String name) {
		if(subroutScope.containsKey(name)) {
			return subroutScope.get(name).getKind();
		}
		else if(classScope.containsKey(name)) {
			return classScope.get(name).getKind();
		}
		else {
			return null;
		}
	}

	/**
	 * @param name
	 * @return type of named variable. If not found, returns null
	 */
	public String typeOf(String name) {
		if(subroutScope.containsKey(name)) {
			return subroutScope.get(name).getType();
		}
		else if(classScope.containsKey(name)) {
			return classScope.get(name).getType();
		}
		else {
			return null;
		}
	}

	/**
	 * @param name
	 * @return index of named variable. If not found, returns null
	 */
	public int indexOf(String name) {
		if(subroutScope.containsKey(name)) {
			return subroutScope.get(name).getIndex();
		}
		else if(classScope.containsKey(name)) {
			return classScope.get(name).getIndex();
		}
		else {
			return -1;
		}
	}
}