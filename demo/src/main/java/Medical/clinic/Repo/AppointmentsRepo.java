package Medical.clinic.Repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import Medical.clinic.Model.Appointments;
@Repository
public interface AppointmentsRepo extends PagingAndSortingRepository <Appointments, Long> {
	//@Query(value=" select * from appointments  where  status != 'Cancelled' ",nativeQuery = true)
	@Query(value=" select * from appointments  where  DATE(date) = DATE(NOW())",nativeQuery = true)
	public List<Appointments> getAllForTheCurrentDay(Pageable pageable);
	public Slice<Appointments> findByAppointmentDate(Date date,Pageable pageable);
	public Slice<Appointments> findByPatientNameContaining(String name,Pageable pageable);


}
