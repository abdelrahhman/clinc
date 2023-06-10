package Medical.clinic.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Medical.clinic.Model.Appointments;


public interface AppointmentsService {
public List<Appointments> getAllForTheCurrentDay(int pageNo,int pageSize,String sortBy);
public void addOne(Appointments appointment);
public Optional<Appointments> cancelAppointment(Long id,String Reason);
public List<Appointments> filterByDate(Date date,int pageNo,int pageSize,String sortBy);
public List<Appointments> filterByName(String patientName,int pageNo,int pageSize,String sortBy);

}
