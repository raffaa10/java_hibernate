package fr.epsi.persist;

import fr.epsi.DataUtils;
import fr.epsi.HibernateUtils;
import fr.epsi.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class EvictDemo {

    public static void main(String[] args) {
        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();
        Employee emp = null;
        try {
            session.getTransaction().begin();

            // This is object has Persistent status
            emp = DataUtils.findEmployee(session, "E7499");

            // ==> true
            System.out.println("- emp Persistent? " + session.contains(emp));


            // using evict() to evicts a single object from the session
            session.evict(emp);

            // Now 'emp' has Detached status
            // ==> false
            System.out.println("- emp Persistent? " + session.contains(emp));


            // All change on the 'emp' will not update
            // if not reatach 'emp' to session
            emp.setEmpNo("NEW");

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

}

