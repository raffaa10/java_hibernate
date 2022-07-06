package fr.epsi.entities;

import javax.persistence.*;
import java.util.Date;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "TIMEKEEPER")
public class Timekeeper {
    public static final char IN = 'I';
    public static final char OUT = 'O';

    private String timekeeperId;

    private Date dateTime;

    private Employee employee;

    // 'I' or 'O'
    private char inOut;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "Timekeeper_Id", length = 36)
    public String getTimekeeperId() {
        return timekeeperId;
    }

    public void setTimekeeperId(String timekeeperId) {
        this.timekeeperId = timekeeperId;
    }

    @Column(name = "Date_Time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_ID", nullable = false)
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Column(name = "In_Out", nullable = false, length = 1)
    public char getInOut() {
        return inOut;
    }

    public void setInOut(char inOut) {
        this.inOut = inOut;
    }

}