package sed.jettys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.imageio.ImageIO;

public class Some {

	private static final int THE_X = 5;//21;
	private static final int THE_Y = 5;//80;

	public static void main(String[] args) throws IOException {

		
		new Thread(){
			@Override
			public void run() {
				while (true){
					 
					try {
						Thread.sleep(1234);
						
						BufferedImage bim = ImageIO.read(new File("c:\\11\\ss1.gif"));
						Graphics2D gr = bim.createGraphics();
						int[] arr = new int[48*11];
						 int col = bim.getRGB(THE_X, THE_Y);
						for (int i = 0; i < arr.length; i++) {
							arr[i] = col; 
						}
						
						bim.setRGB(THE_X, THE_Y, 48, 11, arr, 0, 48);
						gr.setColor(new Color(0,0,0));
						gr.setFont(new Font("SanSerif", Font.PLAIN, 9));
						String c = (""+Calendar.getInstance().getTime()).replaceAll("\\w{3}\\s{1}\\w{3}\\s{1}\\d{1,2}\\s{1}(\\d{2}:\\d{2}:\\d{2}).+", "$1");
						gr.drawString("[ "+c+" ]", THE_X, THE_Y+8); 

						ImageIO.write(bim, "gif", new File("c:\\11\\ss1.gif"));
						
						
						
					} catch (IOException e) {
						e.printStackTrace();
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
	}

}
