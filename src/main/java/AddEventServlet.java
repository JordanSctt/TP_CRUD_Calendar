import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;


@WebServlet("/event/add")
public class AddEventServlet extends HttpServlet {

    private EventRepository repository = new EventRepository();

    private static final String TITRE = "titre_parameter";
    private static final String DATE = "date_parameter";
    private static final String DESCRIPTION = "description_parameter";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Event event = new Event();
            event.setTitre(request.getParameter(TITRE));
            event.setDate(simpleDateFormat.parse(request.getParameter(DATE)));
            event.setDescription(request.getParameter(DESCRIPTION));
            repository.create(event);

            //request.getRequestDispatcher("/event").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/event");
        } catch (RepositoryException | ParseException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Event event = new Event();
            event.setTitre(request.getParameter(TITRE));
            event.setDate(simpleDateFormat.parse(request.getParameter(DATE)));
            event.setDescription(request.getParameter(DESCRIPTION));
            repository.create(event);

            //request.getRequestDispatcher("/event").forward(request, response);
            response.sendRedirect(request.getContextPath() + "/event");
        } catch (RepositoryException | ParseException e) {
            e.printStackTrace();
        }
    }
}
