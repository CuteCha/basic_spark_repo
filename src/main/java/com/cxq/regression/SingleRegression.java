package com.cxq.regression;

import org.deeplearning4j.datasets.iterator.impl.ListDataSetIterator;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.joda.convert.ToString;
import org.junit.Test;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by cxq on 2018/9/12.
 */
public class SingleRegression {
    private static final int seed = 12345;
    private static final int nEpochs = 20;
    private static final int nSamples = 1000;
    private static final int batchSize = 100;
    private static final double learningRate = 0.01;
    private static int MIN_RANGE = 0;
    private static int MAX_RANGE = 3;

    private static final Random rng = new Random(seed);

    public static void main(String[] args) {
        linearRegression();
        testJfree2();
    }

    private static void linearRegression() {
        int numInput = 1;
        int numOutputs = 1;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .weightInit(WeightInit.XAVIER)
                .updater(new Sgd(learningRate))
                .list()
                .layer(0, new OutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(numInput)
                        .nOut(numOutputs).build())
                .pretrain(false)
                .backprop(true).build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();

        System.out.println(net.summary());

//        net.setListeners(new ScoreIterationListener(1));

        DataSetIterator iterator = getTrainingData(batchSize, rng);


        for (int i = 0; i < nEpochs; i++) {
            iterator.reset();
            net.fit(iterator);

            Map<String, INDArray> params = net.paramTable();
            params.forEach((key, value) -> System.out.println("key:" + key + ", value = " + value));

        }
        final INDArray input = Nd4j.create(new double[]{10, 100}, new int[]{2, 1});
        INDArray out = net.output(input, false);
        System.out.println(out);
    }

    private static DataSetIterator getTrainingData(int batchSize, Random rand) {
        double[] output = new double[nSamples];
        double[] input = new double[nSamples];
        for (int i = 0; i < nSamples; i++) {
            input[i] = MIN_RANGE + (MAX_RANGE - MIN_RANGE) * rand.nextDouble();

            output[i] = 0.5 * input[i] + 0.1;
        }

        INDArray inputNDArray = Nd4j.create(input, new int[]{nSamples, 1});

        INDArray outPut = Nd4j.create(output, new int[]{nSamples, 1});

        DataSet dataSet = new DataSet(inputNDArray, outPut);
        List<DataSet> listDs = dataSet.asList();

        return new ListDataSetIterator(listDs, batchSize);
    }

    public static void testJfree() {
        XYSeries series = new XYSeries("xySeries");
        for (int x = -100; x < 100; x++) {
            int y = x * x;
            series.add(x, y);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "y = x^2",
                "x",
                "y",
                dataset, // data
                PlotOrientation.VERTICAL,
                false,
                false,
                false
        );


        ChartFrame frame = new ChartFrame("picture", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void testJfree2() {
        XYSeries series1 = new XYSeries("xx");
        double x = -10.0;
        while (x <= 10.0) {
            double y = x * x;
            series1.add(x, y);
            x += 0.5;
        }
        XYSeries series2 = new XYSeries("xx+10");
        x = -10.0;
        while (x <= 10.0) {
            double y = x * x + 10;
            series2.add(x, y);
            x += 0.5;
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "y=x^2 && y=x^2+10",
                "x",
                "y",
                dataset, // data
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.GREEN);

        renderer.setSeriesStroke(0, new BasicStroke(1.0f));
        renderer.setSeriesStroke(1, new BasicStroke(1.0f));

        plot.setOutlinePaint(Color.BLUE);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.WHITE);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        ChartFrame frame = new ChartFrame("picture", chart);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
