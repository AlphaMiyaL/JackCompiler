/**
 * @author Brian Song
 * @description Defines a Symbol as a class object
 * contains a type, kind, and index
 */
public class Symbol {
	private String type;
	private String kind;
	private int index;

	public Symbol(String t, String k, int i) {
		this.type = t;
		this.kind = k;
		this.index = i;
	}

	// Getters

	public String getType() {
		return this.type;
	}

	public String getKind() {
		return this.kind;
	}

	public int getIndex() {
		return this.index;
	}
}
