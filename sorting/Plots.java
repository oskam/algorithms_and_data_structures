import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Plots extends ApplicationFrame {

    private HashMap<String, ArrayList<String[]>> plotdata = new HashMap<>();

    private Plots(String title, String[] files) {
        super(title);

        for (String file : files) {
            System.out.println(file);
            readFile(file);
        }

        final CategoryDataset dataset = createDataset();
        final JFreeChart chart = createChart(dataset);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));
        setContentPane(chartPanel);
    }

    private CategoryDataset createDataset() {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (String dataname : plotdata.keySet()) {
            final String compares = dataname + " Compares";
            final String swaps = dataname + " Swaps";

            for (String[] data : plotdata.get(dataname)) {
                String size = data[0];
                Float comparesValue = Float.parseFloat(data[1]);
                Float swapsValue = Float.parseFloat(data[2]);
                dataset.addValue(comparesValue, compares, size);
                dataset.addValue(swapsValue, swaps, size);
            }
        }
        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset) {

        final JFreeChart chart = ChartFactory.createLineChart(
                "Sorting Algorithms",       // chart title
                "Size of data",                    // domain axis label
                "Average values",                   // range axis label
                dataset,                   // data
                PlotOrientation.VERTICAL,  // orientation
                true,                      // include legend
                true,                      // tooltips
                false                      // urls
        );

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        final CategoryAxis domainAxis = (CategoryAxis) plot.getDomainAxis();
        domainAxis.setMaximumCategoryLabelLines(10);

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);

        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();

        for (int i=0; i<plotdata.size()*2; i++) {
            renderer.setSeriesStroke(
                    i, new BasicStroke(
                            2.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND
                    )
            );
        }
        return chart;
    }

    private void readFile(String filename) {
        Scanner sc2 = null;
        try {
            sc2 = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert sc2 != null;

        String name = sc2.nextLine();
        ArrayList<String[]> filedata = new ArrayList<>();
        while (sc2.hasNextLine()) {
            String line = sc2.nextLine();
            String[] data = line.split("\\t");
            filedata.add(data);
        }
        plotdata.put(name, filedata);
    }


    public static void main(final String[] args) {
        final Plots demo = new Plots("Sorting Algorithms", args);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
