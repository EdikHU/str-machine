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

		String jetty_home = System.getProperty("jetty.home", "../.."); // ../

		Server server8902 = new Server(8902);
		WebAppContext webapp = new WebAppContext();
		webapp.setContextPath("/");
		webapp.setWar(jetty_home
				+ "/str-gwt-shower/target/str-gwt-shower-0.1.war");
		server8902.setHandler(webapp);

		Server server8903 = new Server(8903);
		WebAppContext context = new WebAppContext();
		context.setContextPath("/");
		String webappPath = jetty_home
				+ "/str-some-webapp/target/str-some-webapp-0.1";
		// context.setDescriptor(webappPath+"/WEB-INF/web.xml");
		context.setResourceBase(webappPath);
		server8903.setHandler(context);

		Server server = new Server(8901);
		Jetty8901 handler = new Jetty8901();
		server.setHandler(handler);

		server.start();
		server8902.start();
		server8903.start();
		while (!serverStop) {
			Thread.sleep(1234);
		}

		DBUtil.dbClose();
		server8902.stop();
		server8903.stop();
		server.stop();
	}

	public void handle(String target, Request baseRequest, HttpServletRequest request,
			HttpServletResponse resp) throws IOException, ServletException {
		if ("/quit".equals(target)) {
			serverStop = true;
			baseRequest.setHandled(true);
		} else if ("/favicon.ico".equals(target)) {
			baseRequest.setHandled(true);
		} else if ("/showInfo".equals(target)) {
			resp.getWriter().write(DBUtil.showInfo());
			baseRequest.setHandled(true);
		} else {
			RequestInfo requestInfo = getRequestInfoSetCookie(baseRequest);
			saveRequestInfo(requestInfo);
			baseRequest.setHandled(true);
		}
	}

	private void saveRequestInfo(RequestInfo ci) {

		DBUtil.writeCookie(ci.id, ci.cookie, ci.ip, ci.date);

	}

	private RequestInfo getRequestInfoSetCookie(Request baseRequest) {

		RequestInfo reqInfo = new RequestInfo();
		reqInfo.id = baseRequest.getRequestURI();
		reqInfo.ip = baseRequest.getRemoteAddr();
		reqInfo.port = "" + baseRequest.getRemotePort();

		//System.out.println("--> "+baseRequest.getHeader(""));
		
		
		
		
		Cookie cookie = null;
		if (baseRequest.getHeader("Cookie") != null) {
			List<String> cookies = Arrays.asList(baseRequest.getHeader("Cookie")
					.replaceAll("=", "").split("[;][\\s?]"));
			Arrays.asList(baseRequest.getCookies()).iterator();
			for (String cook : cookies) {
				if (cook.contains("s.e.d-"
						+ baseRequest.getRequestURI().replaceAll("/", "") + "-")) {
					cookie = new Cookie(cook, "");
				}
			}
		}

		if (cookie == null) {
			cookie = new Cookie("s.e.d-"
					+ baseRequest.getRequestURI().replaceAll("/", "") + "-"
					+ baseRequest.getRemoteAddr() + "-" + System.currentTimeMillis(), "");
		}

		cookie.setMaxAge(60 * 60 * 24 * 30 * 12);
		baseRequest.getResponse().addCookie(cookie);
		reqInfo.cookie = cookie.getName();

		return reqInfo;
	}

	class RequestInfo {
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
