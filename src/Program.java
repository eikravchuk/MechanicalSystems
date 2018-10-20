import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Program {

	MathCalc mathCalc = new MathCalc();
	JFrame guiFrame;
	JPanel pnl;
	JPanel pnlA, pnlW, pnlM, pnlC, pnlL0, pnlR, pnlX0, pnlXD0, pnlF, pnlRBtns, pnlABC;
	JRadioButton ex1, ex2, ex3, ex4;
	ButtonGroup bgroup;
	JSlider sldrA, sldrW, sldrM, sldrC, sldrL0, sldrR, sldrX0, sldrXD0, sldrF, sldrA0, sldrB0, sldrC0;
	JLabel chA, chW, chM, chC, chL0, chR, chX0, chXD0, chF, chA0, chB0, chC0;
	Hashtable<Integer, JLabel> lblA, lblW, lblM, lblC, lblL0, lblR, lblX0, lblXD0, lblF;
	XYChart chart;
	SwingWrapper<XYChart> sw;
	XYSeries srs1, srs2, srs3, srs4;
	final double g = 9.80665;

	public static void main(String[] args) { new Program(); }

	public Program() {

		guiFrame = new JFrame();
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("Lab 5");
		guiFrame.setSize(1000,250);
		guiFrame.setLayout(new BorderLayout());
		guiFrame.setLocationRelativeTo(null);

		pnl = new JPanel();	pnl.setLayout(new GridLayout(3, 4));
		pnlA = new JPanel();	pnlA.setLayout(new GridLayout(2, 1));
		pnlM = new JPanel();	pnlM.setLayout(new GridLayout(2, 1));
		pnlC = new JPanel();	pnlC.setLayout(new GridLayout(2, 1));
		pnlL0 = new JPanel();	pnlL0.setLayout(new GridLayout(2, 1));
		pnlR = new JPanel();	pnlR.setLayout(new GridLayout(2, 1));
		pnlX0 = new JPanel();	pnlX0.setLayout(new GridLayout(2, 1));
		pnlXD0 = new JPanel();	pnlXD0.setLayout(new GridLayout(2, 1));
		pnlF = new JPanel();	pnlF.setLayout(new GridLayout(2, 1));
		pnlRBtns = new JPanel();	pnlRBtns.setLayout(new GridLayout(4, 1));
		pnlABC = new JPanel();	pnlABC.setLayout(new GridLayout(3, 2));
		guiFrame.add(pnl, BorderLayout.CENTER);

		ex1 = new JRadioButton("w = c");
		ex1.setMnemonic(KeyEvent.VK_1);
		ex1.setActionCommand("1");
		ex1.setSelected(true);

		ex2 = new JRadioButton("w = bt + c");
		ex2.setMnemonic(KeyEvent.VK_2);
		ex2.setActionCommand("2");

		ex3 = new JRadioButton("w = at^2 + bt + c");
		ex3.setMnemonic(KeyEvent.VK_3);
		ex3.setActionCommand("3");

		ex4 = new JRadioButton("w = asin(bt)");
		ex4.setMnemonic(KeyEvent.VK_4);
		ex4.setActionCommand("4");

		//Group the radio buttons.
		bgroup = new ButtonGroup();
		bgroup.add(ex1);
		bgroup.add(ex2);
		bgroup.add(ex3);
		bgroup.add(ex4);

		// Градусы
		chA = new JLabel("Угол наклона");
		sldrA = new JSlider(JSlider.HORIZONTAL, 0, 90, 30);
		lblA = new Hashtable<Integer, JLabel>();
		lblA.put(new Integer(0), new JLabel("0"));
		lblA.put(new Integer(90),new JLabel("90"));
		sldrA.setLabelTable(lblA);
		sldrA.setPaintLabels(true);

		// Масса * 10 000
		chM = new JLabel("Масса");
		sldrM = new JSlider(JSlider.HORIZONTAL, 10, 1000, 100);
		lblM = new Hashtable<Integer, JLabel>();
		lblM.put(new Integer(10), new JLabel("0"));
		lblM.put(new Integer(1000),new JLabel("0.1"));
		sldrM.setLabelTable(lblM);
		sldrM.setPaintLabels(true);

		// Коэффициент жесткости * 10 000
		chC = new JLabel("Коэффициент жесткости пружины (Н/м)");
		sldrC = new JSlider(JSlider.HORIZONTAL, 10, 100000, 10000);
		lblC = new Hashtable<Integer, JLabel>();
		lblC.put(new Integer(10), new JLabel("0"));
		lblC.put(new Integer(100000),new JLabel("10"));
		sldrC.setLabelTable(lblC);
		sldrC.setPaintLabels(true);

		// Длина недеформированой пружины * 100
		chL0 = new JLabel("Длина недеформированной пружины (м)");
		sldrL0 = new JSlider(JSlider.HORIZONTAL, 1, 100, 20);
		lblL0 = new Hashtable<Integer, JLabel>();
		lblL0.put(new Integer(1), new JLabel("0"));
		lblL0.put(new Integer(100),new JLabel("1"));
		sldrL0.setLabelTable(lblL0);
		sldrL0.setPaintLabels(true);

		// Размер * 100
		chR = new JLabel("Размер (м)");
		sldrR = new JSlider(JSlider.HORIZONTAL, 1, 100, 20);
		lblR = new Hashtable<Integer, JLabel>();
		lblR.put(new Integer(1), new JLabel("0"));
		lblR.put(new Integer(100),new JLabel("1"));
		sldrR.setLabelTable(lblR);
		sldrR.setPaintLabels(true);

		// Начальная координата * 100
		chX0 = new JLabel("Начальная координата (м)");
		sldrX0 = new JSlider(JSlider.HORIZONTAL, 1, 100, 30);
		lblX0 = new Hashtable<Integer, JLabel>();
		lblX0.put(new Integer(1), new JLabel("0"));
		lblX0.put(new Integer(100),new JLabel("1"));
		sldrX0.setLabelTable(lblX0);
		sldrX0.setPaintLabels(true);

		// Скорость * 100
		chXD0 = new JLabel("Скорость (м/сек)");
		sldrXD0 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 200);
		lblXD0 = new Hashtable<Integer, JLabel>();
		lblXD0.put(new Integer(1), new JLabel("0"));
		lblXD0.put(new Integer(1000),new JLabel("10"));
		sldrXD0.setLabelTable(lblXD0);
		sldrXD0.setPaintLabels(true);

		// Сила трения * 100
		chF = new JLabel("Сила трения");
		sldrF = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
		lblF = new Hashtable<Integer, JLabel>();
		lblF.put(new Integer(1), new JLabel("0"));
		lblF.put(new Integer(100),new JLabel("10"));
		sldrF.setLabelTable(lblF);
		sldrF.setPaintLabels(true);

		// A0 * 10
		chA0 = new JLabel("a: ");
		sldrA0 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 130);

		// B0 * 10
		chB0 = new JLabel("b: ");
		sldrB0 = new JSlider(JSlider.HORIZONTAL, 1, 100, 40);

		// C0 * 100
		chC0 = new JLabel("c: ");
		sldrC0 = new JSlider(JSlider.HORIZONTAL, 1, 1000, 50);

		double a = sldrA.getValue();
		double m = sldrM.getValue() / 10000.;
		double c = sldrC.getValue() / 10000.;
		double l0 = sldrL0.getValue() / 100.;
		double r = sldrR.getValue() / 100.;
		double x0 = sldrX0.getValue() / 100.;
		double xd0 = sldrXD0.getValue() / 100.;
		double f = sldrF.getValue() / 100.;
		double a0 = sldrA0.getValue() / 10.;
		double b0 = sldrB0.getValue() / 10.;
		double c0 = sldrC0.getValue() / 100.;
		System.out.print(a + "	" + m + "	" + c + "	" + l0 + "	" + r + "	" + x0 + "	" + xd0 + "	" + f + "	" + a0 + "	" + b0 + "	" + c0 + "\n");

		double[] testT = new double[1000]; testT[0] = .0;
		for(int i = 1; i < testT.length; i++) {
			testT[i] = testT[i-1] + .01;
		}
		double[] warr = new double[1000];
		for(int i = 0; i < warr.length; i++) {
			warr[i] = mathCalc.wEps(testT[i], a0, b0, c0, bgroup)[0];
		}
		double[] epsarr = new double[1000];
		for(int i = 0; i < epsarr.length; i++) {
			epsarr[i] = mathCalc.wEps(testT[i], a0, b0, c0, bgroup)[1];
		}

		double h = .01;

		double[][] testX = new double[1000][1000];
		testX[0][0] = x0;
		testX[1][0] = xd0;
		for(int i = 1; i < testX.length; i++) {
			double B = mathCalc.B(warr[i-1], r, a, g, c, l0, m);
			double kk = mathCalc.kk(c, m, warr[i-1], a);
			testX[0][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[0];
			testX[1][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[1];
			testX[2][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[2];
			testX[3][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[3];
		}

		double[] N = new double[1000];
		for(int i = 0; i < N.length; i++) {
			N[i] = Math.sqrt(testX[2][i] * testX[2][i] + testX[3][i] * testX[3][i]);
		}
		chart = new XYChart(1000, 500);
		chart.setXAxisTitle("Ось t");
		chart.setYAxisTitle("Ось x");
		srs1 = chart.addSeries("lab 5", testT, testX[0]);
		srs1.setMarker(SeriesMarkers.NONE);
		srs1.setLineColor(Color.BLUE);
		srs2 = chart.addSeries("N1", testT, testX[2]);
		srs2.setMarker(SeriesMarkers.NONE);
		srs2.setLineColor(Color.RED);
		srs3 = chart.addSeries("N2", testT, testX[3]);
		srs3.setMarker(SeriesMarkers.NONE);
		srs3.setLineColor(Color.BLACK);
		srs4 = chart.addSeries("N", testT, N);
		srs4.setMarker(SeriesMarkers.NONE);
		srs4.setLineColor(Color.GREEN);
		sw = new SwingWrapper<XYChart>(chart);
		sw.displayChart("Моделирование сложного движения МТ"); 

		sldrA.addChangeListener(e -> stateChanged());
		sldrM.addChangeListener(e -> stateChanged());
		sldrC.addChangeListener(e -> stateChanged());
		sldrL0.addChangeListener(e -> stateChanged());
		sldrR.addChangeListener(e -> stateChanged());
		sldrX0.addChangeListener(e -> stateChanged());
		sldrXD0.addChangeListener(e -> stateChanged());
		sldrF.addChangeListener(e -> stateChanged());
		ex1.addActionListener(e -> stateChanged());
		ex2.addActionListener(e -> stateChanged());
		ex3.addActionListener(e -> stateChanged());
		ex4.addActionListener(e -> stateChanged());
		sldrA0.addChangeListener(e -> stateChanged());
		sldrB0.addChangeListener(e -> stateChanged());
		sldrC0.addChangeListener(e -> stateChanged());

		pnlA.add(chA);	pnlA.add(sldrA);	pnl.add(pnlA);
		pnlM.add(chM);	pnlM.add(sldrM);	pnl.add(pnlM);
		pnlC.add(chC);	pnlC.add(sldrC);	pnl.add(pnlC);
		pnlL0.add(chL0);	pnlL0.add(sldrL0);	pnl.add(pnlL0);
		pnlR.add(chR);	pnlR.add(sldrR);	pnl.add(pnlR);
		pnlX0.add(chX0);	pnlX0.add(sldrX0);	pnl.add(pnlX0);
		pnlXD0.add(chXD0);	pnlXD0.add(sldrXD0);	pnl.add(pnlXD0);
		pnlF.add(chF);	pnlF.add(sldrF);	pnl.add(pnlF);
		pnlRBtns.add(ex1);	pnlRBtns.add(ex2);	pnlRBtns.add(ex3);	pnlRBtns.add(ex4);	pnl.add(pnlRBtns);
		pnlABC.add(chA0);	pnlABC.add(sldrA0);	pnlABC.add(chB0);	pnlABC.add(sldrB0); pnlABC.add(chC0);	pnlABC.add(sldrC0);	pnl.add(pnlABC);

		guiFrame.setVisible(true);

	}

	public void stateChanged() {
		double a = sldrA.getValue();
		double m = sldrM.getValue() / 10000.;
		double c = sldrC.getValue() / 10000.;
		double l0 = sldrL0.getValue() / 100.;
		double r = sldrR.getValue() / 100.;
		double x0 = sldrX0.getValue() / 100.;
		double xd0 = sldrXD0.getValue() / 100.;
		double f = sldrF.getValue() / 100.;
		double a0 = sldrA0.getValue() / 10.;
		double b0 = sldrB0.getValue() / 10.;
		double c0 = sldrC0.getValue() / 100.;
		System.out.print(a + "	" + m + "	" + c + "	" + l0 + "	" + r + "	" + x0 + "	" + xd0 + "	" + f + "	" + a0 + "	" + b0 + "	" + c0 + "\n");

		double[] testT = new double[1000]; testT[0] = .0;
		for(int i = 1; i < testT.length; i++) {
			testT[i] = testT[i-1] + .01;
		}
		double[] warr = new double[1000];
		for(int i = 0; i < warr.length; i++) {
			warr[i] = mathCalc.wEps(testT[i], a0, b0, c0, bgroup)[0];
		}
		double[] epsarr = new double[1000];
		for(int i = 0; i < epsarr.length; i++) {
			epsarr[i] = mathCalc.wEps(testT[i], a0, b0, c0, bgroup)[1];
		}

		double h = .01;

		double[][] testX = new double[1000][1000];
		testX[0][0] = x0;
		testX[1][0] = xd0;
		for(int i = 1; i < testX.length; i++) {
			double B = mathCalc.B(warr[i-1], r, a, g, c, l0, m);
			double kk = mathCalc.kk(c, m, warr[i-1], a);
			testX[0][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[0];
			testX[1][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[1];
			testX[2][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[2];
			testX[3][i] = mathCalc.nextY2(h, B, kk, testT[i-1], warr[i-1], a, r, f, m, epsarr[i-1], testX[0][i-1], testX[1][i-1])[3];
		}

		double[] N = new double[1000];
		for(int i = 0; i < N.length; i++) {
			N[i] = Math.sqrt(testX[2][i] * testX[2][i] + testX[3][i] * testX[3][i]);
		}
		chart.updateXYSeries("lab 5", testT, testX[0], null);
		chart.updateXYSeries("N1", testT, testX[2], null);
		chart.updateXYSeries("N2", testT, testX[3], null);
		chart.updateXYSeries("N", testT, N, null);
		sw.repaintChart();

	}

}
