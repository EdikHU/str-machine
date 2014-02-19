/**
 * 
 */
package sed.jettys;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.webapp.WebAppContext;

import sed.DBUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author edik
 */
public class Jetty8901 extends AbstractHandler {

	private static boolean serverStop = false;

	public static void main(String[] args) throws Exception {



    	String jetty_home = System.getProperty("jetty.home","../.."); //   ../
    	 
        Server server8902 = new Server(8902);
 
        WebAppContext webapp = new WebAppContext();
        webapp.setContextPath("/");
        webapp.setWar(jetty_home+"/str-gwt-shower/target/str-gwt-shower-0.1.war");
        server8902.setHandler(webapp);


		Server server = new Server(8901);
		Jetty8901 handler = new Jetty8901();
		server.setHandler(handler);
		server.start();
		server8902.start();
		while (!serverStop) {
			Thread.sleep(1234);
		}
		DBUtil.dbClose();
		server8902.stop();
		server.stop();
	}

	public void handle(String target, Request br, HttpServletRequest req,
			HttpServletResponse resp) throws IOException, ServletException {
		if ("/quit".equals(target)) {
			serverStop = true;
			br.setHandled(true);
		} else if ("/favicon.ico".equals(target)) {
			br.setHandled(true);
		} else if ("/showInfo".equals(target)) {
			resp.getWriter().write(DBUtil.showInfo());
			br.setHandled(true);
		} else {
			CookieInfo cookieInfo = getSetCookie(br);
			saveCookieInfo(cookieInfo);
			br.setHandled(true);
		}
	}

	private void saveCookieInfo(CookieInfo ci) {

		DBUtil.writeCookie(ci.id,ci.cookie,ci.ip,ci.date);

	}

	private CookieInfo getSetCookie(Request br) {

		CookieInfo ci = new CookieInfo();
		ci.id = br.getRequestURI();
		ci.ip = br.getRemoteAddr();
		ci.port = "" + br.getRemotePort();

		Cookie cookie = null;
		if (br.getHeader("Cookie") != null) {
			List<String> cookies = Arrays.asList(br.getHeader("Cookie")
					.replaceAll("=", "").split("[;][\\s?]"));
			Arrays.asList(br.getCookies()).iterator();
			for (String cook : cookies) {
				if (cook.contains("s.e.d-"
						+ br.getRequestURI().replaceAll("/", "") + "-")) {
					cookie = new Cookie(cook, "");
				}
			}
		}

		if (cookie == null) {
			cookie = new Cookie("s.e.d-"
					+ br.getRequestURI().replaceAll("/", "") + "-"
					+ br.getRemoteAddr() + "-" + System.currentTimeMillis(), "");
		}

		cookie.setMaxAge(60 * 60 * 24 * 30 * 12);
		br.getResponse().addCookie(cookie);
		ci.cookie = cookie.getName();

		return ci;
	}

	class CookieInfo {
		String id;
		String ip;
		String port;
		String cookie;
		String date = "" + new Date();

		@Override
		public String toString() {
			return "CookieInfo [id=" + id + ", ip=" + ip + ", port=" + port
					+ ", cookie=" + cookie + ", date=" + date + "]";
		}

	}

}
