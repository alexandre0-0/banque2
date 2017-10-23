package context;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * @author Lamine
 */

@WebListener
public class ContextListener implements ServletContextListener {

   @Override
   public void contextInitialized(ServletContextEvent event) {
      ServletContext context = event.getServletContext();
      context.setAttribute("nbUtilisateurs", 0);
      context.setAttribute("nbIdentifies", 0);
   }

   @Override
   public void contextDestroyed(ServletContextEvent sce) {
   }

}
