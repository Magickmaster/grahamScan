package frontend;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import data.PointSupplier;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart.Series;
import point.Point;
import utility.Async;
import utility.GrahamScan;

public class ChartController {

	static BooleanProperty active = new SimpleBooleanProperty(false);
	static IntegerProperty pointCount = new SimpleIntegerProperty(500);
	static IntegerProperty sleepTime = new SimpleIntegerProperty(2);

	public static void fillRepeatedly(ScatterChart<Number, Number> chart) {
		while (active.get()) {
			System.out.println("Showing new");
			ObservableList<Point> points = FXCollections.observableArrayList(PointSupplier.getRandom(pointCount.get()));
			Async.executor.submit(() -> fillData(points, chart));
			try {
				Thread.sleep(sleepTime.get() * 500L);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	public static void fillData(List<Point> points, ScatterChart<Number, Number> chart) {
		System.out.println("Reading data");

		ObservableList<Series<Number, Number>> newData = FXCollections.observableArrayList();
		Platform.runLater(() -> chart.setData(newData));

		Platform.runLater(() -> {
			Series<Number, Number> series = new Series<Number, Number>();
			series.getData().addAll(points.stream().map(Point::toData).collect(Collectors.toList()));
			series.setName("Points");
			newData.add(series);
		});

		System.out.println("Calculating");
		List<Point> results = GrahamScan.generateConvexHull(points);
		System.out.println("Displaying");

		Platform.runLater(() -> {
			Series<Number, Number> hull = new Series<Number, Number>();
			hull.setName("Hull");
			hull.getData().addAll(results.stream().map(Point::toData).collect(Collectors.toList()));
			newData.add(hull);
		});

	}

	public static void fillDataFrom(File showOpenDialog, ScatterChart<Number, Number> chart) {
		if (showOpenDialog != null)
			fillData(PointSupplier.getFor(showOpenDialog), chart);
	}

}
