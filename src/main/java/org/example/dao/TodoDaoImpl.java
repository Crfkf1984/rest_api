package org.example.dao;

import org.example.entity.Todo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TodoDaoImpl implements TodoDao {
    private SessionFactory sessionFactory;

    @Autowired
    public TodoDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Todo> getAllTodo() {
        Session session = sessionFactory.getCurrentSession();
        List<Todo> list = session.createQuery("from Todo ", Todo.class).getResultList();
        return list;
    }

    @Override
    public void save(Todo todo) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(todo);
    }

    @Override
    public Todo getTodo(int id) {
        Session session = sessionFactory.getCurrentSession();
        Todo todos = session.get(Todo.class, id);
        return todos;
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        Todo todos = session.get(Todo.class, id);
        session.delete(todos);
    }

    @Override
    public void removeAllTodos(List<Todo> list) {
        Session session = sessionFactory.getCurrentSession();
        for (Todo todos : list) {
            session.delete(todos);
        }

    }
}
