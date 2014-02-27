package sed.jettys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.imageio.ImageIO;

// need preparing ^ converting BI for place to buffer as rgb 

public class Some {

	private static final int THE_X = 5;// 21;
	private static final int THE_Y = 5;// 80;

	static class LC {

		protected static int r;
		protected static int g;
		protected static int b;
		private static Properties prop;

		public static void get() throws FileNotFoundException, IOException {
			prop = new Properties();
			prop.load(new FileReader("c:\\11\\color.prop"));
			r = getInt("r");
			g = getInt("g");
			b = getInt("b");
		}

		private static int getInt(String clr) {
			try {
				int num = Integer.parseInt(prop.getProperty(clr, "0"));
				return (num > 255 ? 255 : num);
			} catch (NumberFormatException e) {
				return 0;
			}
		}

	}

	public static void main(String[] args) throws IOException {

		new Thread() {
			@Override
			public void run() {
				BufferedImage bim = null;
				while (true) {
					try {
						Thread.sleep(1234);
						if (bim == null) {
							BufferedImage inp = ImageIO.read(new File(
									"c:\\11\\ss1.gif"));
							bim = new BufferedImage(inp.getWidth(),
									inp.getHeight(), BufferedImage.TYPE_INT_RGB);
							bim.createGraphics().drawImage(inp, null, 0, 0);
							System.out.println("uno");
						}
						Graphics2D gr = bim.createGraphics();
						int[] arr = new int[48 * 11];
						int col = bim.getRGB(THE_X, THE_Y);
						for (int i = 0; i < arr.length; i++) {
							arr[i] = col;
						}

						bim.setRGB(THE_X, THE_Y, 48, 11, arr, 0, 48);
						// gr.setColor(new Color(0, 0, 0));

						LC.get();
						gr.setColor(new Color(LC.r, LC.g, LC.b));

						gr.setFont(new Font("SanSerif", Font.PLAIN, 9));
						String c = ("" + Calendar.getInstance().getTime())
								.replaceAll(
										"\\w{3}\\s{1}\\w{3}\\s{1}\\d{1,2}\\s{1}(\\d{2}:\\d{2}:\\d{2}).+",
										"$1");
						gr.drawString("[ " + c + " ]", THE_X, THE_Y + 8);

						// bim.setRGB(0, 0, tmpPic);

						ImageIO.write(bim, "gif", new File("c:\\11\\ss1.gif"));

						//System.out.println("fin");
					} catch (IOException e) {
						BufferedImage inp;
						try {
							inp = ImageIO.read(new File(
									"c:\\11\\ss.gif"));
							bim = new BufferedImage(inp.getWidth(),
									inp.getHeight(), BufferedImage.TYPE_INT_RGB);
							bim.createGraphics().drawImage(inp, null, 0, 0);
						} catch (IOException e1) {
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (NullPointerException e) {
						e.printStackTrace();
					} catch (ArrayIndexOutOfBoundsException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {

			@Override
			public void run() {

				try {
					BufferedImage in = ImageIO.read(new File("c:\\11\\ss.gif"));
					BufferedImage out = new BufferedImage(in.getWidth(),
							in.getHeight(), BufferedImage.TYPE_INT_RGB);
					Graphics2D gr = out.createGraphics();
					// gr.drawImage(in, null, 0, 0);
					// gr.setColor(Color.RED);
					// gr.drawLine(10, 10, 80, 80);

					gr.drawImage(in, null, 0, 0);

					gr.setColor(Color.RED);
					gr.drawLine(0, 0, 0, 0);
					gr.setColor(Color.yellow);
					gr.drawLine(0, 0, 0, 0);

					// out.setRGB(0, 0, tmpPic);

					System.out.println("[" + out.getWidth() + "]["
							+ out.getHeight() + "]");

					ImageIO.write(out, "gif", new File("c:\\11\\ss0.gif"));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};// .start();

	}

}
