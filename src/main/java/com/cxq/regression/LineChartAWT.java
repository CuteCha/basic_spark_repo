package com.cxq.regression;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

/**
 * Created by cxq on 2018/9/12.
 */
public class LineChartAWT extends ApplicationFrame {
    public LineChartAWT(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        XYSeriesCollection dataset = genDataset();
        JFreeChart chart = ChartFactory.createXYLineChart(
                chartTitle,//"y=x^2 && y=x^2+1"
                "x",
                "y",
                dataset, // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        ChartPanel chartPanel = new ChartPanel( chart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        final XYPlot plot = chart.getXYPlot( );

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED );
        renderer.setSeriesPaint( 1 , Color.GREEN );
//        renderer.setSeriesPaint( 2 , Color.YELLOW );
        renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
        renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
//        renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
        plot.setRenderer( renderer );
        setContentPane( chartPanel );

//        XYPlot plot = chart.getXYPlot();
//        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesPaint(1, Color.GREEN);
//
//        renderer.setSeriesStroke(0, new BasicStroke(4.0f));
//        renderer.setSeriesStroke(1, new BasicStroke(3.0f));
//
//        plot.setOutlinePaint(Color.BLUE);
//        plot.setOutlineStroke(new BasicStroke(2.0f));
//
//        plot.setRenderer(renderer);
//        plot.setBackgroundPaint(Color.DARK_GRAY);
//
//        plot.setRangeGridlinesVisible(true);
//        plot.setRangeGridlinePaint(Color.BLACK);
//
//        plot.setDomainGridlinesVisible(true);
//        plot.setDomainGridlinePaint(Color.BLACK);
    }

    private XYSeriesCollection genDataset() {
        final XYSeriesCollection dataset = new XYSeriesCollection();
        final XYSeries series1 = new XYSeries("xySeries1");
        double x = -10.0;
        while (x <= 10.0) {
            double y = x * x;
            series1.add(x, y);
            x += 0.1;
        }
        final XYSeries series2 = new XYSeries("xySeries2");
        x = -10.0;
        while (x <= 10.0) {
            double y = x * x + 10;
            series2.add(x, y);
            x += 0.1;
        }
        dataset.addSeries(series1);
        dataset.addSeries(series2);

        return dataset;
    }

    public static void main(String[] args) {
        LineChartAWT chart = new LineChartAWT("test","y=x^2 && y=x^2+1");
        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}
