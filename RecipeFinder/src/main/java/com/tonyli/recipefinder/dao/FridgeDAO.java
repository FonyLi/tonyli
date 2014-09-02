package com.tonyli.recipefinder.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.tonyli.recipefinder.dao.ds.Fridge;
import com.tonyli.recipefinder.dao.ds.Material;
import com.tonyli.recipefinder.dao.ds.Unit;



/**
 * put items into DB
 * @author Tony
 *
 */
public class FridgeDAO extends AbstractDAO{
	
	private static Logger logger = Logger.getLogger(FridgeDAO.class.getName());
	
	public FridgeDAO(Session session) {
		super(session);
	}
	
	public List<Material> getItemWithSameNameAndUnit(String itemName, Unit unit)
	{
		List<Material> items = null;
		
		try
		{
			Query sameMaterial = session.createQuery("from Material as material where material.item=? and material.unit=?")
					.setString(0, itemName).setParameter(1, unit);
	
			List results = sameMaterial.list();
			if (!results.isEmpty() && results.size() > 0) {
				items = new ArrayList<Material>(results.size());
				for (Object object : results)
				{
					items.add((Material)object);
				}	
			}
		}
		catch(Exception e)
		{
			logger.error("", e);
		}
		
		return items;
	}
	
	public String addFridge(Fridge fridge)
	{
		Transaction transaction = session.beginTransaction();
		
		String uuid = null;
		try
		{
			uuid = generateUUID();
			fridge.setUuid(uuid);
			session.save(fridge);
			transaction.commit();
		}
		catch(Exception e)
		{
			logger.error("failed to save item", e);
			transaction.rollback();
			uuid = null;
		}
		
		return uuid;
	}
	
	/**
	 * add one item into DB.
	 * if the unit or useby is not as before, add a new items
	 * @param item
	 */
	public String addItem(Material item)
	{
		if (item == null)
			return null;

		String itemName = item.getItem();
		
		//if the item initialised unsuccessfully
		if (itemName == null)
		{
			//do not need to record into log, as we've recorded it in the constructor of Material
			return null ;
		}
		
		//An ingredient that is past its use-by date cannot be used for cooking
		if (item.getUseBy().getTime() < System.currentTimeMillis())
		{
			logger.info(itemName + " is past its useby date, do not put it into fridge");
			return null ;
		}
		
		Query sameMaterial = session.createQuery("from Material as material where material.item=? and material.useBy=?").setString(0, itemName).setDate(1, item.getUseBy());		

		Transaction transaction = session.beginTransaction();
		
		String uuid = null;
			
		try
		{
			List results = sameMaterial.list();
			if (!results.isEmpty() && results.size() > 0) {
				Material material = (Material)(results.get(0));
				material.setAmount(material.getAmount() + item.getAmount());
				session.update(material);
			}
			else
			{
				uuid = generateUUID();
				item.setUuid(uuid);
				session.save(item);
			}
			transaction.commit();
		}
		catch(Exception e)
		{
			logger.error("failed to save item", e);
			transaction.rollback();
			uuid = null;
		}
		
		return uuid;
	}
	
	public void deleteItems()
	{
		Query query = session.createQuery("delete from Material");
		query.executeUpdate();
		session.createQuery("delete from Fridge");
		query.executeUpdate();
		session.createQuery("delete from CommonObject");
		query.executeUpdate();
	}
}
