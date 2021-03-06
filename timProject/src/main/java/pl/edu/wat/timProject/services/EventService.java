package pl.edu.wat.timProject.services;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.wat.timProject.dataModel.hibernate.Event;

@Component
public class EventService implements Serializable {

	private static final long serialVersionUID = 4244246336163575018L;

	@Autowired
	private SessionFactory sessionFactory;

	@Transactional
	public void register(Event event) {
		sessionFactory.getCurrentSession().save(event);
	}

	@Transactional
	public Event getByName(String name) {
		List list = getSessionFactory().getCurrentSession()
				.createQuery(" from Event where eventName = ?")
				.setParameter(0, name).list();

		return (Event) list.get(0);
	}

	@Transactional
	public void update(Event event) {
		getSessionFactory().getCurrentSession().update(event);
	}

	@Transactional
	public void delete(Event event) {
		event.getEventsTag().clear();
		event.getEventsClothesTypes().clear();
		sessionFactory.getCurrentSession().delete(event);
	}

	@Transactional
	public List<Event> listAll() {
		return getSessionFactory().getCurrentSession()
				.createQuery("from Event").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
