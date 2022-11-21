
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Brian Song
 * @description Translates .jack files into .vm files
 */
public class JackCompiler {
	public static void main(String[] args) {
		String argy=null;
		if(args.length==0) {
			Scanner kb = new Scanner(System.in);
			System.out.print("Please enter the filepath: ");
			argy = kb.nextLine();
			kb.close();
		}
		else {
			argy = args[0];
		}
		try {
			ArrayList<File> files = new ArrayList<File>();

			File input = new File(argy);
			getFiles(input, files);

			if(!files.isEmpty()) {
				for(File f: files) {
					String outputName = f.getName();
					outputName = outputName.substring(0, outputName.indexOf('.'));
					File output = new File(f.getParent(), outputName + ".vm");

					CompilationEngine ce = new CompilationEngine(f, output);
					ce.compileClass();
					ce.close();
					System.out.println(outputName + " created. Please check same folder .jack file was in.");
				}
			}
			else {
				System.out.println("No .jack files found.");
			}
		}
		catch(Exception e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void getFiles(File input, ArrayList<File> files) throws FileNotFoundException {
		if(input.isFile()) {
			// check for .vm extension before adding to list of files
			String filename = input.getName();
			int extension = filename.indexOf('.');
			if(extension>0) {
				String fileExtension = filename.substring(extension+1);
				if(fileExtension.equalsIgnoreCase("jack")) {
					files.add(input);
				}
			}
		}
		else if(input.isDirectory()) {
			File[] innerFiles = input.listFiles();
			for(File f: innerFiles) {
				getFiles(f, files);
			}
		}
		else {
			throw new FileNotFoundException("Could not find file or directory.");
		}
	}
}
