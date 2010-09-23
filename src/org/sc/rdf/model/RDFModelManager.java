package org.sc.rdf.model;

import java.util.*;
import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.sc.rdf.model.*;

import tdanford.autodata.AutoModel;

public class RDFModelManager {

	protected RDFObjectManager rdfManager;
	protected Map<String,RDFModel> modeled;
	
	public RDFModelManager(RDFObjectManager m) { 
		rdfManager = m;
		modeled = new HashMap<String,RDFModel>();
	}
	
	public <T> Collection<T> byType(Class<T> cls) { 
		LinkedList<T> lst = new LinkedList<T>();
		for(String key : modeled.keySet()) { 
			RDFModel m = modeled.get(key);
			if(AutoModel.isSubclass(m.getClass(), cls)) { 
				lst.addLast((T)m);
			}
		}
		return lst;
	}
	
	public RDFObjectManager getManager() { 
		return rdfManager;
	}
	
	public <T extends RDFModel> void addByCommand(String cmd, Class<T> cls) {
		int c = 0;
		for(Object obj : rdfManager.execute(cmd).objects()) {
			if(obj instanceof RDFObject) { 
				RDFObject robj = (RDFObject)obj;
				asModel(robj, cls);
				c += 1;
			}
		}
		System.out.println(String.format("\"%s\" => %d objects", cmd, c));
	}
	
	public RDFModel findModel(String key) { 
		return modeled.get(key);
	}
	
	public <T extends RDFModel> T findModel(String key, Class<T> cls) { 
		if(modeled.containsKey(key)) { 
			RDFModel m = modeled.get(key);
			if(AutoModel.isSubclass(m.getClass(), cls)) { 
				return (T)m;
			} else { 
				throw new IllegalArgumentException(String.format(
						"Model for KEY %s is not of type %s", 
						key, cls.getSimpleName()));
			}
		}
		return null;
	}
	
	public void insertModel(RDFModel m) { 
		if(modeled.containsKey(m._uri)) { 
			throw new IllegalArgumentException(String.format(
					"Model already contained for KEY %s", m._uri));
		} else { 
			modeled.put(m._uri, m);
		}
	}
	
	public <T extends RDFModel> T asModel(RDFObject obj, Class<T> modelClass) { 
		try {
			if(modeled.containsKey(obj.key())) { 
				RDFModel m = modeled.get(obj.key());
				
				if(AutoModel.isSubclass(m.getClass(), modelClass)) { 
					return (T)m;

				} else if(AutoModel.isSubclass(modelClass, m.getClass())) {
					Constructor<T> constructor = modelClass.getConstructor();
					T model = constructor.newInstance();
					model.setFromObject(rdfManager, obj, modeled);
					modeled.put(obj.key(), model);
					return model;

				} else { 
					throw new IllegalArgumentException(
							String.format("Can't remodel %s as %s", 
									m.getClass().getSimpleName(), modelClass.getSimpleName()));
				}
			} else { 
				Constructor<T> constructor = modelClass.getConstructor();
				T model = constructor.newInstance();
				model.setFromObject(rdfManager, obj, modeled);
				modeled.put(obj.key(), model);
				return model;
			}

		} catch (SecurityException e) {
			e.printStackTrace();
			return null;

		} catch (NoSuchMethodException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);

		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(modelClass.getName());

		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(modelClass.getName());

		} catch (InvocationTargetException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(modelClass.getName());
		} 
	}
}
