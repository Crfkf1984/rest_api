package org.example.service;

import org.example.dao.TodoDao;
import org.example.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    private TodoDao todoDao;

    @Autowired
    public ServiceImpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    @Transactional
    public List<Todo> getAllTodo() {
        return todoDao.getAllTodo();
    }

    @Override
    @Transactional
    public void save(Todo todo) {
        todoDao.save(todo);
    }

    @Override
    @Transactional
    public Todo getTodo(int id) {
        return todoDao.getTodo(id);
    }

    @Override
    @Transactional
    public void remove(int id) {
        todoDao.remove(id);

    }

    @Override
    @Transactional
    public void removeAllTodos(List<Todo> todos) {
        todoDao.removeAllTodos(todos);
    }
}
