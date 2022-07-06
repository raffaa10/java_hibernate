package fr.epsi.query;

import fr.epsi.HibernateUtils;
import fr.epsi.entities.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class QueryObjectDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtils.getSessionFactory();

        Session session = factory.getCurrentSession();

        try {

            // All the action with DB via Hibernate
            // must be located in one transaction.
            // Start Transaction.
            session.getTransaction().begin();

            // Create an HQL statement, query the object.
            // Equivalent to the SQL statement:
            // Select e.* from EMPLOYEE e order by e.EMP_NAME, e.EMP_NO
            String sql = "Select e from " + Employee.class.getName() + " e "
                    + " order by e.empName, e.empNo ";


            // Create Query object.
            Query<Employee> query = session.createQuery(sql);


            // Execute query.
            List<Employee> employees = query.getResultList();

            for (Employee emp : employees) {
                System.out.println("Emp: " + emp.getEmpNo() + " : "
                        + emp.getEmpName());
            }

            // Commit data.
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            // Rollback in case of an error occurred.
            session.getTransaction().rollback();
        }
    }


}
