package de.svenwillrich.htwcalendarapi.war.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.svenwillrich.htwcalendarapi.api.data.Data;
import de.svenwillrich.htwcalendarapi.war.logik.Option;
import de.svenwillrich.htwcalendarapi.war.logik.OptionController;
import de.svenwillrich.htwcalendarapi.war.logik.ReadCalOption;

public class APIServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Data.FILE = getServletContext().getRealPath("/WEB-INF/classes/apidata");
		OptionController oC = new OptionController();
		resp.setContentType("text/plain; charset=UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter w = resp.getWriter();
		String strOption = req.getParameter("option");

		if (strOption == null
				|| !oC.getOptionMap().containsKey(strOption.toUpperCase())) {
			strOption = ReadCalOption.ID.toUpperCase();
		}

		String result = null;

		try {
			Option option = oC.getOptionMap().get(strOption.toUpperCase());
			result = option.work(req.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}

		w.write(result);
		w.close();
	}
}