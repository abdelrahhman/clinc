package Medical.clinic.Model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Table(name="APPOINTMENTS")
@Entity
@Data
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
    @Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	@JsonFormat(shape =  JsonFormat.Shape.STRING,timezone = "UTC",pattern ="yyyy-MM-dd")
	private Date appointmentDate;
	@Column(name = "patientname")
	private String patientName;
	@Column(name = "STATUS")
	private String appointmentStatus;
@Column(name="Reason")
private String reason;
}
