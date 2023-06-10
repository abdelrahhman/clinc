package Medical.clinic.Controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Medical.clinic.Model.Appointments;
import Medical.clinic.Service.AppointmentsService;
import Medical.clinic.Service.AppointmentsServiceImpl;

@RestController
public class AppointmentsController {
    Logger logger = LoggerFactory.getLogger(AppointmentsController.class);	

	@Autowired
	private AppointmentsService appointmetsSerivce;
	
@GetMapping("/all")
public List<Appointments> getAllForTheCurrentDay(  @RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "id") String sortBy)
{
	logger.info("Start retrive operation for today's appointments");
	List<Appointments> appointmentsList=new ArrayList<Appointments>();

	try {
		appointmentsList=appointmetsSerivce.getAllForTheCurrentDay(pageNo,pageSize,sortBy);
		logger.info(" retrive operation for today's appointments Done");

	}catch (Exception e) {
		// TODO: handle exception
		logger.error("Error in retrive operation");
		logger.error(e.getMessage());
	}
	return appointmentsList;

//	

}
@PostMapping("/add")
public void add(@RequestBody Appointments appointments)
{
	logger.info("Start add operation for appointment",appointments);
	try {
		System.out.println(appointments);
		appointmetsSerivce.addOne(appointments);
		logger.info("Add operation for appointment is Done",appointments);
	}
	catch(Exception e)
	{
		logger.error("Error in add operation");
		logger.error(e.getMessage());

	}
	



}

@GetMapping("/cancel/{id}")
public void delete(@PathVariable("id") long id ,@RequestBody String Reason)
{
	System.out.println("aa");
	Optional <Appointments> appointmentOpt=appointmetsSerivce.cancelAppointment(id,Reason);
			if(appointmentOpt.isPresent())
			{
				logger.info("Status Changed to Cancelled Successfuly");
			}
			else {
				logger.info("This Appointment not found");

			}

//	

}
@GetMapping("/search-by-name")
public List<Appointments> searchByName(@RequestBody String name,@RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "patientName") String sortBy)
{
	logger.info("Start add operation for appointment with name",name);

List<Appointments>appointmentsByName=new ArrayList<Appointments>();
	try {
		appointmentsByName=appointmetsSerivce.filterByName(name,pageNo,pageSize,sortBy);
		
		if(!appointmentsByName.isEmpty())
		{
			logger.info("Found Result");
		}
		else {
			logger.info("No Result");

		}
		logger.info("search with name is working successfully",name);

	} catch (Exception e) {
		// TODO: handle exception
		logger.error("issue in search name");
		logger.error(e.getMessage());
	}

//	
return appointmentsByName;
}

@GetMapping("/search-by-date")
public List<Appointments> searchByDate(@RequestBody Date date,@RequestParam(defaultValue = "0") Integer pageNo,
        @RequestParam(defaultValue = "10") Integer pageSize,
        @RequestParam(defaultValue = "appointmentDate") String sortBy)
{
	logger.info("Start add operation for appointment with date",date.toString());

	List<Appointments>appointmentsByDate=new ArrayList<Appointments>();
	try {
		appointmentsByDate=appointmetsSerivce.filterByDate(date,pageNo,pageSize,sortBy);
		System.out.println("appointmentsByDate"+appointmentsByDate);
		if(!appointmentsByDate.isEmpty())
		{
			logger.info("Found Result in search by date");
		}
		else {
			logger.info("No Result in search by date");

		}
		logger.info("search with date is working successfully",date);

	} catch (Exception e) {
		// TODO: handle exception
		logger.error("issue in search by date");
		logger.error(e.getMessage());
	}
		

//	
return appointmentsByDate;
}
}
