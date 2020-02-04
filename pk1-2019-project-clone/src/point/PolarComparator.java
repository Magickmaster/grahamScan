package point;
import java.util.Comparator;

public class PolarComparator implements Comparator<Point> {

	private final Point origin;

	public PolarComparator(Point origin) {
		this.origin = origin;
	}

	@Override
	public int compare(Point a, Point b) {
		Point vA = origin.to(a);
		Point vB = origin.to(b);
		double phiA = vA.originAngle();
		double phiB = vB.originAngle();
		return Double.compare(phiA, phiB);
	}

}
