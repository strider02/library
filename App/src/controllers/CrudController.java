package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CrudController<Model> {

    Model find(long id) throws Exception;
    Model get(Model model, Map<String, String> params) throws Exception;

    List<Model> getAll();

    void save(Model model);

    void update(Model model, HashMap<String, String> params);

    void delete(Model model);
}
