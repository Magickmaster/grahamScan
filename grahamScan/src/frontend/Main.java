package frontend;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.Axis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import utility.Async;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(Main.class);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setWidth(720);
		primaryStage.setHeight(480);
		BorderPane pane = new BorderPane();

		RadioButton fillRepeat = new RadioButton("Fill Repeatedly");
		Button selectFile = new Button("Select file");
		FileChooser chooser = new FileChooser();
		TextField pointCnt = new TextField("500");

		Slider slider = new Slider(1, 10, 2);
		slider.setBlockIncrement(1);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setShowTickLabels(true);
		slider.setSnapToTicks(true);

		ChartController.sleepTime.bind(slider.valueProperty());

		pointCnt.setOnAction(e -> ChartController.pointCount.set(Integer.parseInt(pointCnt.getText())));
		Axis<Number> x = new NumberAxis();
		Axis<Number> y = new NumberAxis();
		x.setLabel("x");
		y.setLabel("y");
		ScatterChart<Number, Number> chart = new ScatterChart<>(x, y);

		selectFile.setOnAction(a -> {
			ChartController.fillDataFrom(chooser.showOpenDialog(primaryStage), chart);
			fillRepeat.setSelected(false);
		});

		fillRepeat.setOnAction(e -> {
			if (fillRepeat.isSelected()) {
				System.out.println("Starting random fill");
				Async.executor.submit(() -> ChartController.fillRepeatedly(chart));
			} else {

				System.out.println("No fill repeat");
			}
		});
		ChartController.active.bind(fillRepeat.selectedProperty());

		pane.setTop(new HBox(10, selectFile, fillRepeat, pointCnt, slider));
		pane.setCenter(chart);
		primaryStage.setScene(new Scene(pane));
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> Async.executor.shutdown());
	}

}
