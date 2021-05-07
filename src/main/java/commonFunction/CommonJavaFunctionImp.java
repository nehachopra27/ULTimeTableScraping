package commonFunction;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class CommonJavaFunctionImp extends GlobalVariables implements CommonJavaFunction {

	public boolean writedata(List<String[]> dataset) throws IOException {
		// TODO Auto-generated method stub

		File file = new File(dirTestResource + "dataset.csv");

		if (file.exists())
			file.delete();
		// create FileWriter object with file as parameter
		FileWriter outputfile = new FileWriter(file);

		// create CSVWriter object filewriter object as parameter
		CSVWriter writer = new CSVWriter(outputfile);

		// create a List which contains String array
		writer.writeAll(dataset);

		// closing writer connection
		writer.close();
		return true;

	}

}
