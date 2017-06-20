package br.imd.dao;

import java.util.List;

import javax.persistence.Query;

import br.imd.model.Sector;

public class SectorDao extends GenericDao{
	
	public void save(Sector sector) {
		if (sector.getId() == 0) {
			super.save(sector);
		} else {
			super.update(sector);
		}
	}
	
	public List<Sector> list(){
		em = Connection.getInstance();
		String sql = "select s from Sector s order by s.name";
		List<Sector> list = em.createQuery(sql, Sector.class).getResultList();
		return list;
	}
	
	public List<Sector> search(String name){
		em = Connection.getInstance();
		name = "%" + name + "%";
		String sql = "select s from Sector s where lower(s.name) like :name order by s.name";
		Query query = em.createQuery(sql);
		query.setParameter("name", name.toLowerCase());
		List<Sector> list = query.getResultList();
		return list;
	}
}
