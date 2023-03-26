package org.kaefea;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


@Slf4j
public class RestServer {

    public void start() throws Exception {

        try {

            startJetty();
            System.out.println("Rest server started");
            //tomcat.getServer().await();
        }catch (Exception ex){
            System.out.println(ex);
            //throw new Exception(ex.getMessage());
        }
    }

    private void startJetty(){
        Server server = new Server(8080);

        ServletContextHandler ctx =
                new ServletContextHandler(ServletContextHandler.NO_SESSIONS);

        ctx.setContextPath("/");

        ServletHolder serHol = ctx.addServlet(ServletContainer.class, "/rest/*");
        serHol.setInitOrder(1);
        //serHol.setInitParameter("jakarta.ws.rs.core.Application", "org.kaefea.RestApp");
        serHol.setInitParameter("jersey.config.server.provider.packages", "org.kaefea");

        try {
            server.setHandler(ctx);
            server.getConnectors();
            server.start();
            //server.join();
        } catch (Exception ex) {
            log.error(ex.getMessage());
        } finally {
            //server.destroy();
        }
    }

    /*
    private void startTomcat(){
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        tomcat.setPort(8082);

        Context context = tomcat.addContext("/", new File(".").getAbsolutePath());
        //StandardContext ctx = (StandardContext) tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath());


            tomcat.addServlet(ctx, "Embedded", new HttpServlet() {
                @Override
                protected void service(HttpServletRequest req, HttpServletResponse resp)
                        throws ServletException, IOException {

                    Writer w = resp.getWriter();
                    w.write("Embedded Tomcat servlet.\n");
                    w.flush();
                    w.close();
                }
            });

            //context.addServletMappingDecoded("/*", "Embedded");
            context.addServletMapping("/*", "Embedded");

        tomcat.addServlet(context, "jersey-container-servlet", resourceConfig());
        context.addServletMappingDecoded("/*", "jersey-container-servlet");

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        context.setResources(resources);

        tomcat.start();
    }

    private ServletContainer resourceConfig() {
        return new ServletContainer(new ResourceConfig(new ResourceLoader().getClasses()));
    }*/
}
