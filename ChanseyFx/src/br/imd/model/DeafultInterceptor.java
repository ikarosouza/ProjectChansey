package br.imd.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import br.imd.dao.GenericDao;

public class DeafultInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	
	private String operation;
	
	private HashMap<Serializable, Object> created = new HashMap<>();
	private HashMap<Serializable, Object> updated = new HashMap<>();
	private HashMap<Serializable, Object> deleted = new HashMap<>();
	
	private GenericDao dao = new GenericDao();

	@Override
	public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(!(entity instanceof LogDatabase)){
			deleted.put(id, entity);
			operation = "delete";
		}
		super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		if(!(entity instanceof LogDatabase)){
			updated.put(id, entity);
			operation = "update";
		}
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	@Override
	public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		if(!(entity instanceof LogDatabase)){
			created.put(id, entity);
			operation = "save";
		}		
		return super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void postFlush(Iterator entities) {
		for(Map.Entry<Serializable, Object> entry : created.entrySet()){
			LogDatabase log = new LogDatabase();
			log.setEntity(entry.getValue().getClass().getSimpleName());
			log.setOperationType(operation);
			log.setUpdatedValue(entry.getKey().toString());
			log.setUpdatedAt(new Date());
			dao.saveLog(log);
		}
		for(Map.Entry<Serializable, Object> entry : updated.entrySet()){
			LogDatabase log = new LogDatabase();
			log.setEntity(entry.getValue().getClass().getSimpleName());
			log.setOperationType(operation);
			log.setUpdatedValue(entry.getKey().toString());
			log.setUpdatedAt(new Date());
			dao.saveLog(log);
		}
		for(Map.Entry<Serializable, Object> entry : deleted.entrySet()){
			LogDatabase log = new LogDatabase();
			log.setEntity(entry.getValue().getClass().getSimpleName());
			log.setOperationType(operation);
			log.setUpdatedValue(entry.getKey().toString());
			log.setUpdatedAt(new Date());
			dao.saveLog(log);
		}
		created.clear();
		updated.clear();
		deleted.clear();
		super.postFlush(entities);
	}
	
	
}
