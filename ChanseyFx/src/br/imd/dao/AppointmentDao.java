package br.imd.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.imd.model.Appointment;

public class AppointmentDao extends GenericDao{

	
	public void save(Appointment appointment) {
		if (appointment.getId() == 0) {
			super.save(appointment);
		} else {
			super.update(appointment);
		}
	}
	
	public List<Appointment> list(){
		em = Connection.getInstance();
		String sql = "select a from Appointment a order by a.patient.name";
		List<Appointment> list = em.createQuery(sql, Appointment.class).getResultList();
		return list;
		
	}
	
	public List<Appointment> search(String patient, String doctor, Date date){
		em = Connection.getInstance();
		patient = "%" + patient + "%";
		doctor = "%" + doctor + "%";
		String sql = "select a from Appointment a where lower(a.patient.name) like :patient and lower(a.doctor.name) like :doctor";
		if(date != null){
			sql +=  " and a.date = :date"; 
		}
		sql += " order by a.patient.name";
		Query query = em.createQuery(sql);
		query.setParameter("patient", patient.toLowerCase());
		query.setParameter("doctor", doctor.toLowerCase());
		if(date != null){
			query.setParameter("date", date);
		}		
		
		List<Appointment> list = query.getResultList();
		return list;
	}
}
