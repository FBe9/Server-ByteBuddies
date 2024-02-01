package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 * Saves all the classes for server application usage.
 * 
 * @author Alex
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    /**
     * Gets all the classes of the server application.
     * 
     * @return A collection containing all classes.
     */
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.EnrolledFacadeREST.class);
        resources.add(service.ExamFacadeREST.class);
        resources.add(service.ExerciseFacadeREST.class);
        resources.add(service.MarkFacadeREST.class);
        resources.add(service.StudentFacadeREST.class);
        resources.add(service.SubjectFacadeREST.class);
        resources.add(service.TeacherFacadeREST.class);
        resources.add(service.UnitFacadeREST.class);
        resources.add(service.UserFacadeREST.class);
    }
    
}
