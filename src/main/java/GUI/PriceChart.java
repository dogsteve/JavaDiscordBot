package GUI;

import entities.Stonks;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class PriceChart {

    private JFreeChart chart;
    private ChartPanel chartPanel;

    public PriceChart (Stonks[] stonks) {
        super();
        this.chart = ChartFactory.createLineChart("Price Chart", "Date", "Price", createDataset(stonks));
        this.chart.setAntiAlias(true);
        CategoryPlot plot = this.chart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRange(true);
        yAxis.setAutoRangeIncludesZero(false);
    }


    public CategoryDataset createDataset (Stonks[] stonks) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String OPEN = "OPEN";
        String CLOSE = "CLOSE";

        for (Stonks stk : stonks) {
            dataset.addValue(stk.getOpen(), OPEN,stk.getDate().substring(5,10));
        }

        for (Stonks stk : stonks) {
            dataset.addValue(stk.getClose(), CLOSE,stk.getDate().substring(5,10));
        }

        return dataset;
    }

    public void getImage () throws IOException {
        File imgae = new File("chart.png" );
        ChartUtils.saveChartAsPNG(imgae, this.chart, 1920, 1200);
    }

}
