package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CrudController<Model> {

    Model get(Model model, Map<String, String> params) throws Exception;

    List<Model> all() throws Exception;

    void save(Model model);

    void update(Model model, HashMap<String, String> params);

    void delete(Model model);
}
