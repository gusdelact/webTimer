package mx.gob.sat.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sat.ejb.TimerHome;
import mx.gob.sat.ejb.TimerRemote;

/**
 * Servlet implementation class LaunchTimer
 */
public class LaunchTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LaunchTimer() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter wr = response.getWriter();
		wr.print(this.getClass().toString()+"Arrancando timer");
		this.call();
		wr.print(this.getClass().toString()+"Fin de arranque ");
		wr.flush();
		wr.close();
		
	}

	 

	private Context getInitialContext() throws Exception {
		
		return new InitialContext();
	}

	private void call() {
		try {
			Context ctx = getInitialContext();
			TimerRemote timerBean = null;
			TimerHome tHome = (TimerHome) ctx.lookup("mx.gob.sat.ejb.TimerEjbjndi");
			timerBean = tHome.create();
			timerBean.createMyTimer();
			Thread.sleep(10000);
			timerBean.getTimerHandleForTimer();
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
