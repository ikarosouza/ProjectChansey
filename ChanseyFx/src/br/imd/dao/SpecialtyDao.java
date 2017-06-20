package br.imd.dao;

import java.util.List;

import javax.persistence.Query;

import br.imd.model.Specialty;

public class SpecialtyDao extends GenericDao{
	
	public void save(Specialty specialty) {
		if (specialty.getId() == 0) {
			super.save(specialty);
		} else {
			super.update(specialty);
		}
	}
	
	public List<Specialty> list(){
		em = Connection.getInstance();
		String sql = "select s from Specialty s order by s.name";
		List<Specialty> list = em.createQuery(sql, Specialty.class).getResultList();
		return list;
	}
	
	public List<Specialty> search(String name){
		em = Connection.getInstance();
		name = "%" + name + "%";
		String sql = "select s from Specialty s where lower(s.name) like :name order by s.name";
		Query query = em.createQuery(sql);
		query.setParameter("name", name.toLowerCase());
		List<Specialty> list = query.getResultList();
		return list;		
	}
}
