package servlet;

import event.EventRepository;
import generic.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.lang.Integer.parseInt;


@WebServlet("/event/delete")
public class DeleteEventServlet extends HttpServlet {

    private EventRepository repository = new EventRepository();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strid = request.getParameter("eventRemove");
        int id = parseInt(strid);

        try {
            repository.deleteEvent(id);            
            response.sendRedirect(request.getContextPath() + "/event");
            
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String strid = request.getParameter("eventRemove");
        int id = parseInt(strid);

        try {
            repository.deleteEvent(id);                       
            response.sendRedirect(request.getContextPath() + "/event");
            
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }


}
