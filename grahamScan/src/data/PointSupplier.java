package data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

import point.Point;

public class PointSupplier {

	private static final Random r = new Random();
	public static List<Point> getRandom(int j) {
		double range = 5;

		List<Point> circle = new ArrayList<>();
		for (int i = 0; i < j; i++) {
			double a = r.nextDouble() * 2 * Math.PI;
			double R = Math.sqrt(r.nextDouble()) * range;
			circle.add(new Point(R * Math.cos(a), R * Math.sin(a)).normized().scaled(r.nextDouble()));
		}

		return circle.stream().distinct().collect(Collectors.toList());
	}

	public static List<Point> getFor(File file) {

		List<Point> readPoints = new ArrayList<Point>();
		try (Scanner s = new Scanner(file)) {
			while (s.hasNextLine()) {
				double x = s.nextDouble();
				double y = s.nextDouble();
				readPoints.add(new Point(x, y));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return readPoints.stream().distinct().collect(Collectors.toList());

	}

}
