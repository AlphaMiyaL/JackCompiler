import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * @author Brian Song
 * @description Class of a set of simple routines for writing VM commands into output file.
 */
public class VMWriter {
	private PrintWriter writer;

	public VMWriter(File file) throws FileNotFoundException {
		try {
			writer = new PrintWriter(file);
		}
		catch(FileNotFoundException fnf) {
			throw fnf;
		}
	}

	/**
	 * Writes VM push command
	 * 
	 * @param seg segment
	 * @param idx index
	 */
	public void writePush(String seg, int idx) {
		writer.println("push " + seg + " " + idx);
	}

	/**
	 * Writes VM pop command
	 * 
	 * @param seg segment
	 * @param idx index
	 */
	public void writePop(String seg, int idx) {
		writer.println("pop " + seg + " " + idx);
	}

	/**
	 * Writes a VM arithmetic-logical command
	 * 
	 * @param cmd command
	 */
	public void writeArithmetic(String cmd) {
		writer.println(cmd);
	}

	/**
	 * Writes a VM label command
	 * 
	 * @param label
	 */
	public void writeLabel(String label) {
		writer.println("label " + label);
	}

	/**
	 * Writes a VM goto command
	 * 
	 * @param label
	 */
	public void writeGoto(String label) {
		writer.println("goto " + label);
	}

	/**
	 * Writes a VM if-goto command
	 * 
	 * @param label
	 */
	public void writeIf(String label) {
		writer.println("if-goto " + label);
	}

	/**
	 * Writes a VM call command
	 * 
	 * @param name
	 * @param nArgs
	 */
	public void writeCall(String name, int nArgs) {
		writer.println("call " + name + " " + nArgs);
	}

	/**
	 * Writes a VM function command
	 * 
	 * @param name
	 * @param nLocals
	 */
	public void writeFunction(String name, int nLocals) {
		writer.println("function " + name + " " + nLocals);
	}

	/**
	 * Writes a VM return command
	 */
	public void writeReturn() {
		writer.println("return");
	}

	/**
	 * Closes output file
	 */
	public void close() {
		writer.close();
	}
}