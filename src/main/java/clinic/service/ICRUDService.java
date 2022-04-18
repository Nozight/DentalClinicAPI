package clinic.service;

import clinic.exceptions.ResourseNotFountException;

import java.util.Set;

public interface ICRUDService<T> {
    T findById(Integer id) throws ResourseNotFountException;
    T create(T t);
    void deleteById(Integer id) throws ResourseNotFountException;
    T update(T t) throws ResourseNotFountException;
    Set<T> findAll();
}
