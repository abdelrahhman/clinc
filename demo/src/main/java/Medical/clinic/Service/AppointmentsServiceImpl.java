package Medical.clinic.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Medical.clinic.Model.Appointments;
import Medical.clinic.Repo.AppointmentsRepo;
@Service

public class AppointmentsServiceImpl implements AppointmentsService {
@Autowired 
AppointmentsRepo appointmentsRepo;

@Override
public List<Appointments> getAllForTheCurrentDay(int pageNo,int pageSize,String sortBy) {
	// TODO Auto-generated method stub
	List<Appointments> appointmentsList=new ArrayList<>();
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	appointmentsList=appointmentsRepo.getAllForTheCurrentDay(paging);
	return appointmentsList;
}

@Override
public void addOne(Appointments appointment) {
	// TODO Auto-generated method stub
	appointmentsRepo.save(appointment);
}

@Override
public Optional<Appointments> cancelAppointment(Long id,String Reason) {
	// TODO Auto-generated method stub
	Optional <Appointments> appointmentOpt=appointmentsRepo.findById(id);
	if(appointmentOpt.isPresent())
	{
		appointmentOpt.get().setAppointmentStatus("Cancelled");
		appointmentOpt.get().setReason(Reason);
		appointmentsRepo.save(appointmentOpt.get());
	}
	else {
	}
	return appointmentOpt;

}

@Override
public List<Appointments> filterByDate(Date date,int pageNo,int pageSize,String sortBy) {
Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

Slice slicedList=appointmentsRepo.findByAppointmentDate(date,paging);
	return slicedList.getContent();
}

@Override
public List<Appointments> filterByName(String patientName,int pageNo,int pageSize,String sortBy) {
	// TODO Auto-generated method stub
    Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

    Slice slicedList=appointmentsRepo.findByPatientNameContaining(patientName,paging);
		return slicedList.getContent();
}


}
