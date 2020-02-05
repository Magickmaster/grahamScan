package point;

import javafx.scene.chart.XYChart.Data;

public class Point implements Comparable<Point> {
	public final double x, y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Data<Number, Number> getData() {
		return new Data<>(x, y);
	}

	public static Data<Number, Number> toData(Point p) {
		return p.getData();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Point) {
			Point p = (Point) obj;
			return p.x == this.x && p.y == this.y;
		}
		return super.equals(obj);
	}

	public double originMagnitude() {
		return x * x + y * y;
	}

	public double originDistance() {
		return Math.sqrt(originMagnitude());
	}

	public double distanceMagnitude(Point other) {
		return (x - other.x) * (x - other.x) + (y - other.y) * (y - other.y);
	}

	public double distanceTo(Point other) {
		return Math.sqrt(distanceMagnitude(other));
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	@Override
	public int compareTo(Point other) {
		if (this.equals(other))
			return 0;
		if (this.y < other.y)
			return -1;
		return 1;
	}

	public Point to(Point b) {
		return new Point(b.x - this.x, b.y - this.y);
	}

	public Point normized() {
		double len = originDistance();
		return new Point(this.x / len, this.y / len);
	}

	public double originAngle() {
		if (this.originMagnitude() > 0)
			return Math.acos(this.normized().x) * (this.y < 0 ? -1 : 1);
		else
			return 0;
	}

	public Point scaled(double a) {
		return new Point(a * x, a * y);
	}
}
