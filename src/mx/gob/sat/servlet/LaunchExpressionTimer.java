package mx.gob.sat.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mx.gob.sat.ejb.ScheduleExpressionTimerLocal;
import mx.gob.sat.ejb.ScheduleExpressionTimerLocalHome;

/**
 * Servlet implementation class LaunchExpressionTimer
 */

public class LaunchExpressionTimer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LaunchExpressionTimer() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter wr = response.getWriter();
		
		try {
			wr.print(this.getClass().toString()+"Arrancando timer");
			
	    	this.call();
	    	wr.print(this.getClass().toString()+"Fin de arranque ");
			wr.flush();
			wr.close();
		} catch (Exception e) {
			wr.print(this.getClass().toString()+"Excepcion " + e.getMessage());
			e.printStackTrace(wr);
			wr.flush();
			wr.close();
		}
		
	}
	private void call() throws Exception {
		try {
			Context ctx = getInitialContext();
			ScheduleExpressionTimerLocal timerBean=null;
		 
			ScheduleExpressionTimerLocalHome tHome = (ScheduleExpressionTimerLocalHome) ctx.lookup("mx.gob.sat.ejb.ScheduleExpressionTimer");
			timerBean = tHome.create();
			timerBean.createScheduleExpressionTime();
			Thread.sleep(10000);
			System.out.println("DONE");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	private Context getInitialContext() throws Exception {
		
		return new InitialContext();
	}

}
