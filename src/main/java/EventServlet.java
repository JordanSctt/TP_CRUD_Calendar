import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/event")
public class EventServlet extends HttpServlet {

    private EventRepository repository = new EventRepository();
    private Event event = new Event();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Event> events = repository.findAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        } catch (ServletException | RepositoryException e) {
            e.printStackTrace();
        }
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            List<Event> events = repository.findAllEvents();
            request.setAttribute("events", events);
            request.getRequestDispatcher("accueil.jsp").forward(request, response);
        } catch (ServletException | RepositoryException e) {
            e.printStackTrace();
        }
    } 
}
