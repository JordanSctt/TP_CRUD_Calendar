package servlet;

import event.Event;
import event.EventRepository;
import generic.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;


@WebServlet("/event/update")
public class UpdateEventServlet extends HttpServlet {

	private static final String EVENT_ID = "event_id";
	private static final String TITRE = "titre_parameter";
    private static final String DATE = "date_parameter";
    private static final String DESCRIPTION = "description_parameter";
    
    private EventRepository repository = new EventRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strid = request.getParameter(EVENT_ID);
        int id = parseInt(strid);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
        	Event event = new Event();
        	 event.setTitre(request.getParameter(TITRE));
             event.setDate(simpleDateFormat.parse(request.getParameter(DATE)));
             event.setDescription(request.getParameter(DESCRIPTION));
            repository.updateEvent(event, id); 
            
            //request.getRequestDispatcher("/event").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/event");
        } catch (RepositoryException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	Event event;
		try {
			event = findOrNull(request);			
			if(event != null) {
	            request.setAttribute("event", event);
	            request.getRequestDispatcher("/updateEvent.jsp").forward(request, response);
	        } else {
	            redirectUpdatedError(request, response);
	        }
		} catch (RepositoryException e) {
			
			e.printStackTrace();
		}       
    }
    
    private Event findOrNull(HttpServletRequest request) throws RepositoryException {
        
            String eventIdStr = request.getParameter(EVENT_ID);
            if(eventIdStr != null && !eventIdStr.isEmpty()) {
                int eventId = Integer.parseInt(eventIdStr);
                return repository.findById(eventId);           
        } 
        return null;
    }

	private void redirectUpdatedError(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(request.getContextPath() + "/event?errorMessage=UPDATE_ERROR");
	}


}