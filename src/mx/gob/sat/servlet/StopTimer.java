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
 * Servlet implementation class StopTimer
 */
public class StopTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StopTimer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter wr = response.getWriter();
		wr.print(this.getClass().toString()+"Cancelando timer");
		this.call();
		wr.print(this.getClass().toString()+"Fin de cancelacion ");
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
		
			
			timerBean.cancelMyTimer();
			System.out.println(this.getClass().toString()+":cancelMyTimer ONE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
