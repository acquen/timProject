package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Clothes;

@Component
public class ClothesService implements Serializable {
	private static final long serialVersionUID = 7362486861803929816L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void register(Clothes colour) {
		sessionFactory.getCurrentSession().save(colour);
	}

	@Transactional
	public List<Clothes> listAll() {
		return getSessionFactory().getCurrentSession()
				.createQuery("from Clothes").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
