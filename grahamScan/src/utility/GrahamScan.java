package utility;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import point.Point;
import point.PolarComparator;

public class GrahamScan {

	private GrahamScan() {
		throw new IllegalStateException("Utility Class");
	}

	public static List<Point> generateConvexHull(List<Point> points) {
		points = points.stream().collect(Collectors.toList());
		if (points.size() < 3) {
			System.out.println("Not enough points to form hull, ABORT");
			return points;
		}

		Point origin = Collections.min(points);
		System.out.println("Sorting");
		Collections.sort(points, new PolarComparator(origin));
		System.out.println("Iterating");

		for (int i = 0; i < points.size();) {
			int prevP = (i == 0) ? points.size() - 1 : i - 1;
			int currP = i;
			int nextP = (i == points.size() - 1) ? 0 : i + 1;

			Point prev = points.get(prevP);
			Point current = points.get(currP);
			Point next = points.get(nextP);

			if (isCollinear(prev, next, current)) {
				if (prev.distanceMagnitude(next) < prev.distanceMagnitude(current)) {
					points.remove(next);
				} else {
					points.remove(current);
				}

			} else if (isRight(prev, next, current)) {
				i++;
			} else {
				points.remove(current);
				i--;
			}
		}

		System.out.println("Solved");
		return points;
	}

	private static boolean isCollinear(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y) == 0;
	}

	private static boolean isRight(Point a, Point b, Point c) {
		return (b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.y) < 0;
	}
}
