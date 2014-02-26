package sed.jettys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

public class Some {

	public static void main(String[] args) throws IOException {

		
		new Thread(){
			@Override
			public void run() {
				while (true){
					 
					try {
						BufferedImage bim = ImageIO.read(new File("c:\\11\\ss1.gif"));
						Graphics2D gr = bim.createGraphics();
						int[] arr = new int[48*11];
						 int col = bim.getRGB(21, 80);
						for (int i = 0; i < arr.length; i++) {
							arr[i] = col;
						}
						
						bim.setRGB(21, 80, 48, 11, arr, 0, 48);
						gr.setColor(new Color(0,0,0));
						gr.setFont(new Font("SanSerif", Font.PLAIN, 9));
						String c = (""+Calendar.getInstance().getTime()).replaceAll("\\w{3}\\s{1}\\w{3}\\s{1}\\d{1,2}\\s{1}(\\d{2}:\\d{2}:\\d{2}).+", "$1");
						gr.drawString("[ "+c+" ]", 21, 88); 

						ImageIO.write(bim, "gif", new File("c:\\11\\ss1.gif"));
						
						Thread.sleep(1234);
					} catch (IOException e) {
					} catch (InterruptedException e) {
					}
				}
			}
		}.start();
	}

}
