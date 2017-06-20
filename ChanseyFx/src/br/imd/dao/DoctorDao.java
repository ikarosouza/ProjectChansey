package br.imd.dao;

import java.util.List;

import javax.persistence.Query;

import br.imd.model.Doctor;
import br.imd.model.Sector;
import br.imd.model.Specialty;

public class DoctorDao extends GenericDao{
	
	public void save(Doctor doctor) {
		if (doctor.getId() == 0) {
			super.save(doctor);
		} else {
			super.update(doctor);
		}
	}
	
	public List<Doctor> list(){
		em = Connection.getInstance();
		String sql = "select d from Doctor d order by d.name";
		List<Doctor> list = em.createQuery(sql, Doctor.class).getResultList();
		return list;
		
	}
	
	public List<Doctor> search(String name, String phone, String crmNumber, Specialty specialty, Sector sector){
		em = Connection.getInstance();
		name = "%" + name + "%";
		phone = "%" + phone + "%";
		crmNumber = "%" + crmNumber + "%";
		String sql = "select d from Doctor d where lower(d.name) like :name "
				+ "and d.phone like :phone and d.crmNumber like :crmNumber";
		if(specialty != null){
			sql += " and :specialty member of d.specialties";
		}
		if(sector != null){
			sql += " and :sector member of d.sectors";
		}
		sql+= " order by d.name";
		Query query = em.createQuery(sql);
		query.setParameter("name", name.toLowerCase());
		query.setParameter("phone", phone);
		query.setParameter("crmNumber", crmNumber);
		if(specialty != null){
			query.setParameter("specialty", specialty);
		}
		if(sector != null){
			query.setParameter("sector", sector);
		}		
		List<Doctor> list = query.getResultList();
		return list;
	}
}
