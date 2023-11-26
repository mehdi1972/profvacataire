package com.JAVA.DAO;

import java.util.List;



public interface ModuleDAO {
	Module getById(int id_Module);
    List<Module> getAll();
    void insert(Module Module);
    void update(Module Module);
    void delete(int id_module);
}