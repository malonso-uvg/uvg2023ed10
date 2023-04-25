

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;


import dataAccessLayer.EmbeddedNeo4j;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			PrintWriter out = response.getWriter();
		 	response.setContentType("application/json");
		 	response.setCharacterEncoding("UTF-8");
		 	JSONObject myResponse = new JSONObject();
		 	
		 	JSONArray nombresActores = new JSONArray();
		 	
		 	 //try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "Test1234" ) )
		 	try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://44.215.127.186:7687", "neo4j", "elapse-career-realignments" ) )
		        {
				 	LinkedList<String> myactors = greeter.getActors();
				 	
				 	for (int i = 0; i < myactors.size(); i++) {
				 		 //out.println( "<p>" + myactors.get(i) + "</p>" );
				 		nombresActores.add(myactors.get(i));
				 	}
		        	
		        } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 	
		 	myResponse.put("conteo", nombresActores.size()); //Guardo la cantidad de actores
		 	myResponse.put("actores", nombresActores);
		 	out.println(myResponse);
		 	out.flush();  
		 	
	        /*
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Hello World!</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>"+ request.getParameter("parametro") +"</h1>");
	        
	        try ( EmbeddedNeo4j greeter = new EmbeddedNeo4j( "bolt://localhost:7687", "neo4j", "Test1234" ) )
	        {
			 	LinkedList<String> myactors = greeter.getActors();
			 	
			 	for (int i = 0; i < myactors.size(); i++) {
			 		 out.println( "<p>" + myactors.get(i) + "</p>" );
			 	}
	        	
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        out.println("</body>");
	        out.println("</html>");
	        */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
