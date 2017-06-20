package br.imd.dao;

import java.util.List;

import javax.persistence.Query;

import br.imd.model.Patient;

public class PatientDao  extends GenericDao{

	public void save(Patient patient) {
		if (patient.getId() == 0) {
			super.save(patient);
		} else {
			super.update(patient);
		}
	}
	
	public List<Patient> list(){
		em = Connection.getInstance();
		String sql = "select p from Patient p order by p.name";
		List<Patient> list = em.createQuery(sql, Patient.class).getResultList();
		return list;
	}
	
	public List<Patient> search(String name, String phone, String cpf, String rg){
		em = Connection.getInstance();
		name = "%" + name + "%";
		phone = "%" + phone + "%";
		cpf = "%" + cpf + "%";
		rg = "%" + rg + "%";
		String sql = "select p from Patient p where lower(p.name) like :name and p.phone like :phone and p.cpf like :cpf and p.rg like :rg order by p.name";
		Query query = em.createQuery(sql);
		query.setParameter("name", name.toLowerCase());
		query.setParameter("phone", phone);
		query.setParameter("cpf", cpf);
		query.setParameter("rg", rg);
		List<Patient> list = query.getResultList();
		return list;
	}
}
