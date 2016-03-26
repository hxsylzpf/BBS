package com.bbs.daoImpl;

import com.bbs.dao.MainForumDao;
import com.bbs.hibernate.factory.BaseHibernateDAO;
import com.bbs.model.MainForum;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * MainForum entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.bbs.model.MainForum
 * @author MyEclipse Persistence Tools
 */
public class MainForumDaoImpl extends BaseHibernateDAO implements MainForumDao {
	private static final Logger log = LoggerFactory
			.getLogger(MainForumDaoImpl.class);
	// property constants
	public static final String TITLE = "title";
	public static final String INFO = "info";

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#save(com.bbs.model.MainForum)
	 */
	@Override
	public void save(MainForum transientInstance) {
		log.debug("saving MainForum instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#delete(com.bbs.model.MainForum)
	 */
	@Override
	public void delete(MainForum persistentInstance) {
		log.debug("deleting MainForum instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findById(java.lang.Integer)
	 */
	@Override
	public MainForum findById(java.lang.Integer id) {
		log.debug("getting MainForum instance with id: " + id);
		try {
			MainForum instance = (MainForum) getSession().get(
					"com.bbs.model.MainForum", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByExample(com.bbs.model.MainForum)
	 */
	@Override
	public List findByExample(MainForum instance) {
		log.debug("finding MainForum instance by example");
		try {
			List results = getSession()
					.createCriteria("com.bbs.model.MainForum")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByProperty(java.lang.String, java.lang.Object)
	 */
	@Override
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding MainForum instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from MainForum as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByTitle(java.lang.Object)
	 */
	@Override
	public List findByTitle(Object title) {
		return findByProperty(TITLE, title);
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findByInfo(java.lang.Object)
	 */
	@Override
	public List findByInfo(Object info) {
		return findByProperty(INFO, info);
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#findAll()
	 */
	@Override
	public List findAll() {
		log.debug("finding all MainForum instances");
		try {
			String queryString = "from MainForum";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#merge(com.bbs.model.MainForum)
	 */
	@Override
	public MainForum merge(MainForum detachedInstance) {
		log.debug("merging MainForum instance");
		try {
			MainForum result = (MainForum) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#attachDirty(com.bbs.model.MainForum)
	 */
	@Override
	public void attachDirty(MainForum instance) {
		log.debug("attaching dirty MainForum instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/* (non-Javadoc)
	 * @see com.bbs.daoImpl.MainForumDao#attachClean(com.bbs.model.MainForum)
	 */
	@Override
	public void attachClean(MainForum instance) {
		log.debug("attaching clean MainForum instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}