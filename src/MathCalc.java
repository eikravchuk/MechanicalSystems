import javax.swing.ButtonGroup;

public class MathCalc {

	final double g = 9.80665;

	double B(double w, double r, double a, double g, double c, double l0, double m) {
		return (w * w * r * Math.sin(a)) - (g * Math.cos(a)) + (c * l0 / m);
	}

	double kk(double c, double m, double w, double a) {
		return (c / m) - (w * w * Math.sin(a) * Math.sin(a));
	}

	double F(double B, double kk, double x, double w, double a, double r, double f, double y0, double y1) {
		double d0 = Math.pow(2 * w * y1 * Math.sin(a), 2);
		double d1 = Math.pow(w * w * (r + y0 * Math.sin(a)) * Math.cos(a) + g * Math.sin(a), 2);
		double d = Math.signum(y1) * f * Math.sqrt(d0 + d1);
		return B - kk * y0 - d;
	}

	double N1(double y0, double y1, double m, double w, double r, double a, double eps){
		return m * (Math.sin(a) * (2 * w * y1 + eps * y0) + eps * r);
	}

	double N2(double y0, double m, double w, double r, double a){
		return m * w * w * (r + y0 * Math.sin(a)) * Math.cos(a) + m * g * Math.sin(a);
	}

	double[] wEps(double t, double a0, double b0, double c0, ButtonGroup bgroup) {
		double w = 0, eps = 0;
		switch(bgroup.getSelection().getActionCommand()) {
		case "1": w = c0; eps = 0;
			break;
		case "2": w = .001 * b0 + c0; eps = .1 * b0;
			break;
		case "3": w = .0001 * a0 * t * t + b0 * t + c0; eps = .02 * a0 * t + b0;
			break;
		case "4": w = a0 * Math.sin(b0 * t * Math.PI / 180); eps = a0 * b0 * Math.cos(b0 * t * Math.PI / 180);
			break;
		}
		double[] ans = {w, eps};
		return ans;
	}

	double[] nextY2(double h, double B, double kk, double t, double w, double a, double r, double f, double m, double eps, double y0, double y1) {
		double rk1 = h * F(B, kk, t, w, a, r, f, y0, y1);
		double rk2 = h * F(B, kk, t + h / 2, w, a, r, f, y0 + h / 2 * y1, y1 + rk1 / 2);
		double rk3 = h * F(B, kk, t + h / 2, w, a, r, f, y0 + h / 2 * y1 + h / 8 * rk2, y1 + rk2 / 2);
		double rk4 = h * F(B, kk, t + h, w, a, r, f, y0 + h * y1 + h / 2 * rk3, y1 + rk3);

		y0 = y0 + h * (y1 + (rk1 + rk2 + rk3) / 6);
		y1 = y1 + 1. / 6 * (rk1 + 2 * rk2 + 2 * rk3 + rk4);

		double[] ans = {y0, y1, N1(y0, y1, m, w, r, a, eps), N2(y1, m, w, r, a)};
		return ans;
	}

}
