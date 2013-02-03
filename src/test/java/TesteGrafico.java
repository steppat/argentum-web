import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TesteGrafico {

	public static void main(String[] args) throws IOException {

		DefaultCategoryDataset ds = new DefaultCategoryDataset();
		ds.addValue(33.8, "mmv", "dia1");
		ds.addValue(35.0, "mmv", "dia2");
		ds.addValue(33.7, "mmv", "dia3");
		ds.addValue(36.1, "mmv", "dia4");
		
		JFreeChart chart = ChartFactory.createLineChart("MMS", "dias",
				"valueAxisLabel", ds,
				PlotOrientation.VERTICAL, false, true, false);

		ChartUtilities.writeChartAsPNG(new FileOutputStream("grafico.png"), chart, 300, 300);
		
	}

}